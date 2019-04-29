package com.example.pokedex.model

import com.google.gson.annotations.SerializedName

class PokeType {
    @SerializedName("slot")
    val slot: Int? = null

    @SerializedName("type")
    val type: NameUrl? = null
}
