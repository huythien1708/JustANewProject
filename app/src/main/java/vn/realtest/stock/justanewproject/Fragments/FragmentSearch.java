package vn.realtest.stock.justanewproject.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vn.realtest.stock.justanewproject.Activities.MainActivity;
import vn.realtest.stock.justanewproject.Adapter.SearchAdapter;
import vn.realtest.stock.justanewproject.Data.SearchData;
import vn.realtest.stock.justanewproject.R;

/**
 * Created by Paul on 4/23/2018.
 */

public class FragmentSearch extends Fragment {
    private View view;
    private SearchView searchView;
    private RecyclerView rv_search;
    private TextView tv_search_result;
    private SearchAdapter mAdapter;
    private List<SearchData> searchDataList = new ArrayList<SearchData>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);
        searchView = (SearchView) view.findViewById(R.id.search_view);
        tv_search_result = (TextView) view.findViewById(R.id.tv_search_result);
        rv_search = (RecyclerView) view.findViewById(R.id.rv_search);
        tv_search_result.setVisibility(View.GONE);
        mAdapter = new SearchAdapter(searchDataList, view.getContext());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv_search.setLayoutManager(layoutManager);
        rv_search.setItemAnimator(new DefaultItemAnimator());
        rv_search.setAdapter(mAdapter);

        searchView.setIconified(false);
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
                return false;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(!s.isEmpty())
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        setData();
//        mAdapter.notifyDataSetChanged();
        return view;
    }

    private void setData() {
        SearchData searchData = new SearchData("PVD", "31", "32");
        searchDataList.add(searchData);

        searchData = new SearchData("PVD", "31", "-32");
        searchDataList.add(searchData);

        searchData = new SearchData("PVD", "31", "0");
        searchDataList.add(searchData);
    }
}
