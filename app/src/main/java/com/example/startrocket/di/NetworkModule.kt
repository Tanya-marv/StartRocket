package com.example.startrocket.di

import com.example.startrocket.BuildConfig
import com.example.startrocket.data.network.SpaceXDataSource
import com.example.startrocket.data.network.api.SpaceXApi
import com.example.startrocket.data.network.impl.SpaceXDataSourceImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {

    single(named("BaseUrl")) { BuildConfig.SPACE_X_BASE_URL }

    single {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(get<String>(named("BaseUrl")))
            .build()
            .create(SpaceXApi::class.java)
    }

    single<SpaceXDataSource> {
        SpaceXDataSourceImpl(api = get())
    }
}