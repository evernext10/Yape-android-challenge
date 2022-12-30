package com.evernext10.core.domain.model.recipes

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Recipes(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "image")
    val image: String? = null,
    @Json(name = "average_cost")
    val average_cost: Int? = null,
    @Json(name = "average_time")
    val average_time: Int? = null,
    @Json(name = "type")
    val type: String? = null,
    @Json(name = "description")
    val description: String? = null,
    @Json(name = "location")
    val location: Location? = null,
    @Json(name = "pictures")
    val pictures: List<Picture>? = null
) : Parcelable
