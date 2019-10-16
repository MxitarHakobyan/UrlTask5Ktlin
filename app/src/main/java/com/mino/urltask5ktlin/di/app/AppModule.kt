package com.mino.urltask5ktlin.di.app

import android.app.Application
import androidx.room.Room
import com.mino.urltask5ktlin.data.db.UrlDb
import com.mino.urltask5ktlin.data.remote.UrlApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

@Module
abstract class AppModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @PerApplication
        internal fun provideUrlDb(application: Application) =
            Room.databaseBuilder(
                application,
                UrlDb::class.java,
                "url.db"
            ).fallbackToDestructiveMigration().build()

        @JvmStatic
        @Provides
        @PerApplication
        internal fun provideUrlDao(urlDb: UrlDb) = urlDb.getUrlDao()

        @JvmStatic
        @Provides
        @PerApplication
        internal fun provideRetrofit(): Retrofit = Retrofit.Builder()
            .baseUrl("https://www.google.com")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        @JvmStatic
        @Provides
        @PerApplication
        internal fun provideUrlApi(retrofit: Retrofit): UrlApi = retrofit.create(UrlApi::class.java)
    }
}