package com.android.aop.part2.airbnbpractice.api

import com.android.aop.part2.airbnbpractice.api.response.HouseResponse
import retrofit2.Call
import retrofit2.http.GET

interface HouseService {
    @GET("/v3/f106b522-5d7c-481a-8ca2-15793a4f297b")
    fun getHouseList() : Call<HouseResponse>
}