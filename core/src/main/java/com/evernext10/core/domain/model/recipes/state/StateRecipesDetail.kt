package com.evernext10.core.domain.model.recipes.state

import com.evernext10.core.domain.model.recipes.Recipes

sealed class StateRecipesDetail {
    object Loading : StateRecipesDetail()
    data class Success(val product: Recipes) : StateRecipesDetail()
    object Unauthorized : StateRecipesDetail()
    object Error : StateRecipesDetail()
}
