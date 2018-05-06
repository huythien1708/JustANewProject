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
import vn.realtest.stock.justanewproject.Models.StockType;
import vn.realtest.stock.justanewproject.R;
import vn.realtest.stock.justanewproject.Utils.GlobalStorage.GlobalData;
import vn.realtest.stock.justanewproject.Utils.GlobalStorage.OnDataLoadedListener;
import vn.realtest.stock.justanewproject.Utils.GlobalStorage.StockStorage;

import static vn.realtest.stock.justanewproject.Models.StockType.HNX;

/**
 * Created by Admin on 1/21/2018.
 */


public class FavoriteFragment extends Fragment {
    private View view;
    private static List<MarketStock> marketStockList;
    private RecyclerView rv_favorite;
    private static FavoriteAdapter mAdapter;
    private Context context;
    private static List<FavoriteData> favoriteDataList;
    private static FavoriteHelper db;

    public static FavoriteFragment newInstance() {
        FavoriteFragment fragment = new FavoriteFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        marketStockList = new ArrayList<>();
        mAdapter = new FavoriteAdapter(marketStockList, context);
        favoriteDataList = new ArrayList<FavoriteData>();
        db = new FavoriteHelper(this.getContext());

        //get data ghi vao database, FavoriteData gồm 3 thông số: stock_name, Stock_index, id_san
        updateListView();
    }

    @Override
    public void onResume() {
        super.onResume();
        updateListView();
    }

    public static void updateListView() {
        if (marketStockList != null && favoriteDataList != null) {
            marketStockList.clear();
            favoriteDataList.clear();
            getData();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_favorite, container, false);
        rv_favorite = (RecyclerView) view.findViewById(R.id.rv_favorite);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rv_favorite.setLayoutManager(mLayoutManager);
        rv_favorite.setAdapter(mAdapter);
        updateListView();
        return view;
    }

    private static void getData() {
        for (int i = 1; i <= db.getDatabaseFavoriteCount(); i++) {
            if (db.getFavoriteData(i) != null) {
                ArrayList<Stock> data = StockStorage.getGlobalStockDataByType(getStockTypeFromIdData(db.getFavoriteData(i).getId_san()));

                if (data != null && data.size() > 0) {
                    Stock stock = data.get(Integer.parseInt(db.getFavoriteData(i).getStock_index()));
                    marketStockList.add(new MarketStock(
                            stock.getID() + "",
                            stock.getPriceMatched(),
                            stock.getOffsetMatched(),
                            stock.getTotalVolume())
                    );
                }
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    private static StockType getStockTypeFromIdData(String idData) {
        switch (idData) {
            case "HNX":
                return StockType.HNX;
            case "HOSE":
                return StockType.HOSE;
            case "UPCOM":
                return StockType.UPCOM;
            default:
                return StockType.HNX;
        }
    }

}
