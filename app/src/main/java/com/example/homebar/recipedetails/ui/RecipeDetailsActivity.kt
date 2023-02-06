package com.example.homebar.recipedetails.ui

import android.os.Bundle
import android.widget.ListAdapter
import android.widget.ListView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.homebar.databinding.RecipeDetailsActivityBinding
import com.example.homebar.recipedetails.RecipeDetailsViewModel
import com.example.homebar.recipesearch.model.Drinks
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeDetailsActivity: AppCompatActivity() {

    private val viewModel: RecipeDetailsViewModel by viewModels()
    private lateinit var binding: RecipeDetailsActivityBinding
    // private lateinit var listView: ListView
 ////   var arrayList: ArrayList<Drinks> = ArrayList()
  //  var adapterList: ListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RecipeDetailsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

 //       listView = binding.detailedDrinkIngredientsListView
 //       arrayList.add(Drinks())

     //   val ingredientsList =

       // setSupportActionBar(binding.toolbar)

      supportActionBar?.apply {
          //  title = "Toolbar Back Button Example"
           // show back button on toolbar
            // on back button press, it will navigate to parent activity
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
// Get a support ActionBar corresponding to this toolbar and enable the Up button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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

        val detailedDrinkRecipeObserver = Observer<String> { value ->
            binding.detailedDrinkRecipe.text = value

        }
        viewModel.detailedRecipeDrink.observe(this, detailedDrinkRecipeObserver)

        val detailedDrinkIngredientsObserver = Observer<String> { value ->
            binding.detailedDrinkIngredientsListView.text = value
        }
        viewModel.detailedInstructionDrinkLD.observe(this, detailedDrinkIngredientsObserver)

    }
}
