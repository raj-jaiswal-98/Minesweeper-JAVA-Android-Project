package com.warlocks.minesweeper.views;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import com.warlocks.minesweeper.R;

import org.w3c.dom.Text;

public class ShowNoOfREmainingFlags {
    Context context;
    public ShowNoOfREmainingFlags(Context context){
        this.context=context;
    }
    public void update(int notFlagged){
        TextView textView=(TextView)((Activity)context).findViewById(R.id.noOfFlags);
        textView.setText("Flags left: "+String.valueOf(notFlagged));
    }
}
