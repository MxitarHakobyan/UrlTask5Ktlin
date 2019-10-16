package com.mino.urltask5ktlin.domain

import com.mino.urltask5ktlin.data.db.entity.UrlEntity
import com.mino.urltask5ktlin.data.remote.entity.ResponseInfo
import com.mino.urltask5ktlin.data.repos.UrlRemoteRepository
import com.mino.urltask5ktlin.di.main.PerMain
import com.mino.urltask5ktlin.utils.isResponseValid
import com.mino.urltask5ktlin.utils.response2RequestInfo
import io.reactivex.Maybe
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

@PerMain
class UrlRemoteUseCase @Inject constructor(private val urlRemoteRepository: UrlRemoteRepository) {

    fun checkUrl(url: String): Maybe<UrlEntity> {
        return urlRemoteRepository
            .getResponseObservable(url)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .map { response: Response<ResponseBody> -> response2RequestInfo(response) }
            .map { requestInfo: ResponseInfo ->
                UrlEntity(
                    url,
                    isResponseValid(requestInfo.code),
                    requestInfo.time
                )
            }
    }
}