package com.android.kj.movielist;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listview;
        ListViewAdapter adapter;

        adapter = new ListViewAdapter();

        listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);

        //스레드 생성
        Task task=new Task();
        task.execute();
    }


}

class Task extends AsyncTask<String, String, String> {

    String clientKey = "d911521c2f36edffaab6594e30697509";
    String url="https://api.themoviedb.org/3/movie/latest?api_key=";

    @Override
    protected String doInBackground(String... voids) {
        String result;  //결과값 저장변수
        RequestURLConnection conn = new RequestURLConnection();
        result = conn.request(url);
        Log.i("result","결과값 : "+result);
        return result;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

    }

}

