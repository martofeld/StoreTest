package com.mfeldsztejn.storetest.main.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mfeldsztejn.storetest.R;
import com.mfeldsztejn.storetest.dtos.Article;
import com.mfeldsztejn.storetest.main.helpers.AlphabeticalComparator;

import java.util.Collections;
import java.util.List;

/**
 * Created by mfeldsztejn on 9/22/16.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleViewHolder> {
    private List<Article> articles;
    private AlphabeticalComparator comparator;

    public ArticleAdapter(List<Article> articles) {
        this.articles = articles;
        this.comparator = new AlphabeticalComparator(true);
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ArticleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.article_view, parent, false));
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        holder.bind(articles.get(position));
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public void addAll(List<Article> articles) {
        this.articles.addAll(articles);
    }

    public void sort() {
        comparator.toggle();
        Collections.sort(articles, comparator);
        notifyDataSetChanged();
    }

    public void shuffle() {
        Collections.shuffle(articles);
        notifyDataSetChanged();
    }
}
