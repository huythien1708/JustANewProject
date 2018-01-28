package vn.realtest.stock.justanewproject.Presenters;

import vn.realtest.stock.justanewproject.Models.Stock;

/**
 * Created by Tran on 28-Jan-18.
 */

public class StockPresenter {

    private String _stockData;

    public StockPresenter(String stockData) {
        this._stockData = stockData;
    }

    public Stock GetStockModel() {
        Stock stock = new Stock();
        String[] columns = _stockData.split("|");



        return stock;
    }

}
