package com.android.aop.part2.airbnbpractice.api.response

import com.android.aop.part2.airbnbpractice.data.model.HouseItem

data class HouseResponse(
    val items: List<HouseModel>
)

data class HouseModel(
    val id: Int,
    val title: String,
    val price: String,
    val lat: Double,
    val lng: Double,
    val imgUrl: String
) {
    fun toHouseItem(): HouseItem =
        HouseItem(id, title, price, lat, lng, imgUrl)
}

