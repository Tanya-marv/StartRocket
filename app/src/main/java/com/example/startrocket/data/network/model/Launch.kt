package com.example.startrocket.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LaunchResponse(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "details") val details: String?,
    @Json(name = "date_utc") val dateUtc: String,
    @Json(name = "links") val media: Media
    ) {
    @JsonClass(generateAdapter = true)
    data class Media(
        @Json(name = "webcast") val webcast: String?,
        @Json(name = "patch") val patch: Image?
    )
    @JsonClass(generateAdapter = true)
    data class Image(
        @Json(name = "small") val small: String?
    )
}