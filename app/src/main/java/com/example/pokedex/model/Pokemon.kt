package com.example.pokedex.model

import com.google.gson.annotations.SerializedName

//I kept all information from the API call on a single pokemon so that I could customize more later. Many of these
//also have subclasses.
class Pokemon {
    @SerializedName("abilities")
    val abilities: List<Ability>? = null

    @SerializedName("base_experience")
    val baseExperience: Int? = null

    @SerializedName("forms")
    val forms: List<NameUrl>? = null

    @SerializedName("game_indices")
    val gameIndices: List<GameIndex>? = null

    @SerializedName("height")
    val height: Int? = null

    @SerializedName("held_items")
    val heldItems: List<HeldItem>? = null

    @SerializedName("id")
    val id: Int? = null

    @SerializedName("is_default")
    val isDefault: Boolean? = null

    @SerializedName("location_area_encounters")
    val locationAreaEncounters: String? = null

    @SerializedName("moves")
    val moves: List<Move>? = null

    @SerializedName("name")
    val name: String? = null

    @SerializedName("order")
    val order: Int? = null

    @SerializedName("species")
    val species: NameUrl? = null

    @SerializedName("sprites")
    val sprites: Sprites? = null

    @SerializedName("stats")
    val stats: List<Stat>? = null

    @SerializedName("types")
    val types: List<PokeType>? = null

    @SerializedName("weight")
    val weight: Int? = null
}