package com.example.pokemonapp.models

import com.google.gson.annotations.SerializedName
import kotlin.collections.ArrayList

class PokemonResponse {

    @SerializedName("pokemon")
    var pokemon: ArrayList<PokemonModel>? = null

}