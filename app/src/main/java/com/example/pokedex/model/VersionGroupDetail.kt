package com.example.pokedex.model

import com.google.gson.annotations.SerializedName

class VersionGroupDetail {
    @SerializedName("level_learned_at")
    val levelLearnedAt: Int? = null

    @SerializedName("move_learn_method")
    val moveLearnMethod: NameUrl? = null

    @SerializedName("version_group")
    val versionGroup: NameUrl? = null
}
