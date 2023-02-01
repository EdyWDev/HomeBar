package com.example.homebar.recipedetails.ui

import android.database.Observable
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.homebar.databinding.RecipeDetailsActivityBinding
import com.example.homebar.recipedetails.RecipeDetailsViewModel
import com.example.homebar.recipesearch.model.Recipe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeDetailsActivity: AppCompatActivity() {

    private val viewModel: RecipeDetailsViewModel by viewModels()
    private lateinit var binding: RecipeDetailsActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RecipeDetailsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageObserver = Observer<String> { value ->
            Glide.with(binding.root.context)
                .load(value)
                .into(binding.detailedDrinkImage)

        }
        viewModel.image.observe(this, imageObserver)

        val drinkNameObserver = Observer<String> { value ->
            binding.detailedDrinkNameTV.text = value
        }
        viewModel.drinkName.observe(this, drinkNameObserver)

        val detailedDrinkRecipeAndIngredientsObserver = Observer<String>{value ->
            binding.detailedDrinkRecipeAndIngredients.text = value

        }
        viewModel.detailedDrink.observe(this, detailedDrinkRecipeAndIngredientsObserver)

    }
}
