package br.com.eliascoelho911.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.com.eliascoelho911.R
import br.com.eliascoelho911.extensions.showErrorDialog
import br.com.eliascoelho911.extensions.showToast
import br.com.eliascoelho911.model.Repository
import br.com.eliascoelho911.ui.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.activity_search.*
import org.koin.android.viewmodel.ext.android.viewModel


class SearchActivity : AppCompatActivity() {
    private val usernameEditText by lazy {
        activity_search_username
    }
    private val viewModel: SearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setSupportActionBar(activity_search_toolbar)
    }

    fun View.search() {
        val username = usernameEditText.text.toString()
        if (username.isBlank()) {
            showToast(getString(R.string.error_username_blank))
        } else {
            viewModel.getRepositories(username, success = { repositories ->
                goToUserDetailsActivity(repositories, username)
            }, failure = {
                showErrorDialog(it)
            })
        }
    }

    private fun goToUserDetailsActivity(repositories: List<Repository>, username: String) {
        val intent = Intent(this@SearchActivity, UserDetailsActivity::class.java).apply {
            putExtra(KEY_REPOSITORIES, repositories.toTypedArray())
            putExtra(KEY_USERNAME, username)
        }
        startActivity(intent)
    }
}