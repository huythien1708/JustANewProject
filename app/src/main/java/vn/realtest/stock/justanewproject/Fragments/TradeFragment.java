package vn.realtest.stock.justanewproject.Fragments;

/**
 * Created by Admin on 1/20/2018.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.realtest.stock.justanewproject.R;

public class TradeFragment extends Fragment {
    public static TradeFragment newInstance() {
        TradeFragment fragment = new TradeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_trade, container, false);
    }
}