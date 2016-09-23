package com.mfeldsztejn.storetest.main.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mfeldsztejn.storetest.R;
import com.mfeldsztejn.storetest.dtos.Article;

/**
 * Created by mfeldsztejn on 9/22/16.
 */

public class ArticleViewHolder extends RecyclerView.ViewHolder {
    private final TextView nameTextView;

    public ArticleViewHolder(View itemView) {
        super(itemView);
        nameTextView = (TextView) itemView.findViewById(R.id.article_name);
    }

    public void bind(Article article) {
        nameTextView.setText(article.getName());
    }
}
