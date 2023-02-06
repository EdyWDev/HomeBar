package com.example.homebar.recipedetails

import android.util.Log
import androidx.lifecycle.*
import com.example.homebar.recipedetails.model.DetailsExtraData
import com.example.homebar.recipedetails.model.ExtraDataConstDetails
import com.example.homebar.recipesearch.service.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val recipeRepository: RecipeRepository //zmienic nazwe RecipeRepository
) : ViewModel() {

    private val extraData: DetailsExtraData? =
        savedStateHandle[ExtraDataConstDetails.EXTRA_DATA_RECIPE_DETAILS]

    // val idDrinkLiveData = MutableLiveData<Int>(extraData?.idDrink)
    init {
        searchDetailsForID()
    }

    private val _imageLD = MutableLiveData<String>()
    val image: LiveData<String> = _imageLD

    private val _drinkNameLD = MutableLiveData<String>()
    val drinkName: LiveData<String> = _drinkNameLD

    private val _detailedRecipeDrinkLD = MutableLiveData<String>()
    val detailedRecipeDrink: LiveData<String> = _detailedRecipeDrinkLD

    private val _detailedInstructionDrinkLD = MutableLiveData<String>()
    val detailedInstructionDrinkLD: LiveData<String> = _detailedInstructionDrinkLD
    private fun searchDetailsForID() {                       // zapytanie do backendu
        viewModelScope.launch {
            try {
                val response = recipeRepository.getRecipeByID(extraData?.idDrink.toString())
                response?.strDrinkThumb?.let {
                    _imageLD.value = it
                }
                val responseStrDrink =
                    recipeRepository.getRecipeByID(extraData?.idDrink.toString())
                responseStrDrink?.strDrink?.let {
                    _drinkNameLD.value = it.toUpperCase()
                }
                val detailedDrinkResponse =
                    recipeRepository.getRecipeByID(extraData?.idDrink.toString())
                val instruction = detailedDrinkResponse?.strInstructions
                val ingredients =
                    (detailedDrinkResponse?.strMeasure1.orEmpty()+detailedDrinkResponse?.strIngredient1.orEmpty() + "\n" +
                            detailedDrinkResponse?.strMeasure2.orEmpty() +detailedDrinkResponse?.strIngredient2.orEmpty() + "\n" +
                            detailedDrinkResponse?.strMeasure3.orEmpty() +detailedDrinkResponse?.strIngredient3.orEmpty() + "\n" +
                            detailedDrinkResponse?.strMeasure4.orEmpty() +detailedDrinkResponse?.strIngredient4.orEmpty() + "\n" +
                            detailedDrinkResponse?.strMeasure5.orEmpty() +detailedDrinkResponse?.strIngredient5.orEmpty() + "\n" +
                            detailedDrinkResponse?.strMeasure6.orEmpty()+detailedDrinkResponse?.strIngredient6.orEmpty() + "\n" +
                            detailedDrinkResponse?.strMeasure7.orEmpty() +detailedDrinkResponse?.strIngredient7.orEmpty() + "\n" +
                            detailedDrinkResponse?.strMeasure8.orEmpty() +detailedDrinkResponse?.strIngredient8.orEmpty() + "\n" +
                            detailedDrinkResponse?.strMeasure9.orEmpty() +detailedDrinkResponse?.strIngredient9.orEmpty() + "\n" +
                            detailedDrinkResponse?.strMeasure10.orEmpty() +detailedDrinkResponse?.strIngredient10.orEmpty() + "\n" +
                            detailedDrinkResponse?.strMeasure11.orEmpty() +detailedDrinkResponse?.strIngredient11.orEmpty() + "\n" +
                            detailedDrinkResponse?.strMeasure12.orEmpty() +detailedDrinkResponse?.strIngredient12.orEmpty() + "\n" +
                            detailedDrinkResponse?.strMeasure13.orEmpty() +detailedDrinkResponse?.strIngredient13.orEmpty() + "\n" +
                            detailedDrinkResponse?.strMeasure14.orEmpty() +detailedDrinkResponse?.strIngredient14.orEmpty() + "\n" +
                            detailedDrinkResponse?.strMeasure15.orEmpty() +detailedDrinkResponse?.strIngredient15.orEmpty())
                instruction.let {
                    _detailedRecipeDrinkLD.value = it
                }

                ingredients.let{
                    _detailedInstructionDrinkLD.value = it
                }

            } catch (e: Exception) {
                // Retrofit error
                Log.e("MYAPP", "exception", e)
            }

        }


    }
}

private fun String?.getFormattedTextOrEmpty(): String {
    return this?.let {
        "$it" + " " } ?: ""
}
