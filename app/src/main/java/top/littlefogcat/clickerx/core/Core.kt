package top.littlefogcat.clickerx.core

import android.app.Activity
import android.app.Instrumentation
import android.os.*
import android.util.DisplayMetrics
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.luaj.vm2.Globals
import org.luaj.vm2.LuaClosure
import org.luaj.vm2.LuaTable
import org.luaj.vm2.lib.ResourceFinder
import org.luaj.vm2.lib.jse.JsePlatform
import top.littlefogcat.clickerx.Injector
import top.littlefogcat.clickerx.core.Core.CoreHandlerCompat.innerH
import top.littlefogcat.clickerx.core.Core.init
import top.littlefogcat.clickerx.core.LuaException.Companion.NULL_SCRIPT
import top.littlefogcat.clickerx.core.LuaException.Companion.NULL_SERVICE
import top.littlefogcat.clickerx.core.accessibility.ClickerXA11yService
import top.littlefogcat.clickerx.core.trigger.NotificationTrigger
import top.littlefogcat.clickerx.core.trigger.Trigger
import top.littlefogcat.clickerx.utils.LuaUtils
import top.littlefogcat.clickerx.utils.equalsOrNull

/**
 * Lua脚本通过[Connector]类实现Lua到Java的转换，再通过此类将具体的任务分配给相应的执行者。
 *
 * 该类的主要作用是根据操作的种类实现自动化操作。具体的任务将分配给[ClickerXA11yService]或
 * [ShellExecutor]来实现。
 *
 * 必须最先调用[init]方法进行初始化才可以使用。
 *
 * 任务的执行过程：
 *
 * [Core.runScript] -> [CoreHandler.post] -> [Runnable.run]
 *
 * @Author：littlefogcat
 * @Date：2021/4/27-10:39
 * @Email：littlefogcat@foxmail.com
 */
object Core : LuaJavaInterface {
    const val TAG = "Core"
    private var service: ClickerXA11yService? = null
    private val shellExecutor = ShellExecutor()
    private val coroutine = CoroutineScope(Dispatchers.IO) // 在IO线程读文件
    private val globals: Globals = JsePlatform.standardGlobals()
    private val screenSize: DisplayMetrics = DisplayMetrics()
    private val handler = CoreHandler
    private val configRepository = Injector.provideConfigsDataSource()

    /**
     * Mutex in coroutines, like `synchronized` in thread.
     *
     * Usage:
     * ```
     * LOCK.withLock {
     *     // do something coroutine-safe
     * }
     * ```
     */
    private val LOCK = Mutex()

    /**
     * 当前正在执行的脚本，如果没有则为null。
     *
     * 运行[runScript]时会设值，并在运行完毕之后重新赋值为null。
     */
    internal var runningScript: Script? = null

