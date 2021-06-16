package top.littlefogcat.clickerx.base.base

import android.view.View

/**
 * 标记这个类包含了默认的toolbar。
 * 在继承自[BaseFragment]的类中，如果该Fragment实现了[DefaultToolbarHolder]，那么就会进行标题、返回、确定的设置。
 * 这个toolbar对应的是[res/layout/toolbar.xml]，需要在布局中引入。如果不引入布局，则需要在布局文件中设置对应id为
 * [tvTitle]、[btnBack]、[btnDone]的控件，分别对应标题、返回按钮、确认按钮。
 *
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
interface DefaultToolbarHolder {
    fun getTitle(): String
    fun onBtnBackClicked(): ((View) -> Unit)?
    fun onDoneClicked(): ((View) -> Unit)?
}