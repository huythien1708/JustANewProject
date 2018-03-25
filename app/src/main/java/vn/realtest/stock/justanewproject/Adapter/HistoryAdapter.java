package vn.realtest.stock.justanewproject.Adapter;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import vn.realtest.stock.justanewproject.Data.History;
import vn.realtest.stock.justanewproject.R;

/**
 * Created by Paul on 3/25/2018.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    List<History> historyList;
    int increase_value, decrease_value;

    public static class HistoryViewHolder extends RecyclerView.ViewHolder {
        CardView cv_history;
        TextView stock_name, stock_amount, stock_value, buy_sell, matched_stock_amount, matched_date;

        public HistoryViewHolder(View itemView) {
            super(itemView);
            cv_history = (CardView) itemView.findViewById(R.id.cv_history);
            stock_name = (TextView) itemView.findViewById(R.id.stock_name_history);
            stock_amount = (TextView) itemView.findViewById(R.id.tv_trade_amount_history);
            buy_sell = (TextView) itemView.findViewById(R.id.tv_buy_sell_history);
            stock_value = (TextView) itemView.findViewById(R.id.tv_matched_value_history);
            matched_date = (TextView) itemView.findViewById(R.id.tv_time_matched_history);
            matched_stock_amount = (TextView) itemView.findViewById(R.id.tv_matched_amount_history);

        }
    }

    public HistoryAdapter(List<History> historyList) {
        this.historyList = historyList;
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.history_item, viewGroup, false);
        increase_value = ContextCompat.getColor(v.getContext(), R.color.increase_value);
        decrease_value = ContextCompat.getColor(v.getContext(), R.color.decrease_value);
        return new HistoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(HistoryViewHolder historyViewHolder, int i) {
        History history = historyList.get(i);
        historyViewHolder.stock_name.setText(history.getStock_name());
        historyViewHolder.stock_amount.setText(history.getStock_amount());
        historyViewHolder.buy_sell.setText(history.getBuy_sell());
        historyViewHolder.stock_value.setText(history.getMatched_value());
        historyViewHolder.matched_stock_amount.setText(history.getMatched_amount());
        historyViewHolder.matched_date.setText(history.getTime_matched());
        if(historyViewHolder.buy_sell.getText().toString().equals("Mua")){
            historyViewHolder.buy_sell.setBackgroundColor(increase_value);
        } else if (historyViewHolder.buy_sell.getText().toString().equals("Bán")){
            historyViewHolder.buy_sell.setBackgroundColor(decrease_value);
        }
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }


}
