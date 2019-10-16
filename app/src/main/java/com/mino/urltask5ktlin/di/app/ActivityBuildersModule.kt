package com.mino.urltask5ktlin.di.app

import com.mino.urltask5ktlin.di.main.MainModule
import com.mino.urltask5ktlin.di.main.PerMain
import com.mino.urltask5ktlin.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @PerMain
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun contributeMainActivity(): MainActivity
}