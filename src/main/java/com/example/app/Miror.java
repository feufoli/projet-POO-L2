package com.example.app;

import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class Miror extends Filtre{

    public Miror(){
        nom = "Miror" ;
    }

    protected void Apply(PixelReader PR, PixelWriter PW, Color col, int x, int y, int width, int height){
        PW.setColor(width-1-x, y , col);
    }
}
