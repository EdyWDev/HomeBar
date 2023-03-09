package com.example.homebar.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
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
    val drinkUrl: String = ""
)