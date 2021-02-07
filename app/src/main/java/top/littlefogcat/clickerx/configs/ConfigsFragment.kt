package top.littlefogcat.clickerx.configs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import top.littlefogcat.clickerx.R
import top.littlefogcat.clickerx.databinding.ConfigsFragBinding

/**
 * 配置列表。
 *
 * @Author littlefogcat
 * @Date 2020/8/4-2:08
 * @Email littlefogcat@foxmail.com
 */
class ConfigsFragment : Fragment() {
    private lateinit var binding: ConfigsFragBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.configs_frag, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
    }

}