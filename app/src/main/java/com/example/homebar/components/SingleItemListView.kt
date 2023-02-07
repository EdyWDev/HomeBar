package com.example.homebar.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.example.homebar.R
import com.example.homebar.recipesearch.ui.UnitAndIngredients
import org.w3c.dom.Text

class SingleItemListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0,
) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    private var unitIngredientsText: TextView
    private var ingredientsText: TextView

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.single_list_item, this, true)
        orientation = HORIZONTAL

        unitIngredientsText = findViewById(R.id.unit_ingredient)
        ingredientsText = findViewById(R.id.ingredient)
    }

    fun setTextOnFields(unitIngredients: String, ingredients: String) {
        unitIngredientsText.text = unitIngredients
        ingredientsText.text = ingredients
    }

}