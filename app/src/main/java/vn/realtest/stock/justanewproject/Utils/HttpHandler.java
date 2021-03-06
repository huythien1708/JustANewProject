package vn.realtest.stock.justanewproject.Utils;

/**
 * Created by nam.tran on 28-Jan-18.
 */

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpHandler {

    private static final String TAG = HttpHandler.class.getSimpleName();

    private static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public String sendRequest(String reqUrl, String params) {
        try {

            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = RequestBody.create(JSON, params);

            Request request = new Request.Builder().addHeader("Content-Type", "application/json")
                    .url(reqUrl)
                    .post(requestBody)
                    .build();

            Response response = client.newCall(request).execute();
            return response.body().string();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


}
