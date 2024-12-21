package com.example.pokedexappv2.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokedexappv2.ui.components.BottomBarScreen
import com.example.pokedexappv2.ui.components.BottomNavigationBar
import com.example.pokedexappv2.ui.screens.HomeScreen
import com.example.pokedexappv2.ui.screens.FavoriteScreen
import com.example.pokedexappv2.ui.screens.DetailsScreen
import com.example.pokedexappv2.viewModel.PokemonViewModel

@ExperimentalMaterial3Api
@Composable
fun NavGraph(
    onSettingsClick: () -> Unit,
    onHelpClick: () -> Unit,
    pokemonViewModel: PokemonViewModel
) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomBarScreen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            // Tela Inicial (Home)
            composable(BottomBarScreen.Home.route) {
                HomeScreen(
                    pokemonViewModel = pokemonViewModel,
                    navController = navController,
                    onSettingsClick = onSettingsClick,
                    onHelpClick = onHelpClick
                )
            }

            // Tela de Favoritos
            composable(BottomBarScreen.Favorites.route) {
                FavoriteScreen(
                    pokemonViewModel = pokemonViewModel,
                    navController = navController,
                    onFavoriteToggle = { pokemon -> pokemonViewModel.toggleFavorite(pokemon) }
                )
            }

            // Tela de Detalhes
            composable("details/{pokemonName}") { backStackEntry ->
                val pokemonName = backStackEntry.arguments?.getString("pokemonName")
                val pokemons = pokemonViewModel.pokemons.collectAsState().value  // Observa a lista de pokémons
                val selectedPokemon = pokemons.firstOrNull { it.name == pokemonName }

                if (selectedPokemon != null) {
                    DetailsScreen(selectedPokemon)
                } else {
                    // Caso o Pokémon não seja encontrado, você pode exibir um estado de erro ou carregar
                    Text(text = "Pokémon não encontrado!")
                }
            }
        }
    }
}
