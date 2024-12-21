package com.example.pokedexappv2.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pokedexappv2.models.Pokemon
import com.example.pokedexappv2.models.pokemonList
import com.example.pokedexappv2.ui.components.PokemonListItem

@ExperimentalMaterial3Api
@Composable
fun FavoriteScreen(
    onPokemonSelected: (Pokemon) -> Unit,
    onFavoriteToggle: (Pokemon) -> Unit
) {
    val favoritePokemon = pokemonList.filter { it.isFavorite }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Favoritos",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            )
        }
    ) { innerPadding ->
        if (favoritePokemon.isEmpty()) {
            Text(
                text = "Nenhum Pokémon favorito encontrado.",
                modifier = Modifier.padding(innerPadding)
            )
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 8.dp)
            ) {
                items(favoritePokemon) { pokemon ->
                    PokemonListItem(
                        pokemon = pokemon,
                        onPokemonSelected = { onPokemonSelected(it) },
                        onFavoriteToggle = { onFavoriteToggle(it) }
                    )
                }
            }
        }
    }
}
