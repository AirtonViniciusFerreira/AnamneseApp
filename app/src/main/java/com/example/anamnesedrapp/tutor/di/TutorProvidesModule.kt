package com.example.anamnesedrapp.tutor.di

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.anamnesedrapp.config.AppDataBase
import com.example.anamnesedrapp.tutor.repository.dao.TutorDao
import com.example.anamnesedrapp.tutor.service.TutorService
import com.example.anamnesedrapp.tutor.ui.vm.TutorViewModel
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.Module
import dagger.Provides

@ExperimentalLayoutApi
@ExperimentalMaterial3Api
@Module
@InstallIn(SingletonComponent::class)
class TutorProvidesModule {

    @Provides
    fun provideTutorDao(
        appDataBase: AppDataBase
    ): TutorDao = appDataBase.tutorDao()

    @Provides
    fun provideTutorViewModel(
        service: TutorService
    ): TutorViewModel = TutorViewModel(service)
}