    /**
     * 初始化，主要执行初始化脚本init.lua
     */
    fun init(context: Activity) {
        // 获取屏幕尺寸
        val display = context.windowManager.defaultDisplay
        display.getMetrics(screenSize)

        coroutine.launch {
            globals.finder = ResourceFinder { filename ->
                context.applicationContext.assets.open(filename)
            }
            LOCK.withLock {
                try {
                    // 加载初始化脚本
                    globals.loadfile("init.lua").call()
                    // run sample
                    globals.loadfile("sample.lua").call()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            // 加载运行本地脚本
            val localConfigs = configRepository.loadLocalConfigs()
            localConfigs.forEach {
                // 执行触发器脚本，初始化触发器
                if (it.trigger && it.state == Script.STATE_SCHEDULED) {
                    runScript(it)
                }
            }
        }
    }

    /**
     * 执行脚本时，[runningScript]将被设置为当前运行脚本的。
     */
    fun runScript(script: Script) {
        Log.v(TAG, "runScript: $script")
        coroutine.launch {
            LOCK.withLock {
                runningScript = script
                try {
                    globals.load(script.code).call()
                } catch (e: Exception) {
                    e.printStackTrace()
                    CoreHandler.removeCallbacks(null)
                } finally {
                    runningScript = null
                }
            }
        }
    }

    fun unScheduleScript(script: Script) {
        service?.removeTrigger(script.id)
        configRepository.updateConfig(script.id, script)
    }

    fun setService(service: ClickerXA11yService?) {
        this.service = service
    }

    /**
     * 启动包名为[pkgName]的应用。`
     *
     * 返回值：
     * -1 - 启动成功
     * 0 - 没有找到对应包名的应用
     * 1 - 引用的Context为null。可能是由于应用已经被清理掉了。
     * 2 - 尚未初始化。必须在[init]函数调用之后再调用此函数。
     */
    override fun launchApp(pkgName: String): Int {
        handler.post(runningScript?.id ?: throw LuaException(NULL_SCRIPT, "launchApp")) {

            Log.d(TAG, "launchApp: $pkgName")
            service?.packageManager?.let { pm ->
                val intent = pm.getLaunchIntentForPackage(pkgName)
                if (intent == null) {
                    Log.i(TAG, "launchApp: package name not found: $pkgName")
                    throw LuaException(LuaException.PACKAGE_NOT_FOUND, pkgName)
                } else {
                    service?.startActivity(intent)
                }
            } ?: throw LuaException(NULL_SERVICE, "launchApp")
        }
        return -1
    }

    override fun delay(millis: Int) {
        handler.delay(millis.toLong())
    }

    override fun findView(args: LuaTable): LuaTable? {
        // TODO: Delayed exec?
        val table = LuaTable()
        val text = args["text"].run { if (isnil()) null else tojstring() }
        val id = args["id"].run { if (isnil()) null else tojstring() }
        var index = 1
        service?.run {
            traverse(rootInActiveWindow) {
                if (it.text.equalsOrNull(text) && it.viewIdResourceName.equalsOrNull(id)) {
                    table.set(index, it.viewIdResourceName)
                    index++
                }
                false
            }
        }
        return if (index == 1) null else table
    }

    override fun clickById(id: String) {
        service?.let {
            handler.post { (it.clickById(id)) }
        } ?: throw LuaException(NULL_SERVICE, "clickById")
    }

    override fun clickByText(text: String) {
        service?.let {
            handler.post { it.clickByText(text) }
        } ?: throw LuaException(NULL_SERVICE, "clickByText")
    }

    override fun clickAt(x: Int, y: Int) {
        handler.post {
            Log.d(TAG, "clickAt: $x, $y")
            val svc = service ?: throw LuaException(NULL_SERVICE, "clickAt")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                svc.clickAt(x.toFloat(), y.toFloat())
            } else {
                shellExecutor.execute("input tab $x $y") {
                    throw LuaException(
                        LuaException.API_VERSION_TOO_LOW, "clickAt",
                        "如果要在API 23及以下系统版本使用clickAt函数，需要加载坐标点击补丁！"
                    )
                }
            }
        }
    }

    override fun clickAtPercent(xPercent: Double, yPercent: Double) {
        val x = (xPercent * screenWidth()).toInt()
        val y = (yPercent * screenHeight()).toInt()
        clickAt(x, y)
    }

    override fun screenWidth(): Int {
        return screenSize.widthPixels
    }

    override fun screenHeight(): Int {
        return screenSize.heightPixels
    }

    override fun swipe(x1: Float, y1: Float, x2: Float, y2: Float, duration: Long) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            handler.post {
                service?.swipe(x1, y1, x2, y2, duration)
            }
        } else {
            val cmd = "input swipe $x1 $y1 $x2 $y2"
            shellExecutor.execute(cmd)
        }
    }

    override fun setDefaultDelay(millis: Long) {
        handler.defaultDelay = millis
    }

    override fun input(text: String) {
        handler.post {
            service?.input(text)
        }
    }

    override fun inputKey(keyCode: Int) {
        handler.post {
            val inst = Instrumentation()
            inst.sendKeyDownUpSync(keyCode)

        }
    }

    override fun pressKey(keyCode: Int) {
        handler.post {
            service?.performGlobalAction(keyCode)
        }
    }

    override fun setTrigger(name: String, triggerType: Int, args: LuaTable, action: LuaClosure) {
        val service = this.service ?: throw LuaException(NULL_SERVICE, "setTrigger")
        val script = runningScript ?: throw LuaException(NULL_SCRIPT, "setTrigger")

        when (triggerType) {
            Trigger.TYPE_NOTIFICATION -> {
                val params = LuaUtils.luaTableToMap(args)
                val trigger = NotificationTrigger(script.id, name, params, action)
                handler.post {
                    service.addNotificationTrigger(trigger)
                }
                script.state = Script.STATE_SCHEDULED
                configRepository.updateConfig(script.id, script)
            }
        }
    }

    override fun clickBy(args: LuaTable) {
        val argMap = LuaUtils.luaTableToMap(args)
        handler.post {
            Log.d(TAG, "clickBy: $argMap")
            service?.clickBy(argMap)
        }
    }

    /**
     * Lua脚本中的每一个操作，对应的Runnable对象都会传递到这个类里，并通过[innerH]在设置的延时之后执行。
     *
     * 不立即执行主要是为了处理延时任务，即Lua脚本中的`delay`函数。
     *
     * todo 考虑使用Transaction，并标记调用者
     *
     * @Author：littlefogcat
     * @Email：littlefogcat@foxmail.com
     */
    @Suppress("all", "unused", "MemberVisibilityCanBePrivate")
    object CoreHandlerCompat {
        const val MSG_LAUNCH_APP = 0
        const val MSG_CLICK_AT = 1

        private val innerH = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
            }
        }
        var defaultDelay = -1L

        /**
         * 超时时间。当调用[delay]方法，则设置超时时间；所有的[Runnable]都在之后进行，类似于[Thread.sleep]。
         * 实现Lua中的delay()函数。
         */
        private var expire = 0L

        private fun checkServiceAndScript() {
        }

        /**
         * 根据[expire]决定[r]立即执行或是延迟执行。
         *
         * [id]为脚本的id，这个是为了在运行出错之后移除所有预设的任务。
         */
        fun post(id: Long, r: () -> Unit) {
            val ct = SystemClock.uptimeMillis()
            if (ct > expire) {
                innerH.postAtTime(r, id, ct + 1)
            } else {
                innerH.postAtTime(r, id, expire)
            }
            if (defaultDelay > 0) {
                delay(defaultDelay)
            }
        }

        fun delay(millis: Long) {
            if (expire < SystemClock.uptimeMillis()) {
                expire = SystemClock.uptimeMillis() + millis
            } else {
                expire += millis
            }
        }

        fun removeCallbacks(who: Long) {
            innerH.removeCallbacksAndMessages(who)
        }
    }
}