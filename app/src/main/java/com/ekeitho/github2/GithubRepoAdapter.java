package com.ekeitho.github2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ekeitho.github2.dagger.ActivityScope;
import com.ekeitho.github2.databinding.ListViewBinding;
import com.ekeitho.github2.model.GithubRepos;

import java.util.ArrayList;

import javax.inject.Inject;

@ActivityScope
public class GithubRepoAdapter extends RecyclerView.Adapter<GithubRepoAdapter.RepoHolder>{

    @Inject MainActivity mainActivity;
    private ArrayList<GithubRepos> repos;

    @Inject
    public GithubRepoAdapter() {
        repos = new ArrayList<>();
    }

    public void updateRepos(GithubRepos repo) {
        this.repos.add(repo);
        this.notifyItemInserted(repos.size() - 1);
    }

    @Override
    public RepoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RepoHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view, parent, false));
    }

    @Override
    public void onBindViewHolder(RepoHolder holder, int position) {
        holder.binding.setRepo(repos.get(position));
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    public class RepoHolder extends RecyclerView.ViewHolder {

        private ListViewBinding binding;

        public RepoHolder(View view) {
            super(view);
            binding = ListViewBinding.bind(view);
        }
    }
}
