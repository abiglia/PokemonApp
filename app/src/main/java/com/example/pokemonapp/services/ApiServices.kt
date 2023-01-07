package com.example.pokemonapp.services

import com.example.pokemonapp.models.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiServices {

    @GET("pokedex.json")
    suspend fun getPokemon(): Response<PokemonResponse>

}