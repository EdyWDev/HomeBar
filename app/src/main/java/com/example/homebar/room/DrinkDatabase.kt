package com.example.homebar.room

import androidx.room.Database
import androidx.room.RoomDatabase
// To jest jakby pojedyczna instacja która spina w sobie wszystko co jest potrzebne w room -> Entity, Database i Dao
@Database(entities = [DrinkEntity::class], version = 1)
abstract class DrinkDatabase : RoomDatabase(){
    abstract fun drinkDao(): DrinkDao
}