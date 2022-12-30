package com.evernext10.marketplace.product.list.domain.repository

import com.evernext10.core.domain.model.recipes.response.MarketplaceRecipesListResponse
import com.evernext10.core.domain.network.Either
import com.evernext10.core.domain.network.Failure

interface MarketplaceRecipesListRepository {
    suspend fun recipesList(): Either<Failure, MarketplaceRecipesListResponse>
}
