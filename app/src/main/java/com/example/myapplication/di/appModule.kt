package com.example.myapplication.di

import com.example.myapplication.mapper.NumberDtoMapper
import com.example.myapplication.network.ApiService
import com.example.myapplication.network.number.NumberDataFetcher
import com.example.myapplication.repository.NumberRepository
import com.example.myapplication.repository.NumberRepositoryImpl
import com.example.myapplication.ui.home.HomeViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

val applicationModule = module {
    //OkHttp3
    single {
        OkHttpClient.Builder()
            .callTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .retryOnConnectionFailure(false)
            .build()
    }
    //ApiService
    single { ApiService(get()) }

    //DataFetchers
    single { NumberDataFetcher(get()) }

    //Mappers
    single { NumberDtoMapper() }

    //Repositories
    single<NumberRepository> { NumberRepositoryImpl(get(), get()) }

    //ViewModels
    viewModel { HomeViewModel(get()) }
}