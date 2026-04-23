package com.example.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;

public class Controller1 {

    Sepia sepia = new Sepia() ;
    Miror miror = new Miror() ;
    BlackWhite blackWhite = new BlackWhite() ;
    Composant composant = new Composant() ;

    @FXML
    private Label l_Selection;

    @FXML
    protected ImageView image1 ;

    @FXML
    protected void F_sepia(ActionEvent event) {
        sepia.ReadIt(image1) ;
    }

    @FXML
    protected void F_blackWhite(ActionEvent event) {
        blackWhite.ReadIt(image1) ;
    }

    @FXML
    protected void F_miror(ActionEvent event) {
        miror.ReadIt(image1) ;
    }

    @FXML
    protected void F_composant(ActionEvent event) {
        composant.ReadIt(image1) ;
    }

    @FXML
    protected void ButtonClick(ActionEvent event) {

        FileChooser FC = new FileChooser() ;
        File selectedFile =  FC.showOpenDialog(null) ;

        if (selectedFile != null ) {
            Image NewImage = new Image(selectedFile.toURI().toString()) ;
            image1.setImage(NewImage);
            l_Selection.setText("fichier selectionné");
        } else {
            l_Selection.setText("pas de fichier selectionné");
        }
    }


    @FXML
    public void Rotate(ActionEvent event){
        Image NewImage = image1.getImage() ;
        PixelReader PR = NewImage.getPixelReader();
        int width = (int) NewImage.getWidth() ;
        int height = (int) NewImage.getHeight() ;

        WritableImage output = new WritableImage(height, width) ;
        PixelWriter PW =  output.getPixelWriter() ;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color col = PR.getColor(x, y);
                PW.setColor(height-1- y, x, col );
            }
        }
        image1.setImage( output );
    }



}
