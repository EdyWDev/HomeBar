package com.example.homebar.room

import androidx.room.*
import com.example.homebar.room.DrinkConstants.DRINK_TABLE

// to tutaj jest troche takim odpowiednikiem Api w Retrofit, czysty interface z annotacjami,
// dzięki któremu room wie o co Ci chodzi i co ma zrobić po stronie swoje implementacji z tym co Ty
// mu podajesz
@Dao
interface DrinkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDrink(noteEntity: DrinkEntity)

    @Update
    fun updateDrink(noteEntity: DrinkEntity)

    @Delete
    fun deleteDrink(noteEntity: DrinkEntity)

    @Query("SELECT * FROM $DRINK_TABLE ORDER BY drinkId DESC")
    fun getAllDrink() : MutableList<DrinkEntity>

    @Query("SELECT * FROM $DRINK_TABLE WHERE drinkId LIKE :id")
    fun getDrink(id : Int) : DrinkEntity

}