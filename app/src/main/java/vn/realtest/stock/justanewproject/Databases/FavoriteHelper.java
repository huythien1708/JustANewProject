package vn.realtest.stock.justanewproject.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import vn.realtest.stock.justanewproject.Data.FavoriteData;

/**
 * Created by Paul on 4/17/2018.
 */

public class FavoriteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "favorite.db";
    private static final String TABLE_FAVORITE = "tb_favorite";

    private static final String ID_FAVORITE = "id";
    private static final String STOCK_NAME = "stock_name";
    private static final String STOCK_INDEX = "stock_index";
    private static final String ID_SAN = "id_san";

    public static final String CREATE_TABLE_GAUGE =
            "CREATE TABLE " + TABLE_FAVORITE + "(" +
                    ID_FAVORITE + " INTEGER PRIMARY KEY " +
                    ", " + STOCK_NAME + " TEXT NOT NULL," +
                    STOCK_INDEX + " TEXT NOT NULL," +
                    ID_SAN + " TEXT NOT NULL" + ")";

    public FavoriteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Chạy lệnh tạo bảng.
        db.execSQL(CREATE_TABLE_GAUGE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITE);
        // Và tạo lại.
        onCreate(db);
    }

    public void addFavoriteData(FavoriteData favoriteData) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(STOCK_NAME, favoriteData.getStock_name());
        values.put(STOCK_INDEX, favoriteData.getStock_index());
        values.put(ID_SAN, favoriteData.getId_san());

        db.insert(TABLE_FAVORITE, null, values);

        db.close();
    }

    public FavoriteData getFavoriteData(int id) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_FAVORITE, new String[]{ID_FAVORITE,
                        STOCK_NAME, STOCK_INDEX, ID_SAN}, ID_FAVORITE + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0)
            cursor.moveToFirst();

        FavoriteData favoriteData = new FavoriteData(Long.parseLong(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3));
        // return data
        return favoriteData;
    }

    public List<FavoriteData> getAllFavoriteData() {
        List<FavoriteData> favoriteDataList = new ArrayList<FavoriteData>();

        String selectQuery = "SELECT *FROM " + TABLE_FAVORITE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (cursor.moveToFirst()) {
            do {
                FavoriteData favoriteData = new FavoriteData();
                favoriteData.setId(Long.parseLong(cursor.getString(0)));
                favoriteData.setStock_name(cursor.getString(1));
                favoriteData.setStock_index(cursor.getString(2));
                favoriteData.setId_san(cursor.getString(3));

                // Thêm vào danh sách.
                favoriteDataList.add(favoriteData);
            } while (cursor.moveToNext());
        }
        return favoriteDataList;
    }

    public int getDatabaseFavoriteCount() {
        String countQuery = "SELECT *FROM " + TABLE_FAVORITE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public void deleteFavoriteData(FavoriteData favoriteData) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FAVORITE, ID_FAVORITE + "=?",
                new String[]{String.valueOf(favoriteData.getId())});
        db.execSQL("UPDATE " + TABLE_FAVORITE + " set " + ID_FAVORITE + " = (" + ID_FAVORITE + "-1) where " + ID_FAVORITE + " > " + String.valueOf(favoriteData.getId()));
        db.close();
    }

    public boolean dataExist(String stockname) {
        String countQuery = "SELECT * FROM " + TABLE_FAVORITE + " WHERE stock_name = '" + stockname + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        boolean exist = (cursor.getCount() > 0);
        cursor.close();
        db.close();
        return exist;

    }
}
