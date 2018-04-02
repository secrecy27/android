package com.android.kj.movielist;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
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
    ListViewAdapter adapter=new ListViewAdapter();
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
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListViewItem listViewItem=(ListViewItem)parent.getItemAtPosition(position);

                Intent intent =new Intent(getApplicationContext(),movie_detail.class);
                intent.putExtra("title", listViewItem.getTitle().toString());
                intent.putExtra("date",listViewItem.getDate().toString());
                intent.putExtra("grade",listViewItem.getGrade().toString());
                intent.putExtra("poster",listViewItem.getPoster().toString());
                intent.putExtra("overview",listViewItem.getOverview().toString());
                startActivity(intent);

            }
        });
    }


    private void dataParser(String result){


        String title; //제목
        String date;  //개봉일
        String grade; //평점
        String poster;//영화포스터 경로
        String overview;//줄거리

        try{
            final JSONArray array=new JSONObject(result).getJSONArray("results");

            for (int i=0; i<array.length();i++){
                JSONObject object=array.getJSONObject(i);

                title=object.optString("title");
                date=object.optString("release_date");
                grade=object.optString("vote_average");
                poster=object.optString("poster_path");
                overview=object.optString("overview");

                adapter.addItem(title,date,grade,poster,overview);

            }

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