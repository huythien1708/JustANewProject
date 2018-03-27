package vn.realtest.stock.justanewproject.Fragments;

/**
 * Created by Paul on 3/20/2018.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import vn.realtest.stock.justanewproject.Adapter.HistoryAdapter;
import vn.realtest.stock.justanewproject.Data.History;
import vn.realtest.stock.justanewproject.R;

public class TradeHistoryFragment extends Fragment {
    View view;
    List<History> historyList;
    RecyclerView rv_history;
    HistoryAdapter mAdapter;

    public static TradeHistoryFragment newInstance() {
        TradeHistoryFragment fragment = new TradeHistoryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_trade_history, container, false);
        historyList = new ArrayList<>();
        rv_history = (RecyclerView) view.findViewById(R.id.rv_history);
        mAdapter = new HistoryAdapter(historyList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rv_history.setLayoutManager(mLayoutManager);
        rv_history.setAdapter(mAdapter);
        prepareData();
        return view;
    }

    private void prepareData() {
        History history = new History("Bán","PVS", "21.2", "2000","1000/2000","15:30 25-03");
        historyList.add(history);

        history = new History("Mua","PVS", "21.2", "2000","1000/2000","15:30 25-03");
        historyList.add(history);


        history = new History("Bán","PVS", "21.2", "2000","1000/2000","15:30 25-03");
        historyList.add(history);

        history = new History("Mua","PVS", "21.2", "2000","1000/2000","15:30 25-03");
        historyList.add(history);

        history = new History("Bán","PVS", "21.2", "2000","1000/2000","15:30 25-03");
        historyList.add(history);

        history = new History("Mua","PVS", "21.2", "2000","1000/2000","15:30 25-03");
        historyList.add(history);

        history = new History("Bán","PVS", "21.2", "2000","1000/2000","15:30 25-03");
        historyList.add(history);

        history = new History("Mua","PVS", "21.2", "2000","1000/2000","15:30 25-03");
        historyList.add(history);
    }
}