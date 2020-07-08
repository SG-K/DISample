package com.example.koinsample.di

import com.example.koinsample.network.GitHubApi
import com.example.koinsample.network.GitHubRepo
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


fun createRetrofitClient() =
    retrofitClient(baseUrl, okHttpClient())

val networkModule = module {
    single { createRetrofitClient().create(GitHubApi::class.java) }
    single { GitHubRepo(get()) }
}

private const val baseUrl = "https://api.github.com/"

private fun okHttpClient() =
    OkHttpClient.Builder().run {
            addInterceptor(HttpLoggingInterceptor().apply{
                if (com.example.koinsample.BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                readTimeout(60L, TimeUnit.SECONDS)
                connectTimeout(60L, TimeUnit.SECONDS)
                writeTimeout(60L, TimeUnit.SECONDS)
            })
        build()
    }

private fun retrofitClient(baseUrl: String, httpClient: OkHttpClient): Retrofit =
    Retrofit.Builder().run {
        baseUrl(baseUrl)
        client(httpClient)
        addConverterFactory(GsonConverterFactory.create())
        build()
    }