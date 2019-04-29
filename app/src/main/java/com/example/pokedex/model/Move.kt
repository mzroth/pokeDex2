package com.example.pokedex.model

import com.google.gson.annotations.SerializedName

class Move {
    @SerializedName("move")
    val move: NameUrl? = null

    @SerializedName("version_group_details")
    val versionGroupDetails: List<VersionGroupDetail>? = null
}
