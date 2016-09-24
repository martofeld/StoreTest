package com.mfeldsztejn.storetest.main.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mfeldsztejn.storetest.R;
import com.mfeldsztejn.storetest.dtos.Article;
import com.mfeldsztejn.storetest.main.helpers.AlphabeticalComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This adapter is used to load the data to the recycler view
 */
public class ArticleAdapter extends RecyclerView.Adapter<ArticleViewHolder> {
    /**
     * The articles
     */
    private List<Article> articles;
    /**
     * The original articles before filtering
     */
    private List<Article> originalArticles;
    /**
     * A comparator to sort alphabetically
     */
    private AlphabeticalComparator comparator;

    /**
     * Constructor taking a list of the articles
     *
     * @param articles The original articles
     */
    public ArticleAdapter(List<Article> articles) {
        this.articles = articles;
        this.originalArticles = new ArrayList<>(articles);
        this.comparator = new AlphabeticalComparator(true);
    }

    /**
     * On create view holder implementation
     * @param parent    the parent
     * @param viewType  the view type
     * @return A view holder for the article
     */
    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ArticleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.article_view, parent, false));
    }

    /**
     * On bind view holder implementation
     * @param holder    the holder to bind
     * @param position  the position of the holder
     */
    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        holder.bind(articles.get(position));
    }

    /**
     * Get item count implementation
     * @return The size of the items
     */
    @Override
    public int getItemCount() {
        return articles.size();
    }

    /**
     * Add articles to the current list (used for paging)
     * @param articles  The articles to add
     */
    public void addAll(List<Article> articles) {
        this.articles.addAll(articles);
        this.originalArticles.addAll(articles);
    }

    /**
     * Sort the list
     */
    public void sort() {
        comparator.toggle();
        Collections.sort(originalArticles, comparator);
        notifyDataSetChanged();
    }

    /**
     * Shuffle the list
     */
    public void shuffle() {
        Collections.shuffle(originalArticles);
        notifyDataSetChanged();
    }

    /**
     * Filter the list based on a query
     * @param query the query from which to filter
     */
    public void filter(String query) {
        final String lowerCaseQuery = query.toLowerCase();
        List<Article> filterFrom = query.length() <= 1 ? originalArticles : articles;

        final List<Article> filteredArticles = new ArrayList<>();
        for (Article model : filterFrom) {
            final String text = model.getName().toLowerCase();
            if (text.contains(lowerCaseQuery)) {
                filteredArticles.add(model);
            }
        }
        this.articles = filteredArticles;
        notifyDataSetChanged();
    }
}
