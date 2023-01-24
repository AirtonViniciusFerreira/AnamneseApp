package com.example.anamnesedrapp.usuario.di

import android.content.Context
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavController
import androidx.room.Room
import com.example.anamnesedrapp.MainActivity
import com.example.anamnesedrapp.MainViewModel
import com.example.anamnesedrapp.config.AppDataBase
import com.example.anamnesedrapp.usuario.repository.dao.UsuarioDao
import com.example.anamnesedrapp.usuario.service.UsuarioService
import com.example.anamnesedrapp.usuario.ui.HomeFragment
import com.example.anamnesedrapp.usuario.ui.LoginFragment
import com.example.anamnesedrapp.usuario.ui.RegisterFragment
import com.example.anamnesedrapp.usuario.ui.vm.LoginViewModel
import com.example.anamnesedrapp.usuario.ui.vm.RegisterViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@ExperimentalLayoutApi
@ExperimentalMaterial3Api
@Module
@InstallIn(SingletonComponent::class)
class UsuarioDaoModule {

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
    fun providesLoginFragment(
        loginViewModel: LoginViewModel,
        mainViewModel: MainViewModel
    ): LoginFragment =
        LoginFragment(loginViewModel, mainViewModel)
//        LoginFragment()

    @Provides
    fun  providesRegisterFragment(
        registerViewModel: RegisterViewModel
    ): RegisterFragment = RegisterFragment(registerViewModel)

    @Provides
    fun providesHomeFragment(
        mainViewModel: MainViewModel
    ): HomeFragment = HomeFragment()

}