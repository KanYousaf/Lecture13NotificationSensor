package com.example.kanwal.listwithswipetest;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private static final String TAG="List_APP";
    private ListView myFavSeasonList;
    private ArrayList<String> myFavSeasonArrayList;
    private ArrayList<Integer> myFavSeasonImageArrayList;
    private static final String[] myFavSeasonArray={
            "Silicon Valley",
            "Game of Thrones",
            "Big Bang Theory",
            "Prison Break",
            "Citizen Khan",
            "Divinci Demons",
            "Mr. Robot",
            "House of Cards",
            "Sherlock Holmes"};

    private static final int[] myFavSeasonImageArray={
            R.drawable.siliconvalley,
            R.drawable.gameofthrones,
            R.drawable.bigbangtheory,
            R.drawable.prisonbreak,
            R.drawable.citizenkhan,
            R.drawable.divincidemons,
            R.drawable.mrrobot,
            R.drawable.houseofcards,
            R.drawable.sherlockholmes};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myFavSeasonList=(ListView)this.findViewById(R.id.list_of_seasons);

        if(savedInstanceState==null) {
            myFavSeasonArrayList = new ArrayList<>(Arrays.asList(myFavSeasonArray));
            myFavSeasonImageArrayList = new ArrayList<>();
            for (int index = 0; index < myFavSeasonImageArray.length; index++) {
                myFavSeasonImageArrayList.add(myFavSeasonImageArray[index]);
            }
        }
        else{
            loadArray(this);
        }

        //array adapter
//        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(MainActivity.this, android.R.layout
//                                                .simple_list_item_1,myFavSeasonArray);

        //Custom Adapter

//        CustomAdapter myOwnAdapter=new CustomAdapter(MainActivity.this, myFavSeasonArray, myFavSeasonImageArray);

        //Custom Adapter ArrayList
        final CustomAdapter myOwnAdapter=new CustomAdapter(MainActivity.this, myFavSeasonArrayList, myFavSeasonImageArrayList);
        myFavSeasonList.setAdapter(myOwnAdapter);
        myFavSeasonList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String season_name = myFavSeasonList.getItemAtPosition(position).toString();
                Intent sendForwardIntent = new Intent(MainActivity.this, DetailsActivity.class);
                sendForwardIntent.putExtra("Season_ID", position);
                sendForwardIntent.putExtra("Season_Name", season_name);
                startActivity(sendForwardIntent);
            }
        });

        myFavSeasonList.setOnTouchListener(new OnSwipeMotionListener(MainActivity.this, myFavSeasonList){

            @Override
            public void onSwipeRight(final int pos) {
                super.onSwipeRight(pos);

                String season_name=myFavSeasonArrayList.get(pos).toString();
                final AlertDialog.Builder alertBox=new AlertDialog.Builder(MainActivity.this);
                //set Title and Dialog Message
                alertBox.setTitle("Do You Want to Delete it? " + season_name);
                alertBox.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myFavSeasonArrayList.remove(pos);
                        myFavSeasonImageArrayList.remove(pos);
                        myOwnAdapter.notifyDataSetChanged();
                    }
                });

                alertBox.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                // create alert dialog
                AlertDialog alertDialog=alertBox.create();
                alertDialog.show();
            }


            @Override
            public void onSwipeLeft(int pos) {
                super.onSwipeLeft(pos);
                myFavSeasonArrayList.remove(pos);
                myFavSeasonImageArrayList.remove(pos);
                myOwnAdapter.notifyDataSetChanged();
            }
        });

//        myFavSeasonList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
//                String season_name=myFavSeasonArrayList.get(position).toString();
//                final AlertDialog.Builder alertBox=new AlertDialog.Builder(MainActivity.this);
//                //set Title and Dialog Message
//                alertBox.setTitle("Do You Want to Delete it? " + season_name);
//                alertBox.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        myFavSeasonArrayList.remove(position);
//                        myFavSeasonImageArrayList.remove(position);
//                        myOwnAdapter.notifyDataSetChanged();
//                    }
//                });
//
//                alertBox.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//
//                // create alert dialog
//                AlertDialog alertDialog=alertBox.create();
//                alertDialog.show();
//                return true;
//            }
//        });
    }

    @Override
    protected void onPause() {
        saveArray();
        Log.i(TAG, "on pause called");
        super.onPause();
    }

    @Override
    protected void onStop() {
        saveArray();
        Log.i(TAG, "on stop called");
        super.onStop();
    }

    public boolean saveArray(){
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        SharedPreferences.Editor prefEditor=preferences.edit();
        prefEditor.putInt("name_array_size", myFavSeasonArrayList.size());
        prefEditor.putInt("image_array_size", myFavSeasonImageArrayList.size());
        for(int i=0; i< myFavSeasonArrayList.size(); i++){
            prefEditor.remove("season_name"+i);
            prefEditor.putString("season_name", myFavSeasonArrayList.get(i));
        }

        for(int i=0; i< myFavSeasonImageArrayList.size(); i++){
            prefEditor.remove("season_image" + i);
            prefEditor.putInt("season_image", myFavSeasonImageArrayList.get(i));
        }
        return prefEditor.commit();
    }

    public void loadArray(Context mContext){
        SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(mContext);
        int name_size=preferences.getInt("name_array_size", 0);
        int image_size=preferences.getInt("image_array_size",0);

        for(int i=0; i< name_size; i++) {
                myFavSeasonArrayList.add(preferences.getString("season_name" + i, null));
        }

        for(int i=0; i< image_size; i++) {
            myFavSeasonImageArrayList.add(preferences.getInt("season_image" + i, 0));
        }
    }
}
