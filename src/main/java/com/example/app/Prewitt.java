package com.example.app;

import javafx.scene.image.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;


public class Prewitt extends Filtre{

    public Prewitt(){
        this.nom = "Prewitt" ;
    }

    public WritableImage ReadIt(ImageView view, ArrayList<String> filter){
        WritableImage lat = new Lateral().ReadIt(view, new ArrayList<String>()) ;
        WritableImage hor = new Horisontal().ReadIt(view, new ArrayList<String>()) ;

        filter.add(nom) ;
        PixelReader PRL = lat.getPixelReader() ;
        PixelReader PRH = hor.getPixelReader() ;

        int width = (int) lat.getWidth() ;
        int height = (int) lat.getHeight() ;
        WritableImage output = CopieConversion(lat, width, height) ;
        PixelWriter PW =  output.getPixelWriter() ;

        Color col1 ;
        Color col2 ;
        double total ;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                col1 = PRL.getColor(x,y) ;
                col2 = PRH.getColor(x,y) ;
                total = Math.sqrt(col1.getBlue() * col1.getBlue() + col2.getBlue() * col2.getBlue() ) ;
                total = Math.clamp(total, 0.0, 1.0);

                PW.setColor(x,y, Color.color(total, total, total ) );
            }
        }
        return output;
    }
}
