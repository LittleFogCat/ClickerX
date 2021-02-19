package top.littlefogcat.clickerx.configs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.GravityCompat
import kotlinx.android.synthetic.main.configs_act.*
import top.littlefogcat.clickerx.addconfig.AddConfigActivity
import top.littlefogcat.clickerx.R
import top.littlefogcat.clickerx.utils.launchActivity
import top.littlefogcat.clickerx.utils.replaceFragmentIfAbsent

/**
 * 配置文件列表
 */
class ConfigsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.configs_act)

        initToolBar()
        initNavigationView()
        initFragments()
    }

    private fun initToolBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_action_menu)
        }
    }

    /**
     * 初始化配置列表Fragment
     */
    private fun initFragments() {
        replaceFragmentIfAbsent(R.id.contentFrame, ConfigsFragment.newInstance())
    }

    private fun initNavigationView() {
        navi.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.configs -> {

                }
                R.id.settings -> {
                }
            }
            drawer.closeDrawer(GravityCompat.START)
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.configs_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add -> launchActivity(
                AddConfigActivity::class.java
            )
            android.R.id.home -> drawer.openDrawer(GravityCompat.START)
        }
        return true
    }
}