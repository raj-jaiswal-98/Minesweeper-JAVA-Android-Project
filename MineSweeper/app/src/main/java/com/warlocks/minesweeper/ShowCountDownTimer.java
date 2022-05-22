package com.warlocks.minesweeper;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;
    public class ShowCountDownTimer {
        Context context;
        public ShowCountDownTimer(Context context,String text){
            TextView textView=(TextView)((Activity)context).findViewById(R.id.timer);
            textView.setText(text);
        }
    }
