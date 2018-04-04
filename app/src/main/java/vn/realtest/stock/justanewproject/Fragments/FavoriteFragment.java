package vn.realtest.stock.justanewproject.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vn.realtest.stock.justanewproject.Activities.MainActivity;
import vn.realtest.stock.justanewproject.Adapter.HoseAdapter;
import vn.realtest.stock.justanewproject.Data.MarketStock;
import vn.realtest.stock.justanewproject.R;

/**
 * Created by Admin on 1/21/2018.
 */


public class FavoriteFragment extends Fragment {
    View view;
    List<MarketStock> marketStockList;
    RecyclerView rv_favorite;
    HoseAdapter mAdapter;
    String stock_name_data, stock_index_data, id_data;


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
        mAdapter = new HoseAdapter(marketStockList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rv_favorite.setLayoutManager(mLayoutManager);
        rv_favorite.setAdapter(mAdapter);
        prepareStockData();
        receiveData();
        return view;
    }

    private void receiveData() {
        MainActivity activity = (MainActivity) getActivity();
        stock_name_data = activity.getStockName();
        stock_index_data = activity.getStockIndex();
        id_data = activity.getIdData();
        if (!(stock_name_data.isEmpty() && stock_index_data.isEmpty())) {
            Toast.makeText(view.getContext(), "Trade: stock name: " + stock_name_data + " stock index: " + stock_index_data + " id_san: " + id_data, Toast.LENGTH_SHORT).show();
            MarketStock marketStock = new MarketStock(stock_name_data, "31.5", "-1.36%", "Vol: 3000");
            marketStockList.add(marketStock);
        }

    }

    private void prepareStockData() {
        mAdapter.notifyDataSetChanged();
    }

}
