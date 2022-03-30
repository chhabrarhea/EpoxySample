package com.rhea.epoxy.di

import android.content.Context
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import com.rhea.epoxy.EpoxyApp
import com.rhea.epoxy.api.Service
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    @Provides
    fun provideContext(@ApplicationContext application: EpoxyApp): Context =
        application.applicationContext

    @IODispatcher
    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Singleton
    @Provides
    fun provideRetrofitBuilder(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .build()

    @Singleton
    @Provides
    fun provideSlashService(retrofit: Retrofit): Service =
        retrofit.create(Service::class.java)
}