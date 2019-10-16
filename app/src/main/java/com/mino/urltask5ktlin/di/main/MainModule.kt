package com.mino.urltask5ktlin.di.main

import androidx.lifecycle.ViewModel
import com.mino.urltask5ktlin.di.app.ViewModelKey
import com.mino.urltask5ktlin.ui.main.viewmodel.UrlViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import io.reactivex.disposables.CompositeDisposable

@Module
abstract class MainModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()
    }

    @Binds
    @IntoMap
    @ViewModelKey(UrlViewModel::class)
    abstract fun bindUrlViewModel(viewModel: UrlViewModel): ViewModel
}