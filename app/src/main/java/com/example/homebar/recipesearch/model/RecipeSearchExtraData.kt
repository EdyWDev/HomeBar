package com.example.homebar.recipesearch.model

import android.os.Parcelable
import com.example.homebar.model.PresenceOfAlcoholCategory
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeSearchExtraData(
    val alcoholPresence: PresenceOfAlcoholCategory
) : Parcelable