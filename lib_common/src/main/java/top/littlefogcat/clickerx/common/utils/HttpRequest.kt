package top.littlefogcat.clickerx.common.utils

import okhttp3.Request
import okhttp3.Response

/**
 * @Author：littlefogcat
 * @Date：2020/12/23-21:29
 * @Email：littlefogcat@foxmail.com
 */
class HttpRequest {
    private var _url: String? = null
    private var _headers: MutableMap<String, String>? = null
    private var _method = "GET"
    private var _body: String? = null

    fun url(url: String): HttpRequest {
        _url = url
        return this
    }

    fun addHeader(k: String, v: String): HttpRequest {
        if (_headers == null) _headers = mutableMapOf()
        _headers?.put(k, v)
        return this
    }

    fun get(): HttpRequest {
        _method = "GET"
        return this
    }

    fun post(body: String): HttpRequest {
        _method = "POST"
        _body = body
        return this
    }

    fun method(method: String, body: String): HttpRequest {
        _method = method
        _body = body
        return this
    }

    /**
     * 根据设置的参数发起请求
     * 调用[Http.httpSend]
     */
    fun send(async: Boolean = true, errorHandler: ((Exception) -> Unit)? = null, callback: ((String?) -> Unit)? = null): Response? {
        val builder = Request.Builder()
        if (_url == null) {
            errorHandler?.invoke(Exception("url == null"))
            return null
        }
        builder.url(_url!!)
        _headers?.forEach { entry ->
            builder.addHeader(entry.key, entry.value)
        }
        builder.method(_method, Http.makeRequestBody(_body))
        val req = builder.build()
        return Http.httpSend(async, req, callback, errorHandler)
    }
}