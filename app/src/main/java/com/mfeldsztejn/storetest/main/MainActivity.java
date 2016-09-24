package com.mfeldsztejn.storetest.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.mfeldsztejn.storetest.R;
import com.mfeldsztejn.storetest.dtos.Article;
import com.mfeldsztejn.storetest.main.adapters.ArticleAdapter;
import com.mfeldsztejn.storetest.repositories.ApiManager;
import com.mfeldsztejn.storetest.repositories.ArticleApiInterface;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final int PAGE_SIZE = 100;
    /**
     * View elements
     */
    //It should be package protected since it will be used from an inner class
    LinearLayoutManager layoutManager;
    /**
     * Api elements
     */
    //It should be package protected since it will be used from an inner class
    boolean isLoading;
    int page;
    private RecyclerView articleRecyclerView;
    private ArticleAdapter articleAdapter;
    private Subscription subscription;
    private ArticleApiInterface articleApi;

    /**
     * Other elements
     */
    private boolean isSorted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        articleApi = ApiManager.getInstance().create(ArticleApiInterface.class);
        page = 1;
        getArticles();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        MenuItem shuffleItem = menu.findItem(R.id.menu_shuffle);
        MenuItem sortItem = menu.findItem(R.id.menu_sort);
        shuffleItem.setIcon(R.drawable.ic_shuffle);
        sortItem.setIcon(R.drawable.ic_sort);
        if (isSorted) {
            shuffleItem.setVisible(true);
            sortItem.setVisible(false);
        } else {
            sortItem.setVisible(true);
            shuffleItem.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_sort:
                isSorted = true;
                articleAdapter.sort();
                invalidateOptionsMenu();
                return true;

            case R.id.menu_shuffle:
                isSorted = false;
                articleAdapter.shuffle();
                invalidateOptionsMenu();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    void getArticles() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }

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
                        isLoading = false;
                    }
                });
    }

    private void initView() {
        articleRecyclerView = (RecyclerView) findViewById(R.id.article_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        articleRecyclerView.setLayoutManager(layoutManager);
        articleRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                if (!isLoading) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0
                            && totalItemCount >= PAGE_SIZE) {
                        page++;
                        getArticles();
                    }
                }
            }
        });
    }

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
}
