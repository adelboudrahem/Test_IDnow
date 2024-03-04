package com.example.idnow_v2application.di

import com.example.idnow_v2application.network.ProductApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val timeOut = 20L
    private const val retrofitUrl = "https://dummyjson.com/"

    @Provides
    fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .apply {
                readTimeout(timeOut, TimeUnit.SECONDS)
                connectTimeout(timeOut, TimeUnit.SECONDS)
            }
            .build()
    }

    @Provides
    fun createGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun buildRetrofit(okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(retrofitUrl)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    fun createProductApiService(retrofit: Retrofit): ProductApi {
        return retrofit.create(ProductApi::class.java)
    }

}