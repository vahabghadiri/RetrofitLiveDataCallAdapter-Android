package com.android.ghadiri.v.sample.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.android.ghadiri.v.sample.BuildConfig
import com.android.ghadiri.v.sample.model.cloud.CloudApiContract
import com.android.ghadiri.v.sample.liveadapter.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 *
 * @author vahab ghadiri
 */

@Module
open class NetworkModule {

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }


    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }


    @Singleton
    @Provides
    open fun provideServiceWithToken(retrofit: Retrofit): CloudApiContract =
        retrofit.create(CloudApiContract::class.java)
}