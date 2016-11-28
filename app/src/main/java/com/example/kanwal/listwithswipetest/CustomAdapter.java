package com.example.kanwal.listwithswipetest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kanwal on 10/10/2016.
 */
public class CustomAdapter extends ArrayAdapter<String>{
//    private String[] season_name;
//    private int[] season_image;

    private ArrayList<String> season_name;
    private ArrayList<Integer> season_image;
//    public CustomAdapter(Context context, String[] season_name, int[] season_image) {
//        super(context, R.layout.ownlist, season_name);
//        this.season_image=season_image;
//        this.season_name=season_name;
//    }

    public CustomAdapter(Context context, ArrayList<String> season_name, ArrayList<Integer> season_image) {
        super(context, R.layout.ownlist, season_name);
        this.season_image=season_image;
        this.season_name=season_name;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;
        LayoutInflater inflater=LayoutInflater.from(getContext());
        rowView=inflater.inflate(R.layout.ownlist, parent, false);

        TextView display_season_name=(TextView)rowView.findViewById(R.id.display_season_name);
        ImageView display_season_image=(ImageView)rowView.findViewById(R.id.display_season_image);

//        display_season_name.setText(season_name[position]);
//        display_season_image.setImageResource(season_image[position]);

        display_season_name.setText(season_name.get(position));
        display_season_image.setImageResource(season_image.get(position));
        return rowView;
    }
}
