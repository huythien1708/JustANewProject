package vn.realtest.stock.justanewproject.Adapter;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import vn.realtest.stock.justanewproject.Data.OpenOrder;
import vn.realtest.stock.justanewproject.R;

/**
 * Created by Paul on 3/25/2018.
 */

public class OpenOrderAdapter extends RecyclerView.Adapter<OpenOrderAdapter.OpenOrderViewHolder> {
    List<OpenOrder> openOrderList;
    int increase_value, decrease_value;

    public static class OpenOrderViewHolder extends RecyclerView.ViewHolder {
        CardView cv_open_order;
        TextView stock_name, stock_amount, stock_value, buy_sell;

        public OpenOrderViewHolder(View itemView) {
            super(itemView);
            cv_open_order = (CardView) itemView.findViewById(R.id.cv_open_order);
            stock_name = (TextView) itemView.findViewById(R.id.tv_stock_name_open_order);
            stock_amount = (TextView) itemView.findViewById(R.id.tv_stock_amount_open_order);
            buy_sell = (TextView) itemView.findViewById(R.id.tv_buy_sell_open_order);
            stock_value = (TextView) itemView.findViewById(R.id.tv_stock_value_open_order);
        }
    }

    public OpenOrderAdapter(List<OpenOrder> openOrderList) {
        this.openOrderList = openOrderList;
    }

    @Override
    public OpenOrderViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.open_order_item, viewGroup, false);
        increase_value = ContextCompat.getColor(v.getContext(), R.color.increase_value);
        decrease_value = ContextCompat.getColor(v.getContext(), R.color.decrease_value);
        return new OpenOrderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(OpenOrderViewHolder openOrderViewHolder, int i) {
        OpenOrder openOrder = openOrderList.get(i);
        openOrderViewHolder.stock_name.setText(openOrder.getStock_name());
        openOrderViewHolder.stock_amount.setText(openOrder.getStock_amount());
        openOrderViewHolder.buy_sell.setText(openOrder.getBuy_sell());
        openOrderViewHolder.stock_value.setText(openOrder.getStock_value());
        if (openOrderViewHolder.buy_sell.getText().toString().equals("Mua")) {
            openOrderViewHolder.buy_sell.setBackgroundColor(increase_value);
        } else if (openOrderViewHolder.buy_sell.getText().toString().equals("Bán")) {
            openOrderViewHolder.buy_sell.setBackgroundColor(decrease_value);
        }
    }

    @Override
    public int getItemCount() {
        return openOrderList.size();
    }


}
