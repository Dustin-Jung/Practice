package com.android.aop.part2.airbnbpractice.ui.main

import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.android.aop.part2.airbnbpractice.R
import com.android.aop.part2.airbnbpractice.data.repo.HouseRepositoryImpl
import com.android.aop.part2.airbnbpractice.data.source.HouseRemoteDataSourceImpl
import com.android.aop.part2.airbnbpractice.databinding.ActivityMainBinding
import com.android.aop.part2.airbnbpractice.ui.adapter.HouseRecyclerViewAdapter
import com.android.aop.part2.airbnbpractice.ui.adapter.HouseViewPagerAdapter
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import com.naver.maps.map.util.MarkerIcons
import dagger.hilt.android.AndroidEntryPoint
import java.lang.IllegalArgumentException

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel>()

    lateinit var naverMap: NaverMap
    lateinit var locationSource: FusedLocationSource

    private val houseRecyclerViewAdapter by lazy { HouseRecyclerViewAdapter() }
    private val houseViewPagerAdapter by lazy { HouseViewPagerAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initViewModel()

        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.getMapAsync(this)
        binding.containerBottomSheet.recyclerView.adapter = houseRecyclerViewAdapter
        binding.houseViewPager2.adapter = houseViewPagerAdapter

        binding.houseViewPager2.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                val selectedHouseModel = houseViewPagerAdapter.houseList[position]
                val cameraUpdate =
                    CameraUpdate.scrollTo(LatLng(selectedHouseModel.lat, selectedHouseModel.lng))
                        .animate(CameraAnimation.Easing)

                naverMap.moveCamera(cameraUpdate)
            }
        })
    }

    private fun initViewModel() {
        binding.viewModel = mainViewModel
        mainViewModel.mainViewStateLiveData.observe(this) { viewState ->
            when (viewState) {
                is MainViewState.GetHouseList -> {
                    viewState.list.forEach { model ->
                        val marker = Marker().apply {
                            position = LatLng(model.lat, model.lng)
                            map = naverMap
                            tag = model.id
                            icon = MarkerIcons.BLACK
                            iconTintColor = Color.RED
                        }
                    }
                    houseRecyclerViewAdapter.addAll(viewState.list)
                    houseViewPagerAdapter.addAll(viewState.list)
                }

                is MainViewState.Error -> {
                    //Error 메세지에 대한 화면처리.
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