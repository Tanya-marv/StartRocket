package com.example.startrocket.data.repository

import com.example.startrocket.domain.Launch
import kotlinx.coroutines.flow.Flow


interface LaunchRepository {

    val pastLaunches: Flow<List<Launch>>

    suspend fun retrievePastLaunchDetailsById(id: String): Launch

    suspend fun retrievePastLaunches()

    suspend fun delete(launch: Launch)
}