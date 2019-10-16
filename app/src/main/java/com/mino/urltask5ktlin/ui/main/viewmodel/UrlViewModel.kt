package com.mino.urltask5ktlin.ui.main.viewmodel

import android.webkit.URLUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mino.urltask5ktlin.data.db.entity.UrlEntity
import com.mino.urltask5ktlin.domain.UrlUseCase
import com.mino.urltask5ktlin.utils.URL_LOADING
import io.reactivex.Flowable
import javax.inject.Inject

class UrlViewModel @Inject constructor(
    private val urlUseCase: UrlUseCase
) : ViewModel() {

    var insertUrl = MutableLiveData<String>()
    var error = MutableLiveData<String>()

    fun insert() {
        insertUrl.value?.let {
            if (URLUtil.isValidUrl(it)) {
                urlUseCase.insert(UrlEntity(it, URL_LOADING, 0))
            } else {
                error.postValue("Not Valid url")
            }
        }
    }

    fun reCheck(models: List<UrlModel>) = urlUseCase.reCheck(models)

    fun deleteByUrl(url: String) = urlUseCase.deleteByUrl(url)

    fun getUrlsOrderBy(orderType: String): Flowable<List<UrlModel>> =
        urlUseCase.getUrlsOrderBy(orderType)

    override fun onCleared() {
        super.onCleared()
        urlUseCase.unsubscribe()
    }
}