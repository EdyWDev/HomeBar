package com.example.homebar.recipesearch.service;

import com.example.homebar.recipesearch.service.model.DrinksDTO
import com.example.homebar.recipesearch.service.model.RecipeDTO
import retrofit2.http.GET;
import retrofit2.http.Url;

interface RecipeService {

    //@GET("recipe")
    @GET
    suspend fun getRecipe(@Url url: String) : RecipeDTO
}
