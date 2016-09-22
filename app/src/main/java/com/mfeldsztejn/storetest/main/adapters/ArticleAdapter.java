package com.mfeldsztejn.storetest.main.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.mfeldsztejn.storetest.dtos.Article;

import java.util.List;

/**
 * Created by mfeldsztejn on 9/22/16.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleViewHolder> {
    private List<Article> articles;

    public ArticleAdapter(List<Article> articles) {

    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        holder.bind(articles.get(position));
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
}
