package vn.realtest.stock.justanewproject.Fragments;

/**
 * Created by Admin on 1/20/2018.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

import vn.realtest.stock.justanewproject.Activities.MainActivity;
import vn.realtest.stock.justanewproject.Utils.GlobalStorage.StockStorage;
import vn.realtest.stock.justanewproject.Utils.GlobalStorage.OnDataLoadedListener;
import vn.realtest.stock.justanewproject.Models.Stock;
import vn.realtest.stock.justanewproject.Models.StockType;
import vn.realtest.stock.justanewproject.R;

public class SellFragment extends Fragment {
    private TextView buy_price_1, buy_price_2, buy_price_3, buy_amount_1, buy_amount_2, buy_amount_3;
    private TextView sell_price_1, sell_price_2, sell_price_3, sell_amount_1, sell_amount_2, sell_amount_3;
    private TextView stock_name;
    private TextView total_value;
    private EditText trade_value, trade_amount;
    private TextView minus_price, plus_price, minus_amount, plus_amount;
    private Spinner spinner_trade;
    private View view;
    private Button btn_trade;
    private int up, down;
    private String buy, sell;
    private Context mContext;
    private int increase_value, decrease_value, ref_value, floor_value, ceil_value;
    private DecimalFormat df;
    String dx, stock_name_data, stock_index_data, id_data;
    private StockType type;
    private double ref, floor, ceil;


    public static SellFragment newInstance() {
        SellFragment fragment = new SellFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sell, container, false);
        init();
        mContext = view.getContext();
        increase_value = ContextCompat.getColor(mContext, R.color.increase_value);
        decrease_value = ContextCompat.getColor(mContext, R.color.decrease_value);
        ref_value = ContextCompat.getColor(mContext, R.color.ref_value);
        floor_value = ContextCompat.getColor(mContext, R.color.floor_value);
        ceil_value = ContextCompat.getColor(mContext, R.color.ceil_value);

        minusPlusFunction();
        editTextFunction();

        stock_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "OK", Toast.LENGTH_SHORT).show();
            }
        });

        receiveData();

        getData();
        setTextColor();
        StockStorage.AddOnDataLoadedListener(type, new OnDataLoadedListener() {
            @Override
            public void OnStockDataParsed() {
                getData();
            }
        });

        return view;
    }

    private void getData() {
        ArrayList<Stock> data = StockStorage.getGlobalStockDataByType(type);
        if (data != null && data.size() > 0) {
            Stock stock = data.get(Integer.parseInt(stock_index_data));
            stock_name.setText(stock_name_data);
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
            ref = Double.parseDouble(String.valueOf(stock.getReference()));
            floor = Double.parseDouble(String.valueOf(stock.getFloor()));
            ceil = Double.parseDouble(String.valueOf(stock.getCeil()));
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
        compareToRef(buy_price_1, Double.parseDouble(buy_price_1.getText().toString()));
        compareToRef(buy_price_2, Double.parseDouble(buy_price_2.getText().toString()));
        compareToRef(buy_price_3, Double.parseDouble(buy_price_3.getText().toString()));
        compareToRef(sell_price_1, Double.parseDouble(sell_price_1.getText().toString()));
        compareToRef(sell_price_2, Double.parseDouble(sell_price_2.getText().toString()));
        compareToRef(sell_price_3, Double.parseDouble(sell_price_3.getText().toString()));
    }

    private void init() {
        stock_name = (TextView) view.findViewById(R.id.tv_stock_name_trade);
        buy_price_1 = (TextView) view.findViewById(R.id.tv_buy_price_1);
        buy_price_2 = (TextView) view.findViewById(R.id.tv_buy_price_2);
        buy_price_3 = (TextView) view.findViewById(R.id.tv_buy_price_3);
        buy_amount_1 = (TextView) view.findViewById(R.id.tv_buy_amount_1);
        buy_amount_2 = (TextView) view.findViewById(R.id.tv_buy_amount_2);
        buy_amount_3 = (TextView) view.findViewById(R.id.tv_buy_amount_3);
        sell_price_1 = (TextView) view.findViewById(R.id.tv_sell_price_1);
        sell_price_2 = (TextView) view.findViewById(R.id.tv_sell_price_2);
        sell_price_3 = (TextView) view.findViewById(R.id.tv_sell_price_3);
        sell_amount_1 = (TextView) view.findViewById(R.id.tv_sell_amount_1);
        sell_amount_2 = (TextView) view.findViewById(R.id.tv_sell_amount_2);
        sell_amount_3 = (TextView) view.findViewById(R.id.tv_sell_amount_3);
        total_value = (TextView) view.findViewById(R.id.tv_total_trade);
        trade_amount = (EditText) view.findViewById(R.id.edt_amount_value);
        trade_value = (EditText) view.findViewById(R.id.edt_price_value);
        minus_amount = (TextView) view.findViewById(R.id.tv_decrease_amount_value);
        minus_price = (TextView) view.findViewById(R.id.tv_decrease_price_value);
        plus_amount = (TextView) view.findViewById(R.id.tv_increase_amount_value);
        plus_price = (TextView) view.findViewById(R.id.tv_increase_price_value);
        btn_trade = (Button) view.findViewById(R.id.btn_buy_sell);
        up = ContextCompat.getColor(view.getContext(), R.color.increase_value);
        down = ContextCompat.getColor(view.getContext(), R.color.decrease_value);
        buy = getResources().getString(R.string.action_buy);
        sell = getResources().getString(R.string.action_sell);
    }

    private void compareToRef(TextView tv, double value) {
        int result = Double.compare(value, ref);
        if (result < 0) {
            if (value == floor) {
                tv.setTextColor(floor_value);
            } else {
                tv.setTextColor(decrease_value);
            }

        } else if (result > 0) {
            if (value == ceil) {
                tv.setTextColor(ceil_value);
            } else {
                tv.setTextColor(increase_value);
            }

        } else if (result == 0) {
            tv.setTextColor(ref_value);
        }
    }


    private void receiveData() {
        if(getArguments()!=null){
            stock_name_data = getArguments().getString("stock_name");
            stock_index_data = getArguments().getString("stock_index");
            id_data = getArguments().getString("id_san");
            type = getStockTypeFromIdData(id_data);
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
}