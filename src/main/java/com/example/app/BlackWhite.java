package com.example.app;

import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class BlackWhite extends Filtre {

    public BlackWhite(){
        nom = "BlackWhite" ;
    }


    protected void Apply(PixelWriter PW, Color col, int x, int y, int width, int height){
        double red = col.getRed();
        double green = col.getGreen();
        double blue = col.getBlue();

        double out = (red + blue + green) /3.0 ;
        PW.setColor(x,y, Color.color(out, out, out, col.getOpacity()) );
    }
}
