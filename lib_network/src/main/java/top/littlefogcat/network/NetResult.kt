package top.littlefogcat.network

/**
 * Http request result.
 *
 * Notice that the [data] **must not** be `null`.
 * If the request is success and the `data` field does not exists, Server should return an
 * empty json object instead of nothing. Like:
 * ```json
 * {
 *   "errorCode": 200,
 *   "errorMsg": "ok",
 *   "data": {}
 * }
 * ```
 *
 * If [errorCode] is [HttpStatusCode.OK] but [data] is `null`, the server returns a wrong result.
 *
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
open class NetResult<out T>(
    val errorCode: Int,
    val errorMsg: String?,
    val data: T?
) {
    val isSuccess get() = errorCode == HttpStatusCode.OK
    val isFailure get() = errorCode != HttpStatusCode.OK

    /**
     * For mock use.
     */
    class Success<T>(data: T, errorMsg: String = "ok") : NetResult<T>(HttpStatusCode.OK, errorMsg, data)
    class Failure<T>(errorCode: Int, errorMsg: String?) : NetResult<T>(errorCode, errorMsg, null)
}