package com.example.pokedexappv2.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import androidx.navigation.NavController
import com.example.pokedexappv2.models.Pokemon
import com.example.pokedexappv2.viewModel.PokemonViewModel

@Composable
fun FavoriteScreen(
    pokemonViewModel: PokemonViewModel,
    navController: NavController,
    onFavoriteToggle: (Pokemon) -> Unit
) {
    val pokemons = pokemonViewModel.pokemons.collectAsState().value

    // Filtra Pokémon favoritos
    val favoritePokemons = pokemons.filter { it.isFavorite }

    // Exibe a lista de Pokémon favoritos
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(favoritePokemons) { pokemon ->
            PokemonItem(
                pokemon = pokemon,
                navController = navController,
                onFavoriteToggle = onFavoriteToggle
            )
        }
    }
}

@Composable
fun PokemonItem(pokemon: Pokemon, navController: NavController, onFavoriteToggle: (Pokemon) -> Unit) {
    Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Image(
            painter = rememberImagePainter(pokemon.imageRes),
            contentDescription = "Pokemon Image",
            modifier = Modifier.size(50.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.align(Alignment.CenterVertically)) {
            Text(text = pokemon.name)
            Text(text = pokemon.type, color = Color.Gray)
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = { navController.navigate("details/${pokemon.name}") }) {
            Text(text = "Details")
        }
        Spacer(modifier = Modifier.width(8.dp))
        Button(onClick = { onFavoriteToggle(pokemon) }) {
            Text(text = if (pokemon.isFavorite) "Unfavorite" else "Favorite")
        }
    }
}
