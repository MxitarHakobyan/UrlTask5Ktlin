package com.mino.urltask5ktlin.data.repos

import com.mino.urltask5ktlin.data.db.dao.UrlDao
import com.mino.urltask5ktlin.data.db.entity.UrlEntity
import com.mino.urltask5ktlin.di.app.PerApplication
import io.reactivex.Flowable
import javax.inject.Inject

@PerApplication
class UrlRepository @Inject constructor(private val urlDao: UrlDao) {

    fun update(urlEntity: UrlEntity) = urlDao.update(urlEntity)

    fun insert(urlEntity: UrlEntity) = urlDao.insert(urlEntity)

    fun deleteByUrl(url: String) = urlDao.deleteByUrl(url)

    fun getUrlsOrderByUrl(): Flowable<List<UrlEntity>> = urlDao.getUrlsOrderByUrl()

    fun getUrlsOrderByAvailability(): Flowable<List<UrlEntity>> = urlDao.getUrlsOrderByAvailability()

    fun getUrlsOrderByLoadingTime(): Flowable<List<UrlEntity>> = urlDao.getUrlsOrderByLoadingTime()

}