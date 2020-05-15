package com.object173.mvvi.sample.di

import android.content.Context
import com.object173.mvvi.R
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { provideRetrofit(get<Context>().getString(R.string.api_url)) }
}

private fun provideRetrofit(baseUrl: String): Retrofit =
    Retrofit.Builder()
        .client(OkHttpClient())
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()