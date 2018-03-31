package vn.realtest.stock.justanewproject.Listeners;

import java.util.ArrayList;

import vn.realtest.stock.justanewproject.Models.GlobalData;

/**
 * Created by Tran on 31-Mar-18.
 */

public class GlobalDataLoadedListener {

    private static ArrayList<OnDataLoadedListener> onDataLoadedListeners = new ArrayList<>();

    public static void addOnDataLoadedListener(OnDataLoadedListener onDataLoadedListener) {
        if (onDataLoadedListener != null) {
            onDataLoadedListeners.add(onDataLoadedListener);
        }
    }

    public static void onGlobalDataLoaded() {
        for (OnDataLoadedListener onDataLoadedListener : onDataLoadedListeners) {
            switch (onDataLoadedListener.GetStockType()) {
                case HNX:
                    onDataLoadedListener.OnStockDataParsed(GlobalData.HNX);
                    break;
                case HOSE:
                    onDataLoadedListener.OnStockDataParsed(GlobalData.HOSE);
                    break;
                case UPCOM:
                    onDataLoadedListener.OnStockDataParsed(GlobalData.UPCOM);
                    break;
                default:
                    onDataLoadedListener.OnStockDataParsed(GlobalData.HNX);
                    break;
            }
        }
    }

}
