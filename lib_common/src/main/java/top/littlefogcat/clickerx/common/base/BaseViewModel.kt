package top.littlefogcat.clickerx.common.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

/**
 * 继承[BaseViewModel]实现具体的ViewModel。
 * 通过[Injector]获取相应的Repository。
 *
 * @Author：littlefogcat
 * @Date：2021/1/30-8:32
 * @Email：littlefogcat@foxmail.com
 */
open class BaseViewModel : ViewModel() {
    companion object {
        const val TAG = "BaseViewModel"
    }

    protected val coroutine = MainScope()

    /**
     * 在IO线程执行[block]。
     */
    protected fun runOnIO(block: suspend CoroutineScope.() -> Unit): Job {
        return coroutine.launch(Dispatchers.IO, block = block)
    }

    /**
     * 在IO线程执行[block]，在主线程执行回调[callback]。
     */
    protected fun <T> runOnIO(block: suspend () -> T, callback: (T) -> Unit): Job {
        return coroutine.launch(Dispatchers.IO) {
            val result = block.invoke()
            withContext(Dispatchers.Main) {
                callback(result)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        coroutine.cancel("onViewModelCleared")
    }
}