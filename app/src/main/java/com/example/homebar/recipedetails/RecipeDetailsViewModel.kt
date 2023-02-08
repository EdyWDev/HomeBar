package com.example.homebar.recipedetails

import android.util.Log
import androidx.lifecycle.*
import com.example.homebar.recipedetails.model.DetailsExtraData
import com.example.homebar.recipedetails.model.ExtraDataConstDetails
import com.example.homebar.recipesearch.model.Drinks
import com.example.homebar.recipesearch.service.COCKTAIL_BY_ID
import com.example.homebar.recipesearch.service.RecipeRepository
import com.example.homebar.recipesearch.service.toDomainRecipeModel
import com.example.homebar.recipesearch.ui.UnitAndIngredients
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

    private val _ingredientList = MutableLiveData<List<UnitAndIngredients>>()
    val ingredientList: LiveData<List<UnitAndIngredients>> = _ingredientList


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
                    _drinkNameLD.value = it
                }
                val detailedDrinkResponse =
                    recipeRepository.getRecipeByID(extraData?.idDrink.toString())
                val instruction = detailedDrinkResponse?.strInstructions

                _ingredientList.value = detailedDrinkResponse?.mapToIngredientList()

                instruction.let {
                    _detailedRecipeDrinkLD.value = it
                }

                _detailedInstructionDrinkLD.value.let {
                    _detailedInstructionDrinkLD.value = it
                }

            } catch (e: Exception) {
                // Retrofit error
                Log.e("MYAPP", "exception", e)
            }

        }


    }

    fun getProperStringToShare(): String {
        var actualString = StringBuilder()
        actualString.clear()
        actualString.append("Drink name: ${drinkName.value}" + "\n")
        val mutableList = _ingredientList.value?.toMutableList()
        mutableList?.removeIf {
            it.ingredient.isBlank()
        }
        actualString.append("Ingredients:\n$mutableList\n")
        actualString.append("Recipe:\n${detailedRecipeDrink.value}" + "\n")
        actualString.append("Link to image: " + image.value + "\n")
        return actualString.toString()
    }


}

private fun Drinks.mapToIngredientList(): List<UnitAndIngredients> {
    return listOf(
        UnitAndIngredients(this.strMeasure1.orEmpty(), this.strIngredient1.orEmpty()),
        UnitAndIngredients(this.strMeasure2.orEmpty(), this.strIngredient2.orEmpty()),
        UnitAndIngredients(this.strMeasure3.orEmpty(), this.strIngredient3.orEmpty()),
        UnitAndIngredients(this.strMeasure4.orEmpty(), this.strIngredient4.orEmpty()),
        UnitAndIngredients(this.strMeasure5.orEmpty(), this.strIngredient5.orEmpty()),
        UnitAndIngredients(this.strMeasure6.orEmpty(), this.strIngredient6.orEmpty()),
        UnitAndIngredients(this.strMeasure7.orEmpty(), this.strIngredient7.orEmpty()),
        UnitAndIngredients(this.strMeasure8.orEmpty(), this.strIngredient8.orEmpty()),
        UnitAndIngredients(this.strMeasure9.orEmpty(), this.strIngredient9.orEmpty()),
        UnitAndIngredients(this.strMeasure10.orEmpty(), this.strIngredient10.orEmpty()),
        UnitAndIngredients(this.strMeasure11.orEmpty(), this.strIngredient11.orEmpty()),
        UnitAndIngredients(this.strMeasure12.orEmpty(), this.strIngredient12.orEmpty()),
        UnitAndIngredients(this.strMeasure13.orEmpty(), this.strIngredient13.orEmpty()),
        UnitAndIngredients(this.strMeasure14.orEmpty(), this.strIngredient14.orEmpty()),
        UnitAndIngredients(this.strMeasure15.orEmpty(), this.strIngredient15.orEmpty()),
    )
}
