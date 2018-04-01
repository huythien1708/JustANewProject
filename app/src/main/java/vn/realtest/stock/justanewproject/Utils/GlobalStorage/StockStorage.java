package vn.realtest.stock.justanewproject.Utils.GlobalStorage;

import java.util.ArrayList;

import vn.realtest.stock.justanewproject.Models.Stock;
import vn.realtest.stock.justanewproject.Models.StockType;

/**
 * Created by Tran on 31-Mar-18.
 */

public class StockStorage {

    private static ArrayList<OnDataLoadedListener> onDataLoadedListeners = new ArrayList<>();

    public static void addOnDataLoadedListener(OnDataLoadedListener onDataLoadedListener) {
        if (onDataLoadedListener != null) {
            onDataLoadedListeners.add(onDataLoadedListener);
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

    public static void setGlobalStockDataByType(StockType type, ArrayList<Stock> stocks) {
        switch (type) {
            case HNX: GlobalData.HNX = stocks; break;
            case HOSE: GlobalData.HOSE = stocks; break;
            case UPCOM: GlobalData.UPCOM = stocks; break;
        }
        onGlobalDataChanged();
    }

    private static void onGlobalDataChanged() {
        for (OnDataLoadedListener onDataLoadedListener : onDataLoadedListeners) {
            onDataLoadedListener.OnStockDataParsed();
        }
    }

}
