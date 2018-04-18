package vn.realtest.stock.justanewproject.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import vn.realtest.stock.justanewproject.Data.FundStock;

/**
 * Created by Paul on 4/17/2018.
 */
// String stock_name;
//         String stock_amount;
//         String stock_buy_date;
//         String stock_can_trade;
public class FundHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Fund_Manager";
    private static final String TABLE_FUND = "Fund";

    private static final String ID_FUND = "id";
    private static final String STOCK_NAME = "stock_name";
    private static final String STOCK_AMOUNT = "stock_amount";
    private static final String STOCK_BUY_DATE = "stock_buy_date";
    private static final String STOCK_CAN_TRADE = "stock_can_trade";

    public FundHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Script tạo bảng.
        String script = "CREATE TABLE " + TABLE_FUND + "("
                + ID_FUND + " INTEGER PRIMARY KEY," + STOCK_NAME + " TEXT," + STOCK_AMOUNT + " TEXT," + STOCK_BUY_DATE + " TEXT,"
                + STOCK_CAN_TRADE + " TEXT" + ")";
        // Chạy lệnh tạo bảng.
        db.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FUND);


        // Và tạo lại.
        onCreate(db);
    }

    public FundStock getFundData(int id) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_FUND, new String[]{ID_FUND,
                        STOCK_NAME, STOCK_AMOUNT, STOCK_BUY_DATE, STOCK_CAN_TRADE}, ID_FUND + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        FundStock fundStock = new FundStock(Long.parseLong(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
        // return note
        return fundStock;
    }

    public List<FundStock> getAllFundData() {
        List<FundStock> fundStockList = new ArrayList<FundStock>();

        String selectQuery = "SELECT *FROM " + TABLE_FUND;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (cursor.moveToFirst()) {
            do {
                FundStock fundStock = new FundStock();
                fundStock.setId(Long.parseLong(cursor.getString(0)));
                fundStock.setStock_name(cursor.getString(1));
                fundStock.setStock_amount(cursor.getString(2));
                fundStock.setStock_buy_date(cursor.getString(3));
                fundStock.setStock_can_trade(cursor.getString(4));

                // Thêm vào danh sách.
                fundStockList.add(fundStock);
            } while (cursor.moveToNext());
        }
        return fundStockList;
    }

    public int getDatabaseFundCount() {
        String countQuery = "SECLECT *FROM " + TABLE_FUND;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        return count;
    }

    public void deleteFundData(FundStock fundStock) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FUND, ID_FUND + "=?",
                new String[]{String.valueOf(fundStock.getId())});
        db.close();
    }

    public int updateFundData(FundStock fundStock) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(STOCK_NAME, fundStock.getStock_name());
        values.put(STOCK_AMOUNT, fundStock.getStock_amount());
        values.put(STOCK_BUY_DATE, fundStock.getStock_buy_date());
        values.put(STOCK_CAN_TRADE, fundStock.getStock_can_trade());


        return db.update(TABLE_FUND, values, ID_FUND + "=?",
                new String[]{String.valueOf(fundStock.getId())});
    }
}