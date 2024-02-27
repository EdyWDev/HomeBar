package com.example.homebar.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [DrinkEntity::class], version = 4)
@TypeConverters(DrinkEntitiesConverters::class)
abstract class DrinkDatabase : RoomDatabase(){
    abstract fun drinkDao(): DrinkDao
}