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
 * A view holder for the article
 */
public class ArticleViewHolder extends RecyclerView.ViewHolder {
    /**
     * The text view for the article name
     */
    private final TextView nameTextView;
    /**
     * The image view for the article image
     */
    private final SimpleDraweeView imageView;

    /**
     * A constructor for the view holder
     *
     * @param itemView the view holder view
     */
    public ArticleViewHolder(View itemView) {
        super(itemView);
        nameTextView = (TextView) itemView.findViewById(R.id.article_name);
        imageView = (SimpleDraweeView) itemView.findViewById(R.id.article_image);
    }

    /**
     * Binds an article to the view holder
     * @param article   the article to bind
     */
    public void bind(Article article) {
        nameTextView.setText(article.getName());
        String url;
        if (article.isImageUrlAbsolute()) {
            url = article.getImageUrl();
        } else {
            url = ApiManager.BASE_URL + article.getImageUrl();
        }
        imageView.setImageURI(Uri.parse(url));
    }
}
