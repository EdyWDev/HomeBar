package com.example.homebar.recipedetails.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailsExtraData(
    val idDrink: Int
) : Parcelable
