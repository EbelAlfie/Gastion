package com.example.gastion.ui.main

import android.location.Location
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.gastion.data.GasRepository
import com.example.gastion.data.GasRepositoryImpl
import com.example.gastion.data.LocationRepository
import com.example.gastion.data.LocationRepositoryImpl
import com.example.gastion.data.di.WebSocketModule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import javax.inject.Inject

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @Mock
    private lateinit var locationRepository: LocationRepository

    @Mock
    private lateinit var gasRepository: GasRepository

    private lateinit var viewModel: MainViewModel
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = ViewModelProvider(ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)
        viewModel.locationRepository = locationRepository
        viewModel.gasRepository = gasRepository
    }

    @Test
    fun `test requestLocationUpdate should update userLocation and nearestGasStations`() = runBlockingTest {
        val mockLocation = Location("network")
        mockLocation.latitude = 1.0
        mockLocation.longitude = 1.0

        val mockNearestGasStations = arrayListOf<Location>()

        Mockito.`when`(locationRepository.getCurrentLocation(Mockito.any()))
            .thenReturn(flowOf(mockLocation))

        Mockito.`when`(gasRepository.getNearestGasStation(mockLocation, 0.03))
            .thenReturn(mockNearestGasStations)

        viewModel.requestLocationUpdate()

        val userLocation = viewModel.userLocation.first()
        val nearestGasStations = viewModel.nearestGasStations.first()

        assert(userLocation?.latitude == mockLocation.latitude)
        assert(userLocation?.longitude == mockLocation.longitude)
        assert(nearestGasStations == mockNearestGasStations)
    }

    @Test
    fun `test operateLocation should update userLocation and nearestGasStations if location changes`() = runBlockingTest {
        val mockLocation1 = Location("network")
        mockLocation1.latitude = 1.0
        mockLocation1.longitude = 1.0

        val mockLocation2 = Location("network")
        mockLocation2.latitude = 4.0
        mockLocation2.longitude = 4.0

        val mockNearestGasStations = arrayListOf<Location>()

        Mockito.`when`(gasRepository.getNearestGasStation(mockLocation2, 0.03))
            .thenReturn(mockNearestGasStations)

        viewModel.userLocation.value = mockLocation1
        viewModel.operateLocation(mockLocation2)

        val userLocation = viewModel.userLocation.first()
        val nearestGasStations = viewModel.nearestGasStations.first()

        assert(userLocation?.latitude == mockLocation2.latitude)
        assert(userLocation?.longitude == mockLocation2.longitude)
        assert(nearestGasStations == mockNearestGasStations)
    }

    @Test
    fun `test operateLocation should not update userLocation and nearestGasStations if location does not change`() = runBlockingTest {
        val mockLocation = Location("network")
        mockLocation.latitude = 1.0
        mockLocation.longitude = 1.0

        val mockNearestGasStations = arrayListOf<Location>()

        Mockito.`when`(gasRepository.getNearestGasStation(mockLocation, 0.03))
            .thenReturn(mockNearestGasStations)

        viewModel.userLocation.value = mockLocation
        viewModel.operateLocation(mockLocation)

        val userLocation = viewModel.userLocation.first()
        val nearestGasStations = viewModel.nearestGasStations.first()

        assert(userLocation?.latitude == mockLocation.latitude)
        assert(userLocation?.longitude == mockLocation.longitude)
        assert(nearestGasStations == mockNearestGasStations)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}