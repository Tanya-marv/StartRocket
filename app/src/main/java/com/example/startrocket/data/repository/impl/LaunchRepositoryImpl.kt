package com.example.startrocket.data.repository.impl

import com.example.startrocket.data.local.dao.LaunchDao
import com.example.startrocket.data.local.mapper.toLaunch
import com.example.startrocket.data.local.mapper.toRoom
import com.example.startrocket.data.network.SpaceXDataSource
import com.example.startrocket.data.repository.LaunchRepository
import com.example.startrocket.domain.Launch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LaunchRepositoryImpl(
    private val dataSource: SpaceXDataSource,
    private val launchDao: LaunchDao
) : LaunchRepository {

    override val pastLaunches: Flow<List<Launch>> = launchDao.getAllLaunchesFlow()
        .map { launches ->
            launches.map { launch ->
                launch.toLaunch()
            }
        }

    override suspend fun retrievePastLaunchDetailsById(id: String): Launch {
        return dataSource.fetchPastLaunchDetailsById(id)
    }

    override suspend fun retrievePastLaunches() {
        val launches = dataSource.fetchPastLaunches()
        launchDao.addLaunches(launches.map { it.toRoom() })
    }

    override suspend fun delete(launch: Launch) {
        launchDao.deleteLaunch(launch.toRoom())
    }
}