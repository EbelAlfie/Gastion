package com.example.gastion.di

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
  fun provideLocationRep(locationRepository: LocationRepositoryImpl): LocationRepository

}