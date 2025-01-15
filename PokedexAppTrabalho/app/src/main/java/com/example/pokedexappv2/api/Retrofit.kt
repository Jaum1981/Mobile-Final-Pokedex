package com.example.pokedexappv2.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PokeApi {
    private const val BASE_URL = "https://pokeapi.co/api/v2/"

    val service: PokeApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PokeApiService::class.java)
}
