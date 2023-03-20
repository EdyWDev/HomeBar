package com.example.homebar.favouritedrink.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homebar.databinding.FavouriteDrinkBinding
import com.example.homebar.favouritedrink.FavouriteDrinkViewModel
import com.example.homebar.favouritedrink.model.FavouriteDrinkRecipe
import com.example.homebar.navigation.HomeBarNavigationManager.navigateToRecipeDetails
import com.example.homebar.recipedetails.model.DetailsExtraData
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavouriteDrinkActivity : AppCompatActivity() {

    private val viewModel: FavouriteDrinkViewModel by viewModels()
    private lateinit var binding: FavouriteDrinkBinding


    private lateinit var recyclerViewFavourite: RecyclerView
    private lateinit var recyclerAdapterFavourite: RecyclerSearchFavourite
    private lateinit var favouriteDrinkList: List<FavouriteDrinkRecipe>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FavouriteDrinkBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recyclerViewFavourite = binding.rvFavouriteList
        favouriteDrinkList = ArrayList()
        val numberOfColumns = 2
        recyclerViewFavourite.layoutManager = GridLayoutManager(this, numberOfColumns)

        recyclerAdapterFavourite = RecyclerSearchFavourite(
            favouritesDrinkList = favouriteDrinkList
        ) {
            navigateToRecipeDetails(DetailsExtraData((it.idDrink ?: 0)))
        }
        recyclerViewFavourite.adapter = recyclerAdapterFavourite

        val favouriteDrinkListObserver = Observer<List<FavouriteDrinkRecipe>> { list ->
            recyclerAdapterFavourite.updateNewDataList(list)
        }
        viewModel.favouriteDrinkList.observe(this, favouriteDrinkListObserver)

        viewModel.searchForResult()

        /*  val arrayAdapterFavourite = ArrayAdapter(
              this,
              android.R.layout.simple_spinner_dropdown_item,
              viewModel.
          )*/
    }

}

/// ZEBY GWIAZDKA POZOSTAWALA ZOT=ŁTA TO ZA KAZDYM RAZEM PRZY KLIKANIU W ITEM DETAILS MUSI SPRAWDZAC
// CZY DANE ID DRINKA ZNAJDUJE SIE W FAVOURITES I NA PODSTAWIE TEGO DODAJE GWIAZDKE ZÓLTA