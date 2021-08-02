package com.example.startrocket.di

import com.example.startrocket.data.local.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    single { AppDatabase.getInstance(androidApplication()) }

    single { get<AppDatabase>().launchDao() }
}