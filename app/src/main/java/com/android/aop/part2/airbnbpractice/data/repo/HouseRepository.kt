package com.android.aop.part2.airbnbpractice.data.repo

import com.android.aop.part2.airbnbpractice.api.response.HouseModel

interface HouseRepository {
    fun getHouseList(
        isSuccess: (List<HouseModel>) -> Unit,
        isFailure: (String) -> Unit
    )
}