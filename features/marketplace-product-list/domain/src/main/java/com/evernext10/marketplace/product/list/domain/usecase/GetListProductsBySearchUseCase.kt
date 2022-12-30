package com.evernext10.marketplace.product.list.domain.usecase

import com.evernext10.core.domain.model.recipes.response.MarketplaceRecipesListResponse
import com.evernext10.core.domain.network.Either
import com.evernext10.core.domain.network.Failure
import com.evernext10.core.domain.network.UseCase
import com.evernext10.marketplace.product.list.domain.repository.MarketplaceRecipesListRepository

class GetListProductsBySearchUseCase constructor(
    private val mercadoLibreRepository: MarketplaceRecipesListRepository
) : UseCase<MarketplaceRecipesListResponse, GetListProductsBySearchUseCase.Params>() {
    override suspend fun run(params: Params): Either<Failure, MarketplaceRecipesListResponse> {
        return mercadoLibreRepository.recipesList()
    }

    data class Params(val search: String?, val limit: Int)
}
