package br.com.eliascoelho911.ui.viewmodel

import androidx.lifecycle.ViewModel
import br.com.eliascoelho911.model.User
import br.com.eliascoelho911.repository.GitRepository
import org.koin.java.KoinJavaComponent.inject

class SearchViewModel : ViewModel() {
    private val gitRepository: GitRepository by inject(GitRepository::class.java)

    fun getUser(
        username: String,
        success: (User) -> Unit,
        failure: (error: String) -> Unit
    ) {
        gitRepository.getRepositories(username, success = {
            val user = User(username, it)
            success(user)
        }, failure = {
            failure(it)
        })
    }
}