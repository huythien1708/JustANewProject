package vn.realtest.stock.justanewproject.Fragments;

/**
 * Created by Admin on 1/20/2018.
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.realtest.stock.justanewproject.Adapter.ViewPagerAdapterStatistic;
import vn.realtest.stock.justanewproject.R;

public class OrderBookFragment extends Fragment {
    Context mContext;

    @SuppressLint({"NewApi", "ResourceType"})
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_statistic, container, false);
        final TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.tab_layout_statistic);
        final ViewPager pager = (ViewPager) rootView.findViewById(R.id.pager_statistic);
        pager.setAdapter(new ViewPagerAdapterStatistic(getChildFragmentManager(), mContext));
        pager.addOnPageChangeListener(new PageListener());
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(pager, true);
            }
        });

        //set text color trên tab indicator của pager view
        if (Build.VERSION.SDK_INT >= 23) {
            tabLayout.setTabTextColors(ColorStateList.valueOf(getContext().getColor(R.color.pager_view_text)));
        } else {
            tabLayout.setTabTextColors(Color.parseColor(getResources().getString(R.color.pager_view_text)), Color.parseColor(getResources().getString(R.color.pager_view_text)));
        }
        return rootView;
    }

    public static int _CurrentPage = 0;

    private class PageListener extends ViewPager.SimpleOnPageChangeListener {
        @Override
        public void onPageSelected(int position) {
            _CurrentPage = position;
        }
    }
}