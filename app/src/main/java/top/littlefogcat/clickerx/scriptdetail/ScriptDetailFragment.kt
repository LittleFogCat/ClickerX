package top.littlefogcat.clickerx.scriptdetail

import android.app.Instrumentation
import android.content.Context
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import top.littlefogcat.clickerx.R
import top.littlefogcat.clickerx.base.BaseFragment
import top.littlefogcat.clickerx.base.DefaultToolbarHolder
import top.littlefogcat.clickerx.core.Script
import top.littlefogcat.clickerx.core.accessibility.ClickerXA11yService
import top.littlefogcat.clickerx.databinding.ScriptDetailFragBinding
import top.littlefogcat.clickerx.utils.getViewModelWithActivityLifecycle
import top.littlefogcat.clickerx.utils.isAccessibilitySettingsOn

/**
 * 配置详情页。
 *
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
class ScriptDetailFragment : BaseFragment<ScriptDetailFragBinding>(), DefaultToolbarHolder, View.OnClickListener {
    override val layoutId = R.layout.script_detail_frag
    private var dialog: AlertDialog? = null

    override fun onDataBinding(binding: ScriptDetailFragBinding) {
        binding.viewModel = getViewModelWithActivityLifecycle(ScriptDetailViewModel::class.java)
        binding.onClickListener = this
    }

    /**
     * 处理点击事件
     */
    override fun onClick(view: View) {
        val viewModel = binding.viewModel ?: return
        if (view.id == R.id.btnEdit) {
            // 跳转到编辑界面
            findNavController().navigate(R.id.action_editManagerNameFragment_to_editManagerHeadFragment)
        } else if (view.id == R.id.btnRunOnce) {
            val isA11yOn = requireContext().isAccessibilitySettingsOn(ClickerXA11yService::class.java)
            if (!isA11yOn) {
                Toast.makeText(requireContext(), "无障碍服务未开启，无法执行", Toast.LENGTH_SHORT).show()
                return
            }
            // 提示运行一次
            val scheduled = viewModel.config.value?.run {
                state == Script.STATE_SCHEDULED
            } ?: false
            dialog = AlertDialog.Builder(requireActivity())
                .setTitle("提示")
                .setMessage("${if (scheduled) "停止运行" else "立即执行"}该脚本？")
                .setPositiveButton("是") { _, _ -> viewModel.runScriptOrPause() }
                .setNegativeButton("取消") { dialog, _ -> dialog?.dismiss() }
                .setCancelable(true)
                .setOnDismissListener { dialog = null }
                .show()
        } else if (view.id == R.id.btnSchedule) {
            // 提示定时运行
            dialog = AlertDialog.Builder(requireActivity())
                .setTitle("提示")
                .setTitle("为该脚本配置定时计划？")
                .setPositiveButton("是") { _, _ -> viewModel.scheduleScript() }
                .setCancelable(true)
                .setOnDismissListener { dialog = null }
                .show()
        }
    }

    override fun getTitle(): String {
        return getString(R.string.title_config_detail)
    }

    override fun onBtnBackClicked(): (View) -> Unit = {
        runOnDefault { Instrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_BACK) }
    }

    override fun onDoneClicked(): Nothing? = null

    override fun onResume() {
        super.onResume()
        // 当从编辑界面返回时，隐藏软键盘
        val ims = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        ims.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}