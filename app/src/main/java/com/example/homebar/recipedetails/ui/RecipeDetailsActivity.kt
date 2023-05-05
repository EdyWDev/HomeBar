package com.example.homebar.recipedetails.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.homebar.databinding.RecipeDetailsActivityBinding
import com.example.homebar.recipedetails.RecipeDetailsViewModel
import com.example.homebar.recipesearch.ui.UnitAndIngredients
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeDetailsActivity : AppCompatActivity() {

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

        val detailedDrinkInstructionObserver = Observer<String> { value ->
            binding.detailedDrinkInstruction.text = value

        }
        viewModel.detailedDrinkInstructionLD.observe(this, detailedDrinkInstructionObserver)

        val listIngredientObserver = Observer<List<UnitAndIngredients>> { list ->
            binding.listView.updateList(list)
        }

        viewModel.ingredientList.observe(this, listIngredientObserver)



        binding.shareBT.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, viewModel.getProperStringToShare())
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)

        }

        val buttonFavObserver = Observer<Boolean> { checked ->
            setFavButtonObserver(setNullValue = true)
            binding.favouriteBT.isChecked = checked
            setFavButtonObserver()
        }

        viewModel.buttonFavLD.observe(this, buttonFavObserver)
        setFavButtonObserver()
    }

    private fun setFavButtonObserver(setNullValue: Boolean = false) {
        if (setNullValue) {
            binding.favouriteBT.setOnCheckedChangeListener(null)

        } else {
            binding.favouriteBT.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    viewModel.saveDateAfterClickFavouriteButton()
                    Toast.makeText(this, "Recipe added to favorites", Toast.LENGTH_SHORT).show()

                } else {
                    viewModel.deleteDateAfterUnclickFavouriteButton()
                    Toast.makeText(this, "Recipe has been removed from favourites", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun showSnackbar(it: View) {
        binding.favouriteBT.setOnClickListener {
            Snackbar.make(
                it,
                "Do you want to remove this item from your favourites?",
                Snackbar.LENGTH_LONG
            ).setAction("YES") {
                viewModel.deleteDateAfterUnclickFavouriteButton()
            }.show()
        }
    }
}

