package com.evernext10.marketplace.product.list.data.remote.network

import com.evernext10.core.domain.model.recipes.response.MarketplaceRecipesListResponse
import retrofit2.Call
import retrofit2.http.GET

interface RecipesListService {
    @GET("/recipes")
    fun getRecipesBySearch(): Call<MarketplaceRecipesListResponse>
}
