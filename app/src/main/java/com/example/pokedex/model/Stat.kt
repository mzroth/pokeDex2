package com.example.pokedex.model

import com.google.gson.annotations.SerializedName

class Stat {
    @SerializedName("base_stat")
    val baseStat: Int? = null

    @SerializedName("effort")
    val effort: Int? = null

    @SerializedName("stat")
    val stat: NameUrl? = null
}
