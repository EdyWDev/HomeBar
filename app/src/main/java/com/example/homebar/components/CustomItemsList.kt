package com.example.homebar.components

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.example.homebar.R
import com.example.homebar.recipesearch.ui.UnitAndIngredients

class CustomItemsList @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    private lateinit var listView: LinearLayout

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.custom_items_list, this, true)
        orientation = VERTICAL

        listView = findViewById(R.id.list_container)
    }

    fun updateList(list: List<UnitAndIngredients>) {
        list.forEach {
            if (it.ingredient.isNotBlank()) {
                val itemToAdd = SingleItemListView(this.context)
                itemToAdd.setTextOnFields(
                    unitIngredients = it.unitIngredient,
                    ingredients = it.ingredient
                )
                listView.addView(itemToAdd)
            }
        }
    }

}