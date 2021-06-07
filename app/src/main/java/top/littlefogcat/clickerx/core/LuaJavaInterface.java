package top.littlefogcat.clickerx.core;

import org.luaj.vm2.LuaClosure;
import org.luaj.vm2.LuaTable;

import top.littlefogcat.clickerx.core.trigger.Trigger;

/**
 * Lua-Java函数接口。
 * 规定了所有Lua可以调用的Java函数。
 *
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 * @see Connector
 */
public interface LuaJavaInterface {

    /**
     * 启动包名为[packageName]的应用
     */
    int launchApp(String packageName);

    /**
     * 在此处暂停[millis]毫秒
     */
    void delay(int millis);

    /**
     * 查找所有满足条件的控件，并返回其id数组。
     */
    LuaTable findView(LuaTable args);

    /**
     * 点击id为[id]的按钮
     */
    void clickById(String id);

    /**
     * 点击文字为[text]的按钮
     */
    void clickByText(String text);

    /**
     * 点击坐标[x, y]
     */
    void clickAt(int x, int y);

    /**
     * 点击横纵坐标处于百分之[xPercent]、[yPercent]的点
     */
    void clickAtPercent(double xPercent, double yPercent);

    /**
     * 获取屏幕宽度
     */
    int screenWidth();

    /**
     * 获取屏幕高度
     */
    int screenHeight();

    /**
     * 模拟手指滑动操作
     *
     * @param x1       - 按下位置x坐标
     * @param y1       - 按下位置y坐标
     * @param x2       - 手指离开屏幕位置的x坐标
     * @param y2       - 手指离开屏幕位置的y坐标
     * @param duration - 滑动总时长
     */
    void swipe(float x1, float y1, float x2, float y2, long duration);

    /**
     * -- 设置默认每步之间的间隔时间，相当于在每2步操作之间插入了一个delay()函数。
     * -- 这样脚本中就不需要频繁的调用delay()函数了。
     * -- 但是如果两步操作之间调用了delay()函数，则以实际调用的为准。
     */
    void setDefaultDelay(long millis);

    /**
     * 输入文字
     */
    void input(String text);

    /**
     * 键盘输入
     *
     * @param keyCode 键值
     */
    void inputKey(int keyCode);

    /**
     * 模拟按键，键值为 keyCode
     */
    void pressKey(int keyCode);

    /**
     * 设置触发条件。
     * <p>
     * 对于一个触发脚本，必须调用这个方法，否则不能被触发。
     *
     * @param name        触发器名字，自定义设置，用于人为区分不同的触发器。实际触发器与脚本绑定，公用id，由系统自行设置。
     * @param triggerType 触发器类型 @{@link Trigger#TYPE_NOTIFICATION}
     * @param action      一个Lua函数回调，当触发器触发时调用
     * @param args        触发器参数，是一个Lua table类型，每种触发器参数不同。
     */
    void setTrigger(String name, int triggerType, LuaTable args, LuaClosure action);

    void clickBy(LuaTable args);

}
