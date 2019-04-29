package com.example.pokedex.model

import com.google.gson.annotations.SerializedName

class Ability {
    @SerializedName("ability")
    val ability: NameUrl? = null

    @SerializedName("is_hidden")
    val isHidden: Boolean? = null

    @SerializedName("slot")
    val slot: Int? = null
}
