package com.example.kanwal.listwithswipetest;


import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

import java.util.List;

public class OnSwipeMotionListener implements View.OnTouchListener {
    private Context context;
    private ListView listView;
    private GestureDetector gestureDetector;
    public OnSwipeMotionListener() {
        super();
    }

    public OnSwipeMotionListener(Context context,ListView listView ) {
        gestureDetector=new GestureDetector(context, new GestureListener());
        this.context=context;
        this.listView=listView;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return gestureDetector.onTouchEvent(motionEvent);
    }

    public void onSwipeRight(int pos){}
    public void onSwipeLeft(int pos){}

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener{
        private static final int THRESHOLD_DISTANCE=200;
        private static final int THRESHOLD_VELOCITY=50;

        private int getPosition(MotionEvent e1){
            return listView.pointToPosition((int)e1.getX(), (int)e1.getY());
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float distanceX= e2.getX() -e1.getX();
            float distanceY=e2.getY() -e1.getY();

            if(Math.abs(distanceX) > Math.abs(distanceY)
                    && Math.abs(distanceX) > THRESHOLD_DISTANCE
                    && Math.abs(velocityX) > THRESHOLD_VELOCITY){
                if(Math.abs(distanceX) > 0) onSwipeRight(getPosition(e1));
                else onSwipeLeft(getPosition(e1));
                return true;
            }
            return false;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }
    }
}
