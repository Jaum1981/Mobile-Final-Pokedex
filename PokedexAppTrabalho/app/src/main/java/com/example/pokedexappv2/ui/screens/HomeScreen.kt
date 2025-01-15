package com.example.pokedexappv2.ui.screens

import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pokedexappv2.models.Pokemon
import com.example.pokedexappv2.models.fetchPokemonList
import com.example.pokedexappv2.ui.components.PokemonListItem
import com.example.pokedexappv2.ui.components.TopAppBarMenu

@RequiresApi(35)
@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    onPokemonSelected: (Pokemon) -> Unit,
    onSettingsClick: () -> Unit,
    onHelpClick: () -> Unit,
    onLogoutClick: () -> Unit,
    pokemonList: List<Pokemon>,
    isLoading: Boolean,
    onFavoriteToggle: (Pokemon) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var pokemonList by remember { mutableStateOf<List<Pokemon>>(emptyList()) }
    val filteredPokemon = remember(searchQuery, pokemonList) {
        pokemonList.filter { it.name.contains(searchQuery, ignoreCase = true) }
    }
    val recentSearches = remember { mutableStateListOf<Pokemon>() }
    var isLoading by remember { mutableStateOf(true) }

    // Carrega a lista de Pokémon
    LaunchedEffect(Unit) {
        isLoading = true
        pokemonList = fetchPokemonList()
        isLoading = false
    }

    Scaffold(
        topBar = {
            TopAppBarMenu(
                onSettingsClick = onSettingsClick,
                onHelpClick = onHelpClick,
                onLogoutClick = { onLogoutClick() }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            // Campo de busca
            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Pesquisar Pokémon") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            // Exibição de carregamento
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .wrapContentWidth()
                )
            } else {
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

                // Lista filtrada de Pokémon
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
                                val updatedPokemon = pokemon.copy(isFavorite = !pokemon.isFavorite)
                                pokemonList = pokemonList.map {
                                    if (it.id == pokemon.id) updatedPokemon else it
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
