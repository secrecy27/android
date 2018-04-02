package com.android.kj.movielist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    private ArrayList<ListViewItem> listViewItemList = new ArrayList<>() ;

    public ListViewAdapter(){
    }

    public void addItem(String title, String date,String grade,String poster) {
        ListViewItem item = new ListViewItem();

        item.setTitle(title);
        item.setDate(date);
        item.setGrade(grade);
        item.setPoster(poster);

        listViewItemList.add(item);

        Log.i("poster path: ",poster);

    }

    @Override
    public int getCount() {
        return listViewItemList.size() ;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }

        ImageView poster = (ImageView) convertView.findViewById(R.id.imageView) ;
        TextView title = (TextView) convertView.findViewById(R.id.textView1) ;
        TextView date = (TextView) convertView.findViewById(R.id.textView2);
        TextView grade=(TextView) convertView.findViewById(R.id.textView3);


        ListViewItem listViewItem = listViewItemList.get(position);


        Glide.with(context).load("https://image.tmdb.org/t/p/w300_and_h450_bestv2"+listViewItemList.get(position).getPoster()).into(poster);

        title.setText("제목 : "+listViewItem.getTitle());
        date.setText("개봉일 :"+listViewItem.getDate());
        grade.setText("평점 : "+listViewItem.getGrade());

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position ;
    }

    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position) ;
    }


}
