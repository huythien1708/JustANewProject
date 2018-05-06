package vn.realtest.stock.justanewproject.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import vn.realtest.stock.justanewproject.Adapter.SearchAdapter;
import vn.realtest.stock.justanewproject.Data.SearchData;
import vn.realtest.stock.justanewproject.Models.Stock;
import vn.realtest.stock.justanewproject.Models.StockType;
import vn.realtest.stock.justanewproject.R;
import vn.realtest.stock.justanewproject.Utils.GlobalStorage.StockStorage;
import vn.realtest.stock.justanewproject.Utils.RxImplementation.RxSearchObservable;

/**
 * Created by Paul on 5/5/2018.
 */

public class SearchActivity extends AppCompatActivity {
    private SearchView searchView;
    private RecyclerView rv_search;
    private TextView tv_search_result;
    private SearchAdapter mAdapter;
    private ArrayList<SearchData> searchDataList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchView = (SearchView) findViewById(R.id.search_view);

        ImageView searchClose = (ImageView) searchView.findViewById (android.support.v7.appcompat.R.id.search_close_btn);
        searchClose.setColorFilter (ContextCompat.getColor(getApplicationContext(), R.color.text_color), PorterDuff.Mode.SRC_ATOP);

        EditText searchBox =((EditText) searchView.findViewById (android.support.v7.appcompat.R.id.search_src_text));
        searchBox.setTextColor(Color.WHITE);
        searchBox.setHintTextColor(Color.WHITE);
        searchBox.setLinkTextColor(Color.WHITE);

        tv_search_result = (TextView) findViewById(R.id.tv_search_result);
        rv_search = (RecyclerView) findViewById(R.id.rv_search);
        tv_search_result.setVisibility(View.GONE);
        mAdapter = new SearchAdapter(searchDataList, getApplicationContext());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rv_search.setLayoutManager(layoutManager);
        rv_search.setItemAnimator(new DefaultItemAnimator());
        rv_search.setAdapter(mAdapter);

        searchView.setIconified(false);

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                return false;
            }
        });


        RxSearchObservable.fromView(searchView)
                .debounce(500, TimeUnit.MILLISECONDS)
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String text) throws Exception {
                        if (text.isEmpty()) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                })
                .distinctUntilChanged()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String result) throws Exception {
                        tv_search_result.setVisibility(View.VISIBLE);
                        showSearchedData(result, searchDataList);
                        mAdapter.notifyDataSetChanged();
                    }

                });

    }

    private void showSearchedData(String stockID, ArrayList<SearchData> showedSearchList) {
        showedSearchList.clear();
        showedSearchList.addAll(searchStock(stockID, StockStorage.getGlobalStockDataByType(StockType.HNX), "HNX"));
        showedSearchList.addAll(searchStock(stockID, StockStorage.getGlobalStockDataByType(StockType.HOSE), "HOSE"));
        showedSearchList.addAll(searchStock(stockID, StockStorage.getGlobalStockDataByType(StockType.UPCOM), "UPCOM"));
    }

    private ArrayList<SearchData> searchStock(String keyword, ArrayList<Stock> data, String id_san) {
        int cnt =0;
        keyword = keyword.toUpperCase();
        ArrayList<SearchData> results = new ArrayList<>();
        for(Stock stock : data) {
            if (stock.getID().contains(keyword)) {
                results.add(new SearchData(stock.getID(), stock.getPriceMatched(), stock.getOffsetMatched(), cnt, id_san));
            }
            cnt++;
        }
        return results;
    }
}
