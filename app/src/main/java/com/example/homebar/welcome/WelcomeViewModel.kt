package com.example.homebar.welcome

import android.util.Log
import androidx.lifecycle.*
import com.example.homebar.coroutinesScopeUtils.safeLaunch
import com.example.homebar.recipedetails.model.DetailsExtraData
import com.example.homebar.recipedetails.model.ExtraDataConstDetails
import com.example.homebar.recipesearch.service.RecipeRepository
import com.example.homebar.room.DrinkDatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
        viewModelScope.safeLaunch(
            actionToTake = {
                val responseRandom = recipeRepository.getRandomCocktail()
                responseRandom?.idDrink.let { _idDrinkRandomLD.value = it }
                responseRandom?.strDrinkThumb.let {
                    _imageRandomLD.value = it

                }
                responseRandom?.strDrink.let {
                    _drinkNameRandomLD.value = it
                }
            },
            onException = { error ->
                Log.e("MYAPP", "exception", error)
            }
        )
    }
}





