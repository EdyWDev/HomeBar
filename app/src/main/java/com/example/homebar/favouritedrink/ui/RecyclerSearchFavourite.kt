package com.example.homebar.favouritedrink.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homebar.databinding.RecyclerviewItemRowBinding
import com.example.homebar.favouritedrink.model.FavouriteDrinkRecipe
import com.example.homebar.recipesearch.model.Drinks

class RecyclerSearchFavourite(
    var favouritesDrinkList: List<FavouriteDrinkRecipe>,
    private val onItemClick: (FavouriteDrinkRecipe) -> Unit
) : RecyclerView.Adapter<RecyclerSearchFavourite.ViewHolder>() {

    inner class ViewHolder(val binding: RecyclerviewItemRowBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerSearchFavourite.ViewHolder {
        val view =
            RecyclerviewItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return favouritesDrinkList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val favouriteDrinksListPosition = favouritesDrinkList[position]

        with(holder) {
            with(favouritesDrinkList[position]) {
                binding.drinkName.text = this.strDrink


                Glide.with(holder.binding.root.context)
                    .load(strDrinkThumb)                               // poczytac o cache Glide
                    .into(binding.drinkImage)

                holder.itemView.setOnClickListener {      // holder który łapie item zeby byl klikalny
                    onItemClick.invoke(favouriteDrinksListPosition)
                }
            }
        }

    }

    fun updateNewDataList(data: List<FavouriteDrinkRecipe>){
        favouritesDrinkList = emptyList()
        favouritesDrinkList = data
        notifyDataSetChanged()
    }
}