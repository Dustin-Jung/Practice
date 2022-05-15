package com.android.aop.part2.airbnbpractice.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.aop.part2.airbnbpractice.api.response.HouseResponse
import com.android.aop.part2.airbnbpractice.api.HouseService
import com.android.aop.part2.airbnbpractice.data.repo.HouseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val houseRepository: HouseRepository) :
    ViewModel() {

    private val _mainViewStateLiveData = MutableLiveData<MainViewState>()
    val mainViewStateLiveData: LiveData<MainViewState> = _mainViewStateLiveData

    init {
        getHouseListFromAPI()
    }

    private fun getHouseListFromAPI() {
        houseRepository.getHouseList(isSuccess = { list ->
            val toHouseItem = list.map { it.toHouseItem() }
            _mainViewStateLiveData.value = MainViewState.GetHouseList(toHouseItem)
        }, isFailure = { errorMessage ->
            _mainViewStateLiveData.value = MainViewState.Error(errorMessage)
        })
    }
}
