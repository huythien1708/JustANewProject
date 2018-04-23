package vn.realtest.stock.justanewproject.Data;

/**
 * Created by Paul on 4/23/2018.
 */

public class SearchData {
    String stock_name;
    String stock_value;
    String stock_rate;

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

    public String getStock_rate() {
        return stock_rate;
    }

    public void setStock_rate(String stock_rate) {
        this.stock_rate = stock_rate;
    }

    public SearchData () {
    }

    public SearchData (String stock_name, String stock_value, String stock_rate) {
        this.stock_name = stock_name;
        this.stock_value = stock_value;
        this.stock_rate = stock_rate;
    }
}
