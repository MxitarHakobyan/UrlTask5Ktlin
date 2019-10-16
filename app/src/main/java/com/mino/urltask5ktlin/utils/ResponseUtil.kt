package com.mino.urltask5ktlin.utils

import com.mino.urltask5ktlin.data.remote.entity.ResponseInfo
import okhttp3.ResponseBody
import retrofit2.Response

fun response2RequestInfo(response: Response<ResponseBody>): ResponseInfo = ResponseInfo(
    response.raw().sentRequestAtMillis().toInt(),
    response.code()
)

fun isResponseValid(code: Int) : Int {
    return if((code.toString()).startsWith("2")) {
        URL_AVAILABLE
    }else {
        URL_UNAVAILABLE
    }
}