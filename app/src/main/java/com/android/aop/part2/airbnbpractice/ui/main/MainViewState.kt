package com.android.aop.part2.airbnbpractice.ui.main

import com.android.aop.part2.airbnbpractice.data.model.HouseItem


sealed class MainViewState {
    data class GetHouseList(val list: List<HouseItem>) : MainViewState()
    data class Error(val message : String) : MainViewState()
}