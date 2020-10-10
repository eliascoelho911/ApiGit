package br.com.eliascoelho911.repository

import br.com.eliascoelho911.model.Repository
import br.com.eliascoelho911.model.User
import br.com.eliascoelho911.retrofit.GitRetrofit
import br.com.eliascoelho911.retrofit.callback.Answer
import org.koin.java.KoinJavaComponent.inject

class GitRepository {
    private val retrofit: GitRetrofit by inject(GitRetrofit::class.java)
    private val service = retrofit.service

    fun getRepositories(
        username: String,
        success: (List<Repository>) -> Unit,
        failure: (error: String) -> Unit
    ) {
        val answer = Answer<List<Repository>>(success = {
            success(it)
        }, failure = {
            failure(it)
        })
        service.getRepositories(username).enqueue(answer)
    }

    fun getUser(
        username: String,
        success: (User) -> Unit,
        failure: (error: String) -> Unit
    ) {
        val answer = Answer<User>(success = {
            success(it)
        }, failure = {
            failure(it)
        })
        service.getUser(username).enqueue(answer)
    }
}