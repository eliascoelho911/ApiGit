package br.com.eliascoelho911.repository

import br.com.eliascoelho911.model.Repository
import br.com.eliascoelho911.retrofit.GitRetrofit
import br.com.eliascoelho911.retrofit.callback.Answer
import org.koin.java.KoinJavaComponent.inject

class GitRepository {
    private val retrofit: GitRetrofit by inject(GitRetrofit::class.java)

    fun getRepositories(
        username: String,
        success: (List<Repository>) -> Unit,
        failure: (error: String) -> Unit
    ) {
        val service = retrofit.service
        val answer = Answer<List<Repository>>(success = {
            success(it)
        }, failure = {
            failure(it)
        })
        service.getRepositories(username).enqueue(answer)
    }
}