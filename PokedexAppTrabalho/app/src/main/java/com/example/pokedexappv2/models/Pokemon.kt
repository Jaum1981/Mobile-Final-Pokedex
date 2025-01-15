package com.example.pokedexappv2.models

import com.example.pokedexappv2.api.PokeApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Locale

data class Pokemon(
    val id: Int,
    val name: String,
    val type: String,
    val region: String = "Kanto",
    val abilities: String,
    val weight: String,
    val height: String,
    val description: String,
    val imageUrl: String, // URL para o sprite do Pokémon
    val isFavorite: Boolean = false
)

// Função para buscar a lista de Pokémon da primeira geração
suspend fun fetchPokemonList(): List<Pokemon> {
    val pokemonList = mutableListOf<Pokemon>()

    // Executa as requisições em um contexto IO (evita travar a UI)
    withContext(Dispatchers.IO) {
        for (id in 1..151) { // IDs da primeira geração
            try {
                // Requisição para obter os dados do Pokémon
                val response = PokeApi.service.getPokemon(id)

                // Adiciona o Pokémon à lista
                pokemonList.add(
                    Pokemon(
                        id = response.id,
                        name = response.name.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.getDefault()
                            ) else it.toString()
                        },
                        type = response.types.joinToString("/") { it.type.name.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.ROOT
                            ) else it.toString()
                        } },
                        abilities = response.abilities.joinToString(", ") { it.ability.name.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.getDefault()
                            ) else it.toString()
                        } },
                        weight = "${response.weight / 10.0} kg",
                        height = "${response.height / 10.0} m",
                        description = "Dados fornecidos pela PokéAPI.",
                        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${response.id}.png"
                    )
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    return pokemonList
}
