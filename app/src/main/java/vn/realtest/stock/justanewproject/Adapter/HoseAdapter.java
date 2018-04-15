package vn.realtest.stock.justanewproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import vn.realtest.stock.justanewproject.Activities.TradeActivity;
import vn.realtest.stock.justanewproject.Data.MarketStock;
import vn.realtest.stock.justanewproject.R;

/**
 * Created by Paul on 3/13/2018.
 */

public class HoseAdapter extends RecyclerView.Adapter<HoseAdapter.MarketStockViewHolder> {
    List<MarketStock> marketStockList;
    int increase_value, decrease_value;
    Context context;
    String index;

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

    public HoseAdapter(List<MarketStock> marketStockList, Context context) {
        this.marketStockList = marketStockList;
        this.context = context;
    }

    @Override
    public MarketStockViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.market_stock_item, viewGroup, false);
        increase_value = ContextCompat.getColor(v.getContext(), R.color.increase_value);
        decrease_value = ContextCompat.getColor(v.getContext(), R.color.decrease_value);
        return new MarketStockViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MarketStockViewHolder holder, final int position) {
        final MarketStock marketStock = marketStockList.get(position);
        holder.cv_market.setTag(position);
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
        holder.cv_market.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = String.valueOf(view.getTag());
                String stock_name = marketStock.getStock_name();
                Intent intent = new Intent("hose_adapter");
                intent.putExtra("stockname", stock_name);
                intent.putExtra("index", index);
                intent.putExtra("id_san", "HOSE");
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                context.startActivity(new Intent(context, TradeActivity.class));
            }
        });
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