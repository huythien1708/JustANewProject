package vn.realtest.stock.justanewproject.Adapter;

/**
 * Created by Paul on 3/20/2018.
 */

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import vn.realtest.stock.justanewproject.Fragments.FavoriteFragment;
import vn.realtest.stock.justanewproject.Fragments.HnxFragment;
import vn.realtest.stock.justanewproject.Fragments.HoseFragment;
import vn.realtest.stock.justanewproject.Fragments.OpenOrdersFragment;
import vn.realtest.stock.justanewproject.Fragments.TradeHistoryFragment;
import vn.realtest.stock.justanewproject.Fragments.UpcomFragment;
import vn.realtest.stock.justanewproject.R;

/**
 * Created by Admin on 1/21/2018.
 */

public class ViewPagerAdapterStatistic extends FragmentPagerAdapter{
    private static int NUM_ITEMS = 2;
    Context context;

    public ViewPagerAdapterStatistic(FragmentManager fragmentManager, Context mContext) {
        super(fragmentManager);
        context = mContext;
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new OpenOrdersFragment();
            case 1:
                return new TradeHistoryFragment();
        }
        return null;
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){
            case 0:
                return "Giao dịch";
            case 1:
                return "Lịch sử";
        }
        return null;
    }
}
