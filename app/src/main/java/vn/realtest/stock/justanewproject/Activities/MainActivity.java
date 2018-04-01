package vn.realtest.stock.justanewproject.Activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import vn.realtest.stock.justanewproject.Fragments.FundFragment;
import vn.realtest.stock.justanewproject.Fragments.MarketFragment;
import vn.realtest.stock.justanewproject.Fragments.StatisticFragment;
import vn.realtest.stock.justanewproject.Fragments.TradeFragment;
import vn.realtest.stock.justanewproject.Helpers.BottomNavigationViewHelper;
import vn.realtest.stock.justanewproject.Models.StockType;
import vn.realtest.stock.justanewproject.Utils.GlobalStorage.GlobalDataLoadedListener;
import vn.realtest.stock.justanewproject.Utils.GlobalStorage.GlobalData;
import vn.realtest.stock.justanewproject.Models.Stock;
import vn.realtest.stock.justanewproject.Presenters.StockPresenter;
import vn.realtest.stock.justanewproject.R;
import vn.realtest.stock.justanewproject.Utils.UrlEndpoints;

public class MainActivity extends AppCompatActivity {
    TextView mTitleTv;
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
                    StatisticFragment statistic = new StatisticFragment();
                    transaction.replace(R.id.content, statistic);
                    mTitleTv.setText(R.string.title_statistic);
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
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
        mTitleTv = (TextView) mCustomView.findViewById(R.id.title_text);
        mTitleTv.setText(R.string.title_market);

        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //disable shift mode by bottomnavigationviewhelper
        BottomNavigationViewHelper.disableShiftMode(navigation);

        //set Market Fragment as default screen
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        MarketFragment market = new MarketFragment();
        transaction.replace(R.id.content, market);
        transaction.commit();

        prepareStockData();
    }

    private void prepareStockData() {
        Log.d("test", "Start fetching: " + System.currentTimeMillis() + "");
        new StockPresenter(UrlEndpoints.StockDetail.HNX) {

            @Override
            public void OnStockModel(ArrayList<Stock> stocks) {
                GlobalDataLoadedListener.setGlobalStockDataByType(StockType.HNX, stocks);
            }

            @Override
            public void OnProgressUpdate(Void... values) {

            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        new StockPresenter(UrlEndpoints.StockDetail.HOSE) {

            @Override
            public void OnStockModel(ArrayList<Stock> stocks) {
                GlobalDataLoadedListener.setGlobalStockDataByType(StockType.HOSE, stocks);
            }

            @Override
            public void OnProgressUpdate(Void... values) {

            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        new StockPresenter(UrlEndpoints.StockDetail.UPCOM) {

            @Override
            public void OnStockModel(ArrayList<Stock> stocks) {
                GlobalDataLoadedListener.setGlobalStockDataByType(StockType.UPCOM, stocks);

            }

            @Override
            public void OnProgressUpdate(Void... values) {

            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}
