package com.example.jetpackmvvm.sample.ui.repo;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.jetpackmvvm.sample.data.model.Repo;
import com.example.jetpackmvvm.sample.databinding.RepoItemBinding;

import java.util.List;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.RepoViewHolder> {

    private List<Repo> items;

    RepoAdapter(List<Repo> items) {
        this.items = items;
    }

    class RepoViewHolder extends RecyclerView.ViewHolder {

        private final RepoItemBinding binding;

        RepoViewHolder(RepoItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Repo repo) {
            binding.setRepo(repo);
            binding.executePendingBindings();
        }
    }

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RepoItemBinding binding = RepoItemBinding.inflate(layoutInflater, parent, false);
        return new RepoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RepoViewHolder holder, int position) {
        Repo repo = items.get(position);
        holder.bind(repo);
        Glide.with(holder.itemView.getContext())
                .load(repo.owner.avatarUrl)
                .into(holder.binding.ownerAvatar);
        holder.binding.name.setText(repo.fullName);
        holder.binding.desc.setText(repo.description);
        holder.binding.stars.setText(""+repo.stars);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    void clearItems() {
        int size = this.items.size();
        this.items.clear();
        notifyItemRangeRemoved(0, size);
    }

    void setItems(List<Repo> newItems) {
        this.items.clear();
        this.items.addAll(newItems);
        notifyDataSetChanged();
    }

    void swapItems(List<Repo> newItems) {
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new RepoDiffCallback(this.items, newItems));
        this.items.clear();
        this.items.addAll(newItems);
        result.dispatchUpdatesTo(this);
    }

    private class RepoDiffCallback extends DiffUtil.Callback {

        private final List<Repo> mOldList;
        private final List<Repo> mNewList;

        RepoDiffCallback(List<Repo> oldList, List<Repo> newList) {
            this.mOldList = oldList;
            this.mNewList = newList;
        }

        @Override
        public int getOldListSize() {
            return mOldList != null ? mOldList.size() : 0;
        }

        @Override
        public int getNewListSize() {
            return mNewList != null ? mNewList.size() : 0;
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            int oldId = mOldList.get(oldItemPosition).id;
            int newId = mNewList.get(newItemPosition).id;
            return oldId == newId;
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            Repo oldRepo = mOldList.get(oldItemPosition);
            Repo newRepo = mNewList.get(newItemPosition);
            if (!oldRepo.owner.avatarUrl.equals(newRepo.owner.avatarUrl)) {
                return false; //如果有內容不同，就返回false
            }
            if (!oldRepo.name.equals(newRepo.name)) {
                return false; //如果有內容不同，就返回false
            }
            if (!oldRepo.description.equals(newRepo.description)) {
                return false;
            }
            if (oldRepo.stars != newRepo.stars) {
                return false;
            }
            return true;
        }
    }
}