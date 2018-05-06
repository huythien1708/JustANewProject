package vn.realtest.stock.justanewproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import vn.realtest.stock.justanewproject.Models.Stock;
import vn.realtest.stock.justanewproject.Models.StockType;
import vn.realtest.stock.justanewproject.R;
import vn.realtest.stock.justanewproject.Utils.GlobalStorage.StockStorage;

/**
 * Created by Admin on 1/20/2018.
 */

public class TradeActivity extends AppCompatActivity {
    private TextView mTitleTv;
    private TextView buy_price_1, buy_price_2, buy_price_3, buy_amount_1, buy_amount_2, buy_amount_3;
    private TextView sell_price_1, sell_price_2, sell_price_3, sell_amount_1, sell_amount_2, sell_amount_3;
    private TextView ceil_price, ref_price, floor_price;
    private TextView min_price, open_price, max_price;
    private TextView change_price, total_value;
    private double rate_change;
    private double ref;
    private TextView match_price;
    private int up, down;
    private String buy, sell;
    private int increase_value, decrease_value, ceil_value, floor_value, reference_value;
    private String dx;
    private String stock_name_data = "";
    private int stock_index_data;
    private StockType id_data;
    private DecimalFormat df;
    private Button btn_buy, btn_sell;
    private String data_passed;
    private ImageView img_back;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade);

        final ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);


        View mCustomView = mInflater.inflate(R.layout.custom_actionbar_trade, null);
        mTitleTv = (TextView) mCustomView.findViewById(R.id.title_text);
