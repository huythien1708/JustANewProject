package vn.realtest.stock.justanewproject.Fragments;

/**
 * Created by Admin on 1/20/2018.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.realtest.stock.justanewproject.Adapter.FundAdapter;
import vn.realtest.stock.justanewproject.Data.FundStock;
import vn.realtest.stock.justanewproject.R;

public class FundFragment extends Fragment {
    ImageView show_total_balance;
    TextView total_balance;
    View view;
    List<FundStock> fundStockList = new ArrayList<>();
    RecyclerView rv_fund;
    FundAdapter mAdapter;

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
        //fix cứng total
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
        //RecyclerView
        rv_fund = (RecyclerView) view.findViewById(R.id.rv_fund);
        mAdapter = new FundAdapter(fundStockList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        rv_fund.setLayoutManager(mLayoutManager);
        rv_fund.setAdapter(mAdapter);
        //rv_fund.setHasFixedSize(true);

        prepareStockData();
        return view;
    }

    private void prepareStockData() {
        FundStock fund_stock = new FundStock("GMD", "3000", "03/12/18","Chưa bán được");
        fundStockList.add(fund_stock);

        fund_stock = new FundStock("HVN", "3000", "03/12/18","Bán được");
        fundStockList.add(fund_stock);

        fund_stock = new FundStock("PVS", "3000", "03/12/18","Chưa bán được");
        fundStockList.add(fund_stock);

        mAdapter.notifyDataSetChanged();
    }


}