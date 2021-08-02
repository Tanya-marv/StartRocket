package com.example.startrocket.data.local.mapper

import com.example.startrocket.data.local.model.LaunchRoom
import com.example.startrocket.domain.Launch

fun Launch.toRoom(): LaunchRoom {
    return LaunchRoom(
        id = id,
        details = details,
        name = name,
        dateUtc = dateUtc,
        webcast = webcast,
        imageUrl = imageUrl
    )
}

fun LaunchRoom.toLaunch(): Launch {
    return Launch(
        id = id,
        details = details,
        name = name,
        dateUtc = dateUtc,
        webcast = webcast,
        imageUrl = imageUrl
    )
}
