package vn.realtest.stock.justanewproject.Data;

/**
 * Created by Paul on 3/25/2018.
 */

public class History {
    String stock_name;
    String buy_sell;
    String stock_amount;
    String matched_amount;
    String matched_value;
    String time_matched;

    public String getStock_name() {
        return stock_name;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public String getBuy_sell() {
        return buy_sell;
    }

    public void setBuy_sell(String buy_sell) {
        this.buy_sell = buy_sell;
    }

    public String getStock_amount() {
        return stock_amount;
    }

    public void setStock_amount(String stock_amount) {
        this.stock_amount = stock_amount;
    }

    public String getMatched_amount() {
        return matched_amount;
    }

    public void setMatched_amount(String matched_amount) {
        this.matched_amount = matched_amount;
    }

    public String getMatched_value() {
        return matched_value;
    }

    public void setMatched_value(String matched_value) {
        this.matched_value = matched_value;
    }

    public String getTime_matched() {
        return time_matched;
    }

    public void setTime_matched(String time_matched) {
        this.time_matched = time_matched;
    }

    public History(String buy_sell, String name, String value, String amount, String matched_amount, String date) {
        this.buy_sell = buy_sell;
        this.stock_name = name;
        this.matched_value = value;
        this.stock_amount = amount;
        this.matched_amount = matched_amount;
        this.time_matched = date;
    }
}

