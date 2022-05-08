package com.android.aop.part2.airbnbpractice.ui.main

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.android.aop.part2.airbnbpractice.R
import com.android.aop.part2.airbnbpractice.data.HouseDto
import com.android.aop.part2.airbnbpractice.data.HouseService
import com.android.aop.part2.airbnbpractice.databinding.ActivityMainBinding
import com.android.aop.part2.airbnbpractice.ui.HouseRecyclerViewAdapter
import com.android.aop.part2.airbnbpractice.ui.HouseViewPagerAdapter
import com.android.aop.part2.airbnbpractice.uitl.RetrofitModule
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import com.naver.maps.map.util.MarkerIcons
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityMainBinding

    lateinit var naverMap: NaverMap
    lateinit var locationSource: FusedLocationSource


    private val houseRecyclerViewAdapter by lazy { HouseRecyclerViewAdapter() }
    private val houseViewPagerAdapter by lazy { HouseViewPagerAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)
        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.getMapAsync(this)

        binding.containerBottomSheet.rvHouse.adapter = houseRecyclerViewAdapter
        binding.houseViewPager2.adapter = houseViewPagerAdapter
    }

    override fun onMapReady(map: NaverMap) {

        naverMap = map

        naverMap.maxZoom = 20.0
        naverMap.minZoom = 10.0

        val cameraUpdate = CameraUpdate.scrollTo(LatLng(37.498095, 127.027610))
        naverMap.moveCamera(cameraUpdate)

        val uiSetting = naverMap.uiSettings
        uiSetting.isLocationButtonEnabled = false

        binding.currentLocationButton.map = naverMap

        locationSource = FusedLocationSource(this@MainActivity, 100)
        naverMap.locationSource = locationSource

        getHouseListFromAPI()
    }

    private fun getHouseListFromAPI() {

        val houseService = RetrofitModule.create<HouseService>(baseUrl = BASE_URL_HOUSE)

        houseService.getHouseList().enqueue(object : Callback<HouseDto> {
            override fun onResponse(call: Call<HouseDto>, response: Response<HouseDto>) {
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


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return
        }

        if (locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
            if (!locationSource.isActivated) {
                naverMap.locationTrackingMode = LocationTrackingMode.None
            }
            return
        }
    }

    companion object {
        private const val BASE_URL_HOUSE = "https://run.mocky.io/"
        private const val LOCATION_PERMISSION_REQUEST_CODE = 100
    }


    override fun onStart() {
        super.onStart()
        binding.mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.mapView.onSaveInstanceState(outState)
    }

    override fun onStop() {
        super.onStop()
        binding.mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }

}