package top.littlefogcat.clickerx.base.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.databinding.ViewDataBinding
import kotlinx.coroutines.*
import top.littlefogcat.common.R

/**
 * Fragment基类，继承自[DataBindingFragment]。
 *
 * 需要复写[layoutId]，表示当前Fragment对应的layoutId；
 * [onDataBinding]，在这个函数中创建ViewModel。
 *
 * [coroutine]对象作为协程的Scope。
 *
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
abstract class BaseFragment<T : ViewDataBinding> : DataBindingFragment<T>(), TouchEventHandler {
    protected val TAG by lazy {
        this.javaClass.simpleName
    }

    protected val coroutine = MainScope()

    protected val inflater: LayoutInflater get() = LayoutInflater.from(context)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (this is DefaultToolbarHolder) {
            val tvTitle = binding.root.findViewById<TextView>(R.id.toolbar_tvTitle)
            val btnBack = binding.root.findViewById<View>(R.id.toolbar_btnBack)
            val btnDone = binding.root.findViewById<View>(R.id.toolbar_btnDone)

            if (tvTitle != null) {
                tvTitle.text = getTitle()
            }

            if (btnBack != null) {
                val onBackClick = onBtnBackClicked()
                if (onBackClick == null) {
                    btnBack.visibility = View.GONE
                } else {
                    btnBack.visibility = View.VISIBLE
                    btnBack.setOnClickListener(onBackClick)
                }
            }

            if (btnDone != null) {
                val onDoneClick = onDoneClicked()
                if (onDoneClick == null) {
                    btnDone.visibility = View.GONE
                } else {
                    btnDone.visibility = View.VISIBLE
                    btnDone.setOnClickListener(onDoneClick)
                }
            }
        }
    }

    /**
     * 在IO线程执行[block]。
     */
    protected fun runOnIO(block: suspend CoroutineScope.() -> Unit): Job {
        return coroutine.launch(Dispatchers.IO, block = block)
    }

    protected fun runOnDefault(block: suspend CoroutineScope.() -> Unit): Job {
        return coroutine.launch(Dispatchers.Default, block = block)
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

    override fun onTouchEvent(event: MotionEvent) = false

    override fun onDestroy() {
        super.onDestroy()
        coroutine.cancel()
    }
}