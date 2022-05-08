package com.android.aop.part2.airbnbpractice.ui.main

import com.android.aop.part2.airbnbpractice.data.HouseModel
import com.naver.maps.map.overlay.Marker

sealed class MainViewState {
    data class GetHouseList(val houseList: List<HouseModel>, val markerList: List<Marker>) :
        MainViewState()

    data class Error(val message: String) : MainViewState()
}