//        mTitleTv.setText(stock_name_data);

        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
        //get data from adapter
        init();
        increase_value = ContextCompat.getColor(getApplicationContext(), R.color.increase_value);
        decrease_value = ContextCompat.getColor(getApplicationContext(), R.color.decrease_value);

        ceil_value = ContextCompat.getColor(getApplicationContext(), R.color.ceil_value);
        floor_value = ContextCompat.getColor(getApplicationContext(), R.color.floor_value);
        reference_value = ContextCompat.getColor(getApplicationContext(), R.color.ref_value);
       
        setTextColor();
        receiveData();
        ref = Double.parseDouble(ref_price.getText().toString());
        ref = Double.parseDouble(ref_price.getText().toString());

        setTextColor();

        btn_function();

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(TradeActivity.this, MainActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(intent);
                finish();
            }
        });
    }

    private void getCompanyName() {

    }

    private void btn_function() {
        btn_sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TradeActivity.this, MainActivity.class);
                data_passed = "sell";
                intent.putExtra("TRADE_ID", data_passed);
                intent.putExtra("stock_name", stock_name_data);
                intent.putExtra("stock_index", String.valueOf(stock_index_data));
                intent.putExtra("id_san", String.valueOf(id_data));
                startActivity(intent);
            }
        });

        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TradeActivity.this, MainActivity.class);
                data_passed = "buy";
                intent.putExtra("TRADE_ID", data_passed);
                intent.putExtra("stock_name", stock_name_data);
                intent.putExtra("stock_index", String.valueOf(stock_index_data));
                intent.putExtra("id_san", String.valueOf(id_data));
                startActivity(intent);
            }
        });
    }

    private void init() {
        ceil_price = (TextView) findViewById(R.id.tv_trade_ceil);
        floor_price = (TextView) findViewById(R.id.tv_trade_floor);
        ref_price = (TextView) findViewById(R.id.tv_trade_reference);
        min_price = (TextView) findViewById(R.id.tv_min_trade_activity);
        max_price = (TextView) findViewById(R.id.tv_max_trade_activity);
        open_price = (TextView) findViewById(R.id.tv_open_trade_activity);
        change_price = (TextView) findViewById(R.id.tv_change_trade_activity);
        total_value = (TextView) findViewById(R.id.tv_total_trade_activity);
        match_price = (TextView) findViewById(R.id.tv_matched_value_trade_activity);

        buy_price_1 = (TextView) findViewById(R.id.tv_buy_price_1);
        buy_price_2 = (TextView) findViewById(R.id.tv_buy_price_2);
        buy_price_3 = (TextView) findViewById(R.id.tv_buy_price_3);
        buy_amount_1 = (TextView) findViewById(R.id.tv_buy_amount_1);
        buy_amount_2 = (TextView) findViewById(R.id.tv_buy_amount_2);
        buy_amount_3 = (TextView) findViewById(R.id.tv_buy_amount_3);
        sell_price_1 = (TextView) findViewById(R.id.tv_sell_price_1);
        sell_price_2 = (TextView) findViewById(R.id.tv_sell_price_2);
        sell_price_3 = (TextView) findViewById(R.id.tv_sell_price_3);
        sell_amount_1 = (TextView) findViewById(R.id.tv_sell_amount_1);
        sell_amount_2 = (TextView) findViewById(R.id.tv_sell_amount_2);
        sell_amount_3 = (TextView) findViewById(R.id.tv_sell_amount_3);

        buy = getResources().getString(R.string.action_buy);
        sell = getResources().getString(R.string.action_sell);

        btn_buy = (Button) findViewById(R.id.btn_trade_buy);
        btn_sell = (Button) findViewById(R.id.btn_trade_sell);

        img_back = (ImageView) findViewById(R.id.img_back);

    }

    private void receiveData() {  
        Bundle bundle = getIntent().getExtras();
        stock_index_data = getStockIndexFromString(bundle.getString("index"));
        stock_name_data = bundle.getString("stockname");
        id_data = getStockTypeFromIdData(bundle.getString("id_san"));
        mTitleTv.setText(stock_name_data);
        getData();
    }

    private void getData() {
        if (id_data != null) {
            ArrayList<Stock> data = StockStorage.getGlobalStockDataByType(id_data);
            if (data != null && data.size() > 0) {
                Stock stock = data.get(stock_index_data);

                ceil_price.setText(String.valueOf(stock.getCeil()));
                floor_price.setText(String.valueOf(stock.getFloor()));
                ref_price.setText(String.valueOf(stock.getReference()));
                min_price.setText(String.valueOf(stock.getLowPrices()));
                max_price.setText(String.valueOf(stock.getHighPrices()));
                open_price.setText(String.valueOf(stock.getReference()));

                rate_change = stock.getOffsetMatched() / stock.getReference() * 100;
                df = new DecimalFormat("#.##");
                dx = df.format(rate_change);
                change_price.setText(String.valueOf(stock.getOffsetMatched() + " (" + dx + "%)"));
                total_value.setText(String.valueOf(stock.getTotalVolume()));
                match_price.setText(String.valueOf(stock.getPriceMatched()));

                sell_price_1.setText(String.valueOf(stock.getPriceAsk1()));
                sell_price_2.setText(String.valueOf(stock.getPriceAsk2()));
                sell_price_3.setText(String.valueOf(stock.getPriceAsk3()));
                sell_amount_1.setText(String.valueOf(stock.getVolumeAsk1()));
                sell_amount_2.setText(String.valueOf(stock.getVolumeAsk2()));
                sell_amount_3.setText(String.valueOf(stock.getVolumeAsk3()));

                buy_price_1.setText(String.valueOf(stock.getPriceBid1()));
                buy_price_2.setText(String.valueOf(stock.getPriceBid2()));
                buy_price_3.setText(String.valueOf(stock.getPriceBid3()));
                buy_amount_1.setText(String.valueOf(stock.getVolumeBid1()));
                buy_amount_2.setText(String.valueOf(stock.getVolumeBid2()));
                buy_amount_3.setText(String.valueOf(stock.getVolumeBid3()));
            }
        }
    }

    private StockType getStockTypeFromIdData(String idData) {
        switch (idData) {
            case "HNX":
                return StockType.HNX;
            case "HOSE":
                return StockType.HOSE;
            case "UPCOM":
                return StockType.UPCOM;
            default:
                return StockType.HNX;
        }
    }

    private int getStockIndexFromString(String stockIndexData) {
        try {
            return Integer.parseInt(stockIndexData);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }


    private void setTextColor() {

        ceil_price.setTextColor(ceil_value);
        floor_price.setTextColor(floor_value);
        ref_price.setTextColor(reference_value);

        compareToRef(open_price, Double.parseDouble(open_price.getText().toString()));
        compareToRef(min_price, Double.parseDouble(min_price.getText().toString()));
        compareToRef(max_price, Double.parseDouble(max_price.getText().toString()));

        compareToRef(buy_price_1, Double.parseDouble(buy_price_1.getText().toString()));
        compareToRef(buy_price_2, Double.parseDouble(buy_price_2.getText().toString()));
        compareToRef(buy_price_3, Double.parseDouble(buy_price_3.getText().toString()));

        compareToRef(sell_price_1, Double.parseDouble(sell_price_1.getText().toString()));
        compareToRef(sell_price_2, Double.parseDouble(sell_price_2.getText().toString()));
        compareToRef(sell_price_3, Double.parseDouble(sell_price_3.getText().toString()));

        compareToRef(match_price, Double.parseDouble(match_price.getText().toString()));

    }


    //so sánh với giá trị tham chiếu để set màu
    private void compareToRef(TextView tv, double value) {
        int result = Double.compare(value, ref);

        if (result < 0) {
            if (value == Double.parseDouble(floor_price.getText().toString())) {
                tv.setTextColor(floor_value);
                if(tv == match_price){
                    change_price.setTextColor(floor_value);
                }
            } else {
                tv.setTextColor(decrease_value);
                if(tv == match_price){
                    change_price.setTextColor(decrease_value);
                }
            }

        } else if (result > 0) {
            if (value == Double.parseDouble(ceil_price.getText().toString())) {
                tv.setTextColor(ceil_value);
                if(tv == match_price){
                    change_price.setTextColor(ceil_value);
                }
            } else {
                tv.setTextColor(increase_value);
                if(tv == match_price){
                    change_price.setTextColor(increase_value);
                }
            }

        } else if (result == 0) {
            tv.setTextColor(reference_value);
            if(tv == match_price){
                change_price.setTextColor(reference_value);
            }
        }
    }

}
