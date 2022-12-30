package com.evernext10.marketplace.product.list.domain.di

import com.evernext10.marketplace.product.list.domain.usecase.GetListRecipesBySearchUseCase
import org.koin.dsl.module

val recipesListDomainModule = module {

    single {
        GetListRecipesBySearchUseCase(get())
    }
}