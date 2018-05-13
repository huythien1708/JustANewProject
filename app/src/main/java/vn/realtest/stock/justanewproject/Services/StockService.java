package vn.realtest.stock.justanewproject.Services;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;

import vn.realtest.stock.justanewproject.Models.Stock;
import vn.realtest.stock.justanewproject.Models.StockType;
import vn.realtest.stock.justanewproject.Presenters.StockPresenter;
import vn.realtest.stock.justanewproject.Utils.GlobalStorage.StockStorage;
import vn.realtest.stock.justanewproject.Utils.UrlEndpoints;

import static android.os.Process.THREAD_PRIORITY_BACKGROUND;

/**
 * Created by Tran on 12-May-18.
 */

public class StockService extends Service {
    private Looper mServiceLooper;
    private ServiceHandler mServiceHandler;
    private static final long TIMEFORRELOADING = 15 * 1000; // 1 minute

    // Handler that receives messages from the thread
    private class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }
        @Override
        public void handleMessage(Message msg) {
            // Normally we would do some work here, like download a file.
            // For our sample, we just sleep for 5 seconds.
            while (true) {
                try {
                    getStockData();
                    Log.d("services", "Data Loaded");
                    Thread.sleep(TIMEFORRELOADING);
                } catch (InterruptedException e) {
                    // Restore interrupt status.
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // Start up the thread running the service. Note that we create a
        // separate thread because the service normally runs in the process's
        // main thread, which we don't want to block. We also make it
        // background priority so CPU-intensive work doesn't disrupt our UI.
        HandlerThread thread = new HandlerThread("ServiceStartArguments",
                THREAD_PRIORITY_BACKGROUND);
        thread.start();

        // Get the HandlerThread's Looper and use it for our Handler
        mServiceLooper = thread.getLooper();
        mServiceHandler = new ServiceHandler(mServiceLooper);
        Log.d("services", "Created");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // For each start request, send a message to start a job and deliver the
        // start ID so we know which request we're stopping when we finish the job
        Message msg = mServiceHandler.obtainMessage();
        msg.arg1 = startId;
        mServiceHandler.sendMessage(msg);

        Log.d("services", "On Start Command");

        // If we get killed, after returning from here, restart
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("services", "Destroyed");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void getStockData() {
        fetchDataFromUrl(UrlEndpoints.StockDetail.HNX, StockType.HNX);
        fetchDataFromUrl(UrlEndpoints.StockDetail.HOSE, StockType.HOSE);
        fetchDataFromUrl(UrlEndpoints.StockDetail.UPCOM, StockType.UPCOM);
    }

    private void fetchDataFromUrl(String url, final StockType type) {
        new StockPresenter(url) {

            @Override
            public void OnStockModel(ArrayList<Stock> stocks) {
                StockStorage.SetGlobalStockDataByType(type, stocks);
            }

            @Override
            public void OnProgressUpdate(Void... values) {

            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}
