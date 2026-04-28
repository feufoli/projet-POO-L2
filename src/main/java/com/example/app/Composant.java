package com.example.app;

import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class Composant extends Filtre{

    public Composant(){
        nom = "Composant" ;
    }

    protected void Apply(PixelReader PR, PixelWriter PW, Color col, int x, int y, int width, int height){
        double red = col.getRed();
        double green = col.getGreen();
        double blue = col.getBlue();

        PW.setColor(x,y, Color.color(green, blue, red, col.getOpacity()) );
    }
}
