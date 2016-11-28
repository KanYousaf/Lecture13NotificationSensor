package com.example.kanwal.listwithswipetest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {


    private TextView details_season_tv,display_details_season_tv;
    //    public static final String[] Season_Details={"Silicon Valley is about Richard Henricks and his company named pied piper",
//            "Game of Thrones is a fantasy drama",
//            "Big Bang Theory is scientific sci-fi drama",
//            "Prison Break is about the story of Micheal Scofield and his brother",
//            "Citizen Khan: Mr. Khan , a pakistani citizen living abroad in UK",
//            "Divinci Demons: Story about Leonardo Divinci",
//            "Mr. Robot is about hacker's story and how he wants to take revenge on society",
//            "House of Cards is about underwood and his compaign to become president of USA",
//            "Sherlock Holmes: Detective to solve crimes"
//    };
    public static final String[] Season_Details={"Silicon Valley","Silicon Valley is about Richard Henricks and his company named pied piper",
            "Game of Thrones","Game of Thrones is a fantasy drama",
            "Big Bang Theory","Big Bang Theory is scientific sci-fi drama",
            "Prison Break","Prison Break is about the story of Micheal Scofield and his brother",
            "Citizen Khan","Citizen Khan: Mr. Khan , a pakistani citizen living abroad in UK",
            "Divinci Demons","Divinci Demons: Story about Leonardo Divinci",
            "Mr. Robot","Mr. Robot is about hacker's story and how he wants to take revenge on society",
            "House of Cards","House of Cards is about underwood and his compaign to become president of USA",
            "Sherlock Holmes","Sherlock Holmes: Detective to solve crimes"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        details_season_tv=(TextView)this.findViewById(R.id.display_info);
        display_details_season_tv=(TextView)this.findViewById(R.id.details_season);
        //getIntent
        Intent getIntentCalled=getIntent();
        int season_id=getIntentCalled.getExtras().getInt("Season_ID");
        String season_name=getIntentCalled.getStringExtra("Season_Name");

        details_season_tv.setText("SeasonID : " + season_id + "\n" + "Details: " + "\n" + season_name);
//        display_details_season_tv.setText(Season_Details[season_id]);
        for(int i=0; i< Season_Details.length; i+=2){
            if(Season_Details[i].equals(season_name)){
                display_details_season_tv.setText(Season_Details[i+1]);
            }
        }
    }
}
