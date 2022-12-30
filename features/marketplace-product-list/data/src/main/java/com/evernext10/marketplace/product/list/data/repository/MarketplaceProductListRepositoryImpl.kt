package com.evernext10.marketplace.product.list.data.repository

import android.content.Context
import com.evernext10.core.app.isNetworkAvailable
import com.evernext10.core.domain.model.recipes.response.MarketplaceRecipesListResponse
import com.evernext10.core.domain.network.Either
import com.evernext10.core.domain.network.Failure
import com.evernext10.core.domain.network.request
import com.evernext10.marketplace.product.list.data.di.ApiServiceModule
import com.evernext10.marketplace.product.list.domain.repository.MarketplaceRecipesListRepository

class MarketplaceProductListRepositoryImpl(
    private val api: ApiServiceModule,
    private val context: Context
) : MarketplaceRecipesListRepository {
    override suspend fun recipesList(): Either<Failure, MarketplaceRecipesListResponse> {
        return when (context.isNetworkAvailable()) {
            true -> {
                request(
                    api.getRecipesBySearch(),
                    { it },
                    MarketplaceRecipesListResponse()
                )
            }
            false -> Either.Left(Failure.NetworkConnection)
        }
    }
}
