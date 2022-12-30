package com.evernext10.marketplace.product.list.data.di

import com.evernext10.core.domain.model.recipes.response.MarketplaceRecipesListResponse
import com.evernext10.marketplace.product.list.data.remote.network.RecipesListService
import com.evernext10.marketplace.product.list.data.repository.MarketplaceProductListRepositoryImpl
import com.evernext10.marketplace.product.list.domain.repository.MarketplaceRecipesListRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Call
import retrofit2.Retrofit

val productListDataModule = module {
    single<MarketplaceRecipesListRepository> {
        MarketplaceProductListRepositoryImpl(
            ApiServiceModule(get()),
            androidContext()
        )
    }
}

class ApiServiceModule(retrofit: Retrofit) : RecipesListService {
    private val api by lazy { retrofit.create(RecipesListService::class.java) }

    override fun getRecipesBySearch(): Call<MarketplaceRecipesListResponse> = api.getRecipesBySearch()
}
