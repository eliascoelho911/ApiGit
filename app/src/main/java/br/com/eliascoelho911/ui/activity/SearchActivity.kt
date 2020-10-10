
package br.com.eliascoelho911.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.eliascoelho911.R
import br.com.eliascoelho911.extensions.showToast
import kotlinx.android.synthetic.main.activity_search.*


class SearchActivity : AppCompatActivity() {
    private val usernameEditText by lazy {
        activity_search_username
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setSupportActionBar(activity_search_toolbar)
    }

    private fun search() {
        val username = usernameEditText.text.toString()
        if (username.isBlank()) {
            showToast(getString(R.string.error_username_blank))
        } else {
            goToUserDetailsActivity(username)
        }
    }

    @Suppress("unused")
    fun View.search() {
        this@SearchActivity.search()
    }

    private fun goToUserDetailsActivity(username: String) {
        val intent = Intent(this@SearchActivity, UserDetailsActivity::class.java).apply {
            putExtra(KEY_USERNAME, username)
        }
        startActivity(intent)
    }
}