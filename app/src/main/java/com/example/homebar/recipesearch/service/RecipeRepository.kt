package com.example.homebar.recipesearch.service

import com.example.homebar.recipesearch.model.Drinks
import com.example.homebar.recipesearch.model.Recipe
import com.example.homebar.recipesearch.service.model.RecipeDTO

class HomeBarRepository(
    private val homeBarService: RecipeService
) {
    suspend fun getRecipeByCocktailName(inputPart: String):Recipe =
        homeBarService.getRecipe("$COCKTAIL_BY_NAME$inputPart").toDomainModel()
}


fun RecipeDTO.toDomainModel(): Recipe {
    return Recipe(drinks = this.drinks?.map {
        Drinks(
            idDrink = it.idDrink,
            strDrink = it.strDrink,
            strDrinkAlternate = it.strDrinkAlternate,
            strTags = it.strTags,
            strVideo = it.strVideo,
            strCategory = it.strCategory,
            strIBA = it.strIBA,
            strAlcoholic = it.strAlcoholic,
            strGlass = it.strGlass,
            strInstructions = it.strInstructions,
            strInstructionsES = it.strInstructionsES,
            strInstructionsDE = it.strInstructionsDE,
            strInstructionsFR = it.strInstructionsFR,
            strInstructionsIT = it.strInstructionsIT,
            strDrinkThumb = it.strDrinkThumb,
            strIngredient1 = it.strIngredient1,
            strIngredient2 = it.strIngredient2,
            strIngredient3 = it.strIngredient3,
            strIngredient4 = it.strIngredient4,
            strIngredient5 = it.strIngredient5,
            strIngredient6 = it.strIngredient6,
            strIngredient7 = it.strIngredient7,
            strIngredient8 = it.strIngredient8,
            strIngredient9 = it.strIngredient9,
            strIngredient10 = it.strIngredient10,
            strIngredient11 = it.strIngredient11,
            strIngredient12 = it.strIngredient12,
            strIngredient13 = it.strIngredient13,
            strIngredient14 = it.strIngredient14,
            strIngredient15 = it.strIngredient15,
            strMeasure1 = it.strMeasure1,
            strMeasure2 = it.strMeasure2,
            strMeasure3 = it.strMeasure3,
            strMeasure4 = it.strMeasure4,
            strMeasure5 = it.strMeasure5,
            strMeasure6 = it.strMeasure6,
            strMeasure7 = it.strMeasure7,
            strMeasure8 = it.strMeasure8,
            strMeasure9 = it.strMeasure9,
            strMeasure10 = it.strMeasure10,
            strMeasure11 = it.strMeasure11,
            strMeasure12 = it.strMeasure12,
            strMeasure13 = it.strMeasure13,
            strMeasure14 = it.strMeasure14,
            strMeasure15 = it.strMeasure15,
            strImageSource = it.strImageSource,
            strImageAttribution = it.strImageAttribution,
            strCreativeCommonsConfirmed = it.strCreativeCommonsConfirmed,
            dateModified = it.dateModified
        )
    })
}


const val COCKTAIL_BY_NAME = "search.php?s="
