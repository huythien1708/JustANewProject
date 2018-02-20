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

                    stock.setID(columnValues.get(0));
                    stock.setReference(parseFloat(columnValues.get(4)));
                    stock.setCeil(parseFloat(columnValues.get(2)));
                    stock.setFloor(parseFloat(columnValues.get(3)));
                    stock.setTotalVolume(parseInt(columnValues.get(15).replace(",", "")));
                    //                    stock.setTotalValue(parseFloat(columnValues.get(15)));

                    stock.setPriceBid3(parseFloat(columnValues.get(6)));
                    stock.setVolumeBid3(parseInt(columnValues.get(7).replace(",", "")));
                    stock.setPriceBid2(parseFloat(columnValues.get(8)));
                    stock.setVolumeBid2(parseInt(columnValues.get(9).replace(",", "")));
                    stock.setPriceBid1(parseFloat(columnValues.get(10)));
                    stock.setVolumeBid1(parseInt(columnValues.get(11).replace(",", "")));

                    stock.setPriceMatched(parseFloat(columnValues.get(12)));
                    stock.setVolumeMatched(parseInt(columnValues.get(14).replace(",", "")));
                    stock.setOffsetMatched(parseFloat(columnValues.get(13)));

                    stock.setPriceAsk1(parseFloat(columnValues.get(16)));
                    stock.setVolumeAsk1(parseInt(columnValues.get(17).replace(",", "")));
                    stock.setPriceAsk2(parseFloat(columnValues.get(18)));
                    stock.setVolumeAsk2(parseInt(columnValues.get(19).replace(",", "")));
                    stock.setPriceAsk3(parseFloat(columnValues.get(20)));
                    stock.setVolumeAsk3(parseInt(columnValues.get(21).replace(",", "")));

                    stock.setHighPrices(parseFloat(columnValues.get(25)));
                    stock.setAveragePrices(parseFloat(columnValues.get(23)));
                    stock.setLowPrices(parseFloat(columnValues.get(26)));

                    //                    stock.setBidRemain();
                    //                    stock.setAskRemain();

                    stock.setBoughtForeign(parseInt(columnValues.get(27).replace(",", "")));
                    stock.setSoldForeign(parseInt(columnValues.get(28).replace(",", "")));
                    stock.setRoomForeign(parseInt(columnValues.get(29).replace(",", "")));
                    
                }
                stocks.add(stock);
            }
        }
        OnStockModel(stocks);
    }
    
    private int parseInt(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException ex) {
            Log.e("error", ex.toString());
            return 0;
        }
    }

    private float parseFloat(String string) {
        try {
            return Float.parseFloat(string);
        } catch (NumberFormatException ex) {
            Log.e("error", ex.toString());
            return 0;
        }
    }

    public abstract void OnStockModel(ArrayList<Stock> stock);

}
