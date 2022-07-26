package com.example.pokemonapp.services

import com.example.pokemonapp.models.ListPokemonModels
import com.example.pokemonapp.models.PokemonModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiServices {

    @GET("pokedex.json")
    suspend fun getPokemon(): Response<ListPokemonModels>

}