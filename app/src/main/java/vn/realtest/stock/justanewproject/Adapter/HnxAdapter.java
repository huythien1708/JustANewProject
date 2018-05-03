package vn.realtest.stock.justanewproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import vn.realtest.stock.justanewproject.Activities.TradeActivity;
import vn.realtest.stock.justanewproject.Data.FavoriteData;
import vn.realtest.stock.justanewproject.Data.MarketStock;
import vn.realtest.stock.justanewproject.Databases.FavoriteHelper;
import vn.realtest.stock.justanewproject.R;

/**
 * Created by Paul on 3/13/2018.
 */

public class HnxAdapter extends RecyclerView.Adapter<HnxAdapter.MarketStockViewHolder> {
    List<MarketStock> marketStockList;
    int increase_value, decrease_value;
    Context context;
//    String index;

    private enum RATESTATUS {
        UP, DOWN, NOTCHANGED
    }

    public class MarketStockViewHolder extends RecyclerView.ViewHolder {
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

    public HnxAdapter(List<MarketStock> marketStockList, Context context) {
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
    public void onBindViewHolder(final MarketStockViewHolder holder, final int position) {
        final MarketStock marketStock = marketStockList.get(position);
//        holder.cv_market.setTag(position);
        holder.stock_name.setText(marketStock.getStock_name());
        holder.stock_value.setText(String.valueOf(marketStock.getStock_value()));
        holder.stock_change.setText(String.valueOf(marketStock.getStock_change_rate()));
        holder.stock_vol.setText(String.valueOf(marketStock.getStock_vol()));

        switch (check_rate(marketStock.getStock_change_rate())) {
            case UP:
                holder.stock_change.setBackgroundColor(increase_value);
                break;
            case DOWN:
                holder.stock_change.setBackgroundColor(decrease_value);
                break;
            case NOTCHANGED:
                break;
        }


        holder.cv_market.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String index = String.valueOf(position);
                String stock_name = marketStock.getStock_name();
                Intent intent = new Intent(context, TradeActivity.class);
                intent.putExtra("stockname", stock_name);
                intent.putExtra("index", index);
                intent.putExtra("id_san", "HNX");
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                context.startActivity(intent);
            }
        });

        holder.cv_market.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Snackbar snackbar = Snackbar.make(view, "Thêm vào danh sách ưa thích", Snackbar.LENGTH_SHORT);
                snackbar.setAction("Thêm", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String index = String.valueOf(position);
                        String stock_name = marketStock.getStock_name();
                        FavoriteHelper db = new FavoriteHelper(context);
                        FavoriteData favoriteData = new FavoriteData(stock_name, index, "HNX");
                        if (db.dataExist(stock_name)) {
                            Toast.makeText(view.getContext(), "Đã tồn tại trong danh sách ưa thích", Toast.LENGTH_SHORT).show();
                        } else {
                            db.addFavoriteData(favoriteData);
                        }

                    }
                });
                snackbar.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return marketStockList.size();
    }

    public RATESTATUS check_rate(float rate) {
        //rate tăng thì trả về true
        if (rate > 0) {
            return RATESTATUS.UP;
        } else if (rate < 0) {
            return RATESTATUS.DOWN;
        }

        return RATESTATUS.NOTCHANGED;
    }

}
