package com.example.pokedexappv2.ui.screens

import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pokedexappv2.models.Pokemon
import com.example.pokedexappv2.models.pokemonList
import com.example.pokedexappv2.ui.components.PokemonListItem
import com.example.pokedexappv2.ui.components.TopAppBarMenu

@RequiresApi(35)
@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    onPokemonSelected: (Pokemon) -> Unit,
    onSettingsClick: () -> Unit,
    onHelpClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    val filteredPokemon = remember(searchQuery) {
        pokemonList.filter { it.name.contains(searchQuery, ignoreCase = true) }
    }

    val recentSearches = remember { mutableStateListOf<Pokemon>() }

    Scaffold(
        topBar = {
            TopAppBarMenu(
                onSettingsClick = onSettingsClick,
                onHelpClick = onHelpClick,
                onLogoutClick = {}
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            // Search Field
            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Pesquisar Pokémon") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            // Recent Searches
            LazyRow(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(recentSearches) { pokemon ->
                    Button(onClick = { onPokemonSelected(pokemon) }) {
                        Text(text = pokemon.name)
                    }
                }
            }

            // Filtered Pokémon List
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(horizontal = 18.dp)
            ) {
                items(filteredPokemon) { pokemon ->
                    PokemonListItem(
                        pokemon = pokemon,
                        onPokemonSelected = { selectedPokemon ->
                            if (!recentSearches.contains(selectedPokemon)) {
                                recentSearches.add(0, selectedPokemon)
                            }
                            onPokemonSelected(selectedPokemon)
                        },
                        onFavoriteToggle = {
                            pokemon.isFavorite = !pokemon.isFavorite // Atualiza diretamente
                        }
                    )
                }
            }
        }
    }
}
