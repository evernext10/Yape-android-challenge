package com.evernext10.marketplace.product.detail.domain.di

import com.evernext10.marketplace.product.detail.domain.usecase.GetDetailRecipesByIdUseCase
import org.koin.dsl.module

val recipesDetailDomainModule = module {
    single {
        GetDetailRecipesByIdUseCase(get())
    }
}
