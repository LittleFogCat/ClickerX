package top.littlefogcat.clickerx.base.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import top.littlefogcat.clinj.Clinj
import top.littlefogcat.clinj.Inject
import top.littlefogcat.common.utils.showToast
import top.littlefogcat.network.NetResult

/**
 * 继承[BaseViewModel]实现具体的ViewModel。
 * 通过[Clinj]获取相应的Repository。
 *
 * @Author：littlefogcat
 * @Date：2021/1/30-8:32
 * @Email：littlefogcat@foxmail.com
 */
open class BaseViewModel : ViewModel() {
    companion object {
        const val TAG = "BaseViewModel"
    }

    init {
        if (hasInjectField()) {
            @Suppress("LeakingThis")
            Clinj.inject(this)
        }
    }

    private fun hasInjectField(): Boolean {
        for (field in javaClass.fields) {
            for (a in field.annotations) {
                if (a is Inject) {
                    return true
                }
            }
        }
        return false
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

    fun <T> NetResult<T>.resolveIfSuccess(errorHandler: (() -> Unit)? = null, action: (T) -> Unit) {
        if (isSuccess) {
            action(data!!)
        } else {
            errorHandler?.invoke() ?: defaultResolveFailure(this)
        }
    }

    fun <T> defaultResolveFailure(result: NetResult<T>) {
        showToast("Network error: msg = ${result.errorMsg}, code = ${result.errorCode}")
    }
}