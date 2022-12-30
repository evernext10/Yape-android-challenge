package com.evernext10.core.domain.model.recipes.response

import com.evernext10.core.domain.model.recipes.Recipes
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MarketplaceRecipesDetailResponse(
    @Json(name = "code")
    val code: Int = 0,
    @Json(name = "body")
    val body: Recipes = Recipes()
)
