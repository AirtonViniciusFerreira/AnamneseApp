package com.example.anamnesedrapp.tutor.di

import com.example.anamnesedrapp.tutor.repository.TutorRepository
import com.example.anamnesedrapp.tutor.repository.impl.TutorRepositoryImpl
import com.example.anamnesedrapp.tutor.service.TutorService
import com.example.anamnesedrapp.tutor.service.impl.TutorServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class TutorBindsModule {

    @Binds
    abstract fun bindTutorRepository(
        tutorRepositoryImpl: TutorRepositoryImpl
    ): TutorRepository

    @Binds
    abstract  fun bindTutorService(
        tutorServiceImpl: TutorServiceImpl
    ): TutorService
}