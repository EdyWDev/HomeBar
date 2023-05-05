package com.example.homebar.recipesearch.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homebar.databinding.RecyclerviewItemRowBinding
import com.example.homebar.recipesearch.model.Drinks

// nazwa nie mowic nic o tym ze to widok powiazany z recyler view
class RecyclerSearch(
   // private val onItemClick: (Drinks) -> Unit,
    var drinksList: List<Drinks>, // CZY TO NAPEWNO TA KLASA???
    private val onItemClick: (Drinks) -> Unit,
) : RecyclerView.Adapter<RecyclerSearch.ViewHolder>() {


    inner class ViewHolder(val binding: RecyclerviewItemRowBinding) :
        RecyclerView.ViewHolder(binding.root)

                                    // dzieki temu ponizej dziala recyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = RecyclerviewItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val drinkListPosition = drinksList[position]
      /*  holder.binding.drinkName.text = drinkListPosition.strDrink*/

                        // wybrana pozycja
        with(holder) {

            with(drinksList[position]) {
                binding.drinkName.text = this.strDrink

                Glide.with(holder.binding.root.context)
                    .load(strDrinkThumb)                               // poczytac o cache Glide
                    .into(binding.drinkImage)

                holder.itemView.setOnClickListener{      // holder który łapie item zeby byl klikalny
                    onItemClick.invoke(drinkListPosition)
                }
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


    }


    private fun String?.getFormattedTextOrEmpty(): String {
        return this?.let { "$it, " } ?: ""
    }


