package com.example.homebar.recipedetails.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.homebar.databinding.RecipeDetailsActivityBinding
import com.example.homebar.recipedetails.RecipeDetailsViewModel

class RecipeDetailsActivity: AppCompatActivity() {

    private val viewModel: RecipeDetailsViewModel by viewModels()
    private lateinit var binding: RecipeDetailsActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RecipeDetailsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}