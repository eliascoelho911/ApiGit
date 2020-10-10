package br.com.eliascoelho911.model

import java.io.Serializable

data class Repository(
    val name: String,
    val language: String
) : Serializable
