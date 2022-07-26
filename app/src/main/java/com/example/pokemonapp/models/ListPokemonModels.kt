package com.example.pokemonapp.models

import com.example.pokemonapp.activities.models.PokemonsModels
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

class ListPokemonModels {

    @SerializedName("pokemon") var pokemon :List<PokemonModel> = emptyList()

}