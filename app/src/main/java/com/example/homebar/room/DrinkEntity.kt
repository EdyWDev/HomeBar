package com.example.homebar.room

import android.util.JsonReader
import android.util.JsonWriter
import androidx.room.*
import com.example.homebar.favouritedrink.model.FavouriteDrinkRecipe
import com.example.homebar.recipesearch.ui.UnitAndIngredients
import com.example.homebar.utils.parser.JsonParser
import com.google.gson.reflect.TypeToken
import java.io.StringReader
import java.io.StringWriter

// Enitity jest czymś podobnym jak DTO w warstwie Networkowej z tym, że potrzebuje poszczególnych
// annotacji aby wiedział jak tą struktruę przerabiać na strukture SQL (tabele kolumny itd. Jak nie wiesz
// o co chodzi z ta struktura SQL to poczytaj o tym osobno)
@Entity(tableName = DrinkConstants.DRINK_TABLE)
data class DrinkEntity(

    @PrimaryKey(autoGenerate = true)
    val drinkId: Int = 0,

    @ColumnInfo(name = "drink_name")
    val drinkName: String = "",

    @ColumnInfo(name = "drink_desc")
    val drinkDesc: String = "",

    @ColumnInfo(name = "drink_url")
    val drinkUrl: String = "",

    @ColumnInfo(index = true, name = "drink_unit_and_ingredients")
    var drinkUnitAndIngredients: List<UnitAndIngredients> = listOf()
)

fun DrinkEntity.mapToFavouriteDrink(): FavouriteDrinkRecipe {
    return FavouriteDrinkRecipe(
        idDrink = this.drinkId,
        strDrink = this.drinkName,
        strInstructions = this.drinkDesc,
        strDrinkThumb = this.drinkUrl,
        strIngredient1 = this.drinkUnitAndIngredients
    )
}

@ProvidedTypeConverter
class DrinkEntitiesConverters(
    private val jsonParser: JsonParser
) {
//    @TypeConverter
//    fun toUnitAndIngredients(value: String?): UnitAndIngredients? {
//        return value?.let {
//            UnitAndIngredients(
//                unitIngredient = it.substringBefore(""),
//                ingredient = it.substringAfter("")
//            )
//        }
//    }
//
//    @TypeConverter
//    fun fromUnitAndIngredients(data: UnitAndIngredients?): String? {
//        return data?.toString()
//    }
//
//
//    @TypeConverter
//    fun toUnitAndIngredientsList(value: MutableList<String?>?): MutableList<UnitAndIngredients?> {
//        return value?.map {
//            UnitAndIngredients(
//                unitIngredient = it?.substringBefore("").orEmpty(),
//                ingredient = it?.substringAfter("").orEmpty()
//            )
//        }?.toMutableList() ?: mutableListOf()
//    }
//
//    @TypeConverter
//    fun fromUnitAndIngredientsList(data: MutableList<UnitAndIngredients?>?): MutableList<String?> {
//        return data?.map { it.toString() }?.toMutableList() ?: mutableListOf()
//    }
@TypeConverter
    fun toUnitAndIngredientJson(data: List<UnitAndIngredients>) : String {
        return jsonParser.toJson(
            data,
            object : TypeToken<ArrayList<UnitAndIngredients>>(){}.type
        ) ?: "[]"
    }
    @TypeConverter
    fun fromUnitAndIngredientJson(json: String): List<UnitAndIngredients>{
        return jsonParser.fromJson<ArrayList<UnitAndIngredients>>(
            json,
            object: TypeToken<ArrayList<UnitAndIngredients>>(){}.type
        ) ?: emptyList()
    }
}