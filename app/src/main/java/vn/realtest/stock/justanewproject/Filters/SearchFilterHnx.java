//package vn.realtest.stock.justanewproject.Filters;
//
///**
// * Created by Paul on 4/16/2018.
// */
//import android.widget.Filter;
//
//import java.util.ArrayList;
//
//import vn.realtest.stock.justanewproject.Adapter.MarketAdapter;
//import vn.realtest.stock.justanewproject.Data.MarketStock;
//
//public class SearchFilterHnx extends Filter {
//    MarketAdapter mAdapter;
//    ArrayList<MarketStock> filterList;
//
//    public SearchFilterHnx (ArrayList<MarketStock> filterList, MarketAdapter adapter){
//        this.mAdapter = adapter;
//        this.filterList = filterList;
//    }
//    @Override
//    protected FilterResults performFiltering(CharSequence constraint) {
//
//        FilterResults results=new FilterResults();
//
//        //CHECK CONSTRAINT VALIDITY
//        if(constraint != null && constraint.length() > 0)
//        {
//            //CHANGE TO UPPER
//            constraint=constraint.toString().toUpperCase();
//            //STORE OUR FILTERED STOCKS
//            ArrayList<MarketStock> filteredStock=new ArrayList<>();
//
//            for (int i=0;i<filterList.size();i++)
//            {
//                //CHECK
//                if(filterList.get(i).getStock_name().toUpperCase().contains(constraint))
//                {
//                    //ADD PLAYER TO FILTERED STOCKS
//                    filteredStock.add(filterList.get(i));
//                }
//            }
//
//            results.count=filteredStock.size();
//            results.values=filteredStock;
//        }else
//        {
//            results.count=filterList.size();
//            results.values=filterList;
//
//        }
//
//
//        return results;
//    }
//
//    @Override
//    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
//
//    }
//}
