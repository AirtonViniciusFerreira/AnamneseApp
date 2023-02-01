package com.example.anamnesedrapp.geral.di

import com.example.anamnesedrapp.config.AppDataBase
import com.example.anamnesedrapp.geral.repository.dao.PessoaDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class GeralProvidesModule {

    @Provides
    fun providePessoaDao(
        appDataBase: AppDataBase
    ): PessoaDao = appDataBase.pessoaDao()
}