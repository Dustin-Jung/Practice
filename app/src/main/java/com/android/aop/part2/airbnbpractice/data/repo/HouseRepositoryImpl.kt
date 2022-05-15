package com.android.aop.part2.airbnbpractice.data.repo

import com.android.aop.part2.airbnbpractice.api.response.HouseModel
import com.android.aop.part2.airbnbpractice.data.source.HouseRemoteDataSource
import com.android.aop.part2.airbnbpractice.data.source.HouseRemoteDataSourceImpl
import javax.inject.Inject

class HouseRepositoryImpl @Inject constructor(private val houseRemoteDataSource: HouseRemoteDataSource) :
    HouseRepository {

    override fun getHouseList(isSuccess: (List<HouseModel>) -> Unit, isFailure: (String) -> Unit) {
        houseRemoteDataSource.getHouseList(isSuccess, isFailure)
    }
}