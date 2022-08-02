package com.example.pokemonapp.models

import com.example.pokemonapp.activities.models.PokemonsModels
import com.google.gson.annotations.SerializedName
import java.util.ArrayList
import kotlin.collections.ArrayList

class PokemonModels {

    @SerializedName("pokemon") var pokemon :ArrayList<PokemonModel>? = null

}