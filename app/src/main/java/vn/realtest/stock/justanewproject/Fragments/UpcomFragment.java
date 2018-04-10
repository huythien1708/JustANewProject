package vn.realtest.stock.justanewproject.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import vn.realtest.stock.justanewproject.Adapter.HoseAdapter;
import vn.realtest.stock.justanewproject.Adapter.UpcomAdapter;
import vn.realtest.stock.justanewproject.Data.MarketStock;
import vn.realtest.stock.justanewproject.R;

/**
 * Created by Admin on 1/21/2018.
 */

public class UpcomFragment extends Fragment {
    View view;
    List<MarketStock> marketStockList;
    RecyclerView rv_upcom;
    UpcomAdapter mAdapter;

    public static UpcomFragment newInstance() {
        UpcomFragment fragment = new UpcomFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_upcom, container, false);
        marketStockList = new ArrayList<>();
        rv_upcom = (RecyclerView) view.findViewById(R.id.rv_upcom);
        mAdapter = new UpcomAdapter(marketStockList, getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rv_upcom.setLayoutManager(mLayoutManager);
        rv_upcom.setAdapter(mAdapter);
        prepareStockData();
        return view;
    }

    private void prepareStockData() {

        MarketStock marketStock = new MarketStock("KBC", "31.5", "-1.36%", "Vol: 3000");
        marketStockList.add(marketStock);

        marketStock = new MarketStock("DVN", "20.5", "+5.6%", "Vol: 5000");
        marketStockList.add(marketStock);

        marketStock = new MarketStock("IDI", "20.5", "+5.6%", "Vol: 5000");
        marketStockList.add(marketStock);

        mAdapter.notifyDataSetChanged();
    }


}

