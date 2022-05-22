package com.warlocks.minesweeper;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowId;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.warlocks.minesweeper.util.GridGenerator;
import com.warlocks.minesweeper.util.PrintGrid;
import com.warlocks.minesweeper.views.ShowNoOfREmainingFlags;
import com.warlocks.minesweeper.views.grid.Cell;

import org.w3c.dom.Text;

public class GameEngine{


    private static GameEngine instance;
    private static InitialVariables initialVariables;
    public static  int BOMB_NUMBER = InitialVariables.getInstance().BOMB_NUMBER; //public static??
    int notFLAGGED=BOMB_NUMBER;
    public static  int WIDTH = InitialVariables.getInstance().WIDTH;
    public static  int HEIGHT = InitialVariables.getInstance().HEIGHT;

    private static String endString="";

    private Context context;
    private Cell[][] MineSweeperGrid=new Cell[WIDTH][HEIGHT];
    CountDownTimer countDownTimer= new CountDownTimer(InitialVariables.getInstance().timer, 1000) {

        public void onTick(long millisUntilFinished) {
            ShowCountDownTimer showCountDownTimer=new ShowCountDownTimer(context, String.valueOf(millisUntilFinished / 1000));
        }

        public void onFinish() {
            ShowCountDownTimer showCountDownTimer=new ShowCountDownTimer(context,"Times UP!!");
            endGame();
            Toast.makeText(context,"Game Lost!!",Toast.LENGTH_SHORT).show();
        }
    }.start();
    public static GameEngine getInstance() {
        if( instance == null ){
            instance = new GameEngine();
        }
        return instance;
    }

    private GameEngine(){ }
    public void createGrid(Context context){
        Log.e("","Game Engine Running!!");
        this.context=context;
        int[][] mainGrid= GridGenerator.generate(BOMB_NUMBER,WIDTH,HEIGHT);
        PrintGrid.print(mainGrid,WIDTH,HEIGHT);
        setGrid(context,mainGrid);
    }
    private void setGrid(final Context context,int[][] grid){
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if(MineSweeperGrid[i][j]==null){
                    MineSweeperGrid[i][j]=new Cell(context,i,j);
                }
                MineSweeperGrid[i][j].setValue(grid[i][j]);
                MineSweeperGrid[i][j].invalidate();
            }
        }
    }
    public Cell getCellAt(int position) {
        int x=position%WIDTH;
        int y=position/WIDTH;
        return MineSweeperGrid[x][y];
    }
    public Cell getCellAt(int x,int y){
        return MineSweeperGrid[x][y];
    }

    public void click(int xPos, int yPos) {

        if (xPos >= 0 && yPos >= 0 && xPos < WIDTH && yPos < HEIGHT && !getCellAt(xPos, yPos).isClicked()) {
            getCellAt(xPos,yPos).setClicked();
            if(getCellAt(xPos,yPos).getValue()==0){
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <=1 ; j++) {
                        if(i!=j){
                            click(xPos+i,yPos+j);
                        }
                    }
                }
            }
            if(getCellAt(xPos,yPos).isBomb()){
                onGameLost();
            }
        }
        if(checkEnd()){
            onGameLost();
        }

    }

    private boolean checkEnd() {

        int bomNotFound=BOMB_NUMBER;
        int notRevealed= WIDTH*HEIGHT;
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if(getCellAt(i,j).isRevealed() || getCellAt(i,j).isFlagged()){
                    notRevealed--;
                }
                if(getCellAt(i,j).isFlagged() && getCellAt(i,j).isBomb()){
                    bomNotFound--;
                }
            }
        }

//        remainingFlags.setText(String.valueOf(notFLAGGED));
        if(bomNotFound==notRevealed){ //   && notRevealed==0
            Toast.makeText(context,"Game Won!!",Toast.LENGTH_SHORT).show();
            endString="Game Won!!";
//            Cell.exit();
            endGame();

        }
        return false;
    }

    private void onGameLost(){
        Toast.makeText(context,"Game Lost!!",Toast.LENGTH_SHORT).show();
        endString="Game Lost!!";
        endGame();
//        instance=null;
//        Cell.exit();
    }
    public void endGame(){
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if(!getCellAt(i,j).isRevealed() && !getCellAt(i,j).isBomb())
                {
//                    getCellAt(i,j).setClickable(false);
                }
//                getCellAt(i,j).setGameEnded(true);
                getCellAt(i,j).setRevealed();

            }
        }
//        Toast.makeText(context,"Inside Game engine",Toast.LENGTH_SHORT).show();
//        Dialog showEndDialogBox=new ShowEndDialogBox().onCreateDialog(null);
//        showEndDialogBox.show();

    }


    public void Flag(int xPos, int yPos) {
        boolean isFlagged=getCellAt(xPos,yPos).isFlagged();//to be?

        getCellAt(xPos,yPos).setFlagged(!isFlagged);

        getCellAt(xPos,yPos).invalidate();
        if(getCellAt(xPos,yPos).isFlagged()){
            notFLAGGED--;
        }
        else{
            notFLAGGED++;
        }
        ShowNoOfREmainingFlags showNoOfREmainingFlags=new ShowNoOfREmainingFlags(context);
        showNoOfREmainingFlags.update(notFLAGGED);
    }

}
