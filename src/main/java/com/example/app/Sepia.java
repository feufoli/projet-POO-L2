package com.example.app;

import javafx.event.ActionEvent;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class Sepia extends Filtre{

    public Sepia(){
        nom = "Sepia" ;
    }


    protected void Apply(PixelReader PR,PixelWriter PW, Color col, int x, int y, int width, int height){
        double red = col.getRed();
        double green = col.getGreen();
        double blue = col.getBlue();

        double red_out = Math.min(1,(0.393*red+ 0.769*green + 0.189*blue)) ;
        double green_out = Math.min(1,(0.349*red+ 0.686*green + 0.168*blue)) ;
        double blue_out = Math.min(1,(0.272*red+ 0.534*green + 0.131*blue)) ;

        PW.setColor(x,y, Color.color(red_out, green_out, blue_out, col.getOpacity()) );
    }
}
