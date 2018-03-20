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
    private int TotalVolume;
    private float PriceBid1;
    private float PriceBid2;
    private float PriceBid3;
    private int VolumeBid1;
    private int VolumeBid2;
    private int VolumeBid3;
    private float PriceMatched;
    private int VolumeMatched;
    private float OffsetMatched;
    private float PriceAsk1;
    private float PriceAsk2;
    private float PriceAsk3;
    private int VolumeAsk1;
    private int VolumeAsk2;
    private int VolumeAsk3;
    private float HighPrices;
    private float AveragePrices;
    private float LowPrices;
    private int BidRemain;
    private int AskRemain;
    private int RoomForeign;
    private int BoughtForeign;
    private int SoldForeign;

    public Stock(){}

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

    public int getTotalVolume() {
        return TotalVolume;
    }

    public void setTotalVolume(int totalVolume) {
        TotalVolume = totalVolume;
    }

    public float getPriceBid1() {
        return PriceBid1;
    }

    public void setPriceBid1(float priceBid1) {
        PriceBid1 = priceBid1;
    }

    public float getPriceBid2() {
        return PriceBid2;
    }

    public void setPriceBid2(float priceBid2) {
        PriceBid2 = priceBid2;
    }

    public float getPriceBid3() {
        return PriceBid3;
    }

    public void setPriceBid3(float priceBid3) {
        PriceBid3 = priceBid3;
    }

    public int getVolumeBid1() {
        return VolumeBid1;
    }

    public void setVolumeBid1(int volumeBid1) {
        VolumeBid1 = volumeBid1;
    }

    public int getVolumeBid2() {
        return VolumeBid2;
    }

    public void setVolumeBid2(int volumeBid2) {
        VolumeBid2 = volumeBid2;
    }

    public int getVolumeBid3() {
        return VolumeBid3;
    }

    public void setVolumeBid3(int volumeBid3) {
        VolumeBid3 = volumeBid3;
    }

    public float getPriceMatched() {
        return PriceMatched;
    }

    public void setPriceMatched(float priceMatched) {
        PriceMatched = priceMatched;
    }

    public int getVolumeMatched() {
        return VolumeMatched;
    }

    public void setVolumeMatched(int volumeMatched) {
        VolumeMatched = volumeMatched;
    }

    public float getOffsetMatched() {
        return OffsetMatched;
    }

    public void setOffsetMatched(float offsetMatched) {
        OffsetMatched = offsetMatched;
    }

    public float getPriceAsk1() {
        return PriceAsk1;
    }

    public void setPriceAsk1(float priceAsk1) {
        PriceAsk1 = priceAsk1;
    }

    public float getPriceAsk2() {
        return PriceAsk2;
    }

    public void setPriceAsk2(float priceAsk2) {
        PriceAsk2 = priceAsk2;
    }

    public float getPriceAsk3() {
        return PriceAsk3;
    }

    public void setPriceAsk3(float priceAsk3) {
        PriceAsk3 = priceAsk3;
    }

    public int getVolumeAsk1() {
        return VolumeAsk1;
    }

    public void setVolumeAsk1(int volumeAsk1) {
        VolumeAsk1 = volumeAsk1;
    }

    public int getVolumeAsk2() {
        return VolumeAsk2;
    }

    public void setVolumeAsk2(int volumeAsk2) {
        VolumeAsk2 = volumeAsk2;
    }

    public int getVolumeAsk3() {
        return VolumeAsk3;
    }

    public void setVolumeAsk3(int volumeAsk3) {
        VolumeAsk3 = volumeAsk3;
    }

    public float getHighPrices() {
        return HighPrices;
    }

    public void setHighPrices(float highPrices) {
        HighPrices = highPrices;
    }

    public float getAveragePrices() {
        return AveragePrices;
    }

    public void setAveragePrices(float averagePrices) {
        AveragePrices = averagePrices;
    }

    public float getLowPrices() {
        return LowPrices;
    }

    public void setLowPrices(float lowPrices) {
        LowPrices = lowPrices;
    }

    public int getBidRemain() {
        return BidRemain;
    }

    public void setBidRemain(int bidRemain) {
        BidRemain = bidRemain;
    }

    public int getAskRemain() {
        return AskRemain;
    }

    public void setAskRemain(int askRemain) {
        AskRemain = askRemain;
    }

    public int getRoomForeign() {
        return RoomForeign;
    }

    public void setRoomForeign(int roomForeign) {
        RoomForeign = roomForeign;
    }

    public int getBoughtForeign() {
        return BoughtForeign;
    }

    public void setBoughtForeign(int boughtForeign) {
        BoughtForeign = boughtForeign;
    }

    public int getSoldForeign() {
        return SoldForeign;
    }

    public void setSoldForeign(int soldForeign) {
        SoldForeign = soldForeign;
    }
}
