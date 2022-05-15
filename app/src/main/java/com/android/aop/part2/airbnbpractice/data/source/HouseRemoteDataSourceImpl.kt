package com.android.aop.part2.airbnbpractice.data.source

import com.android.aop.part2.airbnbpractice.api.HouseService
import com.android.aop.part2.airbnbpractice.api.response.HouseModel
import com.android.aop.part2.airbnbpractice.api.response.HouseResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class HouseRemoteDataSourceImpl @Inject constructor(private val houseService: HouseService) : HouseRemoteDataSource {

    override fun getHouseList(isSuccess: (List<HouseModel>) -> Unit, isFailure: (String) -> Unit) {
        houseService.getHouseList()
            .enqueue(object : Callback<HouseResponse> {
                override fun onResponse(
                    call: Call<HouseResponse>,
                    response: Response<HouseResponse>
                ) {
                    if (response.isSuccessful.not()) {
                        return // 실패처리에 대한 구현
                    }

                    response.body()?.let { dto ->
                        isSuccess.invoke(dto.items)
                    }
                }

                override fun onFailure(call: Call<HouseResponse>, t: Throwable) {
                    isFailure.invoke(t.message ?: "GetHouseListFromAPI 실패")
                }
            })
    }
}