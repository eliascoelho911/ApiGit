package br.com.eliascoelho911.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.com.eliascoelho911.R
import br.com.eliascoelho911.extensions.showToast
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
            viewModel.getUser(username, success = {
                //TODO Go to repository screen
            }, failure = {
                showErrorDialog(it)
            })
        }
    }

    private fun showErrorDialog(it: String) {
        AlertDialog.Builder(this@SearchActivity).setTitle(getString(R.string.error)).setMessage(it)
            .setPositiveButton(
                "OK"
            ) { _, _ -> }.show()
    }
}