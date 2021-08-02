package com.example.startrocket.di

import com.example.startrocket.presentation.screen.launches.LaunchesViewModel
import com.example.startrocket.presentation.screen.launchesDetails.LaunchDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { LaunchesViewModel(repository = get()) }

    viewModel { LaunchDetailsViewModel(repository = get(), launchId = get()) }
}