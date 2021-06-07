package top.littlefogcat.clickerx.core;

import android.app.Activity;
import android.util.Log;

import org.luaj.vm2.LuaClosure;
import org.luaj.vm2.LuaTable;

/**
 * Lua调用Java的接口，是[LuaFunctionDispatcher]的实现类。
 * 这个类定义了所有Lua可以调用的Java方法。
 * Lua文件中所有调用的Java方法，统一经过这个类进行。
 * <p>
 * 当Lua脚本调用Java方法时，会经由此类转化为Java本地方法，再委托给Core真正实现功能。
 * <p>
 * 在Lua脚本中使用：
 * <pre>
 * _clazz_ = luajava.bindClass('top.littlefogcat.clickerx.core.LuaInterface'); -- 获取类对象
 * _inst_ = _clazz_:getInstance(); -- 通过getInstance()方法获取到单例实例
 * </pre>
 * <p>
 * 然后就可以通过_inst_实例调用类中的方法了，这里的_inst_对应的即是本类的单例。
 * <p>
 * 以上的初始化已经在`init.lua`文件中完成；这个文件在{@link Core#init(Activity)}方法中被加载。
 * <p>
 * <p>
 * todo 可在Lua中统一通过用字符串的方式调用Java方法。这样做的目的是分离Java层和Lua层，可以分别做修改互不影响。
 *
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
@SuppressWarnings("unused")
public class Connector implements LuaJavaInterface {
    private static final String TAG = "Connector";
    public static final String METHOD_DELAY = "delay";
    public static final String METHOD_LAUNCH_APP = "launchApp";
    private static final LuaJavaInterface mRealDispatcher = Core.INSTANCE;

    private static class Inner {
        private static final Connector INSTANCE = new Connector();
    }

    public static Connector getInstance() {
        return Inner.INSTANCE;
    }

    @Override
    public int launchApp(String pkgName) {
        return mRealDispatcher.launchApp(pkgName);
    }

    @Override
    public void delay(int millis) {
        mRealDispatcher.delay(millis);
    }

    @Override
    public LuaTable findView(LuaTable args) {
        return mRealDispatcher.findView(args);
    }

    @Override
    public void clickAt(int x, int y) {
        mRealDispatcher.clickAt(x, y);
    }

    @Override
    public void clickById(String id) {
        mRealDispatcher.clickById(id);
    }

    @Override
    public void clickByText(String text) {
        mRealDispatcher.clickByText(text);
    }

    @Override
    public void clickAtPercent(double x, double y) {
        mRealDispatcher.clickAtPercent(x, y);
    }

    @Override
    public int screenWidth() {
        return mRealDispatcher.screenWidth();
    }

    @Override
    public int screenHeight() {
        return mRealDispatcher.screenHeight();
    }

    @Override
    public void swipe(float x1, float y1, float x2, float y2, long duration) {
        mRealDispatcher.swipe(x1, y1, x2, y2, duration);
    }

    public void setDefaultDelay(long millis) {
        mRealDispatcher.setDefaultDelay(millis);
    }

    @Override
    public void input(String text) {
        mRealDispatcher.input(text);
    }

    @Override
    public void inputKey(int keyCode) {
        mRealDispatcher.inputKey(keyCode);
    }

    @Override
    public void pressKey(int keyCode) {
        mRealDispatcher.pressKey(keyCode);
    }

    @Override
    public void setTrigger(String name, int triggerType, LuaTable args, LuaClosure action) {
        mRealDispatcher.setTrigger(name, triggerType, args, action);
    }

    @Override
    public void clickBy(LuaTable args) {
        mRealDispatcher.clickBy(args);
    }

    public void test() {
        Log.d(TAG, "test: ");
    }
}
