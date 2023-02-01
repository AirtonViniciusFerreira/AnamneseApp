package com.example.anamnesedrapp.usuario.di

import com.example.anamnesedrapp.usuario.repository.UsuarioRepository
import com.example.anamnesedrapp.usuario.repository.impl.UsuarioRepositoryImpl
import com.example.anamnesedrapp.usuario.service.UsuarioService
import com.example.anamnesedrapp.usuario.service.impl.UsuarioServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UsuarioBindsModule {

    @Binds
    abstract fun bindUsuarioService(
        usuarioServiceImpl: UsuarioServiceImpl
    ): UsuarioService

    @Binds
    abstract fun bindUsuarioRepository(
        usuarioRepositoryImpl: UsuarioRepositoryImpl
    ): UsuarioRepository
}