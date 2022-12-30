package com.evernext10.core.domain.model.recipes.state

import com.evernext10.core.domain.model.recipes.Recipes

sealed class StateRecipesList {
    object Loading : StateRecipesList()
    data class Success(val products: List<Recipes>) : StateRecipesList()
    object Unauthorized : StateRecipesList()
    object Error : StateRecipesList()
}
