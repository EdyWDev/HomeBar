package com.example.homebar.recipedetails

import android.util.Log
import androidx.lifecycle.*
import com.example.homebar.recipedetails.model.DetailsExtraData
import com.example.homebar.recipedetails.model.ExtraDataConstDetails
import com.example.homebar.recipesearch.model.Drinks
import com.example.homebar.recipesearch.service.RecipeSearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val recipeSearchRepository: RecipeSearchRepository //zmienic nazwe RecipeRepository
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

    private val _detailedDrinkLD = MutableLiveData<String>()
    val detailedDrink: LiveData<String> = _detailedDrinkLD
    private fun searchDetailsForID() {                       // zapytanie do backendu
        viewModelScope.launch {
            try {
                val response = recipeSearchRepository.getRecipeByID(extraData?.idDrink.toString())
                response?.strDrinkThumb?.let {
                    _imageLD.value = it
                }
                val responseStrDrink =
                    recipeSearchRepository.getRecipeByID(extraData?.idDrink.toString())
                responseStrDrink?.strDrink?.let {
                    _drinkNameLD.value = it.toUpperCase()
                }
                val detailedDrinkResponse =
                    recipeSearchRepository.getRecipeByID(extraData?.idDrink.toString())
                val instruction = detailedDrinkResponse?.strInstructions
                val ingredients =
                    (detailedDrinkResponse?.strIngredient1.getFormattedTextOrEmpty() + "\n" +
                            detailedDrinkResponse?.strIngredient2.getFormattedTextOrEmpty() + "\n" +
                            detailedDrinkResponse?.strIngredient3.getFormattedTextOrEmpty() + "\n" +
                            detailedDrinkResponse?.strIngredient4.getFormattedTextOrEmpty() + "\n" +
                            detailedDrinkResponse?.strIngredient5.getFormattedTextOrEmpty() + "\n" +
                            detailedDrinkResponse?.strIngredient6.getFormattedTextOrEmpty() + "\n" +
                            detailedDrinkResponse?.strIngredient7.getFormattedTextOrEmpty() + "\n" +
                            detailedDrinkResponse?.strIngredient8.getFormattedTextOrEmpty() + "\n" +
                            detailedDrinkResponse?.strIngredient9.getFormattedTextOrEmpty() + "\n" +
                            detailedDrinkResponse?.strIngredient10.getFormattedTextOrEmpty() + "\n" +
                            detailedDrinkResponse?.strIngredient11.getFormattedTextOrEmpty() + "\n" +
                            detailedDrinkResponse?.strIngredient12.getFormattedTextOrEmpty() + "\n" +
                            detailedDrinkResponse?.strIngredient13.getFormattedTextOrEmpty() + "\n" +
                            detailedDrinkResponse?.strIngredient14.getFormattedTextOrEmpty() + "\n" +
                            detailedDrinkResponse?.strIngredient15.getFormattedTextOrEmpty()).replaceAfterLast(
                        ",",
                        ""
                    )
                val result = instruction + "\n" + "\n" + ingredients
                result.let {
                    _detailedDrinkLD.value = it
                }

            } catch (e: Exception) {
                // Retrofit error
                Log.e("MYAPP", "exception", e)
            }
        }

    }

    /* private fun getIngredientsDetails(data: Drinks): String{
         with(data){
                 return ("${strIngredient1.getFormattedTextOrEmpty()}" +
                         "${strIngredient2.getFormattedTextOrEmpty()}" +
                         "${strIngredient3.getFormattedTextOrEmpty()}" +
                         "${strIngredient4.getFormattedTextOrEmpty()}" +
                         "${strIngredient5.getFormattedTextOrEmpty()}" +
                         "${strIngredient6.getFormattedTextOrEmpty()}" +
                         "${strIngredient7.getFormattedTextOrEmpty()}" +
                         "${strIngredient8.getFormattedTextOrEmpty()}" +
                         "${strIngredient9.getFormattedTextOrEmpty()}" +
                         "${strIngredient10.getFormattedTextOrEmpty()}" +
                         "${strIngredient11.getFormattedTextOrEmpty()}" +
                         "${strIngredient12.getFormattedTextOrEmpty()}" +
                         "${strIngredient13.getFormattedTextOrEmpty()}" +
                         "${strIngredient14.getFormattedTextOrEmpty()}" +
                         "${strIngredient15.getFormattedTextOrEmpty()}").replaceAfterLast(
                     ",",
                     ""
                 )
             }
         }*/
}

private fun String?.getFormattedTextOrEmpty(): String {
    return this?.let {
        "$it, " +
                ""
    } ?: ""
}
