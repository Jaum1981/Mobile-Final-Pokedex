package com.example.pokedexappv2.api

import retrofit2.http.GET
import retrofit2.http.Path

data class PokemonResponse(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val abilities: List<Ability>,
    val types: List<Type>
)

data class Ability(val ability: Name)
data class Type(val type: Name)
data class Name(val name: String)

interface PokeApiService {
    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id") id: Int): PokemonResponse
}
