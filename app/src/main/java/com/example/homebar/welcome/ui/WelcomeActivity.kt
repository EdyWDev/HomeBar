package com.example.homebar.welcome.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.homebar.databinding.WelcomeActivityBinding
import com.example.homebar.model.PresenceOfAlcoholCategory.FREE_OF_ALCOHOL
import com.example.homebar.model.PresenceOfAlcoholCategory.WITH_ALCOHOL
import com.example.homebar.navigation.HomeBarNavigationManager.navigateToRecipeSearch
import com.example.homebar.recipesearch.model.RecipeSearchExtraData
import com.example.homebar.welcome.WelcomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WelcomeActivity : AppCompatActivity() {

    private val viewModel: WelcomeViewModel by viewModels()
    private lateinit var binding: WelcomeActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WelcomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.drinkWithAlcoholBT.setOnClickListener {
            navigateToRecipeSearch(
                extraData = RecipeSearchExtraData(
                    WITH_ALCOHOL
                )
            )
        }

        binding.drinkFreeBT.setOnClickListener {
            navigateToRecipeSearch(
                extraData = RecipeSearchExtraData(
                    FREE_OF_ALCOHOL
                )
            )
        }
    }
}
