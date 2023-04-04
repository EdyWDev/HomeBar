package com.example.homebar.recipesearch

import android.util.Log
import androidx.lifecycle.*
import com.example.homebar.model.ExtraDataConst
import com.example.homebar.recipesearch.model.Recipe
import com.example.homebar.recipesearch.model.RecipeSearchConsts
import com.example.homebar.recipesearch.model.RecipeSearchExtraData
import com.example.homebar.recipesearch.model.TypeOfSearchEnum
import com.example.homebar.recipesearch.service.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.jar.Attributes.Name
import javax.inject.Inject


@HiltViewModel
class RecipeSearchViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val recipeRepository: RecipeRepository,
) : ViewModel() {

    private val extraData: RecipeSearchExtraData =
        requireNotNull(savedStateHandle[ExtraDataConst.EXTRA_DATA_RECIPE_SEARCH])

    private val _typeOfSearch = mutableListOf(TypeOfSearchEnum.NAME, TypeOfSearchEnum.INGREDIENTS, TypeOfSearchEnum.GLASS/*"Name", "Ingredients", "Glass"*/)

    val typeOfSearch = _typeOfSearch.toList()

    private val _typeOfGlass = RecipeSearchConsts.typeOfGlass
    val typeOfGlass = _typeOfGlass

    val indexOfTheSelectedValue = MutableLiveData(0)  // position

    private fun isTypeOfSearchSelected(): TypeOfSearchEnum {
        return _typeOfSearch[indexOfTheSelectedValue.value ?: 0]
    }

    var searchViewMessage = MutableLiveData<String>()


    private val _response = MutableLiveData<Recipe>()
    val responseLD: LiveData<Recipe> = _response

    fun searchForResult() {
        viewModelScope.launch {
            try {
                when (isTypeOfSearchSelected()) {
                    TypeOfSearchEnum.NAME -> _response.value =
                        recipeRepository.getRecipeByCocktailName(searchViewMessage.value.toString())
                    TypeOfSearchEnum.INGREDIENTS -> _response.value =
                        recipeRepository.getRecipeByIngredients(searchViewMessage.value.toString())
                    TypeOfSearchEnum.GLASS -> _response.value =
                        recipeRepository.getRecipeByGlass(searchViewMessage.value.toString())
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
                    recipeRepository.getRecipeByGlass(isTheSelectedTypeOfGlass().toString())

            } catch (e: Exception) {
                // Retrofit error
                Log.e("MYAPP", "exception", e)
            }
        }
    }
}





