package com.example.homebar.recipesearch.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.homebar.databinding.RecipeSearchActivityBinding
import com.example.homebar.recipesearch.RecipeSearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeSearchActivity : AppCompatActivity() {

    private lateinit var binding: RecipeSearchActivityBinding
    private val viewModel: RecipeSearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RecipeSearchActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewmodel = viewModel


        // recycleView
        // viewHolder

    }
}