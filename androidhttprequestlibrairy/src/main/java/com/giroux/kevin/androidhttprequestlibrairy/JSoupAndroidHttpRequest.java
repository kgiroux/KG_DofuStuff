package com.giroux.kevin.androidhttprequestlibrairy;

import android.util.Log;

import com.giroux.kevin.androidhttprequestlibrairy.constants.Constants;
import com.giroux.kevin.androidhttprequestlibrairy.constants.TypeMine;

import org.json.JSONException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by kevin on 30/03/2017.
 */

public class JSoupAndroidHttpRequest extends AndroidHttpRequest {

    private int timeout = 30000;
    protected JSoupAndroidHttpRequest(String url, String method, Map<String, String> paramStr) {
        super(url, method, paramStr);
        this.setTypeMine(TypeMine.APPLICATION);
    }

    private Document executeRequest(String uri){
        Document doc = null;
        try {
            doc = Jsoup.connect(uri).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

    @Override
    protected Object doInBackground(String[]... params) {
        return executeRequest(this.getUrl());
    }
}
