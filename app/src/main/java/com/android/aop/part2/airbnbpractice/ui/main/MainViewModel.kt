package com.android.aop.part2.airbnbpractice.ui.main

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.aop.part2.airbnbpractice.data.HouseDto
import com.android.aop.part2.airbnbpractice.data.HouseService
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.MarkerIcons
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel : ViewModel() {

    private val _mainViewStateLiveData = MutableLiveData<MainViewState>()
    val mainViewStateLiveData: LiveData<MainViewState> = _mainViewStateLiveData


    private fun getHouseList() {


    }


    private fun getHouseListFromAPI() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://run.mocky.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(HouseService::class.java).also {
            it.getHouseList()
                .enqueue(object : Callback<HouseDto> {
                    override fun onResponse(call: Call<HouseDto>, response: Response<HouseDto>) {
                        if (response.isSuccessful.not()) {
                            return // 실패처리에 대한 구현
                        }

                        response.body()?.let { dto ->
                            dto.items.forEach { model ->
                                val marker = Marker()
                                marker.position = LatLng(model.lat, model.lng)
                                marker.map = naverMap
                                marker.tag = model.id
                                marker.icon = MarkerIcons.BLACK
                                marker.iconTintColor = Color.RED
                            }

                            houseRecyclerViewAdapter.addAll(dto.items)
                            houseViewPagerAdapter.addAll(dto.items)
                        }
                    }

                    override fun onFailure(call: Call<HouseDto>, t: Throwable) {
                        //실패처리에 대한 구현
                    }
                })
        }

    }


}