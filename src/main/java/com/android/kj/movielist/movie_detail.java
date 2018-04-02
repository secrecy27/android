package com.android.kj.movielist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class movie_detail extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);

        Intent intent = new Intent(this.getIntent());

        String poster = intent.getStringExtra("poster");
        String title = intent.getStringExtra("title");
        String date = intent.getStringExtra("date");
        String grade = intent.getStringExtra("grade");
        String overview = intent.getStringExtra("overview");


        ImageView imageView = (ImageView) findViewById(R.id.image);
        TextView textView = (TextView) findViewById(R.id.text1);
        TextView textView2 = (TextView) findViewById(R.id.text2);
        TextView textView3 = (TextView) findViewById(R.id.text3);
        TextView textView4 = (TextView) findViewById(R.id.text4);


        Glide.with(this).load("https://image.tmdb.org/t/p/w300_and_h450_bestv2" + poster).into(imageView);
        textView.setText("제목 : " + title);
        textView2.setText("개봉일 : " + date);
        textView3.setText("평점 : " + grade);
        textView4.setText("줄거리 : "+overview);

    }
}