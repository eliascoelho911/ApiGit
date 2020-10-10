package br.com.eliascoelho911.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @SerializedName("login") val name: String,
    @SerializedName("avatar_url") val avatarUrl: String,
    val repositories: List<Repository>
) : Serializable