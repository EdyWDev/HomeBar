package com.example.homebar.recipesearch.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UnitAndIngredients(var unitIngredient: String, var ingredient: String): Parcelable {

    override fun toString() = "$unitIngredient $ingredient"
}
