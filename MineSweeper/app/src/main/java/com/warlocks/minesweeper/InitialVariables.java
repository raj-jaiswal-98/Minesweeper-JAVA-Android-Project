package com.warlocks.minesweeper;

public class InitialVariables {
//    public InitialVariables(long timer,int BOMB_NUMBER,int WIDTH,int HEIGH
    public static long timer;
    public static  int BOMB_NUMBER;
    public static  int WIDTH;
    public static  int HEIGHT;
    public static InitialVariables initialVariables;
    public static InitialVariables getInstance(){
        if(initialVariables==null){
            initialVariables=new InitialVariables();
        }
        return initialVariables;
    }
    public static void beginner(){
        timer=90000;
        BOMB_NUMBER=5;
        WIDTH=6;
        HEIGHT=9;
    }
    public static void mediocre(){
        timer=90000;
        BOMB_NUMBER=8;
        WIDTH=9;
        HEIGHT=12;
    }
    public static void bugaboo(){
        timer=120000;
        BOMB_NUMBER=11;
        WIDTH=12;
        HEIGHT=15;
    }
}
