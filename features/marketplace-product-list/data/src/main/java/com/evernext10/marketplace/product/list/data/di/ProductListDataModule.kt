package com.evernext10.marketplace.product.list.data.di

import com.evernext10.core.domain.model.product.response.MarketplaceProductListResponse
import com.evernext10.marketplace.product.list.data.remote.network.ProductListService
import com.evernext10.marketplace.product.list.data.repository.MarketplaceProductListRepositoryImpl
import com.evernext10.marketplace.product.list.domain.repository.MarketplaceProductListRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Call
import retrofit2.Retrofit

val productListDataModule = module {
    single<MarketplaceProductListRepository> {
        MarketplaceProductListRepositoryImpl(
            ApiServiceModule(get()),
            androidContext()
        )
    }
}

class ApiServiceModule(retrofit: Retrofit) : ProductListService {
    private val api by lazy { retrofit.create(ProductListService::class.java) }

    override fun getProductsBySearch(
        query: String?,
        limit: Int?
    ): Call<MarketplaceProductListResponse> = api.getProductsBySearch(query, limit)
}
