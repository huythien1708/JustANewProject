package vn.realtest.stock.justanewproject.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import vn.realtest.stock.justanewproject.Fragments.FavoriteFragment;
import vn.realtest.stock.justanewproject.Fragments.HnxFragment;
import vn.realtest.stock.justanewproject.Fragments.HoseFragment;
import vn.realtest.stock.justanewproject.Fragments.UpcomFragment;
import vn.realtest.stock.justanewproject.R;

/**
 * Created by Admin on 1/21/2018.
 */

public class ViewPagerAdapterMarket extends FragmentPagerAdapter{
    private static int NUM_ITEMS = 4;
    private String title_favorite;
    Context context;

    public ViewPagerAdapterMarket(FragmentManager fragmentManager, Context mContext) {
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
                return new FavoriteFragment();
            case 1:
                return new HnxFragment();
            case 2:
                return new HoseFragment();
            case 3:
                return new UpcomFragment();
        }
        return null;
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
       switch (position){
           case 0:
               return "Favorite";
           case 1:
               return "HNX";
           case 2:
               return "HOSE";
           case 3:
               return "UPCOM";
       }
        return null;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
