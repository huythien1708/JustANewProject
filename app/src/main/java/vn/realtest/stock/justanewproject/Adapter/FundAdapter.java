package vn.realtest.stock.justanewproject.Adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import vn.realtest.stock.justanewproject.Data.Fund_stock;
import vn.realtest.stock.justanewproject.R;

/**
 * Created by Paul on 3/12/2018.
 */

public class FundRecycleViewAdapter extends RecyclerView.Adapter<FundRecycleViewAdapter.FundStockViewHolder> {
    List<Fund_stock> fundStockList;

    public static class FundStockViewHolder extends RecyclerView.ViewHolder {
        CardView cv_fund;
        TextView stock_name, stock_amount, stock_date, stock_can_trade;
        FundStockViewHolder(View itemView){
            super(itemView);
            cv_fund = (CardView) itemView.findViewById(R.id.cv_fund);
            stock_name = (TextView) itemView.findViewById(R.id.tv_stock_name);
            stock_amount = (TextView) itemView.findViewById(R.id.tv_stock_amount);
            stock_date = (TextView) itemView.findViewById(R.id.tv_stock_buy_date);
            stock_can_trade = (TextView) itemView.findViewById(R.id.tv_stock_can_trade);
        }
    }

    public FundRecycleViewAdapter(List<Fund_stock> fundStockList){
        this.fundStockList = fundStockList;
    }

    @Override
    public FundStockViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fund_stock_item, viewGroup, false);
        return new FundStockViewHolder(v);
    }

    @Override
    public void onBindViewHolder(FundStockViewHolder fundStockViewHolder, int i) {
       Fund_stock fund_stock = fundStockList.get(i);
       fundStockViewHolder.stock_name.setText(fund_stock.getStock_name());
       fundStockViewHolder.stock_amount.setText(fund_stock.getStock_amount());
       fundStockViewHolder.stock_date.setText(fund_stock.getStock_buy_date());
       fundStockViewHolder.stock_can_trade.setText(fund_stock.getStock_can_trade());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        Log.d("abc", "Đây là size: " + String.valueOf(fundStockList.size()));
        return fundStockList.size();
    }


}
