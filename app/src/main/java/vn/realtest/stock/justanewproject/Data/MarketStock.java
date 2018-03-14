package vn.realtest.stock.justanewproject.Data;

/**
 * Created by Paul on 3/13/2018.
 */

public class MarketStock {
    String stock_name;
    String stock_value;
    String stock_change_rate;
    String stock_vol;

    public String getStock_name() {
        return stock_name;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public String getStock_value() {
        return stock_value;
    }

    public void setStock_value(String stock_value) {
        this.stock_value = stock_value;
    }

    public String getStock_change_rate() {
        return stock_change_rate;
    }

    public void setStock_change_rate(String stock_change_rate) {
        this.stock_change_rate = stock_change_rate;
    }

    public String getStock_vol() {
        return stock_vol;
    }

    public void setStock_vol(String stock_vol) {
        this.stock_vol = stock_vol;
    }

    public MarketStock(String name, String value, String change, String vol){
        this.stock_name = name;
        this.stock_value = value;
        this.stock_change_rate = change;
        this.stock_vol = vol;
    }
}
