package vn.realtest.stock.justanewproject.Models;

import java.util.ArrayList;

/**
 * Created by Tran on 20-Jan-18.
 */

public class Stock {
    private String ID;
    private float Reference;
    private float Ceil;
    private float Floor;
    private float TotalValue;
    private float TotalVolumn;
    private Market Bids;
    private Market Asks;
    private Matched Matched;
    private Price Price;
    private Remain Remain;
    private Foreign Foreign;

    public Stock(){}

    public Stock(String id, float reference, float ceil, float floor, float totalValue, float totalVolumn, Market bids, Market asks, Stock.Matched matched, Stock.Price price, Stock.Remain remain, Stock.Foreign foreign) {
        ID = id;
        Reference = reference;
        Ceil = ceil;
        Floor = floor;
        TotalValue = totalValue;
        TotalVolumn = totalVolumn;
        Bids = bids;
        Asks = asks;
        Matched = matched;
        Price = price;
        Remain = remain;
        Foreign = foreign;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public float getReference() {
        return Reference;
    }

    public void setReference(float reference) {
        Reference = reference;
    }

    public float getCeil() {
        return Ceil;
    }

    public void setCeil(float ceil) {
        Ceil = ceil;
    }

    public float getFloor() {
        return Floor;
    }

    public void setFloor(float floor) {
        Floor = floor;
    }

    public float getTotalValue() {
        return TotalValue;
    }

    public void setTotalValue(float totalValue) {
        TotalValue = totalValue;
    }

    public float getTotalVolumn() {
        return TotalVolumn;
    }

    public void setTotalVolumn(float totalVolumn) {
        TotalVolumn = totalVolumn;
    }

    public Market getBids() {
        return Bids;
    }

    public void setBids(Market bids) {
        Bids = bids;
    }

    public Market getAsks() {
        return Asks;
    }

    public void setAsks(Market asks) {
        Asks = asks;
    }

    public Stock.Matched getMatched() {
        return Matched;
    }

    public void setMatched(Stock.Matched matched) {
        Matched = matched;
    }

    public Stock.Price getPrice() {
        return Price;
    }

    public void setPrice(Stock.Price price) {
        Price = price;
    }

    public Stock.Remain getRemain() {
        return Remain;
    }

    public void setRemain(Stock.Remain remain) {
        Remain = remain;
    }

    public Stock.Foreign getForeign() {
        return Foreign;
    }

    public void setForeign(Stock.Foreign foreign) {
        Foreign = foreign;
    }

    class Market {
        private ArrayList<Float> Prices;
        private ArrayList<Float> Volumns;
    }

    class Matched {
        private float Price;
        private float Volumn;
        private float Offset;
    }

    class Price {
        private float High;
        private float Average;
        private float Low;
    }

    class Remain {
        private float Bid;
        private float Ask;
    }

    class Foreign {
        private float Bought;
        private float Sold;
        private float Room;
    }
}
