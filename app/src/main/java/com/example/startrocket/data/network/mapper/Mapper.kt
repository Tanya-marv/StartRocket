package com.example.startrocket.data.network.mapper

import com.example.startrocket.data.network.model.LaunchResponse
import com.example.startrocket.domain.Launch

fun LaunchResponse.toLaunch() : Launch {
    return Launch(
        id = id,
        details = details,
        name = name,
        dateUtc = dateUtc,
        webcast = media.webcast,
        imageUrl = media.patch?.small
    )
}