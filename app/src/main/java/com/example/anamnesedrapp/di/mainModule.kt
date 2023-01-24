package com.example.anamnesedrapp.di

import com.example.anamnesedrapp.MainActivity
import com.example.anamnesedrapp.MainViewModel
import dagger.hilt.InstallIn
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class mainModule {

    @Provides
    fun providesMainActivity(

    ): MainActivity = MainActivity()

    @Provides
    fun providesMainViewModel(

    ): MainViewModel = MainViewModel()
}