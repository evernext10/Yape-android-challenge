package com.evernext10.marketplace.product.list.domain.model

import com.evernext10.core.domain.model.recipes.Picture
import com.evernext10.core.domain.model.recipes.Recipes
import com.evernext10.core.domain.model.recipes.response.MarketplaceRecipesListResponse

fun productListResponse() = MarketplaceRecipesListResponse(
    site_id = "",
    query = "",
    results = listOf(
        Recipes(
            id = "ASDASD",
            title = "TX",
            price = 1626565,
            currencyId = "COP",
            availableQuantity = 5,
            soldQuantity = 3,
            thumbnail = "",
            condition = "",
            sold = 0,
            pictures = listOf(
                Picture(
                    id = "ASDASD",
                    url = "https://http2.mlstatic.com/D_612440-MCO52044933780_102022-I.jpg"
                )
            )
        )
    )
)
