package top.littlefogcat.clickerx.scriptdetail

import android.graphics.Color
import android.graphics.Typeface
import android.text.Editable
import android.text.Spanned
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import top.littlefogcat.clickerx.R
import top.littlefogcat.clickerx.base.BaseFragment
import top.littlefogcat.clickerx.base.DefaultToolbarHolder
import top.littlefogcat.clickerx.databinding.ScriptEditFragBinding
import top.littlefogcat.clickerx.utils.AlertDialogCompat
import top.littlefogcat.clickerx.utils.getViewModelWithActivityLifecycle
import top.littlefogcat.clickerx.utils.navigateBack

/**
 * 编辑单个脚本配置的界面。
 *
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
class ScriptEditFragment : BaseFragment<ScriptEditFragBinding>(), DefaultToolbarHolder {
    override val layoutId = R.layout.script_edit_frag
    var dialog: AlertDialog? = null

    // 点击返回键
    private val onBackPressedCallback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            showDiscardEditAlert()
        }
    }

    override fun onDataBinding(binding: ScriptEditFragBinding) {
        Log.d(TAG, "onDataBinding: ")
        binding.viewModel = getViewModelWithActivityLifecycle(ScriptDetailViewModel::class.java)
        requireActivity().apply {
            onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        }
        val editText = binding.etConfig
        // 注释变绿色
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(txt: Editable) {
                var i = 0
                txt.getSpans(0, txt.length, ForegroundColorSpan::class.java).forEach { txt.removeSpan(it) }
                txt.getSpans(0, txt.length, StyleSpan::class.java).forEach { txt.removeSpan(it) }
                var isInString = false
                while (i < txt.length - 1) {
                    if (!isInString && txt[i] == '-' && txt[i + 1] == '-') {
                        // find next \n or end
                        var j = i + 2
                        while (j < txt.length && txt[j] != '\n') j++
                        // set span to txt[i..j]
                        val noteColor = Color.parseColor("#43dd1c")
                        txt.setSpan(ForegroundColorSpan(noteColor), i, j, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
                        txt.setSpan(StyleSpan(Typeface.ITALIC), i, j, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
                        i = j + 1
                    } else {
                        // find next '-'
                        var j = i + 1
                        while (j < txt.length && txt[j] != '-') {
                            if (txt[j] == '\"') {
                                isInString = !isInString
                            }
                            j++
                        }
                        val normalColor = Color.parseColor("#1d1d1d")
                        txt.setSpan(ForegroundColorSpan(normalColor), i, i + 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
                        txt.setSpan(StyleSpan(Typeface.NORMAL), i, i + 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
                        i = j
                    }
                }
            }

        })
    }

    /**
     * 显示放弃编辑警告窗口
     */
    fun showDiscardEditAlert() {
        AlertDialogCompat.Builder(this, requireContext())
            .setTitle("提示")
            .setMessage("放弃编辑？")
            .setPositiveButton("是") { _, _ ->
                navigateBack()
                onBackPressedCallback.remove()
            }
            .setNegativeButton("否") { _, _ -> /* do nothing */ }
            .show()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: ")
        super.onDestroy()
        val d = dialog
        if (d != null && d.isShowing) {
            d.dismiss()
        }
    }

    override fun getTitle(): String {
        return getString(R.string.title_config_edit)
    }

    override fun onBtnBackClicked(): (View) -> Unit = {
        showDiscardEditAlert()
    }

    override fun onDoneClicked(): ((View) -> Unit) = {
        val viewModel = binding.viewModel as ScriptDetailViewModel
        val et = binding.etConfig
        viewModel.updateScript(et.text.toString())
        navigateBack()
    }

}