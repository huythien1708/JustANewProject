package vn.realtest.stock.justanewproject.Listeners;

import java.util.ArrayList;

import vn.realtest.stock.justanewproject.Models.GlobalData;
import vn.realtest.stock.justanewproject.Models.Stock;
import vn.realtest.stock.justanewproject.Models.StockType;

/**
 * Created by Tran on 31-Mar-18.
 */

public interface OnDataLoadedListener {

    void OnStockDataParsed(ArrayList<Stock> stocks);

    StockType GetStockType();
}
