package top.littlefogcat.clickerx.scriptdetail

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import top.littlefogcat.clickerx.R
import top.littlefogcat.clickerx.common.base.BaseActivity
import top.littlefogcat.clickerx.db.entities.Script
import top.littlefogcat.clickerx.common.utils.INTENT_KEY_CONFIG
import top.littlefogcat.clickerx.common.utils.ROUTE_ACTIVITY_CONFIG_DETAIL
import top.littlefogcat.clickerx.common.utils.getViewModel

/**
 * 脚本配置详情的Activity。
 *
 * 包含了[ScriptDetailFragment]配置详情界面，[ScriptEditFragment]编辑脚本页面。
 *
 * 在启动时通过ARouter注入[script]对象，并将其赋给[ScriptDetailViewModel.script]，用于整个配置详情功能模块共享的config实例对象。
 */
@Route(path = ROUTE_ACTIVITY_CONFIG_DETAIL)
class ScriptDetailActivity : BaseActivity() {
    /**
     * 脚本配置实例对象。
     */
    @Autowired(name = INTENT_KEY_CONFIG)
    lateinit var script: Script

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.script_detail_act)
        ARouter.getInstance().inject(this) // 注入config
        getViewModel(ScriptDetailViewModel::class.java).script.value = script // 通过ViewModel保存config实例
    }

}