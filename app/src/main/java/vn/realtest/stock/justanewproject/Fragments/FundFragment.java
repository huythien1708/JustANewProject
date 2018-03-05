package vn.realtest.stock.justanewproject.Fragments;

/**
 * Created by Admin on 1/20/2018.
 */

import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import vn.realtest.stock.justanewproject.R;

public class FundFragment extends Fragment {
    ImageView show_total_balance;
    TextView total_balance;
    View view;
    int cnt=0;
    public static FundFragment newInstance() {
        FundFragment fragment = new FundFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fund, container, false);
        show_total_balance = (ImageView) view.findViewById(R.id.img_show_total_balance);
        total_balance = (TextView) view.findViewById(R.id.tv_total_balance);
        total_balance.setText("************");
        show_total_balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(total_balance.getText().toString()=="************"){
                    total_balance.setText("10.000.000 VNĐ");
                } else {
                    total_balance.setText("************");
                }
            }
        });
        return view;
    }
}