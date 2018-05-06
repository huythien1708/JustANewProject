package vn.realtest.stock.justanewproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
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
import vn.realtest.stock.justanewproject.Data.SearchData;
import vn.realtest.stock.justanewproject.Databases.FavoriteHelper;
import vn.realtest.stock.justanewproject.R;

/**
 * Created by Paul on 3/13/2018.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    List<SearchData> searchDataList;
    int increase_value, decrease_value, ref_value;
    Context context;
    String index;


    private enum RATESTATUS{
        UP, DOWN, NOTCHANGED
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {
        CardView cv_search;
        TextView stock_name, stock_value, stock_rate;

        public SearchViewHolder(View itemView) {
            super(itemView);
            cv_search = (CardView) itemView.findViewById(R.id.cv_search);
            stock_name = (TextView) itemView.findViewById(R.id.tv_stock_name_search);
            stock_value = (TextView) itemView.findViewById(R.id.tv_stock_value_search);
            stock_rate = (TextView) itemView.findViewById(R.id.tv_stock_change_search);
        }
    }

    public SearchAdapter(List<SearchData> searchDataList, Context context) {
        this.searchDataList = searchDataList;
        this.context = context;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_search, viewGroup, false);
        increase_value = ContextCompat.getColor(v.getContext(), R.color.increase_value);
        decrease_value = ContextCompat.getColor(v.getContext(), R.color.decrease_value);
        ref_value = ContextCompat.getColor(v.getContext(), R.color.ref_value);
        return new SearchViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, final int position) {
        final SearchData searchData = searchDataList.get(position);
        holder.cv_search.setTag(position);
        holder.stock_name.setText(searchData.getStock_name());
        holder.stock_value.setText(String.valueOf(searchData.getStock_value()));
        holder.stock_rate.setText(String.valueOf(searchData.getStock_rate()));


        switch (check_rate(searchData.getStock_rate())) {
            case UP:
                holder.stock_rate.setTextColor(increase_value);
                holder.stock_value.setTextColor(increase_value);
                holder.stock_name.setTextColor(increase_value);
                break;
            case DOWN:
                holder.stock_rate.setTextColor(decrease_value);
                holder.stock_value.setTextColor(decrease_value);
                holder.stock_name.setTextColor(decrease_value);
                break;
            case NOTCHANGED:
                holder.stock_rate.setTextColor(ref_value);
                holder.stock_value.setTextColor(ref_value);
                holder.stock_name.setTextColor(ref_value);
                break;
        }
        holder.cv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = String.valueOf(view.getTag());
                String stock_name = searchData.getStock_name();
                Intent intent = new Intent(context, TradeActivity.class);
                intent.putExtra("stockname", stock_name);
                intent.putExtra("index", index);
                intent.putExtra("id_san", "HOSE");
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                context.startActivity(intent);
            }
        });

        holder.cv_search.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Snackbar snackbar = Snackbar.make(view, "Thêm vào danh sách ưa thích", Snackbar.LENGTH_SHORT);
                snackbar.setAction("Thêm", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String index = String.valueOf(searchData.getStock_index());
                        String stock_name = searchData.getStock_name();
                        String id_san = searchData.getId_san();
                        FavoriteHelper db = new FavoriteHelper(context);
                        FavoriteData favoriteData = new FavoriteData(stock_name, index, id_san);

                        if (db.dataExist(stock_name)){
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
        return searchDataList.size();
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

    public String getIDsan(String input){
        String kq = "";
        int index = 0;
        for(int i = 0; i < input.length(); i++){
            if(input.charAt(i) == '/'){
                index = i;
            }
        }

        for(int i = index + 1; i < input.length(); i++ ){
            kq = kq + input.charAt(i);
        }

        return kq;
    }

    public String getStockName(String input){
        String kq = "";
        int index = 0;
        for(int i = 0; i < input.length(); i++){
            if(input.charAt(i) == '/'){
                index = i;
            }
        }

        for(int i = 0; i < index; i++ ){
            kq = kq + input.charAt(i);
        }

        return kq;
    }
}
