package com.android.kj.movielist;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private List<HashMap<String,String>> list=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list=new ArrayList<HashMap<String,String>>();

        String result="제이슨 담을 스트링";
        //스레드 생성
        Task task = new Task();
        try {
            result = task.execute().get();
        }catch(Exception e){
            e.printStackTrace();
        }

        listView = (ListView) findViewById(R.id.listview);

        dataParser(result);

    }


    private void dataParser(String result){
        ListViewAdapter adapter=new ListViewAdapter();

        String title;
        String date;
        String grade;
        String poster;

        try{
            JSONArray array=new JSONObject(result).getJSONArray("results");

            for (int i=0; i<array.length();i++){
                JSONObject object=array.getJSONObject(i);

                title=object.optString("title");
                date=object.optString("release_date");
                grade=object.optString("vote_average");
                poster=object.optString("poster_path");

                adapter.addItem(title,date,grade,poster);

            }

            listView.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    class Task extends AsyncTask<String, String, String> {
        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=";   //현재 상영중인 영화

        @Override
        protected String doInBackground(String... voids) {
            String result;  //결과값 저장변수
            RequestURLConnection conn = new RequestURLConnection();
            result = conn.request(url);
            //Log.i("result", "결과값 : " + result);
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

}