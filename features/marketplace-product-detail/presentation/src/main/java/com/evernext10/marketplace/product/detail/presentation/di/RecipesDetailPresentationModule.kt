package com.evernext10.marketplace.product.detail.presentation.di

import com.evernext10.marketplace.product.detail.presentation.ui.RecipesDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val recipesDetailPresentationModule = module {
    viewModel {
        RecipesDetailViewModel(
            get(),
            get()
        )
    }
}
