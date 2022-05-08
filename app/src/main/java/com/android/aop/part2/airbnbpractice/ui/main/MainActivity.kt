package com.android.aop.part2.airbnbpractice.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.android.aop.part2.airbnbpractice.R
import com.android.aop.part2.airbnbpractice.databinding.ActivityMainBinding
import com.android.aop.part2.airbnbpractice.ui.HouseRecyclerViewAdapter
import com.android.aop.part2.airbnbpractice.ui.HouseViewPagerAdapter
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.util.FusedLocationSource

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityMainBinding

    lateinit var naverMap: NaverMap
    lateinit var locationSource: FusedLocationSource


    private val mainViewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }

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

        initViewModel()
    }

    private fun initViewModel() {
        mainViewModel.mainViewStateLiveData.observe(this) { viewState ->
            when (viewState) {
                is MainViewState.GetHouseList -> {
                    viewState.markerList.forEach {
                        it.map = naverMap
                    }
                    houseRecyclerViewAdapter.addAll(viewState.houseList)
                    houseViewPagerAdapter.addAll(viewState.houseList)
                }

                is MainViewState.Error -> {
                    //todo
                }
            }
        }
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
        const val BASE_URL_HOUSE = "https://run.mocky.io/"
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