package com.example.homebar.recipesearch.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RecipeDTO(
    @SerializedName("drinks")
    val drinks: List<DrinksDTO>?
)