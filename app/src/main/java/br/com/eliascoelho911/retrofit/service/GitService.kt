package br.com.eliascoelho911.retrofit.service

import br.com.eliascoelho911.model.Repository
import br.com.eliascoelho911.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitService {
    @GET("users/{username}/repos")
    fun getRepositories(
        @Path("username") username: String
    ): Call<List<Repository>>

    @GET("users/{username}")
    fun getUser(
        @Path("username") username: String
    ) : Call<User>
}