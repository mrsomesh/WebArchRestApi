package com.example.webarchrestapi;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;

import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import static android.support.v4.content.ContextCompat.getSystemService;

public class DataFetch extends AsyncTask<Void,Void,Void> {
    String data="";
    String dataParse="";
    String singleParse="";
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("http://www.boredapi.com/api/activity/");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line!=null)
            {
                line = bufferedReader.readLine();
                data= data+line;
            }

            JSONObject ja = new JSONObject(data);

                singleParse="Activity:"+ja.get("activity");
                dataParse = dataParse + singleParse;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        MainActivity.data.setText(this.dataParse);
    }

}
