package com.mino.urltask5ktlin.data.repos

import com.mino.urltask5ktlin.data.remote.UrlApi
import com.mino.urltask5ktlin.di.app.PerApplication
import io.reactivex.Maybe
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

@PerApplication
class UrlRemoteRepository @Inject constructor(private val urlApi: UrlApi) {

    fun getResponseObservable(url: String): Maybe<Response<ResponseBody>> = urlApi.getResponse(url)
}