package com.example.homebar.recipesearch

import android.util.Log
import androidx.lifecycle.*
import com.example.homebar.model.ExtraDataConst
import com.example.homebar.recipesearch.model.Recipe
import com.example.homebar.recipesearch.model.RecipeSearchConsts
import com.example.homebar.recipesearch.model.RecipeSearchExtraData
import com.example.homebar.recipesearch.service.RecipeSearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RecipeSearchViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val recipeSearchRepository: RecipeSearchRepository
) : ViewModel() {

    private val extraData: RecipeSearchExtraData =
        requireNotNull(savedStateHandle[ExtraDataConst.EXTRA_DATA_RECIPE_SEARCH])

    private val _typeOfSearch = mutableListOf("Name", "Ingredients", "Glass")/*.also {
        if (extraData.alcoholPresence == WITH_ALCOHOL) {
            it.add("Alcohol")
        }
    }*/

    val typeOfSearch = _typeOfSearch.toList()

    private val _typeOfGlass = RecipeSearchConsts.typeOfGlass
    val typeOfGlass = _typeOfGlass

    val indexOfTheSelectedValue = MutableLiveData(0)  // position

    private fun isTypeOfSearchSelected(): String? {
        return typeOfSearch[indexOfTheSelectedValue.value ?: 0]
    }

    var searchViewMessage = MutableLiveData<String>()


    private val _response = MutableLiveData<Recipe>()
    val responseLD: LiveData<Recipe> = _response

    fun searchForResult() {
        viewModelScope.launch {
            try {
                when (isTypeOfSearchSelected()) {
                    "Name" -> _response.value =
                        recipeSearchRepository.getRecipeByCocktailName(searchViewMessage.value.toString())
                    "Ingredients" -> _response.value =
                        recipeSearchRepository.getRecipeByIngredients(searchViewMessage.value.toString())
                    /*  "Glass" ->
                          _response.value =
                              recipeSearchRepository.getRecipeByGlass(searchViewMessage.value.toString())*/
                    "Alcohol" -> _response.value =
                        recipeSearchRepository.getRecipeByAlcohol(searchViewMessage.value.toString())
                }
            } catch (e: Exception) {
                // Retrofit error
                Log.e("MYAPP", "exception", e)
            }
        }
    }

    private val _spinnerGlassResponse = MutableLiveData<Recipe>()
    val spinnerGlassResponseLD: LiveData<Recipe> = _spinnerGlassResponse

    val indexOfTheSelectedValueOfGlasses = MutableLiveData(0)            // position

    private fun isTheSelectedTypeOfGlass(): String? {
        return typeOfGlass[indexOfTheSelectedValueOfGlasses.value ?: 0]
    }

    fun searchForChooseTheTypeOfGlass() {
        viewModelScope.launch {
            try {
                _spinnerGlassResponse.value =
                    recipeSearchRepository.getRecipeByGlass(isTheSelectedTypeOfGlass().toString())
                //     recipeSearchRepository.getRecipeByID()                                      // CZY TU WYSTARCZY UZYWYC FUNKCJI GET RECIPE BY ID?

            } catch (e: Exception) {
                // Retrofit error
                Log.e("MYAPP", "exception", e)
            }
        }
    }
}





