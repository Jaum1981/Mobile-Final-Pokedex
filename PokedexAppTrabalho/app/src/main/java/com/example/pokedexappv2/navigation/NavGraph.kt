package com.example.pokedexappv2.navigation

import HelpScreen
import SettingsScreen
import ThemeManager
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokedexappv2.models.Pokemon
import com.example.pokedexappv2.models.fetchPokemonList
import com.example.pokedexappv2.ui.components.BottomBarScreen
import com.example.pokedexappv2.ui.components.BottomNavigationBar
import com.example.pokedexappv2.ui.screens.HomeScreen
import com.example.pokedexappv2.ui.screens.FavoriteScreen
import com.example.pokedexappv2.ui.screens.DetailsScreen
import com.example.pokedexappv2.ui.screens.LogoutScreen

@SuppressLint("UnrememberedMutableState")
@ExperimentalMaterial3Api
@Composable
fun NavGraph(
    onSettingsClick: () -> Unit,
    onHelpClick: () -> Unit,
    onLogoutClick: () -> Unit,
    themeManager: ThemeManager
) {
    val navController = rememberNavController()

    // Estado principal da lista de Pokémon
    var pokemonList by remember { mutableStateOf<List<Pokemon>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    // Carrega a lista de Pokémon ao iniciar o NavGraph
    LaunchedEffect(Unit) {
        isLoading = true
        pokemonList = fetchPokemonList()
        isLoading = false
    }

    // Atualiza a lista de favoritos dinamicamente
    val favoritesList by derivedStateOf { pokemonList.filter { it.isFavorite } }

    // Função para resetar os favoritos
    val resetFavorites: () -> Unit = {
        pokemonList = pokemonList.map { it.copy(isFavorite = false) }
    }

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
                    onPokemonSelected = { pokemon ->
                        navController.navigate("details/${pokemon.name}")
                    },
                    onSettingsClick = { navController.navigate("settings") },
                    onHelpClick = { navController.navigate("help") },
                    onLogoutClick = { navController.navigate("logout") },
                    pokemonList = pokemonList,
                    isLoading = isLoading,
                    onFavoriteToggle = { pokemon ->
                        pokemonList = pokemonList.map {
                            if (it.id == pokemon.id) it.copy(isFavorite = !it.isFavorite) else it
                        }
                    }
                )
            }

            // Tela de Favoritos
            composable(BottomBarScreen.Favorites.route) {
                FavoriteScreen(
                    pokemonList = favoritesList, // Passa apenas os favoritos
                    onPokemonSelected = { pokemon ->
                        navController.navigate("details/${pokemon.name}")
                    },
                    onFavoriteToggle = { pokemon ->
                        pokemonList = pokemonList.map {
                            if (it.id == pokemon.id) it.copy(isFavorite = !it.isFavorite) else it
                        }
                    },
                    resetFavorite = resetFavorites
                )
            }

            // Tela de Detalhes
            composable("details/{pokemonName}") { backStackEntry ->
                val pokemonName = backStackEntry.arguments?.getString("pokemonName")
                val selectedPokemon = pokemonList.firstOrNull { it.name == pokemonName }
                selectedPokemon?.let { DetailsScreen(it) }
            }

            // Tela de Configurações
            composable("settings") {
                SettingsScreen(themeManager = themeManager, resetFavorites = resetFavorites)
            }

            // Tela de Suporte/Ajuda
            composable("help") {
                HelpScreen()
            }

            // Tela de Logout
            composable("logout") {
                LogoutScreen(
                    onCancel = { navController.popBackStack() },
                    onConfirm = {
                        navController.navigate(BottomBarScreen.Home.route) {
                            popUpTo(0)
                        }
                        onLogoutClick()
                    }
                )
            }
        }
    }
}
