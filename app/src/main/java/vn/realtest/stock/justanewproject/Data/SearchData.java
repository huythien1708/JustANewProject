package vn.realtest.stock.justanewproject.Data;

/**
 * Created by Paul on 4/23/2018.
 */

public class SearchData {
    String stock_name;
    String id_san;
    int stock_index;
    float stock_value;
    float stock_rate;

    public String getId_san() {
        return id_san;
    }

    public void setId_san(String id_san) {
        this.id_san = id_san;
    }



    public int getStock_index() {
        return stock_index;
    }

    public void setStock_index(int stock_index) {
        this.stock_index = stock_index;
    }

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

    public float getStock_rate() {
        return stock_rate;
    }

    public void setStock_rate(float stock_rate) {
        this.stock_rate = stock_rate;
    }

    public SearchData(String stock_name, float stock_value, float stock_rate, int stock_index, String id_san) {
        this.stock_name = stock_name;
        this.stock_value = stock_value;
        this.stock_rate = stock_rate;
        this.stock_index = stock_index;
        this.id_san = id_san;
    }
}
