package com.example.startrocket.data.network.api

import com.example.startrocket.data.network.model.LaunchResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface SpaceXApi {

    @GET("launches/past")
    suspend fun fetchPastLaunches(): List<LaunchResponse>

    @GET("launches/{id}")
    suspend fun fetchPastLaunchDetailsById(
        @Path("id") id: String
    ): LaunchResponse
}