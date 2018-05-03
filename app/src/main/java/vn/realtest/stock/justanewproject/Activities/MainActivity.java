package vn.realtest.stock.justanewproject.Activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


import vn.realtest.stock.justanewproject.Fragments.BuyFragment;
import vn.realtest.stock.justanewproject.Fragments.FragmentSearch;
import vn.realtest.stock.justanewproject.Fragments.FundFragment;
import vn.realtest.stock.justanewproject.Fragments.MarketFragment;
import vn.realtest.stock.justanewproject.Fragments.OrderBookFragment;
import vn.realtest.stock.justanewproject.Fragments.SellFragment;
import vn.realtest.stock.justanewproject.Fragments.TradeFragment;
import vn.realtest.stock.justanewproject.GuideForNewbie.GuideActivity;
import vn.realtest.stock.justanewproject.Helpers.BottomNavigationViewHelper;
import vn.realtest.stock.justanewproject.Models.StockType;
import vn.realtest.stock.justanewproject.Utils.GlobalStorage.StockStorage;
import vn.realtest.stock.justanewproject.Models.Stock;
import vn.realtest.stock.justanewproject.Presenters.StockPresenter;
import vn.realtest.stock.justanewproject.R;
import vn.realtest.stock.justanewproject.Utils.IntervalBackgroundJob;
import vn.realtest.stock.justanewproject.Utils.UrlEndpoints;

public class MainActivity extends AppCompatActivity {
    private TextView mTitleTv;
    private ImageView img_search, img_settings;
    public static String stock_name_data = "", stock_index_data = "", id_data = "";

    private static final long TIMEFORRELOADING = 15 * 1000; // 1 minute
    private String data_receive, stock_name_receive, stock_index_receive, id_san_receive;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_market:
                    MarketFragment market = new MarketFragment();
                    transaction.replace(R.id.content, market);
                    mTitleTv.setText(R.string.title_market);
                    break;
                case R.id.navigation_trade:
                    TradeFragment trade = new TradeFragment();
                    transaction.replace(R.id.content, trade);
                    mTitleTv.setText(R.string.title_trade);
                    break;
                case R.id.navigation_fund:
                    FundFragment fund = new FundFragment();
                    transaction.replace(R.id.content, fund);
                    mTitleTv.setText(R.string.title_fund);
                    break;
                case R.id.navigation_statistic:
                    OrderBookFragment statistic = new OrderBookFragment();
                    transaction.replace(R.id.content, statistic);
                    mTitleTv.setText(R.string.title_book);
                    break;
            }
            transaction.commit();
            return true;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        //Action bar
        final ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
        mTitleTv = (TextView) mCustomView.findViewById(R.id.title_text);
        mTitleTv.setText(R.string.title_market);

        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);

        final BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //disable shift mode by bottomnavigationviewhelper
        BottomNavigationViewHelper.disableShiftMode(navigation);

        img_search = (ImageView) findViewById(R.id.img_search);
        img_settings = (ImageView) findViewById(R.id.img_settings);

        //receive data from adapter
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, new IntentFilter("hnx_adapter"));
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, new IntentFilter("hose_adapter"));
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, new IntentFilter("upcom_adapter"));

        //set Market Fragment as default screen or call correspond Fragment when receive data from TradeActivity
        data_receive = getIntent().getStringExtra("TRADE_ID");
        stock_name_receive = getIntent().getStringExtra("stock_name");
        stock_index_receive = getIntent().getStringExtra("stock_index");
        id_san_receive = getIntent().getStringExtra("id_san");

        if(data_receive != null && stock_name_receive != null && stock_index_receive != null && id_san_receive != null){
            if(data_receive.equals("buy")){
                mTitleTv.setText(stock_name_receive);

                Bundle bundle = new Bundle();
                bundle.putString("stock_name", stock_name_receive);
                bundle.putString("stock_index", stock_index_receive);
                bundle.putString("id_san", id_san_receive);

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                BuyFragment buyFragment = new BuyFragment();
                buyFragment.setArguments(bundle);

                transaction.replace(R.id.content, buyFragment);
                transaction.commit();

            } else {
                mTitleTv.setText(stock_name_receive);

                Bundle bundle = new Bundle();
                bundle.putString("stock_name", stock_name_receive);
                bundle.putString("stock_index", stock_index_receive);
                bundle.putString("id_san", id_san_receive);

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                SellFragment sellFragment = new SellFragment();
                sellFragment.setArguments(bundle);

                transaction.replace(R.id.content, sellFragment);
                transaction.commit();
            }
        } else {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            MarketFragment market = new MarketFragment();
            transaction.replace(R.id.content, market);
            transaction.commit();
        }


        //set ImageView on click
        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                FragmentSearch search = new FragmentSearch();
                transaction.replace(R.id.content, search);
                transaction.commit();
                navigation.setVisibility(View.GONE);
                mActionBar.hide();          
            }
        });

        img_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GuideActivity.class);
                startActivity(intent);
            }
        });

        reloadDataPerSpecifiedTime(TIMEFORRELOADING);
    }

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            stock_name_data = intent.getStringExtra("stockname");
            stock_index_data = intent.getStringExtra("index");
            id_data = intent.getStringExtra("id_san");
        }
    };
    //make 3 functions for FavoriteFragment
    public String getStockName() {
        return stock_name_data;
    }

    public String getStockIndex() {
        return stock_index_data;
    }

    public String getIdData() {
        return id_data;
    }

    private void reloadDataPerSpecifiedTime(long miliseconds) {
        new IntervalBackgroundJob(0, miliseconds) {
            @Override
            public void onDoingInterval() {
                getStockData();
            }
        };
    }

    private void getStockData() {
        fetchDataFromUrl(UrlEndpoints.StockDetail.HNX, StockType.HNX);
        fetchDataFromUrl(UrlEndpoints.StockDetail.HOSE, StockType.HOSE);
        fetchDataFromUrl(UrlEndpoints.StockDetail.UPCOM, StockType.UPCOM);
    }

    private void fetchDataFromUrl(String url, final StockType type) {
        new StockPresenter(url) {

            @Override
            public void OnStockModel(ArrayList<Stock> stocks) {
                StockStorage.SetGlobalStockDataByType(type, stocks);
            }

            @Override
            public void OnProgressUpdate(Void... values) {

            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}