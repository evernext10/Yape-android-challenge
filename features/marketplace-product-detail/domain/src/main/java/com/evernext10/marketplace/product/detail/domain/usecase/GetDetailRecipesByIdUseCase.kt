package com.evernext10.marketplace.product.detail.domain.usecase

import com.evernext10.core.domain.model.recipes.Recipes
import com.evernext10.core.domain.model.recipes.response.MarketplaceRecipesDetailResponse
import com.evernext10.core.domain.network.Either
import com.evernext10.core.domain.network.Failure
import com.evernext10.core.domain.network.UseCase
import com.evernext10.marketplace.product.detail.domain.repository.MarketplaceRecipesDetailRepository

class GetDetailRecipesByIdUseCase constructor(
    private val mercadoLibreRepository: MarketplaceRecipesDetailRepository
) : UseCase<MarketplaceRecipesDetailResponse, GetDetailRecipesByIdUseCase.Params>() {
    override suspend fun run(params: Params): Either<Failure, MarketplaceRecipesDetailResponse> {
        return mercadoLibreRepository.recipesDetail(params.item)
    }

    data class Params(val item: Recipes?)
}
