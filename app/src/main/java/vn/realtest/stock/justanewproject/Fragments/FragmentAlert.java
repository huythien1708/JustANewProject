package vn.realtest.stock.justanewproject.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.realtest.stock.justanewproject.R;

/**
 * Created by Paul on 5/8/2018.
 */

public class FragmentAlert extends Fragment{
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_alert, container, false);
        return view;
    }
}
