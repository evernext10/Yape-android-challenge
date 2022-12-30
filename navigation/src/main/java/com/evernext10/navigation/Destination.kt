package com.evernext10.navigation

import android.os.Parcelable
import com.evernext10.core.domain.model.recipes.Recipes
import kotlinx.android.parcel.Parcelize

/**
 * Represents an app screen which the user can navigate to
 */
sealed class Destination() : Parcelable {
    @Parcelize
    object RecipesList : Destination()

    @Parcelize
    class RecipesDetail(val recipes: Recipes) : Destination()

    /**
     * Extends this when origin screen must be known.
     */
    abstract class DestinationWithOrigin(val origin: String) : Destination()
}
