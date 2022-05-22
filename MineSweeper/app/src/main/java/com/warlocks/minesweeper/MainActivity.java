package com.warlocks.minesweeper;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static android.content.Intent.makeRestartActivityTask;

public class MainActivity extends Activity {
//    public boolean gameRunning = true;
    RadioButton choice;
    RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
//        Button button;
//        button=(Button)findViewById(R.id.StartButton);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startGame();
//            }
//        });

    }
    public void startGame(View view){
        Intent intent=new Intent(this,GameActivity.class);
        int id_choice=radioGroup.getCheckedRadioButtonId();
        choice=(RadioButton)findViewById(id_choice);
        if(id_choice==-1){
            Toast.makeText(MainActivity.this,"Please Select Difficulty", Toast.LENGTH_SHORT).show();
        }
        else{
//            String options[] ={"Beginner","Mediocre","BugaBoo"};
            String userChoice=(String)choice.getText();
            if(userChoice.equals("  Beginner")) {
                InitialVariables.getInstance().beginner();
            }
            else if(userChoice.equals("  Mediocre")) {
                InitialVariables.getInstance().mediocre();
            }
            else if(userChoice.equals("  BugaBoo")) {
                InitialVariables.getInstance().bugaboo();
            }
                startActivity(intent);
//            Toast.makeText(this,"After finish",Toast.LENGTH_SHORT).show();
        }

    }
    public void exit_game(View view){
        Toast.makeText(this,"Thanks You!!",Toast.LENGTH_SHORT).show();
        finish();
        System.exit(0);
    }
    public void controls(View view){
        AlertDialog.Builder builder =new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("⁍ Tap an empty square to reveal it.\n" +
                "⁍ Long-press an empty square to flag it.\n" +
                "⁍ Press Main Menu to return to the home screen.");
        builder.setTitle("Controls");
        builder.setCancelable(false);
        builder.setPositiveButton("OK!!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });
        AlertDialog alertDialog= builder.create();
        alertDialog.show();
    }
    public void gameRules(View view){
        AlertDialog.Builder builder =new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Cells have three states: uncovered, covered, and flagged. A covered cell is blank and clickable, while an uncovered cell is exposed. Flagged cells are those marked by the player to indicate a potential mine location.\n" +
                "\n" +
                "If a player uncovers a mined cell, the player loses and the game ends. Otherwise, the uncovered cells displays either a number, indicating the number of mines diagonally and/or adjacent to it, or a blank tile, and all adjacent non-mined cells will automatically be uncovered. Right-clicking on a cell will flag it, causing a flag to appear on it. Flagged cells are still considered covered, and a player can unflag (with an additional right-click) and click to uncover the cell.\n" +
                "\n" +
                "To win the game, players must uncover all non-mine cells. Flagging all the mined cells is not required.");
        builder.setTitle("Game Rules");
        builder.setCancelable(false);
        builder.setPositiveButton("OK!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });
        AlertDialog alertDialog= builder.create();
        alertDialog.show();
    }
}