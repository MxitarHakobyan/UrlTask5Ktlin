package com.mino.urltask5ktlin.data.remote

import io.reactivex.Maybe
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface UrlApi {

    @GET
    fun getResponse(@Url url: String): Maybe<Response<ResponseBody>>
}