package com.mfeldsztejn.storetest.main;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.mfeldsztejn.storetest.R;
import com.mfeldsztejn.storetest.dtos.Article;
import com.mfeldsztejn.storetest.main.adapters.ArticleAdapter;
import com.mfeldsztejn.storetest.main.helpers.PagingScrollCallback;
import com.mfeldsztejn.storetest.main.helpers.PagingScrollListener;
import com.mfeldsztejn.storetest.repositories.ApiManager;
import com.mfeldsztejn.storetest.repositories.ArticleApi;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * The main activity implementation
 */
public class MainActivity extends AppCompatActivity implements PagingScrollCallback, SearchView.OnQueryTextListener {

    /**
     * Api elements
     */
    //It should be package protected since it will be used from an inner class
    boolean isLoading;
    private Subscription subscription;
    private ArticleApi articleApi;

    /**
     * View elements
     */
    private RecyclerView articleRecyclerView;
    private int page;
    private ArticleAdapter articleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        articleApi = ApiManager.getInstance().create(ArticleApi.class);
        page = 1;
        getArticles();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        MenuItem shuffleItem = menu.findItem(R.id.menu_shuffle);
        shuffleItem.setIcon(R.drawable.ic_shuffle);

        MenuItem sortItem = menu.findItem(R.id.menu_sort);
        sortItem.setIcon(R.drawable.ic_sort);

        MenuItem searchItem = menu.findItem(R.id.menu_search);
        searchItem.setIcon(R.drawable.ic_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_sort:
                articleAdapter.sort();
                articleRecyclerView.smoothScrollToPosition(0);
                invalidateOptionsMenu();
                return true;

            case R.id.menu_shuffle:
                articleAdapter.shuffle();
                articleRecyclerView.smoothScrollToPosition(0);
                invalidateOptionsMenu();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     * If we have an subscription clear it to avoid memory leaks
     */
    void unsubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    /**
     * Get the articles from the rest api
     */
    void getArticles() {
        unsubscribe();

        isLoading = true;
        subscription = articleApi.getAllArticles(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Article>>() {
                    @Override
                    public void call(List<Article> articles) {
                        onArticlesLoaded(articles);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        //If an error should happen log it
                        throwable.printStackTrace();
                        unsubscribe();
                        isLoading = false;
                    }
                });
    }

    /**
     * Init the view elements
     */
    private void initView() {
        articleRecyclerView = (RecyclerView) findViewById(R.id.article_recycler_view);
        articleRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        articleRecyclerView.addOnScrollListener(new PagingScrollListener(this));
    }

    /**
     * Articles where obtained from the network
     *
     * @param articles The new articles
     */
    //Should be package private since its used from inside an inner class
    void onArticlesLoaded(List<Article> articles) {
        isLoading = false;
        if (articleAdapter == null) {
            articleAdapter = new ArticleAdapter(articles);
            articleRecyclerView.setAdapter(articleAdapter);
        } else {
            articleAdapter.addAll(articles);
            articleAdapter.notifyDataSetChanged();
        }
    }

    /**
     * On scrolled implementation meaning the recycler view scrolled enough
     */
    @Override
    public void onScrolled() {
        if (!isLoading) {
            page++;
            getArticles();
        }
    }

    /**
     * The on query text submit implementation for the search view
     *
     * @param query the query submitted
     * @return false since we don't handle the event
     */
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    /**
     * The on query text change implementation for the search view
     *
     * @param query the query
     * @return true since we handle the event
     */
    @Override
    public boolean onQueryTextChange(String query) {
        articleAdapter.filter(query);
        articleRecyclerView.smoothScrollToPosition(0);
        return true;
    }
}
