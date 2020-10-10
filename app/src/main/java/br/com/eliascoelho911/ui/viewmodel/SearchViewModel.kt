package br.com.eliascoelho911.ui.viewmodel

import androidx.lifecycle.ViewModel
import br.com.eliascoelho911.model.Repository
import br.com.eliascoelho911.repository.GitRepository
import org.koin.java.KoinJavaComponent.inject

class SearchViewModel : ViewModel() {
    private val gitRepository: GitRepository by inject(GitRepository::class.java)

    fun getRepositories(
        username: String,
        success: (List<Repository>) -> Unit,
        failure: (error: String) -> Unit
    ) {
        gitRepository.getRepositories(username, success = {
            success(it)
        }, failure = {
            failure(it)
        })
    }
}