package com.android.aop.part2.airbnbpractice.data.source

import com.android.aop.part2.airbnbpractice.api.response.HouseModel


interface HouseRemoteDataSource {

    fun getHouseList(
        isSuccess: (List<HouseModel>) -> Unit,
        isFailure: (String) -> Unit
    )
}