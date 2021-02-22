package top.littlefogcat.clickerx.base

import android.content.Context
import android.view.MotionEvent
import androidx.databinding.ViewDataBinding
import kotlinx.coroutines.*

/**
 * Fragment基类，继承自[DataBindingFragment]。
 *
 * 需要复写[layoutId]，表示当前Fragment对应的layoutId；
 * [onCreateViewModel]，在这个函数中创建ViewModel。
 *
 * [coroutine]对象作为协程的Scope。
 *
 * @Author：littlefogcat
 * @Date：2021/1/30-8:37
 * @Email：littlefogcat@foxmail.com
 */
abstract class BaseFragment<T : ViewDataBinding> : DataBindingFragment<T>(), TouchEventHandler {
    protected val TAG by lazy {
        this.javaClass.simpleName
    }

    protected val coroutine = MainScope()

    /**
     * 在IO线程执行[block]。
     */
    protected fun runOnIO(block: suspend CoroutineScope.() -> Unit): Job {
        return coroutine.launch(Dispatchers.IO, block = block)
    }

    /**
     * 为当前Fragment添加触摸事件处理
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity) {
            context.addTouchEventHandler(this)
        }
    }

    override fun dispatchTouchEvent(event: MotionEvent) = false

    override fun onDestroy() {
        super.onDestroy()
        coroutine.cancel()
    }
}