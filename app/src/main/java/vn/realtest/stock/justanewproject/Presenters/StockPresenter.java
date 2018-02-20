package vn.realtest.stock.justanewproject.Presenters;

import android.util.Log;

import java.util.ArrayList;

import vn.realtest.stock.justanewproject.Models.Stock;
import vn.realtest.stock.justanewproject.Utils.DataFetchingBackgroundJob;

/**
 * Created by Tran on 28-Jan-18.
 */

public abstract class StockPresenter extends DataFetchingBackgroundJob{

    public StockPresenter(String dataURL) {
        super(dataURL);
    }

    @Override
    public void OnPostExecute(String result) {
        ArrayList<Stock> stocks = new ArrayList<>();
        if (result != null && result.length() > 0) {
            String[] stockRows = result.split("'")[1].split("#");

            for (String stockRow : stockRows) {
                String[] columns = stockRow.split("\\|");
                Stock stock = new Stock();

                if (columns.length > 0) {
                    ArrayList<String> columnValues = new ArrayList<>();
                    for (String column : columns) {
                        columnValues.add(column.split(":")[0]);
                    }
                    Log.d("data", columnValues.toString());

                    try {
                        stock.setID(columnValues.get(0));
                        stock.setReference(Float.parseFloat(columnValues.get(4)));
                        stock.setCeil(Float.parseFloat(columnValues.get(2)));
                        stock.setFloor(Float.parseFloat(columnValues.get(3)));
                        stock.setTotalVolume(Integer.parseInt(columnValues.get(15).replace(",", "")));
                        //                    stock.setTotalValue(Float.parseFloat(columnValues.get(15)));

                        stock.setPriceBid3(Float.parseFloat(columnValues.get(6)));
                        stock.setVolumeBid3(Integer.parseInt(columnValues.get(7).replace(",", "")));
                        stock.setPriceBid2(Float.parseFloat(columnValues.get(8)));
                        stock.setVolumeBid2(Integer.parseInt(columnValues.get(9).replace(",", "")));
                        stock.setPriceBid1(Float.parseFloat(columnValues.get(10)));
                        stock.setVolumeBid1(Integer.parseInt(columnValues.get(11).replace(",", "")));

                        stock.setPriceMatched(Float.parseFloat(columnValues.get(12)));
                        stock.setVolumeMatched(Integer.parseInt(columnValues.get(14).replace(",", "")));
                        stock.setOffsetMatched(Float.parseFloat(columnValues.get(13)));

                        stock.setPriceAsk1(Float.parseFloat(columnValues.get(16)));
                        stock.setVolumeAsk1(Integer.parseInt(columnValues.get(17).replace(",", "")));
                        stock.setPriceAsk2(Float.parseFloat(columnValues.get(18)));
                        stock.setVolumeAsk2(Integer.parseInt(columnValues.get(19).replace(",", "")));
                        stock.setPriceAsk3(Float.parseFloat(columnValues.get(20)));
                        stock.setVolumeAsk3(Integer.parseInt(columnValues.get(21).replace(",", "")));

                        stock.setHighPrices(Float.parseFloat(columnValues.get(25)));
                        stock.setAveragePrices(Float.parseFloat(columnValues.get(23)));
                        stock.setLowPrices(Float.parseFloat(columnValues.get(26)));

                        //                    stock.setBidRemain();
                        //                    stock.setAskRemain();

                        stock.setBoughtForeign(Integer.parseInt(columnValues.get(27).replace(",", "")));
                        stock.setSoldForeign(Integer.parseInt(columnValues.get(28).replace(",", "")));
                        stock.setRoomForeign(Integer.parseInt(columnValues.get(29).replace(",", "")));
                    }
                    catch (NumberFormatException ex) {
                        Log.e("error", ex.toString());
                    }
                }
                stocks.add(stock);
            }
        }
        OnStockModel(stocks);
    }

    public abstract void OnStockModel(ArrayList<Stock> stock);

}
