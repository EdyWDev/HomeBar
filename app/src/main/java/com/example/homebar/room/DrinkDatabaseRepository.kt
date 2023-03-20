package com.example.homebar.room

import com.example.homebar.favouritedrink.model.FavouriteDrinkRecipe
import javax.inject.Inject

// To repozytorium jest taką warstwa jak repository przy service. Inject'ujesz sobie ją i wsio.
// Korzystasz sobie z metod które tam stworzysz a co się dzieje pod spodem nie obchodzi Ani View Model
// ani Activity bo masz to ukryte w tej klasie
class DrinkDatabaseRepository @Inject constructor(
    private val dao: DrinkDao,
) {
    fun saveDrink(note: DrinkEntity) = dao.insertDrink(note)
    fun updateDrink(note: DrinkEntity) = dao.updateDrink(note)
    fun deleteDrink(note: DrinkEntity) = dao.deleteDrink(note)
    fun getDrink(id : Int) : DrinkEntity = dao.getDrink(id)
    fun getAllDrink() : List<FavouriteDrinkRecipe> = dao.getAllDrink().map { it.mapToFavouriteDrink()  }

}