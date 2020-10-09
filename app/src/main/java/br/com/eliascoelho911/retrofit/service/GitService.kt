package br.com.eliascoelho911.retrofit.service

import br.com.eliascoelho911.model.Repository
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitService {
    @GET("{username}/repos")
    fun getRepositories(
        @Path("username") username: String
    ): Call<List<Repository>>
}