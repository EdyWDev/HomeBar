package com.example.homebar.recipesearch.ui

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homebar.databinding.RecipeSearchActivityBinding
import com.example.homebar.navigation.HomeBarNavigationManager.navigateToRecipeDetails
import com.example.homebar.recipedetails.model.DetailsExtraData
import com.example.homebar.recipesearch.RecipeSearchViewModel
import com.example.homebar.recipesearch.model.Drinks
import com.example.homebar.recipesearch.model.Recipe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeSearchActivity : AppCompatActivity() {

    private lateinit var binding: RecipeSearchActivityBinding
    private val viewModel: RecipeSearchViewModel by viewModels()

    // 1 czesc potrzebna do dzialania RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: RecyclerAdapter
    private lateinit var drinksList: List<Drinks>            // CZY NAPEWNO CHODZI O TA KLASE?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RecipeSearchActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewmodel = viewModel


        //  drinksList = emptyList() zmiana na to nizej
        drinksList = ArrayList()

        // 2 czesc potrzebna do działania RecyclerView
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView = binding.rvList
        //  recyclerView.hasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)



        recyclerAdapter = RecyclerAdapter(drinksList)               // CZY NAPEWNO DOBRA KLASA?
        recyclerView.adapter = recyclerAdapter

        // onItemClick dzieki temu item w recyclerview jest klikalny i przenosi do nowego activity
        recyclerAdapter.onItemClick = {
            navigateToRecipeDetails(DetailsExtraData(it.idDrink?: 0))
        }


        val arrayAdapterSearch = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            viewModel.typeOfSearch
        )
        binding.searchSpinner.adapter = arrayAdapterSearch
        binding.searchSpinner.onItemSelectedListener = object :

            AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.indexOfTheSelectedValue.value = position
                if (position == 2) {
                    binding.typeOfGlassSpinner.visibility = View.VISIBLE
                    binding.glassSearchButton.visibility = View.VISIBLE
                    binding.searchView.visibility = View.GONE
                    binding.searchImageButton.visibility = View.GONE

                } else {
                    binding.typeOfGlassSpinner.visibility = View.GONE
                    binding.glassSearchButton.visibility = View.GONE
                    binding.searchView.visibility = View.VISIBLE
                    binding.searchImageButton.visibility = View.VISIBLE
                }
            }
        }

        val arrayAdapterTypeOfGlass = ArrayAdapter(
            this, android.R.layout.simple_spinner_dropdown_item,
            viewModel.typeOfGlass
        )
        binding.typeOfGlassSpinner.adapter = arrayAdapterTypeOfGlass
        binding.typeOfGlassSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.indexOfTheSelectedValueOfGlasses.value = position
            }
        }

        binding.searchImageButton.setOnClickListener {
            viewModel.searchForResult()
        }

        val resultObserver = Observer<Recipe> { drinkList ->
            drinkList.drinks?.let { recyclerAdapter.updateNewData(it) }
        }
        viewModel.responseLD.observe(this, resultObserver)

        val glassResultObserver = Observer<Recipe> { it ->
            it.drinks?.let { recyclerAdapter.updateNewData(it) }
        }
        viewModel.spinnerGlassResponseLD.observe(this, glassResultObserver)

        binding.glassSearchButton.setOnClickListener {
            viewModel.searchForChooseTheTypeOfGlass()
        }
    }
}
