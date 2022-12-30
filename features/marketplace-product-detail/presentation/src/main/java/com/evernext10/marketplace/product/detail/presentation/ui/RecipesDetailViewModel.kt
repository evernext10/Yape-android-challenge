package com.evernext10.marketplace.product.detail.presentation.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evernext10.core.domain.model.recipes.Recipes
import com.evernext10.core.domain.model.recipes.response.MarketplaceRecipesDetailResponse
import com.evernext10.core.domain.model.recipes.state.StateRecipesDetail
import com.evernext10.core.domain.network.Failure
import com.evernext10.marketplace.product.detail.domain.usecase.GetDetailRecipesByIdUseCase

class RecipesDetailViewModel(
    private val useCase: GetDetailRecipesByIdUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _recipesDetailState: MutableLiveData<StateRecipesDetail> = MutableLiveData()
    val recipesDetailState: LiveData<StateRecipesDetail> = _recipesDetailState

    fun getDataFromStateHandled(recipes: Recipes) {
        if (savedStateHandle.contains(KEY_STATE)) {
            _recipesDetailState.postValue(StateRecipesDetail.Success(savedStateHandle[KEY_STATE]!!))
        } else {
            getMarketplaceProductDetail(recipes)
        }
    }

    private fun getMarketplaceProductDetail(recipes: Recipes) {
        useCase(
            GetDetailRecipesByIdUseCase.Params(recipes),
            viewModelScope
        ) {
            it.fold(
                ::handleFailure,
                ::handleSuccess
            )
        }
    }

    private fun handleSuccess(response: MarketplaceRecipesDetailResponse) {
        savedStateHandle[KEY_STATE] = response.body
        _recipesDetailState.postValue(StateRecipesDetail.Success(response.body))
    }

    private fun handleFailure(failure: Failure) {
        Log.i("Error", failure.toString())
        _recipesDetailState.postValue(StateRecipesDetail.Error)
    }

    companion object {
        const val KEY_STATE = "saved_recipes_detail"
    }
}
