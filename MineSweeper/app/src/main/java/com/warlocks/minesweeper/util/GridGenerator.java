package com.warlocks.minesweeper.util;

import com.warlocks.minesweeper.views.ShowNoOfREmainingFlags;

import java.util.Random;

public class GridGenerator {                //Creates Grid to be played!!

    private static boolean possible (int [][] grid, int x, int y, int width, int height) {
        if (x >= 0 && x < width && y >= 0 && y < height) return true;
        return false;
    }


    public static int[][] generate(int bomb_no,int width,int height) {

        Random rand = new Random();
        int[][] grid = new int[width][height];
//        for (int i=0;i<width;i++){
//        grid[i]=new int[height];
//        }
        while (bomb_no > 0) {
            int wd = rand.nextInt(width);
            int ht = rand.nextInt(height);
            if (grid[wd][ht] == 0) {
                grid[wd][ht] = -1;
                bomb_no--;
            }
        }
        int []x =   { -1, -1, -1, 0, 0, 1, 1, 1};
        int []y =  { -1, 0, 1, -1, 1, -1, 0, 1};
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (grid[i][j] != -1) {
                    int count = 0;
                    for (int k = 0; k < 8; k++) {
                        int xx = i + x[k];
                        int yy = j + y[k];
                        if (possible(grid,xx, yy,width,height)==true) {
                            if (grid[xx][yy] == -1) count++;
                        }
                    }
                    grid[i][j]=count;
                }
            }
        }

        return grid;
    }
}
