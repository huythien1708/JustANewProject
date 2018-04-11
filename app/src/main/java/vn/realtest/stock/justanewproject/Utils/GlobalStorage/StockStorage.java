package vn.realtest.stock.justanewproject.Utils.GlobalStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import vn.realtest.stock.justanewproject.Models.Stock;
import vn.realtest.stock.justanewproject.Models.StockType;

/**
 * Created by Tran on 31-Mar-18.
 */

public class StockStorage {

    private static Map<StockType, ArrayList<OnDataLoadedListener>> onDataLoadedListeners = new HashMap<>();

    public static void AddOnDataLoadedListener(StockType stockType, OnDataLoadedListener onDataLoadedListener) {
        if (stockType != null && onDataLoadedListener != null) {
            if (onDataLoadedListeners.containsKey(stockType)) {
                onDataLoadedListeners.get(stockType).add(onDataLoadedListener);
            } else {
                ArrayList<OnDataLoadedListener> onDataLoadedListenerList = new ArrayList<>();
                onDataLoadedListenerList.add(onDataLoadedListener);
                onDataLoadedListeners.put(stockType, onDataLoadedListenerList);
            }
        }
    }

    public static ArrayList<Stock> getGlobalStockDataByType(StockType type) {
        switch (type) {
            case HNX: return GlobalData.HNX;
            case HOSE: return GlobalData.HOSE;
            case UPCOM: return GlobalData.UPCOM;
            default: return GlobalData.HNX;
        }
    }

    public static void SetGlobalStockDataByType(StockType type, ArrayList<Stock> stocks) {
        switch (type) {
            case HNX: GlobalData.HNX = stocks; break;
            case HOSE: GlobalData.HOSE = stocks; break;
            case UPCOM: GlobalData.UPCOM = stocks; break;
        }
        onGlobalDataChanged(type);
    }

    private static void onGlobalDataChanged(StockType stockType) {
        if (stockType != null && onDataLoadedListeners.containsKey(stockType)) {
            ArrayList<OnDataLoadedListener> onDataLoadedListenerList = onDataLoadedListeners.get(stockType);
            for (OnDataLoadedListener onDataLoadedListener : onDataLoadedListenerList) {
                onDataLoadedListener.OnStockDataParsed();
            }
        }
    }

}
