package com.warlocks.minesweeper.views.grid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.warlocks.minesweeper.GameEngine;
import com.warlocks.minesweeper.R;

import java.util.Objects;

public class Cell extends BaseCell implements View.OnClickListener,View.OnLongClickListener{
    public Cell(Context context, int x,int y){
        super(context);
        setPosition(x,y);
        setOnClickListener(this);
        setOnLongClickListener(this);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawButton(canvas);
        if(isFlagged()){
            drawFlag(canvas);
        }
        else if(isRevealed() && isBomb() && !isClicked()){
            drawNormalBomb(canvas);
        }
        else{
            if(isClicked()){
                if(getValue()==-1){
                    drawBombExploded(canvas);
                }
                else{
                    if(  isRevealed()){
                        drawNumber(canvas);
                    }
                }
            }
            else{
                drawButton(canvas);
            }
        }
    }

    private void drawBombExploded(Canvas canvas) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.bomb_exploded);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }

    private void drawNormalBomb(Canvas canvas) {

            Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.bomb_normal);
            drawable.setBounds(0, 0, getWidth(), getHeight());
            drawable.draw(canvas);

    }

    private void drawFlag(Canvas canvas) {

            Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.flag);

            drawable.setBounds(0, 0, getWidth(), getHeight());
            drawable.draw(canvas);


    }

    private void drawButton(Canvas canvas){
            Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.background_block);
            drawable.setBounds(0, 0, getWidth(), getHeight());
            drawable.draw(canvas);

    }
//    private void drawBomb(Canvas canvas){
//        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.bomb_normal);
//        drawable.setBounds(0,0,getWidth(),getHeight());
//        drawable.draw(canvas);
//    }
    @Override
    public void onClick(View v) {
        GameEngine.getInstance().click(getXPos(),getYPos());
    }
    @Override
    public boolean onLongClick(View v) {
        GameEngine.getInstance().Flag(getXPos(),getYPos());
        return true;
    }
    private void drawNumber(Canvas canvas) {
            Drawable drawable = null;
            if (getValue() == 0) {
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_0);
            } else if (getValue() == 1) {
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_1);
            } else if (getValue() == 2) {
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_2);
            } else if (getValue() == 3) {
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_3);
            } else if (getValue() == 4) {
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_4);
            } else if (getValue() == 5) {
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_5);
            } else if (getValue() == 6) {
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_6);
            } else if (getValue() == 7) {
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_7);
            } else if (getValue() == 8) {
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_8);
            }
            drawable.setBounds(0, 0, getWidth(), getHeight());
            drawable.draw(canvas);
        }

//    public static void exit(){
//        setClickable(false);
//    }


}
