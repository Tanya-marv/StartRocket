package com.example.startrocket.domain

data class Launch(
    val id: String,
    val name: String,
    val details: String?,
    val dateUtc: String,
    val webcast: String?,
    val imageUrl: String?
)
