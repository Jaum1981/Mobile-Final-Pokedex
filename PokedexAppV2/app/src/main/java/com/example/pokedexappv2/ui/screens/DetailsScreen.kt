package com.example.pokedexappv2.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.pokedexappv2.models.Pokemon

@Composable
fun DetailsScreen(pokemon: Pokemon) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Exibindo a imagem do Pokémon com Coil
        AsyncImage(
            model = pokemon.imageRes, // URL da imagem do Pokémon
            contentDescription = "${pokemon.name} image",
            modifier = Modifier
                .size(200.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Nome do Pokémon
        BasicText(text = "Name: ${pokemon.name}")

        // Tipo do Pokémon
        BasicText(text = "Type: ${pokemon.type}")

        // Região do Pokémon
        BasicText(text = "Region: ${pokemon.region}")
    }
}
