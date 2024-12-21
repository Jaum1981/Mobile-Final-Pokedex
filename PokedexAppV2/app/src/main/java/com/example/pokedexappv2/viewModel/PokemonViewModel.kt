package com.example.pokedexappv2.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexappv2.api.fetchPokemonList
import com.example.pokedexappv2.models.Pokemon
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PokemonViewModel : ViewModel() {
    private val _pokemons = MutableStateFlow<List<Pokemon>>(emptyList())
    val pokemons: StateFlow<List<Pokemon>> = _pokemons

    // Função para carregar os Pokémons, por exemplo, da API
//    suspend fun loadPokemons() {
//        _pokemons.value = fetchPokemonList()  // Aqui você pode chamar sua função que busca os Pokémons da API
//    }

    fun loadPokemons() {
        viewModelScope.launch {
            try {
                _pokemons.value = fetchPokemonList()
            } catch (e: Exception) {
                // Adicione um log para ajudar na depuração.
                Log.e("PokemonViewModel", "Erro ao buscar Pokémons", e)
                _pokemons.value = emptyList()
            }

        }
    }


    init {
        fetchPokemons()
    }

    private fun fetchPokemons() {
        viewModelScope.launch {
            try {
                _pokemons.value = fetchPokemonList() // Chama a função que busca os dados da API
            } catch (e: Exception) {
                // Trate o erro de conexão ou exibição de dados (opcional)
                _pokemons.value = emptyList() // Exemplo simples de fallback
            }
        }
    }

    // Método para alternar o estado de favorito de um Pokémon
    fun toggleFavorite(pokemon: Pokemon) {
        val updatedList = _pokemons.value.map {
            if (it.id == pokemon.id) {
                it.copy(isFavorite = !it.isFavorite)  // Altera o status de favorito
            } else {
                it
            }
        }
        _pokemons.value = updatedList
    }
}
