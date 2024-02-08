package com.example.myapplication.di

import androidx.room.Room
import com.example.myapplication.db.MyDatabase
import com.example.myapplication.mapper.HistoryEntityMapper
import com.example.myapplication.mapper.NumberDtoMapper
import com.example.myapplication.network.ApiService
import com.example.myapplication.network.number.NumberDataFetcher
import com.example.myapplication.repository.history.HistoryRepository
import com.example.myapplication.repository.history.HistoryRepositoryImpl
import com.example.myapplication.repository.number.NumberRepository
import com.example.myapplication.repository.number.NumberRepositoryImpl
import com.example.myapplication.ui.home.HomeViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

const val dbName = "dbName"

val applicationModule = module {
    //Room
    single {
        Room.databaseBuilder(
            androidApplication(),
            MyDatabase::class.java,
            dbName
        ).build()
    }

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
    single { HistoryEntityMapper() }

    //Repositories
    single<NumberRepository> { NumberRepositoryImpl(get(), get()) }
    single<HistoryRepository> { HistoryRepositoryImpl(get(), get()) }

    //ViewModels
    viewModel { HomeViewModel(get(), get()) }
}