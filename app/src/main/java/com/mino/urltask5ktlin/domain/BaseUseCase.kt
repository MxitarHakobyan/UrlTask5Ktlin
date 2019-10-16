package com.mino.urltask5ktlin.domain

import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class BaseUseCase {

    fun execute(
        body: () -> Unit,
        observer: CompletableObserver
    ) {
        Completable.fromAction(body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }
}