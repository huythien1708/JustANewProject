package vn.realtest.stock.justanewproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

import vn.realtest.stock.justanewproject.Models.Company;
import vn.realtest.stock.justanewproject.Models.Stock;
import vn.realtest.stock.justanewproject.Presenters.CompanyPresenter;
import vn.realtest.stock.justanewproject.Presenters.StockPresenter;
import vn.realtest.stock.justanewproject.Utils.UrlEndpoints;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        StockPresenter job = new StockPresenter(UrlEndpoints.StockDetail.HNX) {
            @Override
            public void OnStockModel(ArrayList<Stock> stock) {

            }

            @Override
            public void OnProgressUpdate(Void... values) {

            }
        };

        CompanyPresenter companyPresenter = new CompanyPresenter(UrlEndpoints.CompanyInfo.HNX) {
            @Override
            public void OnCompanyModel(ArrayList<Company> companies) {
                for (Company company : companies) {
                    Log.d("data", company.getID() + ": " + company.getName());
                }
            }

            @Override
            public void OnProgressUpdate(Void... values) {

            }
        };

        job.execute();
        companyPresenter.execute();
    }

}
