package com.example.homebar.recipesearch.service;

import com.example.homebar.recipesearch.service.model.DrinksDTO
import retrofit2.http.GET;
import retrofit2.http.Url;

interface RecipeService {

    //@GET("recipe")
    @GET
   suspend fun getRecipe(@Url url: String) : DrinksDTO
   /* @GET("search.php?s=blue")
    suspend fun getRecipe() : RecipeDTO*/
   @GET
   suspend fun getRecipeById(@Url url: String) : DrinksDTO

}
