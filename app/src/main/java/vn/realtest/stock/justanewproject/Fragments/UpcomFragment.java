package vn.realtest.stock.justanewproject.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import vn.realtest.stock.justanewproject.Adapter.HoseAdapter;
import vn.realtest.stock.justanewproject.Adapter.UpcomAdapter;
import vn.realtest.stock.justanewproject.Data.MarketStock;
import vn.realtest.stock.justanewproject.Utils.GlobalStorage.StockStorage;
import vn.realtest.stock.justanewproject.Utils.GlobalStorage.OnDataLoadedListener;
import vn.realtest.stock.justanewproject.Models.Stock;
import vn.realtest.stock.justanewproject.Models.StockType;
import vn.realtest.stock.justanewproject.R;

/**
 * Created by Admin on 1/21/2018.
 */

public class UpcomFragment extends Fragment {
    View view;
    List<MarketStock> marketStockList;
    RecyclerView rv_upcom;
    UpcomAdapter mAdapter;
    StockType stockType = StockType.UPCOM;

    public static UpcomFragment newInstance() {
        UpcomFragment fragment = new UpcomFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        marketStockList = new ArrayList<>();
        mAdapter = new UpcomAdapter(marketStockList, getContext());

        parseStockData(marketStockList, StockStorage.getGlobalStockDataByType(stockType));
        mAdapter.notifyDataSetChanged();

        StockStorage.AddOnDataLoadedListener(stockType, new OnDataLoadedListener() {
            @Override
            public void OnStockDataParsed() {
                parseStockData(marketStockList, StockStorage.getGlobalStockDataByType(stockType));
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_upcom, container, false);
        rv_upcom = (RecyclerView) view.findViewById(R.id.rv_upcom);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rv_upcom.setLayoutManager(mLayoutManager);
        rv_upcom.setAdapter(mAdapter);

        return view;
    }

    private void parseStockData(List<MarketStock> marketStockList, ArrayList<Stock> stocks) {
        if (stocks != null && !stocks.isEmpty()) {
            marketStockList.clear();
            for(Stock stock : stocks) {
                marketStockList.add(new MarketStock(
                        stock.getID() + "",
                        stock.getPriceMatched(),
                        stock.getOffsetMatched(),
                        stock.getTotalVolume())
                );
            }
        }
    }


}

