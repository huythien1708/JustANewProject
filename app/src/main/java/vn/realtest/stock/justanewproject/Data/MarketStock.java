package vn.realtest.stock.justanewproject.Data;

/**
 * Created by Paul on 3/13/2018.
 */
//Chứa thông tin của HNX, HOSE, UPCOM Fragment
public class MarketStock {
    String stock_name;
    float stock_value;
    float stock_change_rate;
    float stock_vol;

    public String getStock_name() {
        return stock_name;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public float getStock_value() {
        return stock_value;
    }

    public void setStock_value(float stock_value) {
        this.stock_value = stock_value;
    }

    public float getStock_change_rate() {
        return stock_change_rate;
    }

    public void setStock_change_rate(float stock_change_rate) {
        this.stock_change_rate = stock_change_rate;
    }

    public float getStock_vol() {
        return stock_vol;
    }

    public void setStock_vol(float stock_vol) {
        this.stock_vol = stock_vol;
    }

    public MarketStock(String stock_name, float stock_value, float stock_change_rate, float stock_vol) {
        this.stock_name = stock_name;
        this.stock_value = stock_value;
        this.stock_change_rate = stock_change_rate;
        this.stock_vol = stock_vol;
    }
}
