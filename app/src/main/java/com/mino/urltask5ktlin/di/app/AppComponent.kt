package com.mino.urltask5ktlin.di.app

import android.app.Application
import com.mino.urltask5ktlin.BaseApplication
import com.mino.urltask5ktlin.ui.common.viewmodels_factory.ViewModelsProviderFactory
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@PerApplication
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBuildersModule::class,
        ViewModelsFactoryModule::class,
        AppModule::class
    ]
)
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}