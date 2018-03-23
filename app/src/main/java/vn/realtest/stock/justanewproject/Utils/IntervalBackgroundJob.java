package vn.realtest.stock.justanewproject.Utils;


import android.os.Handler;
import android.util.Log;

/**
 * Created by Tran on 21-Mar-18.
 */

public abstract class IntervalBackgroundJob {

    private Handler handler;

    public IntervalBackgroundJob(long delayMilis, final long intervalMilis) {
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                onDoingInterval();
                if (handler != null) {
                    handler.postDelayed(this, intervalMilis);
                }
            }
        }, delayMilis);
    }

    public abstract void onDoingInterval();

    public void cancel() {
        if (handler != null) {
            handler = null;
        }
    }

}
