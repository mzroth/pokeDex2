package com.example.pokedex.model

import com.google.gson.annotations.SerializedName

class HeldItem {
    @SerializedName("item")
    val item: NameUrl? = null

    @SerializedName("version_details")
    val versionDetails: List<VersionDetail>? = null
}
