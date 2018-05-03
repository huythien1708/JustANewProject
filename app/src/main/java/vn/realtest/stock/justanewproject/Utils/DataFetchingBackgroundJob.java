package vn.realtest.stock.justanewproject.Utils;

import android.database.DatabaseErrorHandler;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import vn.realtest.stock.justanewproject.Presenters.StockPresenter;

/**
 * Created by Tran on 28-Jan-18.
 */

public abstract class DataFetchingBackgroundJob extends AsyncTask<Void, Void, String> {

    private String url;

    public DataFetchingBackgroundJob(String url) {
        this.url = url;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... voids) {
        String dataString = "";
        try {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            dataString = sh.sendRequest(url, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataString;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        OnProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        OnPostExecute(s);
    }

    public abstract void OnPostExecute(String result);

    public abstract void OnProgressUpdate(Void... values);
}
