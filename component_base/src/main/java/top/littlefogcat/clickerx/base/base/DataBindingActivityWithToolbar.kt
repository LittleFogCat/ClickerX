package top.littlefogcat.clickerx.base.base

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.databinding.ViewDataBinding
import top.littlefogcat.common.R

/**
 *
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
abstract class DataBindingActivityWithToolbar<T : ViewDataBinding> : DataBindingActivity<T>() {
    abstract val title: String?
    abstract val showBackButton: Boolean?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tvTitle = findViewById<TextView>(R.id.toolbar_tvTitle)
        val btnBack = findViewById<View>(R.id.toolbar_btnBack)
        val btnDone = findViewById<View>(R.id.toolbar_btnDone)
        title?.let { tvTitle?.text = it }
        showBackButton?.let {
            btnBack?.apply {
                visibility = if (it) View.VISIBLE else View.GONE
                setOnClickListener {
                    finish()
                }
            }
        }
    }

    override fun onDataBound(binding: T) {
        super.onDataBound(binding)
    }
}