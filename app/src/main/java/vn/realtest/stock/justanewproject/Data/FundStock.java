package vn.realtest.stock.justanewproject.Data;

/**
 * Created by Paul on 3/12/2018.
 */
//Chứa thông tin của Fund Fragment

public class FundStock {
    long id;
    String stock_name;
    String stock_amount;
    String stock_buy_date;
    String stock_can_trade;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getStock_buy_date() {
        return stock_buy_date;
    }

    public void setStock_buy_date(String stock_buy_date) {
        this.stock_buy_date = stock_buy_date;
    }

    public String getStock_can_trade() {
        return stock_can_trade;
    }

    public void setStock_can_trade(String stock_can_trade) {
        this.stock_can_trade = stock_can_trade;
    }

    public  FundStock() {}

    public FundStock(String name, String amount, String date, String can_trade) {
        this.stock_name = name;
        this.stock_amount = amount;
        this.stock_buy_date = date;
        this.stock_can_trade = can_trade;
    }

    public FundStock(long id, String name, String amount, String date, String can_trade) {
        this.stock_name = name;
        this.stock_amount = amount;
        this.stock_buy_date = date;
        this.stock_can_trade = can_trade;
        this.id = id;
    }
}
