package com.example.homebar.recipedetails

import android.util.Log
import androidx.lifecycle.*
import com.example.homebar.coroutinesScopeUtils.safeLaunch
import com.example.homebar.recipedetails.model.DetailsExtraData
import com.example.homebar.recipedetails.model.ExtraDataConstDetails
import com.example.homebar.recipesearch.model.Drinks
import com.example.homebar.recipesearch.service.RecipeRepository
import com.example.homebar.recipesearch.ui.UnitAndIngredients
import com.example.homebar.room.DrinkDatabaseRepository
import com.example.homebar.room.DrinkEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val recipeRepository: RecipeRepository,
    private val drinkDbRepository: DrinkDatabaseRepository

) : ViewModel() {

    private val extraData: DetailsExtraData? =
        savedStateHandle[ExtraDataConstDetails.EXTRA_DATA_RECIPE_DETAILS]

    private val _imageLD = MutableLiveData<String>()
    val image: LiveData<String> = _imageLD

    private val _drinkNameLD = MutableLiveData<String>()
    val drinkName: LiveData<String> = _drinkNameLD

    private val _detailedDrinkInstructionLD = MutableLiveData<String>()
    val detailedDrinkInstructionLD: LiveData<String> = _detailedDrinkInstructionLD

    private val _buttonFavLD = MutableLiveData<Boolean>(false)
    val buttonFavLD: LiveData<Boolean> = _buttonFavLD

    private val _ingredientList = MutableLiveData<List<UnitAndIngredients>>()
    val ingredientList: LiveData<List<UnitAndIngredients>> = _ingredientList

    var currentPresentedDrinkId = 0
    init {
        searchDetailsForID()
    }



    private fun searchDetailsForID() {
        val drinkId =  extraData?.idDrink
        val responseWithDataBase = drinkId?.let{ drinkDbRepository.getDrink(id = it) }
        if (responseWithDataBase != null) {
            _buttonFavLD.value = true
            responseWithDataBase.drinkId.let {
                currentPresentedDrinkId = it
            }
            responseWithDataBase.drinkName.let {
                _drinkNameLD.value = it
            }
            responseWithDataBase.drinkUrl.let {
                _imageLD.value = it
            }
            responseWithDataBase.drinkDesc.let {
                _detailedDrinkInstructionLD.value = it
            }
            responseWithDataBase.drinkUnitAndIngredients.let {
                _ingredientList.value = it
            }



        } else {
            viewModelScope.safeLaunch(
                actionToTake = {
                    val response =
                        recipeRepository.getRecipeByID(drinkId.toString())

                    response?.idDrink?.let {
                        currentPresentedDrinkId = it
                    }

                    response?.strDrinkThumb?.let {
                        _imageLD.value = it
                    }
                    response?.strDrink?.let {
                        _drinkNameLD.value = it
                    }
                    _ingredientList.value = response?.mapToIngredientList()

                    response?.strInstructions.let {
                        _detailedDrinkInstructionLD.value = it
                    }
                },
                onException = {error->
                    Log.e("MYAPP", "exception", error)
                }
            )
        }
    }
    fun saveDateAfterClickFavouriteButton(){
        drinkDbRepository.saveDrink(
            DrinkEntity(
                drinkId = currentPresentedDrinkId,
                drinkName = _drinkNameLD.value ?: "Example text",
                drinkDesc = _detailedDrinkInstructionLD.value ?: "Example instruction",
                drinkUrl = _imageLD.value ?: "Example URL",
                drinkUnitAndIngredients = _ingredientList.value?: listOf()
            )
        )
    }
    fun deleteDateAfterUnclickFavouriteButton(){
        drinkDbRepository.deleteDrink(
            DrinkEntity(
                drinkId = currentPresentedDrinkId
            )
        )
    }
    fun getProperStringToShare(): String {
        val actualString = StringBuilder()
        actualString.clear()
        actualString.append("Drink name: ${drinkName.value}" + "\n")
        val mutableList = _ingredientList.value?.toMutableList()
        mutableList?.removeIf {
            it.ingredient.isBlank()
        }
        actualString.append("Ingredients:\n$mutableList\n")
        actualString.append("Recipe:\n${detailedDrinkInstructionLD.value}" + "\n")
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
