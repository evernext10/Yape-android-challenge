package com.evernext10.marketplace.product.list.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evernext10.core.domain.model.recipes.response.MarketplaceRecipesListResponse
import com.evernext10.core.domain.model.recipes.state.StateRecipesList
import com.evernext10.core.domain.network.Failure
import com.evernext10.marketplace.product.list.domain.usecase.GetListRecipesBySearchUseCase

class RecipesListScreenViewModel constructor(
    private val useCase: GetListRecipesBySearchUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _recipesListState: MutableLiveData<StateRecipesList> = MutableLiveData()
    val recipesListState: LiveData<StateRecipesList> = _recipesListState

    private val _randomWord: MutableLiveData<String> = MutableLiveData()
    val randomWord: LiveData<String> = _randomWord

    private var _queryWord: MutableLiveData<String> = MutableLiveData()
    val queryWord: LiveData<String> = _queryWord

    fun getDataFromStateHandled() {
        if (savedStateHandle.contains(KEY_STATE)) {
            _recipesListState.postValue(StateRecipesList.Success(savedStateHandle[KEY_STATE]!!))
        }
    }

    fun getRandomWordToShowInitialData() {
        _randomWord.postValue(RANDOM_LIST_ITEMS.random())
    }

    fun getMarketplaceProductList(search: String, limit: Int = 30) {
        useCase(
            GetListRecipesBySearchUseCase.Params(search, limit),
            viewModelScope
        ) {
            it.fold(
                ::handleFailure,
                ::handleSuccess
            )
        }
    }

    fun postQueryWord(query: String) = _queryWord.postValue(query)

    private fun handleSuccess(response: MarketplaceRecipesListResponse) {
        savedStateHandle[KEY_STATE] = response.results
        _recipesListState.postValue(StateRecipesList.Success(response.results))
    }

    private fun handleFailure(failure: Failure) {
        _recipesListState.postValue(StateRecipesList.Error)
    }

    companion object {
        val RANDOM_LIST_ITEMS = listOf("TXL", "PS5", "Teclado", "PC", "iphone14")
        const val KEY_STATE = "saved_product_list"
    }
}
