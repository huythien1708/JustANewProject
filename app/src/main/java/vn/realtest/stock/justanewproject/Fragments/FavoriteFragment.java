package vn.realtest.stock.justanewproject.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vn.realtest.stock.justanewproject.Activities.MainActivity;
import vn.realtest.stock.justanewproject.Adapter.FavoriteAdapter;
import vn.realtest.stock.justanewproject.Adapter.HoseAdapter;
import vn.realtest.stock.justanewproject.Data.FavoriteData;
import vn.realtest.stock.justanewproject.Data.MarketStock;
import vn.realtest.stock.justanewproject.Databases.FavoriteHelper;
import vn.realtest.stock.justanewproject.Models.Stock;
import vn.realtest.stock.justanewproject.R;

/**
 * Created by Admin on 1/21/2018.
 */


public class FavoriteFragment extends Fragment {
    View view;
    static List<MarketStock> marketStockList;
    RecyclerView rv_favorite;
    static FavoriteAdapter mAdapter;
    String stock_name_data, stock_index_data, id_data;
    Context context;
    static List<FavoriteData> favoriteDataList;
    static FavoriteHelper db;

    public static FavoriteFragment newInstance() {
        FavoriteFragment fragment = new FavoriteFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateListView();
    }

    public static void updateListView() {
        marketStockList.clear();
        favoriteDataList.clear();
        List<FavoriteData> list = db.getAllFavoriteData();
        favoriteDataList.addAll(list);
        for (int i = 1; i <= db.getDatabaseFavoriteCount(); i++) {
            if (db.getFavoriteData(i) != null) {
                MarketStock marketStock = new MarketStock(db.getFavoriteData(i).getStock_name(), (float) 31.5, (float) -1.36, 3000);
                marketStockList.add(marketStock);
            }

        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_favorite, container, false);
        marketStockList = new ArrayList<>();
        rv_favorite = (RecyclerView) view.findViewById(R.id.rv_favorite);
        favoriteDataList = new ArrayList<FavoriteData>();
        db = new FavoriteHelper(this.getContext());

        //get data ghi vao database, FavoriteData gồm 3 thông số: stock_name, Stock_index, id_san
        marketStockList.clear();
        favoriteDataList.clear();
        List<FavoriteData> list = db.getAllFavoriteData();
        favoriteDataList.addAll(list);
        for (int i = 1; i <= db.getDatabaseFavoriteCount(); i++) {
            if (db.getFavoriteData(i) != null) {
                MarketStock marketStock = new MarketStock(db.getFavoriteData(i).getStock_name(), (float) 31.5, (float) -1.36, 3000);
                marketStockList.add(marketStock);
            }

        }


        mAdapter = new FavoriteAdapter(marketStockList, context);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rv_favorite.setLayoutManager(mLayoutManager);
        rv_favorite.setAdapter(mAdapter);

//        registerForContextMenu(rv_favorite);
        return view;
    }

    private void receiveData() {
        MainActivity activity = (MainActivity) getActivity();
        stock_name_data = activity.getStockName();
        stock_index_data = activity.getStockIndex();
        id_data = activity.getIdData();
  
        if (!(stock_name_data.isEmpty() && stock_index_data.isEmpty())) {
            MarketStock marketStock = new MarketStock(stock_name_data, (float)31.5, (float)-1.36, 3000);
            marketStockList.add(marketStock);
        }
    }
}
