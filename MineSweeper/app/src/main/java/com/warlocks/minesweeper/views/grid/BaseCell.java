package com.warlocks.minesweeper.views.grid;

import android.content.Context;
import android.view.View;
import android.view.WindowId;

import com.warlocks.minesweeper.GameEngine;

public abstract class BaseCell extends View  {
    private int value;
    private boolean isBomb;
    private boolean isRevealed;
    private boolean isClicked;
    private boolean isFlagged;
//    private boolean isClickable=true;
//    private boolean isGameEnded=false;

    private int x,y;
    private int position;

    public BaseCell(Context context){
        super(context);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int val) {
        isBomb = false;
        isClicked=false;
        isFlagged=false;
        isRevealed=false;

        if(val==-1){
            isBomb =true;
        }

        value = val;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void setRevealed() {

        isRevealed = true;
        invalidate();
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked() {

        this.isClicked = true;
        this.isRevealed=true;
        invalidate();

    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }

    public boolean isBomb() {
        return isBomb;
    }

    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }


    public int getXPos() {
        return x;
    }

//    public void setX(int x) {
//        this.x = x;
//    }

    public int getYPos() {
        return y;
    }

//    public void setY(int y) {
//        this.y = y;
//    }

    public int getPosition() {
        return position;
    }

//    public void setPosition(int position) {
//        this.position = position;
//
//        x=position% GameEngine.WIDTH;
//        y=position/GameEngine.WIDTH;
//        invalidate();
//    }
    public void setPosition(int x, int y) {
        this.x=x;
        this.y=y;
        position=x+GameEngine.WIDTH*y;
        invalidate();
    }

//    @Override
//    public boolean isClickable() {
//        return isClickable;
//    }
//
//    @Override
//    public void setClickable(boolean clickable) {
//        isClickable = clickable;
//    }
//
//    public boolean isGameEnded() {
//        return isGameEnded;
//    }
//
//    public void setGameEnded(boolean gameEnded) {
//        isGameEnded = gameEnded;
//    }
}
