package com.antoine.androiddemo.di

import android.app.Application
import android.content.Context
import com.antoine.androiddemo.R
import com.antoine.androiddemo.data.api.AppService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun getContext(context: Application): Context {
        return context
    }

    @Provides
    fun provideLogInterceptor(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    fun provideAnalyticsService(context: Context, client: OkHttpClient): AppService {
        return Retrofit.Builder()
            .baseUrl(context.getString(R.string.api_url))
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(AppService::class.java)
    }
}