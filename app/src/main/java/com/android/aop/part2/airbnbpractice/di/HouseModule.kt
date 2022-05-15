package com.android.aop.part2.airbnbpractice.di

import com.android.aop.part2.airbnbpractice.data.repo.HouseRepository
import com.android.aop.part2.airbnbpractice.data.repo.HouseRepositoryImpl
import com.android.aop.part2.airbnbpractice.data.source.HouseRemoteDataSource
import com.android.aop.part2.airbnbpractice.data.source.HouseRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class HouseModule {


    @Singleton
    @Binds
    abstract fun bindHouseRepository(
        houseRepositoryImpl: HouseRepositoryImpl
    ): HouseRepository

    @Singleton
    @Binds
    abstract fun bindHouseRemoteDataSource(
        houseRemoteDataSourceImpl: HouseRemoteDataSourceImpl
    ): HouseRemoteDataSource

}