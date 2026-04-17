package com.example.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;

public class Controller1 {
    @FXML
    private Label l_Selection;

    @FXML
    protected ImageView image1 ;

    @FXML
    protected void ButtonClick(ActionEvent event) {

        FileChooser FC = new FileChooser() ;
        File selectedFile =  FC.showOpenDialog(null) ;

        if (selectedFile != null ) {
            Image NewImage = new Image(selectedFile.toURI().toString()) ;
            WritableImage output = CopieConversion(NewImage) ;
            image1.setImage(output);
            l_Selection.setText("fichier selectionné");
        } else {
            l_Selection.setText("pas de fichier selectionné");
        }
    }

    @FXML
    protected void PasseEnSepia(ActionEvent event) {
        //implémente l'image
        WritableImage output = CopieConversion(image1.getImage());
        PixelReader PR = output.getPixelReader();
        PixelWriter PW = output.getPixelWriter() ;
        int width = (int) output.getWidth();
        int height = (int) output.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color col = PR.getColor(x, y);
                double red = col.getRed();
                double green = col.getGreen();
                double blue = col.getBlue();

                double red_out = Math.min(1,(0.393*red+ 0.769*green + 0.189*blue)) ;
                double green_out = Math.min(1,(0.349*red+ 0.686*green + 0.168*blue)) ;
                double blue_out = Math.min(1,(0.272*red+ 0.534*green + 0.131*blue)) ;

                PW.setColor(x,y, Color.color(red_out, green_out, blue_out, col.getOpacity()) );
            }
        }
        image1.setImage(output);
    }

    protected WritableImage CopieConversion(Image NewImage){

        //fait un reader pour avoir les dimentions et lire les pixels
        PixelReader PR = NewImage.getPixelReader();
        int width = (int) NewImage.getWidth() ;
        int height = (int) NewImage.getHeight() ;

        //l'image sur laquelle on peut ecrir
        WritableImage output = new WritableImage(width, height) ;
        PixelWriter PW =  output.getPixelWriter() ;

        //effectue la copie
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color col = PR.getColor(x, y);
                PW.setColor(x, y, col );
            }
        }
        return output ;
    }
}
