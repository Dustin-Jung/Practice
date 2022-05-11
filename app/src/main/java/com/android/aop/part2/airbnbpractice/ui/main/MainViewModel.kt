package com.android.aop.part2.airbnbpractice.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.aop.part2.airbnbpractice.api.response.HouseResponse
import com.android.aop.part2.airbnbpractice.api.HouseService
import com.android.aop.part2.airbnbpractice.data.repo.HouseRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel(private val houseRepository: HouseRepository) : ViewModel() {

    private val _mainViewStateLiveData = MutableLiveData<MainViewState>()
    val mainViewStateLiveData: LiveData<MainViewState> = _mainViewStateLiveData

    fun getHouseListFromAPI() {
        houseRepository.getHouseList(isSuccess = {list->
            val toHouseItem = list.map { it.toHouseItem() }
            _mainViewStateLiveData.value = MainViewState.GetHouseList(toHouseItem)
        }, isFailure = {errorMessage ->
            _mainViewStateLiveData.value = MainViewState.Error(errorMessage)
        })
    }
}
