package com.example.anamnesedrapp.geral.di

import com.example.anamnesedrapp.geral.repository.PessoaRepository
import com.example.anamnesedrapp.geral.repository.impl.PessoaRepositoryImpl
import com.example.anamnesedrapp.geral.service.PessoaService
import com.example.anamnesedrapp.geral.service.impl.PessoaServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class GeralBindsModule {

    @Binds
    abstract fun bindPessoaRepository(
        pessoaRepositoryImpl: PessoaRepositoryImpl
    ): PessoaRepository

    @Binds
    abstract fun  bindPessoaService(
        pessoaServiceImpl: PessoaServiceImpl
    ): PessoaService
}