package com.mino.urltask5ktlin.domain

import com.mino.urltask5ktlin.data.db.entity.UrlEntity
import com.mino.urltask5ktlin.data.repos.UrlRepository
import com.mino.urltask5ktlin.di.main.PerMain
import com.mino.urltask5ktlin.ui.main.viewmodel.UrlModel
import com.mino.urltask5ktlin.utils.*
import io.reactivex.CompletableObserver
import io.reactivex.Flowable
import io.reactivex.MaybeObserver
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@PerMain
class UrlUseCase @Inject constructor(
    private val urlRepository: UrlRepository,
    private val compositeDisposable: CompositeDisposable,
    private val remoteUseCase: UrlRemoteUseCase
) : BaseUseCase(),
    BaseUseCaseBehaviour {

    fun insert(urlEntity: UrlEntity) {
        execute(
            body = { urlRepository.insert(urlEntity) },
            observer = object : CompletableObserver {
                override fun onComplete() {
                    remoteUseCase.checkUrl(url = urlEntity.url)
                        .subscribe(getMaybeObserver(url = urlEntity.url))
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {

                }

            })
    }

    fun getMaybeObserver(url: String): MaybeObserver<UrlEntity> =
        object : MaybeObserver<UrlEntity> {

            override fun onSuccess(entity: UrlEntity) {
                update(entity)
            }

            override fun onComplete() {

            }

            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
            }

            override fun onError(e: Throwable) {
                update(UrlEntity(url, URL_UNAVAILABLE, 0))
            }
        }

    fun update(urlEntity: UrlEntity) {
        execute(
            body = { urlRepository.update(urlEntity) },
            observer = object : CompletableObserver {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {

                }

            })
    }

    fun deleteByUrl(url: String) {
        execute(
            body = { urlRepository.deleteByUrl(url) },
            observer = object : CompletableObserver {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {

                }

            })
    }

    fun getUrlsOrderBy(orderType: String): Flowable<List<UrlModel>> {

        when (orderType) {
            ORDER_BY_URL -> return urlRepository
                .getUrlsOrderByUrl()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map { entities: List<UrlEntity> -> convert2UrlModel(entities) }
            ORDER_BY_AVAILABILITY -> return urlRepository
                .getUrlsOrderByAvailability()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map { entities: List<UrlEntity> -> convert2UrlModel(entities) }
            ORDER_BY_TIME -> return urlRepository
                .getUrlsOrderByLoadingTime()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map { entities: List<UrlEntity> -> convert2UrlModel(entities) }
            else -> return urlRepository
                .getUrlsOrderByUrl()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map { entities: List<UrlEntity> -> convert2UrlModel(entities) }
        }
    }

    fun reCheck(models: List<UrlModel>) {
        compositeDisposable.add(
            Observable.fromIterable(models)
                .map { urlModel: UrlModel -> urlModel.resetState(urlModel) }
                .map { urlModel: UrlModel -> urlModel.url.get() }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe { url -> remoteUseCase.checkUrl(url!!).subscribe(getMaybeObserver(url)) }
        )
    }

    override fun unsubscribe() {
        compositeDisposable.clear()
    }
}