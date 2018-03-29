package vn.realtest.stock.justanewproject.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import vn.realtest.stock.justanewproject.Adapter.MarketAdapter;
import vn.realtest.stock.justanewproject.Data.MarketStock;
import vn.realtest.stock.justanewproject.Models.Stock;
import vn.realtest.stock.justanewproject.Presenters.StockPresenter;
import vn.realtest.stock.justanewproject.R;
import vn.realtest.stock.justanewproject.Utils.UrlEndpoints;

/**
 * Created by Admin on 1/21/2018.
 */

public class FavoriteFragment extends Fragment {
    View view;
    List<MarketStock> marketStockList;
    RecyclerView rv_favorite;
    MarketAdapter mAdapter;

    public static FavoriteFragment newInstance() {
        FavoriteFragment fragment = new FavoriteFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_favorite, container, false);
        marketStockList = new ArrayList<>();
        rv_favorite = (RecyclerView) view.findViewById(R.id.rv_favorite);
        mAdapter = new MarketAdapter(marketStockList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rv_favorite.setLayoutManager(mLayoutManager);
        rv_favorite.setAdapter(mAdapter);
        prepareStockData();
        return view;
    }

    private void prepareStockData() {
        Log.d("test", "Start fetching: " + System.currentTimeMillis() + "");
        marketStockList.clear();
        new StockPresenter(UrlEndpoints.StockDetail.HNX) {

            @Override
            public void OnStockModel(ArrayList<Stock> stocks) {
                parseStockData(marketStockList, stocks);
                Log.d("data", marketStockList.size() + "");
//                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void OnProgressUpdate(Void... values) {

            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        new StockPresenter(UrlEndpoints.StockDetail.HOSE) {

            @Override
            public void OnStockModel(ArrayList<Stock> stocks) {
                parseStockData(marketStockList, stocks);
                Log.d("data", marketStockList.size() + "");
//                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void OnProgressUpdate(Void... values) {

            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        new StockPresenter(UrlEndpoints.StockDetail.UPCOM) {

            @Override
            public void OnStockModel(ArrayList<Stock> stocks) {
                parseStockData(marketStockList, stocks);
                Log.d("data", marketStockList.size() + "");
//                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void OnProgressUpdate(Void... values) {

            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private void parseStockData(List<MarketStock> marketStockList, ArrayList<Stock> stocks) {
        Log.d("test", "Start parsing: " + System.currentTimeMillis() + "");
        for(Stock stock : stocks) {
            marketStockList.add(new MarketStock(
                    stock.getID() + "",
                    stock.getTotalValue() + "",
                    stock.getOffsetMatched() + "%",
                    stock.getTotalVolume() + "")
            );
        }
        Log.d("test", "End parsing: " + System.currentTimeMillis() + "");
    }
}
