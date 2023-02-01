package com.example.homebar.navigation

import android.app.Activity
import android.content.Intent
import com.example.homebar.model.ExtraDataConst
import com.example.homebar.recipedetails.model.DetailsExtraData
import com.example.homebar.recipedetails.model.ExtraDataConstDetails
import com.example.homebar.recipedetails.ui.RecipeDetailsActivity
import com.example.homebar.recipesearch.model.RecipeSearchExtraData
import com.example.homebar.recipesearch.ui.RecipeSearchActivity

object HomeBarNavigationManager {

    fun Activity.navigateToRecipeSearch(
        extraData: RecipeSearchExtraData
    ) {
        Intent(this, RecipeSearchActivity::class.java).apply {
            this.putExtra(ExtraDataConst.EXTRA_DATA_RECIPE_SEARCH, extraData)
            startActivity(this)
        }
    }

    fun Activity.navigateToRecipeDetails(
        extraData: DetailsExtraData
    ) {
        Intent(this, RecipeDetailsActivity::class.java).apply {
            this.putExtra(ExtraDataConstDetails.EXTRA_DATA_RECIPE_DETAILS, extraData)
            startActivity(this)
        }
    }
}
