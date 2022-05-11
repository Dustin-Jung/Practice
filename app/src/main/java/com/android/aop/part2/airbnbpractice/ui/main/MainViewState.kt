package com.android.aop.part2.airbnbpractice.ui.main


import com.android.aop.part2.airbnbpractice.data.HouseModel

sealed class MainViewState {
    data class GetHouseList(val list: List<HouseModel>) : MainViewState()
    data class Error(val message : String) : MainViewState()
}