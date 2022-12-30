package com.evernext10.marketplace.product.list.presentation.di

import com.evernext10.marketplace.product.list.presentation.ui.RecipesListScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val recipesListPresentationModule = module {
    viewModel {
        RecipesListScreenViewModel(
            get(),
            get()
        )
    }
}
