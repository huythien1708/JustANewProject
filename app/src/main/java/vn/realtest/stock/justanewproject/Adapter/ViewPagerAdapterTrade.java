package vn.realtest.stock.justanewproject.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import vn.realtest.stock.justanewproject.Fragments.BuyFragment;
import vn.realtest.stock.justanewproject.Fragments.OpenOrdersFragment;
import vn.realtest.stock.justanewproject.Fragments.SellFragment;
import vn.realtest.stock.justanewproject.Fragments.TradeHistoryFragment;

/**
 * Created by Paul on 4/30/2018.
 */

public class ViewPagerAdapterTrade extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 2;
    Context context;

    public ViewPagerAdapterTrade(FragmentManager fragmentManager, Context mContext) {
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
                return new BuyFragment();
            case 1:
                return new SellFragment();
        }
        return null;
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){
            case 0:
                return "Mua";
            case 1:
                return "Bán";
        }
        return null;
    }
}