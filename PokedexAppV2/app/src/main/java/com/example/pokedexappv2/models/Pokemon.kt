package com.example.pokedexappv2.models

import androidx.lifecycle.ViewModel

data class Pokemon(
    val id: Int,
    val name: String,
    val type: String,
    val region: String,
    val imageRes: String,
    var isFavorite: Boolean
)
