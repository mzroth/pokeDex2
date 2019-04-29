package com.example.pokedex.model

import com.google.gson.annotations.SerializedName

class AllPokemon {
//    The only variable I am keeping from the API call for all pokemon
    @SerializedName("results")
    val results: List<NameUrl>? = null
}
