package com.android.aop.part2.airbnbpractice.ui.main

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.aop.part2.airbnbpractice.data.HouseDto
import com.android.aop.part2.airbnbpractice.data.HouseService
import com.android.aop.part2.airbnbpractice.uitl.RetrofitModule
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.MarkerIcons
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _mainViewStateLiveData = MutableLiveData<MainViewState>()
    val mainViewStateLiveData: LiveData<MainViewState> = _mainViewStateLiveData

    init {
        getHouseList()
    }

    private fun getHouseList() {

        val houseService =
            RetrofitModule.create<HouseService>(baseUrl = MainActivity.BASE_URL_HOUSE)

        houseService.getHouseList().enqueue(object : Callback<HouseDto> {
            override fun onResponse(call: Call<HouseDto>, response: Response<HouseDto>) {
                response.body()?.let { dto ->
                    val markerList = mutableListOf<Marker>()

                    dto.items.forEach { model ->
                        val marker = Marker().apply {
                            position = LatLng(model.lat, model.lng)
                            tag = model.id
                            icon = MarkerIcons.BLACK
                            iconTintColor = Color.RED
                        }
                        markerList.add(marker)
                    }

                    _mainViewStateLiveData.value = MainViewState.GetHouseList(dto.items, markerList)
                }
            }

            override fun onFailure(call: Call<HouseDto>, t: Throwable) {
                _mainViewStateLiveData.value =
                    MainViewState.Error(t.message ?: "HouseService Error")
            }
        })
    }
}