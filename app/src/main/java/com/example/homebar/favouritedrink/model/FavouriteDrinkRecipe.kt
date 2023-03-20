package com.example.homebar.favouritedrink.model

import com.example.homebar.recipesearch.ui.UnitAndIngredients

data class FavouriteDrinkRecipe(
    val idDrink: Int?,
    val strDrink: String?,
    val strInstructions: String?,
    val strDrinkThumb: String?,
    val strIngredient1: List<UnitAndIngredients>
)