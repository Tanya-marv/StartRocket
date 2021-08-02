package com.example.startrocket.di

import com.example.startrocket.data.repository.LaunchRepository
import com.example.startrocket.data.repository.impl.LaunchRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    single<LaunchRepository> { LaunchRepositoryImpl(dataSource = get(), launchDao = get()) }
}