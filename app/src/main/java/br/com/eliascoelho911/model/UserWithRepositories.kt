package br.com.eliascoelho911.model

class UserWithRepositories(
    val user: User,
    val repositories: List<Repository>
)