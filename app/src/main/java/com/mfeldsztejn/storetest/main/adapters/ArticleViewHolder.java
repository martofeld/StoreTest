package com.mfeldsztejn.storetest.main.adapters;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.mfeldsztejn.storetest.R;
import com.mfeldsztejn.storetest.dtos.Article;
import com.mfeldsztejn.storetest.repositories.ApiManager;

/**
 * Created by mfeldsztejn on 9/22/16.
 */

public class ArticleViewHolder extends RecyclerView.ViewHolder {
    private final TextView nameTextView;
    private final SimpleDraweeView imageView;

    public ArticleViewHolder(View itemView) {
        super(itemView);
        nameTextView = (TextView) itemView.findViewById(R.id.article_name);
        imageView = (SimpleDraweeView) itemView.findViewById(R.id.article_image);
    }

    public void bind(Article article) {
        nameTextView.setText(article.getName());
        imageView.setImageURI(Uri.parse(ApiManager.BASE_URL + article.getImageUrl()));
    }
}
