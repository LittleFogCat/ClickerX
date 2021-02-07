package top.littlefogcat.clickerx.configs

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import top.littlefogcat.clickerx.model.Config
import top.littlefogcat.clickerx.model.ConfigsRepository

/**
 * @Author littlefogcat
 * @Date 2020/8/4-1:03
 * @Email littlefogcat@foxmail.com
 */
class ConfigsViewModel {
    val configsRepository = ConfigsRepository()

    val empty = MutableLiveData<Boolean>().apply { value = false }
    val configs = MutableLiveData<List<Config>>()

    fun loadConfigs() {
        GlobalScope.launch(Dispatchers.IO) {
            val configList = configsRepository.loadConfigs()
            configs.value = configList
        }
    }
}