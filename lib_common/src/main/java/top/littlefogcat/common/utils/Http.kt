package top.littlefogcat.common.utils

import okhttp3.*
import java.io.IOException

/**
 * @Author：littlefogcat
 * @Date：2020/12/23-17:03
 * @Email：littlefogcat@foxmail.com
 */
object Http {
    private val client = OkHttpClient()

    fun httpGet(url: String, errorHandler: ((IOException) -> Unit)? = null, callback: (String?) -> Unit) {
        val req = Request.Builder().url(url).build()
        httpSend(req, callback, errorHandler)
    }

    fun httpPost(url: String, jsonBody: String?, errorHandler: ((IOException) -> Unit)? = null, callback: (String?) -> Unit) {
        httpPost(url, makeRequestBody(jsonBody), errorHandler, callback)
    }

    fun httpPost(url: String, body: RequestBody, errorHandler: ((IOException) -> Unit)? = null, callback: (String?) -> Unit) {
        val req = Request.Builder()
            .url(url)
            .post(body)
            .build()
        httpSend(req, callback, errorHandler)
    }

    fun httpSend(req: Request, callback: ((String?) -> Unit)?, errorHandler: ((IOException) -> Unit)?) {
        httpSend(true, req, callback, errorHandler)
    }

    fun httpSend(async: Boolean, req: Request, callback: ((String?) -> Unit)?, errorHandler: ((IOException) -> Unit)?): Response? {
        if (async) {
            client.newCall(req).enqueue(
                object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        errorHandler?.invoke(e)
                    }

                    override fun onResponse(call: Call, response: Response) {
                        callback?.invoke(response.body()?.string())
                    }
                }
            )
            return null
        } else {
            return client.newCall(req).execute()
        }
    }

    fun makeRequestBody(json: String?): RequestBody {
        return RequestBody.create(MediaType.parse("application/json"), json ?: return emptyBody())
    }

    private fun emptyBody(): RequestBody {
        return RequestBody.create(null, "")
    }
}