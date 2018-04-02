package vn.realtest.stock.justanewproject.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import vn.realtest.stock.justanewproject.Data.MarketStock;
import vn.realtest.stock.justanewproject.R;

/**
 * Created by Paul on 3/13/2018.
 */

public class MarketAdapter extends RecyclerView.Adapter<MarketAdapter.MarketStockViewHolder>{
    List<MarketStock> marketStockList;
    int increase_value, decrease_value;

    private enum RATESTATUS{
        UP, DOWN, NOTCHANGED
    }

    public static class MarketStockViewHolder extends RecyclerView.ViewHolder {
        CardView cv_market;
        TextView stock_name, stock_value, stock_change, stock_vol;

        public MarketStockViewHolder(View itemView) {
            super(itemView);
            cv_market = (CardView) itemView.findViewById(R.id.cv_market);
            stock_name = (TextView) itemView.findViewById(R.id.tv_stock_name_market);
            stock_value = (TextView) itemView.findViewById(R.id.tv_stock_value);
            stock_change = (TextView) itemView.findViewById(R.id.tv_stock_change);
            stock_vol = (TextView) itemView.findViewById(R.id.tv_stock_vol);

        }
    }

    public MarketAdapter(List<MarketStock> marketStockList){
        this.marketStockList = marketStockList;
    }

    @Override
    public MarketStockViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.market_stock_item, viewGroup, false );
        increase_value = ContextCompat.getColor(v.getContext(), R.color.increase_value);
        decrease_value = ContextCompat.getColor(v.getContext(), R.color.decrease_value);
        return new MarketStockViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MarketStockViewHolder holder, int position) {
        MarketStock marketStock = marketStockList.get(position);
        holder.stock_name.setText(marketStock.getStock_name());
        holder.stock_value.setText(String.valueOf(marketStock.getStock_value()));
        holder.stock_change.setText(String.valueOf(marketStock.getStock_change_rate()));
        holder.stock_vol.setText(String.valueOf(marketStock.getStock_vol()));

        switch (check_rate(marketStock.getStock_change_rate())) {
            case UP:
                holder.stock_change.setBackgroundColor(increase_value); break;
            case DOWN:
                holder.stock_change.setBackgroundColor(decrease_value); break;
            case NOTCHANGED:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return marketStockList.size();
    }

    public RATESTATUS check_rate(float rate){
        //rate tăng thì trả về true
        if(rate > 0) {
            return RATESTATUS.UP;
        }
        else if (rate < 0) {
            return RATESTATUS.DOWN;
        }
        return RATESTATUS.NOTCHANGED;
    }

}
