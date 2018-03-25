package vn.realtest.stock.justanewproject.Data;

/**
 * Created by Paul on 3/25/2018.
 */

public class OpenOrder {
    String buy_sell;
    String stock_name;
    String stock_amount;
    String stock_value;

    public String getBuy_sell() {
        return buy_sell;
    }

    public void setBuy_sell(String buy_sell) {
        this.buy_sell = buy_sell;
    }

    public String getStock_name() {
        return stock_name;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public String getStock_amount() {
        return stock_amount;
    }

    public void setStock_amount(String stock_amount) {
        this.stock_amount = stock_amount;
    }

    public String getStock_value() {
        return stock_value;
    }

    public void setStock_value(String stock_value) {
        this.stock_value = stock_value;
    }

    public OpenOrder(String buy_sell, String name, String value, String amount) {
        this.buy_sell = buy_sell;
        this.stock_name = name;
        this.stock_value = value;
        this.stock_amount = amount;
        this.stock_amount = amount;
    }
}
