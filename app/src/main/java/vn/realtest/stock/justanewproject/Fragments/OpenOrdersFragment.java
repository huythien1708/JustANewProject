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

import vn.realtest.stock.justanewproject.Adapter.OpenOrderAdapter;
import vn.realtest.stock.justanewproject.Data.OpenOrder;
import vn.realtest.stock.justanewproject.R;

public class OpenOrdersFragment extends Fragment {
    View view;
    List<OpenOrder> openOrderList;
    RecyclerView rv_open_order;
    OpenOrderAdapter mAdapter;

    public static OpenOrdersFragment newInstance() {
        OpenOrdersFragment fragment = new OpenOrdersFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_open_order, container, false);
        openOrderList = new ArrayList<>();
        rv_open_order = (RecyclerView) view.findViewById(R.id.rv_open_orders);
        mAdapter = new OpenOrderAdapter(openOrderList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rv_open_order.setLayoutManager(mLayoutManager);
        rv_open_order.setAdapter(mAdapter);
        prepareData();
        return view;
    }

    private void prepareData() {
        OpenOrder openOrder = new OpenOrder("Bán", "PVS", "21.2", "2000");
        openOrderList.add(openOrder);

        openOrder = new OpenOrder("Mua", "PVS", "21.2", "2000");
        openOrderList.add(openOrder);

        openOrder = new OpenOrder("Bán", "PVS", "21.2", "2000");
        openOrderList.add(openOrder);

        openOrder = new OpenOrder("Mua", "PVS", "21.2", "2000");
        openOrderList.add(openOrder);

        openOrder = new OpenOrder("Bán", "PVS", "21.2", "2000");
        openOrderList.add(openOrder);

        openOrder = new OpenOrder("Mua", "PVS", "21.2", "2000");
        openOrderList.add(openOrder);

        openOrder = new OpenOrder("Bán", "PVS", "21.2", "2000");
        openOrderList.add(openOrder);
    }
}
