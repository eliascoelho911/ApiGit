package br.com.eliascoelho911.ui.activity

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import br.com.eliascoelho911.R
import br.com.eliascoelho911.databinding.ActivityUserDetailsBinding
import br.com.eliascoelho911.extensions.showErrorDialog
import br.com.eliascoelho911.extensions.showToast
import br.com.eliascoelho911.model.Repository
import br.com.eliascoelho911.model.User
import br.com.eliascoelho911.ui.recyclerview.adapter.RepositoriesAdapter
import br.com.eliascoelho911.ui.viewmodel.UserDetailsViewModel
import com.makeramen.roundedimageview.RoundedTransformationBuilder
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import kotlinx.android.synthetic.main.activity_user_details.*
import kotlinx.android.synthetic.main.activity_user_details.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class UserDetailsActivity : AppCompatActivity() {
    private val repositoriesList by lazy {
        activity_user_details_repositories
    }
    private val userImage by lazy {
        activity_user_details_user_image
    }
    private val viewModel: UserDetailsViewModel by viewModel()
    private val userObservable = ObservableField<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = configureDataBinding()
        val toolbar = binding.root.activity_user_details_toolbar
        configureToolbar(toolbar)
        findUser()
        findRepositories()
    }

    private fun configureToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun configureDataBinding() : ActivityUserDetailsBinding {
        return DataBindingUtil.setContentView<ActivityUserDetailsBinding>(
            this,
            R.layout.activity_user_details
        ).apply { user = userObservable }
    }

    private fun findRepositories() {
        if (intent.hasExtra(KEY_REPOSITORIES)) {
            @Suppress("UNCHECKED_CAST")
            val repositoriesReturn =
                intent.getSerializableExtra(KEY_REPOSITORIES) as Array<Repository>
            val repositories = repositoriesReturn.toList()
            repositoriesList.adapter = RepositoriesAdapter(repositories)
        }
    }

    private fun findUser() {
        if (intent.hasExtra(KEY_USERNAME)) {
            val username = intent.getStringExtra(KEY_USERNAME)
            if (username == null) {
                showToast(getString(R.string.error_finding_username))
            } else {
                viewModel.getUser(username, success = { user ->
                    userObservable.set(user)
                    setUserImage(user)
                }, failure = {
                    showErrorDialog(it)
                })
            }
        }
    }

    private fun setUserImage(user: User) {
        val transformation: Transformation = RoundedTransformationBuilder()
            .borderColor(Color.WHITE)
            .borderWidthDp(5f)
            .oval(true)
            .build()

        Picasso.get()
            .load(user.avatarUrl)
            .fit()
            .transform(transformation)
            .into(userImage)
    }
}