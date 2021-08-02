package com.example.startrocket.data.network.impl

import android.util.Log
import com.example.startrocket.data.network.SpaceXDataSource
import com.example.startrocket.data.network.api.SpaceXApi
import com.example.startrocket.data.network.mapper.toLaunch
import com.example.startrocket.domain.Launch

class SpaceXDataSourceImpl(
    private val api: SpaceXApi
) : SpaceXDataSource {

    override suspend fun fetchPastLaunches(): List<Launch> {
        return try {
            api.fetchPastLaunches().map {
                it.toLaunch()
            }
        } catch (e: Exception) {
            Log.e(javaClass.simpleName, e.localizedMessage)
            emptyList()
        }
    }

    override suspend fun fetchPastLaunchDetailsById(id: String): Launch {
        return api.fetchPastLaunchDetailsById(id).toLaunch()
    }
}