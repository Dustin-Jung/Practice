package com.android.aop.part2.airbnbpractice.uitl

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitModule {
    inline fun <reified T> create(baseUrl: String): T {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create()).build().create(T::class.java)
    }
}