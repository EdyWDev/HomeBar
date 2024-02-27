package com.example.homebar.di

import android.content.Context
import androidx.room.Room
import com.example.homebar.recipesearch.service.RecipeService
import com.example.homebar.room.DrinkConstants.DRINK_DATABASE
import com.example.homebar.room.DrinkDatabase
import com.example.homebar.room.DrinkEntitiesConverters
import com.example.homebar.room.DrinkEntity
import com.example.homebar.utils.parser.GsonParser
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson =
        GsonBuilder()
            .create()

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit.Builder =
        Retrofit.Builder()
            .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create(gson))

    @Singleton
    @Provides
    fun provideHomeBarService(retrofit: Retrofit.Builder): RecipeService =
        retrofit
            .build()
            .create(RecipeService::class.java)


        //Hilt needs to know how to create an instance of DrinkDatabase. For that add another method below provideDao.
        @Provides
        @Singleton
        fun provide(@ApplicationContext context: Context) = Room.databaseBuilder(
            context, DrinkDatabase::class.java, DRINK_DATABASE)
            .allowMainThreadQueries()
            .addTypeConverter(DrinkEntitiesConverters(GsonParser(Gson())))
            .fallbackToDestructiveMigration()
            .build()
        //This annotation marks the method provideDao as a provider of drinkDoa.
        @Provides
        @Singleton
        fun provideDao(db: DrinkDatabase) = db.drinkDao()

        @Provides
        fun provideEntity() = DrinkEntity()
}