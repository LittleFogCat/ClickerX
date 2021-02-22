package top.littlefogcat.clickerx.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

/**
 * @Author：littlefogcat
 * @Date：2021/1/30-8:32
 * @Email：littlefogcat@foxmail.com
 */
open class BaseViewModel : ViewModel() {
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
    protected fun <T> runOnIO(block: () -> T, callback: (T) -> Unit): Job {
        return coroutine.launch(Dispatchers.IO) {
            val result = block.invoke()
            withContext(Dispatchers.Main) {
                callback(result)
            }
        }
    }

    /**
     * 在IO线程执行[block]，并在主线程将返回值赋值给[liveData]。
     */
    protected fun <T> runOnIO(liveData: MutableLiveData<T>, block: () -> T): Job {
        return coroutine.launch(Dispatchers.IO) {
            val result = block.invoke()
            withContext(Dispatchers.Main) {
                liveData.value = result
            }
        }
    }

    protected suspend fun runOnMainThread(block: suspend CoroutineScope.() -> Unit) {
        withContext(Dispatchers.Main, block)
    }

//    protected fun runOnUIThread(action: () -> Unit) {
//        coroutine.launch(Dispatchers.Main) {
//            action.invoke()
//        }
//    }

    override fun onCleared() {
        super.onCleared()
        coroutine.cancel("onViewModelCleared")
    }
}