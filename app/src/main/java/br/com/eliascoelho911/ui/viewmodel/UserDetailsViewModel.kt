package br.com.eliascoelho911.ui.viewmodel

import androidx.lifecycle.ViewModel
import br.com.eliascoelho911.model.User
import br.com.eliascoelho911.repository.GitRepository
import org.koin.java.KoinJavaComponent

class UserDetailsViewModel : ViewModel() {
    private val gitRepository: GitRepository by KoinJavaComponent.inject(GitRepository::class.java)

    fun getUser(
        username: String,
        success: (User) -> Unit,
        failure: (error: String) -> Unit
    ) {
        gitRepository.getUser(username, success = {
            success(it)
        }, failure = {
            failure(it)
        })
    }
}