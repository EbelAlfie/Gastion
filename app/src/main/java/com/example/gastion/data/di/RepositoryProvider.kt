package com.example.gastion.data.di

import com.example.gastion.data.GasRepository
import com.example.gastion.data.GasRepositoryImpl
import com.example.gastion.data.LocationRepository
import com.example.gastion.data.LocationRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryProvider {

  @Binds
  fun provideLocationRepo(locationRepository: LocationRepositoryImpl): LocationRepository

  @Binds
  fun provideGasRepo(gasRepository: GasRepositoryImpl): GasRepository
}