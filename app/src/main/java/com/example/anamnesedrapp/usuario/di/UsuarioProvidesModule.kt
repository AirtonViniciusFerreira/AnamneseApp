package com.example.anamnesedrapp.usuario.di

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.anamnesedrapp.MainViewModel
import com.example.anamnesedrapp.config.AppDataBase
import com.example.anamnesedrapp.usuario.repository.dao.UsuarioDao
import com.example.anamnesedrapp.usuario.service.UsuarioService
import com.example.anamnesedrapp.usuario.ui.RegisterFragment
import com.example.anamnesedrapp.usuario.ui.vm.LoginViewModel
import com.example.anamnesedrapp.usuario.ui.vm.RegisterViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@ExperimentalLayoutApi
@ExperimentalMaterial3Api
@Module
@InstallIn(SingletonComponent::class)
class UsuarioProvidesModule {

    @Provides
    fun provideUsuarioDao(
        appDataBase: AppDataBase
    ): UsuarioDao = appDataBase.usuarioDao()

    @Provides
    fun providesUsuarioLoginViewModel(
        usuarioService: UsuarioService
    ): LoginViewModel = LoginViewModel(usuarioService)

    @Provides
    fun providesUsuarioRegisterViewModel(
        usuarioService: UsuarioService
    ): RegisterViewModel = RegisterViewModel(usuarioService)

    @Provides
    fun  providesRegisterFragment(
        registerViewModel: RegisterViewModel
    ): RegisterFragment = RegisterFragment(registerViewModel)
}