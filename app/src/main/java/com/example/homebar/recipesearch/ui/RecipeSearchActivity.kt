package com.example.homebar.recipesearch.ui

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homebar.databinding.RecipeSearchActivityBinding
import com.example.homebar.navigation.HomeBarNavigationManager.navigateToRecipeDetails
import com.example.homebar.recipedetails.model.DetailsExtraData
import com.example.homebar.recipesearch.RecipeSearchViewModel
import com.example.homebar.recipesearch.model.Drinks
import com.example.homebar.recipesearch.model.Recipe
import com.example.homebar.room.DrinkDatabaseRepository
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeSearchActivity : AppCompatActivity() {

    private lateinit var binding: RecipeSearchActivityBinding
    private val viewModel: RecipeSearchViewModel by viewModels()
    // 1 czesc potrzebna do dzialania RecyclerView
    private lateinit var recyclerViewSearch: RecyclerView
    private lateinit var recyclerAdapterSearch: RecyclerSearch
    private lateinit var drinksList: List<Drinks>            // CZY NAPEWNO CHODZI O TA KLASE?

    //  private lateinit var gridView: GridView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RecipeSearchActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewmodel = viewModel
        supportActionBar?.setDisplayHomeAsUpEnabled(true)     // dzieki temu mozesz wracac do wczesniejszego activity + trzeba dodac parentActivity do manifest



        drinksList = ArrayList()


        // 2 czesc potrzebna do działania RecyclerView
        //  linearLayoutManager = LinearLayoutManager(this)
        recyclerViewSearch = binding.rvList
        val numberOfColumns = 2
        recyclerViewSearch.layoutManager = GridLayoutManager(this, numberOfColumns)

        recyclerAdapterSearch = RecyclerSearch(
            drinksList = drinksList) {
            navigateToRecipeDetails(DetailsExtraData(it.idDrink ?: 0)) // onItemClick
        }                                // onItemClick dzieki temu item w recyclerview jest klikalny i przenosi do nowego activity
        recyclerViewSearch.adapter = recyclerAdapterSearch

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

                // na if i else mozesz pomyslec tak samo o Constraint View Group
                viewModel.indexOfTheSelectedValue.value = position
                if (position == 2) {
                    binding.typeOfGlassSpinner.visibility = View.VISIBLE
                    binding.searchView.visibility = View.GONE
                    binding.searchImageButton.visibility = View.GONE

                } else {
                    binding.typeOfGlassSpinner.visibility = View.GONE
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
                viewModel.searchForChooseTheTypeOfGlass()
            }
        }



        enterKeyListener()
         binding.searchImageButton.setOnClickListener {
              viewModel.searchForResult()
          }

        val resultObserver = Observer<Recipe> { drinkList ->
            drinkList.drinks?.let { recyclerAdapterSearch.updateNewData(it) }
        }
        viewModel.responseLD.observe(this, resultObserver)

        val glassResultObserver = Observer<Recipe> { it ->
            it.drinks?.let { recyclerAdapterSearch.updateNewData(it) }
        }
        viewModel.spinnerGlassResponseLD.observe(this, glassResultObserver)
    }

    // to moze w formie extensiona do EditText i przenieść to wtedy do jakiś utilsów od widoku?
fun enterKeyListener(){
    binding.searchView.setOnKeyListener(View.OnKeyListener{v, keyCode, event ->
        if (keyCode == KeyEvent.KEYCODE_ENTER){
            //Start your action
            viewModel.searchForResult()
            //End action
            return@OnKeyListener true
        }
        false
    })
}
}