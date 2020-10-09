package br.com.eliascoelho911.model

data class User(
    val name: String,
    val repositories: List<Repository>
)