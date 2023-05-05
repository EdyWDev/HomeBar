package com.example.homebar.favouritedrink

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homebar.favouritedrink.model.FavouriteDrinkRecipe
import com.example.homebar.recipesearch.ui.UnitAndIngredients
import com.example.homebar.room.DrinkDatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteDrinkViewModel @Inject constructor(
    private val drinkDbRepository: DrinkDatabaseRepository
) : ViewModel() {

    private val _favouriteDrinkList = MutableLiveData<List<FavouriteDrinkRecipe>>()
    val favouriteDrinkList: LiveData<List<FavouriteDrinkRecipe>> = _favouriteDrinkList

    fun searchForResult() {
        viewModelScope.launch {
            _favouriteDrinkList.value = drinkDbRepository.getAllDrink()
        }
    }
}

