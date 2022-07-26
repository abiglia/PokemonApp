package com.example.pokemonapp.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class PokemonModel {

    @SerializedName("id")
    @Expose
    val id: Double? = null

    @SerializedName("num")
    @Expose
    val num: String? = null

    @SerializedName("name")
    @Expose
    val name: String? = null

    @SerializedName("img")
    @Expose
    val img: String? = null

    @SerializedName("type")
    @Expose
    val type: List<String>? = null

    @SerializedName("height")
    @Expose
    val height: String? = null

    @SerializedName("weight")
    @Expose
    val wieght: String? = null

    @SerializedName("candy")
    @Expose
    val candy: String? = null

    @SerializedName("candy_count")
    @Expose
    val candyCount: Double? = null

    @SerializedName("egg")
    @Expose
    val egg: String? = null

    @SerializedName("spawn_chance")
    @Expose
    val spawnChance: Double? = null

    @SerializedName("avg_spawns")
    @Expose
    val avgSpawns: Double? = null

    @SerializedName("spawn_time")
    @Expose
    val spawnTime: String? = null

    @SerializedName("multipliers")
    @Expose
    val multipliers: List<Double>? = null

    @SerializedName("weaknesses")
    @Expose
    val weaknesses: List<String>? = null

    @SerializedName("next_evolution")
    @Expose
    val nextEvolution: List<NextEvolutionModel>? = null


}