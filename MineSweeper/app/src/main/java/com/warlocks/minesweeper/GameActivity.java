package com.warlocks.minesweeper;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.warlocks.minesweeper.views.ShowNoOfREmainingFlags;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game);
//        TextView remainingFlags=(TextView)findViewById(R.id.noOfFlags);
        GameEngine.getInstance().createGrid(this);
        ShowNoOfREmainingFlags showNoOfREmainingFlags=new ShowNoOfREmainingFlags(this);
        showNoOfREmainingFlags.update(GameEngine.getInstance().BOMB_NUMBER);
        Button button;
        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);

            }
        });
    }
//    public void ShowEndAlertBox(String endString){
//        AlertDialog.Builder builder =new AlertDialog.Builder(GameActivity.this);
//        builder.setMessage(endString);
//        builder.setTitle("Result!!");
//        builder.setCancelable(false);
//        builder.setPositiveButton("OK!", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                finish();
//            }
//        });
//        AlertDialog alertDialog= builder.create();
//        alertDialog.show();
//    }
}