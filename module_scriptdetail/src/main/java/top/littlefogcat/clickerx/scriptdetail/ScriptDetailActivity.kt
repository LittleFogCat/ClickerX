package top.littlefogcat.clickerx.scriptdetail

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import top.littlefogcat.clickerx.base.base.BaseActivity
import top.littlefogcat.clickerx.base.entities.Script
import top.littlefogcat.clickerx.base.constants.INTENT_KEY_SCRIPT
import top.littlefogcat.clickerx.base.constants.RouteConstants
import top.littlefogcat.clickerx.base.utils.getViewModel

/**
 * 脚本配置详情的Activity。
 *
 * 包含了[ScriptDetailFragment]配置详情界面，[ScriptEditFragment]编辑脚本页面。
 *
 * 在启动时通过ARouter注入[script]对象，并将其赋给[ScriptDetailViewModel.script]，用于整个配置详情功能模块共享的config实例对象。
 */
@Route(path = RouteConstants.ROUTE_ACTIVITY_SCRIPT_DETAIL)
class ScriptDetailActivity : BaseActivity() {
    /**
     * 脚本配置实例对象。
     */
    @Autowired(name = INTENT_KEY_SCRIPT)
    lateinit var script: Script

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.script_detail_act)
        ARouter.getInstance().inject(this) // 注入config
        getViewModel(ScriptDetailViewModel::class.java).script.value = script // 通过ViewModel保存config实例
    }

}