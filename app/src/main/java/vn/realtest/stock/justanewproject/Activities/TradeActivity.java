package vn.realtest.stock.justanewproject.Activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

import vn.realtest.stock.justanewproject.Models.Stock;
import vn.realtest.stock.justanewproject.Models.StockType;
import vn.realtest.stock.justanewproject.R;
import vn.realtest.stock.justanewproject.Utils.GlobalStorage.StockStorage;

/**
 * Created by Admin on 1/20/2018.
 */

public class TradeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private TextView mTitleTv;
    private TextView buy_price_1, buy_price_2, buy_price_3, buy_amount_1, buy_amount_2, buy_amount_3;
    private TextView sell_price_1, sell_price_2, sell_price_3, sell_amount_1, sell_amount_2, sell_amount_3;
    private TextView match_price, stock_name;
    private TextView total_value;
    private EditText trade_value, trade_amount;
    private TextView minus_price, plus_price, minus_amount, plus_amount;
    private Spinner spinner_trade;
    private Button btn_trade;
    private int up, down;
    private String buy, sell;
    private Context mContext;
    private int increase_value, decrease_value;
    private DecimalFormat df;
    private String dx;
    private String stock_name_data = "";
    private int stock_index_data;
    private StockType id_data;
    private ImageView img_search;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_trade);

        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
        mTitleTv = (TextView) mCustomView.findViewById(R.id.title_text);
        mTitleTv.setText(R.string.title_trade);

        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
        //get data from adapter
        init();
        increase_value = ContextCompat.getColor(getApplicationContext(), R.color.increase_value);
        decrease_value = ContextCompat.getColor(getApplicationContext(), R.color.decrease_value);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.buy_sell, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_trade.setAdapter(adapter);
        spinner_trade.setOnItemSelectedListener(this);
        setTextColor();
        minusPlusFunction();
        editTextFunction();

        receiveData();
    }

    private void receiveData() {
        Bundle bundle = getIntent().getExtras();
        stock_index_data = getStockIndexFromString(bundle.getString("index"));
        stock_name_data = bundle.getString("stockname");
        id_data = getStockTypeFromIdData(bundle.getString("id_san"));
        stock_name.setText(stock_name_data);

        getData();
    }

    private void getData() {
        if (id_data != null) {
            ArrayList<Stock> data = StockStorage.getGlobalStockDataByType(id_data);
            if (data != null && data.size() > 0) {
                Stock stock = data.get(stock_index_data);
                stock_name.setText(stock.getID());
                buy_price_1.setText(String.valueOf(stock.getPriceAsk1()));
                buy_price_2.setText(String.valueOf(stock.getPriceAsk2()));
                buy_price_3.setText(String.valueOf(stock.getPriceAsk3()));
                buy_amount_1.setText(String.valueOf(stock.getVolumeAsk1()));
                buy_amount_2.setText(String.valueOf(stock.getVolumeAsk2()));
                buy_amount_3.setText(String.valueOf(stock.getVolumeAsk3()));
                sell_price_1.setText(String.valueOf(stock.getPriceBid1()));
                sell_price_2.setText(String.valueOf(stock.getPriceBid2()));
                sell_price_3.setText(String.valueOf(stock.getPriceBid3()));
                sell_amount_1.setText(String.valueOf(stock.getVolumeBid1()));
                sell_amount_2.setText(String.valueOf(stock.getVolumeBid2()));
                sell_amount_3.setText(String.valueOf(stock.getVolumeBid3()));
            }
        }
    }

    private StockType getStockTypeFromIdData(String idData) {
        switch (idData) {
            case "HNX": return StockType.HNX;
            case "HOSE": return StockType.HOSE;
            case "UPCOM": return StockType.UPCOM;
            default: return StockType.HNX;
        }
    }

    private int getStockIndexFromString(String stockIndexData) {
        try {
            return Integer.parseInt(stockIndexData);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    private void editTextFunction() {
        trade_value.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                double a;
                if (trade_value.getText().toString().equals(""))
                    a = 0;
                else
                    a = Double.parseDouble(trade_value.getText().toString());


                double b = Double.parseDouble(trade_amount.getText().toString());
                double c = a * b;
                df = new DecimalFormat("#.##");
                dx = df.format(c);
                c = Double.valueOf(dx);
                total_value.setText(String.valueOf(c));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        trade_amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                double a;
                if (trade_amount.getText().toString().equals(""))
                    a = 0;
                else
                    a = Double.parseDouble(trade_amount.getText().toString());

                double b = Double.parseDouble(trade_value.getText().toString());
                double c = a * b;
                df = new DecimalFormat("#.##");
                dx = df.format(c);
                c = Double.valueOf(dx);
                total_value.setText(String.valueOf(c));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void minusPlusFunction() {
        minus_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double a = Double.parseDouble(trade_value.getText().toString()) - 0.05;
                if (a < 0) {
                    a = 0;
                }
                df = new DecimalFormat("#.##");
                dx = df.format(a);
                a = Double.valueOf(dx);
                trade_value.setText(String.valueOf(a));
                double b = Double.parseDouble(trade_amount.getText().toString());
                double c = a * b;
                df = new DecimalFormat("#.##");
                dx = df.format(c);
                c = Double.valueOf(dx);
                total_value.setText(String.valueOf(c));
            }
        });
        plus_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double a = Double.parseDouble(trade_value.getText().toString()) + 0.05;
                if (a < 0) {
                    a = 0;
                }
                df = new DecimalFormat("#.##");
                dx = df.format(a);
                a = Double.valueOf(dx);
                trade_value.setText(String.valueOf(a));
                double b = Double.parseDouble(trade_amount.getText().toString());
                double c = a * b;
                df = new DecimalFormat("#.##");
                dx = df.format(c);
                c = Double.valueOf(dx);
                total_value.setText(String.valueOf(c));
            }
        });

        minus_amount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double a = Double.parseDouble(trade_amount.getText().toString()) - 10;
                if (a < 0) {
                    a = 0;
                }
                trade_amount.setText(String.valueOf(a));
                double b = Double.parseDouble(trade_value.getText().toString());
                double c = a * b;
                df = new DecimalFormat("#.##");
                dx = df.format(c);
                c = Double.valueOf(dx);
                total_value.setText(String.valueOf(c));
            }
        });
        plus_amount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double a = Double.parseDouble(trade_amount.getText().toString()) + 10;
                if (a < 0) {
                    a = 0;
                }
                trade_amount.setText(String.valueOf(a));
                double b = Double.parseDouble(trade_value.getText().toString());
                double c = a * b;
                df = new DecimalFormat("#.##");
                dx = df.format(c);
                c = Double.valueOf(dx);
                total_value.setText(String.valueOf(c));
            }
        });
    }

    private void setTextColor() {
        if (Integer.parseInt(buy_price_1.getText().toString()) < Integer.parseInt(match_price.getText().toString())) {
            buy_price_1.setTextColor(decrease_value);
        } else if (Integer.parseInt(buy_price_1.getText().toString()) == Integer.parseInt(match_price.getText().toString())) {
            buy_price_1.setTextColor(increase_value);
        } else {
            buy_price_1.setTextColor(increase_value);
        }
        if (Integer.parseInt(buy_price_2.getText().toString()) < Integer.parseInt(match_price.getText().toString())) {
            buy_price_2.setTextColor(decrease_value);
        } else if (Integer.parseInt(buy_price_1.getText().toString()) == Integer.parseInt(match_price.getText().toString())) {
            buy_price_2.setTextColor(increase_value);
        } else {
            buy_price_2.setTextColor(increase_value);
        }
        if (Integer.parseInt(buy_price_3.getText().toString()) < Integer.parseInt(match_price.getText().toString())) {
            buy_price_3.setTextColor(decrease_value);
        } else if (Integer.parseInt(buy_price_1.getText().toString()) == Integer.parseInt(match_price.getText().toString())) {
            buy_price_3.setTextColor(increase_value);
        } else {
            buy_price_3.setTextColor(increase_value);
        }
        if (Integer.parseInt(sell_price_1.getText().toString()) < Integer.parseInt(match_price.getText().toString())) {
            sell_price_1.setTextColor(decrease_value);
        } else if (Integer.parseInt(buy_price_1.getText().toString()) == Integer.parseInt(match_price.getText().toString())) {
            sell_price_1.setTextColor(increase_value);
        } else {
            sell_price_1.setTextColor(increase_value);
        }
        if (Integer.parseInt(sell_price_2.getText().toString()) < Integer.parseInt(match_price.getText().toString())) {
            sell_price_2.setTextColor(decrease_value);
        } else if (Integer.parseInt(buy_price_1.getText().toString()) == Integer.parseInt(match_price.getText().toString())) {
            sell_price_2.setTextColor(increase_value);
        } else {
            sell_price_2.setTextColor(increase_value);
        }
        if (Integer.parseInt(sell_price_3.getText().toString()) < Integer.parseInt(match_price.getText().toString())) {
            sell_price_3.setTextColor(decrease_value);
        } else if (Integer.parseInt(buy_price_1.getText().toString()) == Integer.parseInt(match_price.getText().toString())) {
            sell_price_3.setTextColor(increase_value);
        } else {
            sell_price_3.setTextColor(increase_value);
        }
    }

    private void init() {
        stock_name = (TextView) findViewById(R.id.tv_stock_name_trade);
        buy_price_1 = (TextView) findViewById(R.id.tv_buy_price_1);
        buy_price_2 = (TextView) findViewById(R.id.tv_buy_price_2);
        buy_price_3 = (TextView) findViewById(R.id.tv_buy_price_3);
        buy_amount_1 = (TextView) findViewById(R.id.tv_buy_amount_1);
        buy_amount_2 = (TextView) findViewById(R.id.tv_buy_amount_2);
        buy_amount_3 = (TextView) findViewById(R.id.tv_buy_amount_3);
        sell_price_1 = (TextView) findViewById(R.id.tv_sell_price_1);
        sell_price_2 = (TextView)findViewById(R.id.tv_sell_price_2);
        sell_price_3 = (TextView) findViewById(R.id.tv_sell_price_3);
        sell_amount_1 = (TextView) findViewById(R.id.tv_sell_amount_1);
        sell_amount_2 = (TextView) findViewById(R.id.tv_sell_amount_2);
        sell_amount_3 = (TextView) findViewById(R.id.tv_sell_amount_3);
        match_price = (TextView) findViewById(R.id.match_value);
        total_value = (TextView) findViewById(R.id.tv_total_trade);
        trade_amount = (EditText) findViewById(R.id.edt_amount_value);
        trade_value = (EditText) findViewById(R.id.edt_price_value);
        minus_amount = (TextView) findViewById(R.id.tv_decrease_amount_value);
        minus_price = (TextView) findViewById(R.id.tv_decrease_price_value);
        plus_amount = (TextView)findViewById(R.id.tv_increase_amount_value);
        plus_price = (TextView) findViewById(R.id.tv_increase_price_value);
        spinner_trade = (Spinner) findViewById(R.id.spinner_buy_sell);
        btn_trade = (Button) findViewById(R.id.btn_buy_sell);
        up = ContextCompat.getColor(getApplicationContext(), R.color.increase_value);
        down = ContextCompat.getColor(getApplicationContext(), R.color.decrease_value);
        buy = getResources().getString(R.string.action_buy);
        sell = getResources().getString(R.string.action_sell);
        img_search = (ImageView) findViewById(R.id.img_search);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String show = spinner_trade.getSelectedItem().toString();

        if (show.equals(buy)) {
            btn_trade.setText(buy);
            btn_trade.setBackgroundColor(up);
        }
        if (show.equals(sell)) {
            btn_trade.setText(sell);
            btn_trade.setBackgroundColor(down);

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}
