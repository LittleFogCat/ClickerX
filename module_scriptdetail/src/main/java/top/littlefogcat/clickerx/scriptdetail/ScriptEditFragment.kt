package top.littlefogcat.clickerx.scriptdetail

import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.activity.OnBackPressedCallback
import top.littlefogcat.clickerx.base.base.BaseFragment
import top.littlefogcat.clickerx.base.base.DefaultToolbarHolder
import top.littlefogcat.clickerx.base.utils.getViewModelWithActivityLifecycle
import top.littlefogcat.clickerx.base.utils.navigateBack
import top.littlefogcat.common.utils.AlertDialogCompat
import top.littlefogcat.clickerx.scriptdetail.databinding.ScriptEditFragBinding

/**
 * 编辑单个脚本配置的界面。
 *
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
class ScriptEditFragment : BaseFragment<ScriptEditFragBinding>(), DefaultToolbarHolder {
    override val layoutId = R.layout.script_edit_frag

    // 点击返回键
    private val onBackPressedCallback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            showDiscardEditAlert()
        }
    }

    override fun onDataBinding(binding: ScriptEditFragBinding) {
        Log.d(TAG, "onDataBinding: ${lifecycle.currentState.name}")
        binding.viewModel = getViewModelWithActivityLifecycle(ScriptDetailViewModel::class.java)
        requireActivity().apply {
            onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        }
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
            }
            .setNegativeButton("否") { _, _ -> /* do nothing */ }
            .show()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: ${lifecycle.currentState.name}")
        super.onDestroy()
        onBackPressedCallback.remove()
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