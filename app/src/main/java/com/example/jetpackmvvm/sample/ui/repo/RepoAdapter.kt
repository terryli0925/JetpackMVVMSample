package com.example.jetpackmvvm.sample.ui.repo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackmvvm.sample.data.model.Repo
import com.example.jetpackmvvm.sample.databinding.RepoItemBinding
import com.example.jetpackmvvm.sample.ui.repo.RepoAdapter.RepoViewHolder

class RepoAdapter internal constructor(private val items: MutableList<Repo>) : RecyclerView.Adapter<RepoViewHolder>() {

    class RepoViewHolder(private val binding: RepoItemBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(repo: Repo) {
            binding.repo = repo
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RepoItemBinding.inflate(layoutInflater, parent, false)
        return RepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repo = items[position]
        holder.bind(repo)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun clearItems() {
        val size = items.size
        items.clear()
        notifyItemRangeRemoved(0, size)
    }

    fun swapItems(newItems: List<Repo>?) {
        if (newItems == null) {
            clearItems()
        } else {
            val result = DiffUtil.calculateDiff(RepoDiffCallback(items, newItems))
            items.clear()
            items.addAll(newItems)
            result.dispatchUpdatesTo(this)
        }
    }

    private class RepoDiffCallback constructor(
        private val oldList: List<Repo>,
        private val newList: List<Repo>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldId = oldList[oldItemPosition].id
            val newId = newList[newItemPosition].id
            return oldId == newId
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val (_, name, _, description, owner, stars) = oldList[oldItemPosition]
            val (_, name1, _, description1, owner1, stars1) = newList[newItemPosition]

            return owner.avatarUrl == owner1.avatarUrl && name == name1 && description == description1 && stars == stars1
        }
    }
}