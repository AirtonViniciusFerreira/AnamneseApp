package com.example.anamnesedrapp.di

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.anamnesedrapp.MainActivity
import com.example.anamnesedrapp.MainViewModel
import dagger.hilt.InstallIn
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent

@ExperimentalLayoutApi
@ExperimentalMaterial3Api
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