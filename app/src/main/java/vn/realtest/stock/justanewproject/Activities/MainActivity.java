package vn.realtest.stock.justanewproject.Activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import android.widget.TextView;
import android.widget.Toast;

import vn.realtest.stock.justanewproject.Fragments.FavoriteFragment;
import vn.realtest.stock.justanewproject.Fragments.FundFragment;
import vn.realtest.stock.justanewproject.Fragments.MarketFragment;
import vn.realtest.stock.justanewproject.Fragments.StatisticFragment;
import vn.realtest.stock.justanewproject.Fragments.TradeFragment;
import vn.realtest.stock.justanewproject.Helpers.BottomNavigationViewHelper;
import vn.realtest.stock.justanewproject.R;

public class MainActivity extends AppCompatActivity {
    private TextView mTitleTv;
    public String pass_data = "";
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

        //receive data from adapter
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, new IntentFilter("wow"));

        //set Market Fragment as default screen
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        MarketFragment market = new MarketFragment();
        transaction.replace(R.id.content, market);
        transaction.commit();
    }

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            pass_data = intent.getStringExtra("stockname");
        }
    };

    public String getMyData() {
        return pass_data;
    }
}
