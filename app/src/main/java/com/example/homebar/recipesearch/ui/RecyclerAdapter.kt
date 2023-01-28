package com.example.homebar.recipesearch.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homebar.databinding.RecyclerviewItemRowBinding
import com.example.homebar.recipesearch.model.Drinks

class RecyclerAdapter(
    var drinksList: List<Drinks>,                  // CZY TO NAPEWNO TA KLASA???
) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(/*itemView: View,*/ val binding: RecyclerviewItemRowBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RecyclerviewItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(drinksList[position]) {
                binding.drinkName.text = this.strDrink
                binding.drinkRecipe.text = /*"""${this.strInstructions}
                    |
                    |*/"""Ingredients: 
                    |${getIngredientsText(this)}""".trimMargin()

                //  binding.drinkIngredients.text = getIngredientsText(this)

                Glide.with(holder.binding.root.context)
                    .load(strDrinkThumb)
                    .into(binding.drinkImage)

            }
        }
    }

    override fun getItemCount(): Int {
        return drinksList.size
    }

    fun updateNewData(data: List<Drinks>) {
        // tutaj czyszczenie listy
        drinksList = emptyList()                    // po co?
        //dodanie danych do listy
        drinksList = data
        notifyDataSetChanged() // to informuje recycler, że zmienily mu sie dane i niech sie przeladuje
    }

    private fun getIngredientsText(data: Drinks): String {
        with(data) {
            return "${strIngredient1.getFormattedTextOrEmpty()}${strIngredient2.getFormattedTextOrEmpty()}${strIngredient3.getFormattedTextOrEmpty()}${strIngredient4.getFormattedTextOrEmpty()}${strIngredient5.getFormattedTextOrEmpty()}${strIngredient6.getFormattedTextOrEmpty()}${strIngredient7.getFormattedTextOrEmpty()}${strIngredient8.getFormattedTextOrEmpty()}${strIngredient9.getFormattedTextOrEmpty()}${strIngredient10.getFormattedTextOrEmpty()}${strIngredient11.getFormattedTextOrEmpty()}${strIngredient12.getFormattedTextOrEmpty()}${strIngredient13.getFormattedTextOrEmpty()}${strIngredient14.getFormattedTextOrEmpty()}${strIngredient15.getFormattedTextOrEmpty()}".replaceAfterLast(
                ",",
                ""
            )
        }
    }

    private fun getDrinksId(data: Drinks): Int? {          // zwróci ID drinka
        with(data) {
            return idDrink
        }
    }


}

fun String?.getFormattedTextOrEmpty(): String {
    return this?.let { "$it, " } ?: ""
}

// TRZEBA USUNAC OSTATNI ELEMNT W STRINGU


