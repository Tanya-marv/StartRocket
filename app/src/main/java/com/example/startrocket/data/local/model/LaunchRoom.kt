package com.example.startrocket.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "launches")
data class LaunchRoom(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "details") val details: String?,
    @ColumnInfo(name = "date_utc") val dateUtc: String,
    @ColumnInfo(name = "webcast") val webcast: String?,
    @ColumnInfo(name = "image_url") val imageUrl: String?
)
