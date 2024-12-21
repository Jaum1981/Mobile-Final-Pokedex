package com.example.pokedexappv2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.lifecycle.lifecycleScope
import com.example.pokedexappv2.navigation.NavGraph
import com.example.pokedexappv2.ui.theme.PokedexAppV2Theme
import com.example.pokedexappv2.viewModel.PokemonViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    // Instancia o ViewModel
    private val pokemonViewModel: PokemonViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)  // Habilita a API Experimental do Scaffold
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PokedexAppV2Theme {
                // Chama o NavGraph passando o ViewModel e as funções de clique
                NavGraph(
                    onSettingsClick = { /* Ação ao clicar nas configurações */ },
                    onHelpClick = { /* Ação ao clicar no ajuda */ },
                    pokemonViewModel = pokemonViewModel
                )
            }
        }

        // Inicia uma coroutine para carregar os Pokémons no ViewModel
        lifecycleScope.launch {
            pokemonViewModel.loadPokemons()
        }
    }
}
