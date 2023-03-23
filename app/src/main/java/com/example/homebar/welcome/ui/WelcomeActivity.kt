package com.example.homebar.welcome.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.homebar.R
import com.example.homebar.databinding.WelcomeActivityBinding
import com.example.homebar.model.PresenceOfAlcoholCategory.WITH_ALCOHOL
import com.example.homebar.navigation.HomeBarNavigationManager.navigateToFavouriteDrink
import com.example.homebar.navigation.HomeBarNavigationManager.navigateToRecipeDetails
import com.example.homebar.navigation.HomeBarNavigationManager.navigateToRecipeSearch
import com.example.homebar.recipedetails.model.DetailsExtraData
import com.example.homebar.recipesearch.model.Drinks
import com.example.homebar.recipesearch.model.RecipeSearchExtraData
import com.example.homebar.welcome.WelcomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WelcomeActivity : AppCompatActivity() {

    private val viewModel: WelcomeViewModel by viewModels()
    private lateinit var binding: WelcomeActivityBinding
    private lateinit var drinksList: List<Drinks>

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

        //JAK TO ROZROZNIC NA PODSTAWIE TYPU EXTRA DATY?

        binding.favouriteBT.setOnClickListener {
            navigateToFavouriteDrink(
                extraData = DetailsExtraData
                    (viewModel.idDrinkRandom.value ?: 0)
            )
       //     val data = viewModel.getDrinkFromDatabase()    //DLACZEGO TO NIE DZIALA I CO TO ZA FCJA?
            //Toast.makeText(this, data, Toast.LENGTH_LONG).show()
        }

        val imageRandomObserver = Observer<String> { value ->
            Glide.with(binding.root.context)
                .load(value)
                .transition(DrawableTransitionOptions.withCrossFade(75))
                .into(binding.drinkImageRandom)
            setVisibilityRandomDrink()
        }
        viewModel.imageRandom.observe(this, imageRandomObserver)

        val drinkNameRandomObserver = Observer<String> { value ->
            binding.drinkNameRandom.text = value
        }
        viewModel.drinkNameRandom.observe(this, drinkNameRandomObserver)


        binding.randomCocktailBT.setOnClickListener {
            viewModel.searchRandom()
        }

        val idDrinkObserver = Observer<Int> {}
        viewModel.idDrinkRandom.observe(this, idDrinkObserver)

        binding.moreInformationBT.setOnClickListener {
            navigateToRecipeDetails(DetailsExtraData(viewModel.idDrinkRandom.value ?: 0))
        }

    }
    override fun onResume() {
        super.onResume()

    }

    private fun setVisibilityRandomDrink(){
        binding.drinkImageRandom.visibility = View.VISIBLE
        binding.drinkNameRandom.visibility = View.VISIBLE
        binding.moreInformationBT.visibility = View.VISIBLE
    }


}

