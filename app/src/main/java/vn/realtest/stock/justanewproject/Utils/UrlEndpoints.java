package vn.realtest.stock.justanewproject.Utils;

/**
 * Created by Tran on 22-Jan-18.
 */

public class UrlEndpoints {

    public static class CompanyInfo {
        public static String HNX = "http://price.tvsi.com.vn/HNXSecuritiesName.ashx?FloorCode=02";
        public static String HOSE = "http://price.tvsi.com.vn/HNXSecuritiesName.ashx?FloorCode=10";
        public static String UPCOM = "http://price.tvsi.com.vn/HNXSecuritiesName.ashx?FloorCode=04";
    }

    public static class StockDetail {
        public static String HNX = "http://price2.tvsi.com.vn/DataForLoad.ashx?FloorCode=02";
        public static String HOSE = "http://price2.tvsi.com.vn/DataForLoad.ashx?FloorCode=10";
        public static String UPCOM = "http://price2.tvsi.com.vn/DataForLoad.ashx?FloorCode=04";
    }
}
