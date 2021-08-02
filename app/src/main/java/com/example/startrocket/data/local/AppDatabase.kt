package com.example.startrocket.data.local

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.startrocket.data.local.dao.LaunchDao
import com.example.startrocket.data.local.model.LaunchRoom

@Database(
    entities = [
        LaunchRoom::class
    ],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun launchDao(): LaunchDao

    companion object {
        private const val dbName = "launches_db"
        private var instance: AppDatabase? = null

        fun getInstance(app: Application): AppDatabase {
            if (instance == null) {
                synchronized(AppDatabase::class.java) {
                    instance = createDataBase(app)
                }
            }
            return instance!!
        }

        private fun createDataBase(app: Application): AppDatabase {
            return Room.databaseBuilder(app.applicationContext, AppDatabase::class.java, dbName)
                .build()
        }
    }
}