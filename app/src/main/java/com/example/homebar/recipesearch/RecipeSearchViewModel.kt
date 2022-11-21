package com.example.homebar.recipesearch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.homebar.model.ExtraDataConst
import com.example.homebar.model.PresenceOfAlcoholCategory.*
import com.example.homebar.recipesearch.model.RecipeSearchExtraData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class RecipeSearchViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val extraData: RecipeSearchExtraData = requireNotNull(savedStateHandle[ExtraDataConst.EXTRA_DATA_RECIPE_SEARCH])

    private val _shouldAlcoholCategoryBeSeen = MutableLiveData<Boolean>(extraData.alcoholPresence == WITH_ALCOHOL)
    val shouldAlcoholCategoryBeSeen: LiveData<Boolean> = _shouldAlcoholCategoryBeSeen




}

