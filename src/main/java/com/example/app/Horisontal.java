package com.example.app;

import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class Horisontal extends Filtre{

    protected void Apply(PixelReader PR, PixelWriter PW, Color col, int x, int y, int width, int height) {
        double totalred = 0 ;
        double totalgreen = 0 ;
        double totalblue = 0 ;

        int[][] matrix = {{1,1,1},{0,0,0},{-1,-1,-1}} ;

        double total =0 ;
        double col3 ;

        for (int i = -1 ; i < 2 ; i++){
            for(int j = -1 ; j <2 ; j++){
                if(i+y >= 0 && i+y < height){
                    if (j+x >= 0 && j+x < width){
                        Color col2 = PR.getColor(j+x, i+y) ;

                        col3 = ( col2.getBlue() + col2.getRed() +col2.getGreen() ) /3;
                        total += matrix[j+1][i+1] * col3;
                    }
                }
            }
        }
        total = Math.clamp(total, 0.0, 1.0);
        PW.setColor(x,y, Color.color(total,total,total));
    }
}