package com.evernext10.marketplace.product.detail.data.di

import com.evernext10.core.domain.model.recipes.response.MarketplaceRecipesDetailResponse
import com.evernext10.marketplace.product.detail.data.remote.network.RecipesDetailService
import com.evernext10.marketplace.product.detail.data.repository.MarketplaceRecipesDetailRepositoryImpl
import com.evernext10.marketplace.product.detail.domain.repository.MarketplaceRecipesDetailRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Call
import retrofit2.Retrofit

val recipesDetailDataModule = module {
    single<MarketplaceRecipesDetailRepository> {
        MarketplaceRecipesDetailRepositoryImpl(
            ApiServiceModule(get()),
            androidContext()
        )
    }
}

class ApiServiceModule(retrofit: Retrofit) : RecipesDetailService {
    private val api by lazy { retrofit.create(RecipesDetailService::class.java) }

    override fun getProductById(
        id: String?
    ): Call<List<MarketplaceRecipesDetailResponse>> = api.getProductById(id)
}
