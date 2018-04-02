package com.android.kj.movielist;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestURLConnection {
    public String request(String _url){
        String str, receiveMsg = null;
        String api_key = "d911521c2f36edffaab6594e30697509";;
        try {
            String urlex=_url+api_key;
            URL url = new URL(urlex);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

            if (conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();
                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                }
                receiveMsg = buffer.toString();
                Log.i("result : ", receiveMsg);

                reader.close();
            } else {
                Log.i("result", conn.getResponseCode() + "에러");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return receiveMsg;

    }

}
