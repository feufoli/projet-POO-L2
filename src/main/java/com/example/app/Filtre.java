package com.example.app;

import javafx.scene.image.*;
import javafx.scene.paint.Color;

public class Filtre {

    public static WritableImage CopieConversion(Image NewImage, int width, int height){
        WritableImage output = new WritableImage(width, height) ;

        return output ;
    }

    public void ReadIt(ImageView view){
        Image img = view.getImage() ;
        PixelReader PR = img.getPixelReader();
        int width = (int) img.getWidth() ;
        int height = (int) img.getHeight() ;
        WritableImage output = CopieConversion(img, width, height) ;
        PixelWriter PW =  output.getPixelWriter() ;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color col = PR.getColor(x, y);
                Apply(PW, col, x, y, width, height);
            }
        }
        view.setImage(output); ;

    }

    protected void Apply(PixelWriter PW, Color col, int x, int y, int width, int height){

    }


}
