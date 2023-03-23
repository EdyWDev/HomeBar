package com.example.homebar.welcome

import android.os.Parcelable
import android.util.Log
import androidx.lifecycle.*
import com.example.homebar.model.PresenceOfAlcoholCategory
import com.example.homebar.recipedetails.model.DetailsExtraData
import com.example.homebar.recipedetails.model.ExtraDataConstDetails
import com.example.homebar.recipesearch.model.Drinks
import com.example.homebar.recipesearch.model.Recipe
import com.example.homebar.recipesearch.service.RecipeRepository
import com.example.homebar.room.DrinkDatabaseRepository
import com.example.homebar.room.DrinkEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val recipeRepository: RecipeRepository,
    private val drinkDbRepository: DrinkDatabaseRepository
) : ViewModel() {
    private val extraData: DetailsExtraData? =
        savedStateHandle[ExtraDataConstDetails.EXTRA_DATA_RECIPE_DETAILS]

    private val _imageRandomLD = MutableLiveData<String>()
    val imageRandom: LiveData<String> = _imageRandomLD

    private val _drinkNameRandomLD = MutableLiveData<String>()
    val drinkNameRandom: LiveData<String> = _drinkNameRandomLD

    private val _idDrinkRandomLD = MutableLiveData<Int>()
    val idDrinkRandom: LiveData<Int> = _idDrinkRandomLD
    fun searchRandom() {
        viewModelScope.launch {
            try {
                val responseRandom = recipeRepository.getRandomCocktail()
                responseRandom?.idDrink.let { _idDrinkRandomLD.value = it }
                responseRandom?.strDrinkThumb.let {
                    _imageRandomLD.value = it

                }
                responseRandom?.strDrink.let {
                    _drinkNameRandomLD.value = it
                }
//                drinkDbRepository.saveDrink(
//                    DrinkEntity(
//                        drinkId = responseRandom?.idDrink ?: 0,
//                        drinkName = responseRandom?.strDrink ?: "Example text",
//                        drinkDesc = responseRandom?.strInstructions ?: "Example instruction",
//                        drinkUrl = responseRandom?.strDrinkThumb ?: "example URL"
//                    ))
            } catch (e: Exception) {
                // Retrofit error
                Log.e("MYAPP", "exception", e)
            }
        }

    }
    
    
    fun searchRandomById() {
        viewModelScope.launch {

            try {
                val getIdDrinkRandom = recipeRepository.getRandomCocktail()
                getIdDrinkRandom?.idDrink.let {
                    _idDrinkRandomLD.value = it
                }
            } catch (e: Exception) {
                // Retrofit error
                Log.e("MYAPP", "exception", e)

            }
        }
    }
}







