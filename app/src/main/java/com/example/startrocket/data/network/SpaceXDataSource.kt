package com.example.startrocket.data.network

import com.example.startrocket.domain.Launch

interface SpaceXDataSource {

    suspend fun fetchPastLaunches(): List<Launch>

    suspend fun fetchPastLaunchDetailsById(id: String): Launch
}