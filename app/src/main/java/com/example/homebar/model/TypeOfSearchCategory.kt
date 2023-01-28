package com.example.homebar.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


enum class TypeOfSearchCategory(val categoryName: String) {
    INGREDIENTS("Ingredients"),
    NAME("Name"),
    GLASS("Glass"),
    ALCOHOL("Alcohol")
}