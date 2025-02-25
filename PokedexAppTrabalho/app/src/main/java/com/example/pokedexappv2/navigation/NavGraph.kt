package com.example.pokedexappv2.navigation

import HelpScreen
import ThemeManager
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pokedexappv2.models.Pokemon
import com.example.pokedexappv2.models.fetchPokemonList
import com.example.pokedexappv2.ui.components.BottomBarScreen
import com.example.pokedexappv2.ui.components.BottomNavigationBar
import com.example.pokedexappv2.ui.screens.HomeScreen
import com.example.pokedexappv2.ui.screens.FavoriteScreen
import com.example.pokedexappv2.ui.screens.DetailsScreen
import com.example.pokedexappv2.ui.screens.ForgotPasswordScreen
import com.example.pokedexappv2.ui.screens.LoginScreen
import com.example.pokedexappv2.ui.screens.LogoutScreen
import com.example.pokedexappv2.ui.screens.RegisterScreen
import com.example.pokedexappv2.ui.screens.SettingsScreen
import com.example.pokedexappv2.viewmodel.AuthViewModel

@SuppressLint("UnrememberedMutableState")
@ExperimentalMaterial3Api
@Composable
fun NavGraph(
    onSettingsClick: () -> Unit,
    onHelpClick: () -> Unit,
    onLogoutClick: () -> Unit,
    themeManager: ThemeManager,
    authViewModel: AuthViewModel
) {
    val navController = rememberNavController()

    var pokemonList by remember { mutableStateOf<List<Pokemon>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        isLoading = true
        pokemonList = fetchPokemonList()
        isLoading = false
    }

    val favoritesList by derivedStateOf { pokemonList.filter { it.isFavorite } }

    val resetFavorites: () -> Unit = {
        pokemonList = pokemonList.map { it.copy(isFavorite = false) }
    }

    // Obtendo a rota atual para condicionar a BottomNavigationBar
    val currentDestination by navController.currentBackStackEntryAsState()
    val hideBottomBarRoutes = listOf("login", "register", "forgot_password")
    val shouldShowBottomBar = currentDestination?.destination?.route !in hideBottomBarRoutes

    Scaffold(
        bottomBar = {
            if (shouldShowBottomBar) {
                BottomNavigationBar(navController = navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "login", // Define a tela inicial como login
            modifier = Modifier.padding(innerPadding)
        ) {
            // Tela de Login
            composable("login") {
                LoginScreen(viewModel = authViewModel, navController = navController)
            }

            // Tela de Registro
            composable("register") {
                RegisterScreen(viewModel = authViewModel, navController = navController)
            }

            // Tela de Recuperação de Senha
            composable("forgot_password") {
                ForgotPasswordScreen(viewModel = authViewModel, navController = navController)
            }

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
                    },
                    context = navController.context
                )
            }

            // Tela de Favoritos
            composable(BottomBarScreen.Favorites.route) {
                FavoriteScreen(
                    pokemonList = favoritesList,
                    onPokemonSelected = { pokemon ->
                        navController.navigate("details/${pokemon.name}")
                    },
                    onFavoriteToggle = { pokemon ->
                        pokemonList = pokemonList.map {
                            if (it.id == pokemon.id) it.copy(isFavorite = !it.isFavorite) else it
                        }
                    }
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
                SettingsScreen(themeManager = themeManager, resetFavorites = resetFavorites, context = navController.context)
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
