package br.com.eliascoelho911.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import br.com.eliascoelho911.databinding.ItemRepositoryBinding
import br.com.eliascoelho911.model.Repository
import br.com.eliascoelho911.ui.recyclerview.adapter.RepositoriesAdapter.RepositoryViewHolder
import org.koin.java.KoinJavaComponent.inject

class RepositoriesAdapter(
    private val repositories: List<Repository>
) : ListAdapter<Repository, RepositoryViewHolder>(DiffUtil) {

    private val context: Context by inject(Context::class.java)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ItemRepositoryBinding.inflate(inflater, parent, false)
        return RepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(repositories[position])
    }

    override fun getItemCount() = repositories.size

    class RepositoryViewHolder(private val binding: ItemRepositoryBinding) :
        ViewHolder(binding.root) {
        fun bind(repository: Repository) {
            binding.repository = repository
        }
    }

    object DiffUtil : ItemCallback<Repository>() {
        override fun areItemsTheSame(oldItem: Repository, newItem: Repository) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Repository, newItem: Repository) =
            oldItem == newItem
    }

}