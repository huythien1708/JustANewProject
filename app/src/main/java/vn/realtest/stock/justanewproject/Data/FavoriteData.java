package vn.realtest.stock.justanewproject.Data;

import java.util.List;

/**
 * Created by Paul on 4/17/2018.
 */
//Chứa thông tin của Favorite Fragment

public class FavoriteData {
    long id;
    String stock_name;
    String stock_index;
    String id_san;

    public FavoriteData(List<MarketStock> marketStockList) {

    }

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

    public String getStock_index() {
        return stock_index;
    }

    public void setStock_index(String stock_index) {
        this.stock_index = stock_index;
    }

    public String getId_san() {
        return id_san;
    }

    public void setId_san(String id_san) {
        this.id_san = id_san;
    }

    public FavoriteData() {}

    public FavoriteData(String stock_name, String stock_index, String id_san) {
        this.stock_name = stock_name;
        this.stock_index = stock_index;
        this.id_san = id_san;
    }

    public FavoriteData(long id, String stock_name, String stock_index, String id_san) {
        this.stock_name = stock_name;
        this.stock_index = stock_index;
        this.id_san = id_san;
        this.id = id;
    }
}
