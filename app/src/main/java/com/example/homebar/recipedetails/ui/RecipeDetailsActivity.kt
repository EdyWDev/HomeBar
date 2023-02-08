package com.example.homebar.recipedetails.ui

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
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
        supportActionBar?.setDisplayHomeAsUpEnabled(true)     // dzieki temu mozesz wracac do wczesniejszego activity + trzeba dodac parentActivity do manifest



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

        val listIngredientObserver = Observer<List<UnitAndIngredients>> { list ->
            binding.listView.updateList(list)
        }
        viewModel.ingredientList.observe(this, listIngredientObserver)


        binding.shareBT.setOnClickListener{
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, viewModel.getProperStringToShare())
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)

        }
    }
}
