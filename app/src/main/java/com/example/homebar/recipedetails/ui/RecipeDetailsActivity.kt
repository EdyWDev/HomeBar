package com.example.homebar.recipedetails.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.ListView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.homebar.R
import com.example.homebar.databinding.RecipeDetailsActivityBinding
import com.example.homebar.recipedetails.RecipeDetailsViewModel
import com.example.homebar.recipesearch.ui.UnitAndIngredients
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeDetailsActivity: AppCompatActivity() {

    private val viewModel: RecipeDetailsViewModel by viewModels()
    private lateinit var binding: RecipeDetailsActivityBinding
     private lateinit var listView: ListView
    private lateinit var arrayList: ArrayList<UnitAndIngredients>
    var adapterList: ListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RecipeDetailsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

      supportActionBar?.apply {
          //  title = "Toolbar Back Button Example"
           // show back button on toolbar
            // on back button press, it will navigate to parent activity
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
// Get a support ActionBar corresponding to this toolbar and enable the Up button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        // access the listView from xml file


        val listIngredientObserver = Observer<List<UnitAndIngredients>> { list ->
            binding.listView.updateList(list)
        }


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
            binding.listView[value.toInt()]
        }
        viewModel.detailedInstructionDrinkLD.observe(this, detailedDrinkIngredientsObserver)

        viewModel.ingredientList.observe(this, listIngredientObserver)

    }
}
