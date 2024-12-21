package com.example.pokedexappv2.api

import com.example.pokedexappv2.models.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url
import java.util.Locale

// Retrofit API Service
interface PokemonApiService {

    // Retorna a lista de Pokémons da primeira geração
    @GET("pokemon?limit=151")
    suspend fun getFirstGenPokemon(): PokemonResponse

    // Retorna os detalhes de um Pokémon usando sua URL
    @GET
    suspend fun getPokemonDetails(@Url url: String): PokemonDetails
}

// Data Classes para a API
data class PokemonResponse(
    val results: List<PokemonResult> // Lista de Pokémons (somente nome e URL)
)

data class PokemonResult(
    val name: String,
    val url: String
)

data class PokemonDetails(
    val id: Int,
    val name: String,
    val types: List<TypeSlot>,
    val abilities: List<AbilitySlot>,
    val weight: Int,
    val height: Int
)

data class TypeSlot(val type: Type)
data class Type(val name: String)
data class AbilitySlot(val ability: Ability)
data class Ability(val name: String)

// Retrofit Instance
object RetrofitInstance {
    private const val BASE_URL = "https://pokeapi.co/api/v2/"

    val api: PokemonApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonApiService::class.java)
    }
}

// Função para buscar a lista de Pokémons
suspend fun fetchPokemonList(): List<Pokemon> = withContext(Dispatchers.IO) {
    val response = RetrofitInstance.api.getFirstGenPokemon()

    response.results.map { result ->
        // Obtém os detalhes de cada Pokémon usando sua URL
        val details = RetrofitInstance.api.getPokemonDetails(result.url)

        Pokemon(
            id = details.id,
            name = details.name.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString()
            },
            type = details.types.joinToString { it.type.name.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString()
            } },
            region = "Kanto",
            imageRes = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${details.id}.png",
            isFavorite = false
        )
    }
}
