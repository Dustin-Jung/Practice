package com.android.aop.part2.airbnbpractice.data.repo

import com.android.aop.part2.airbnbpractice.api.response.HouseModel
import com.android.aop.part2.airbnbpractice.data.source.HouseRemoteDataSource

class HouseRepositoryImpl(private val houseRemoteDataSource: HouseRemoteDataSource) : HouseRepository {

    override fun getHouseList(isSuccess: (List<HouseModel>) -> Unit, isFailure: (String) -> Unit) {
        houseRemoteDataSource.getHouseList(isSuccess, isFailure)
    }
}