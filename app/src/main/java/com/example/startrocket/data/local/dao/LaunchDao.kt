package com.example.startrocket.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.startrocket.data.local.model.LaunchRoom
import kotlinx.coroutines.flow.Flow

@Dao
interface LaunchDao {

    @Query("SELECT * FROM launches")
    suspend fun getAllLaunches(): List<LaunchRoom>

    @Query("SELECT * FROM launches")
    fun getAllLaunchesFlow(): Flow<List<LaunchRoom>>

    @Query("SELECT DISTINCT * FROM launches")
    suspend fun getUniqueLaunches(): List<LaunchRoom>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addLaunches(launches: List<LaunchRoom>)

    @Delete
    suspend fun deleteLaunch(launchRoom: LaunchRoom)
}