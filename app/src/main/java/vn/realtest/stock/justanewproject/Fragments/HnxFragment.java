package vn.realtest.stock.justanewproject.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import vn.realtest.stock.justanewproject.Activities.MainActivity;
import vn.realtest.stock.justanewproject.Adapter.MarketAdapter;
import vn.realtest.stock.justanewproject.Data.MarketStock;
import vn.realtest.stock.justanewproject.R;

/**
 * Created by Admin on 1/21/2018.
 */

public class HnxFragment extends Fragment {

    View view;
    List<MarketStock> marketStockList;
    RecyclerView rv_hnx;
    MarketAdapter mAdapter;

    public static HnxFragment newInstance() {
        HnxFragment fragment = new HnxFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hnx, container, false);
        marketStockList = new ArrayList<>();
        rv_hnx = (RecyclerView) view.findViewById(R.id.rv_hnx);
        mAdapter = new MarketAdapter(marketStockList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rv_hnx.setLayoutManager(mLayoutManager);
        rv_hnx.setAdapter(mAdapter);
        prepareStockData();
        return view;
    }


    private void prepareStockData() {
        MarketStock marketStock = new MarketStock("HVN", "31.5", "-1.36%", "Vol: 3000");
        marketStockList.add(marketStock);

        marketStock = new MarketStock("ACB","20.5","0.00%", "Vol: 5000");
        marketStockList.add(marketStock);

        marketStock = new MarketStock("VCB","20.5","+5.6%", "Vol: 5000");
        marketStockList.add(marketStock);

        mAdapter.notifyDataSetChanged();
    }
}
