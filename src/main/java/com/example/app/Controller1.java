package com.example.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Controller1 {

    Sepia sepia = new Sepia() ;
    Miror miror = new Miror() ;
    BlackWhite blackWhite = new BlackWhite() ;
    Composant composant = new Composant() ;

    Saver S = new Saver() ;

    protected String nom = "@IMG_20250723_135302.jpg" ;
    protected ArrayList<String> tags  = new ArrayList<String>() ;
    protected ArrayList<String> filters = new ArrayList<String>() ;

    FileChooser FC = new FileChooser();
    ArrayList<String> currentTags = new ArrayList<String>() ;


    @FXML
    protected void SaveIT(){
        S.saveIt(nom, tags, filters);
    }

    @FXML
    private Label l_Selection;

    @FXML
    protected ImageView image1 ;

    @FXML
    protected void F_sepia(ActionEvent event) { sepia.ReadIt(image1, filters) ;}

    @FXML
    protected void F_blackWhite(ActionEvent event) {
        blackWhite.ReadIt(image1, filters) ;
    }

    @FXML
    protected void F_miror(ActionEvent event) {
        miror.ReadIt(image1, filters) ;
    }

    @FXML
    protected void F_composant(ActionEvent event) {
        composant.ReadIt(image1, filters) ;
    }

    @FXML
    protected void ResetFilters(ActionEvent event){
        filters = new ArrayList<String>() ;
        Image NewImage = new Image(nom) ;
        image1.setImage(NewImage);
    }

    @FXML
    protected void ResetTags(ActionEvent event){
        tags = new ArrayList<String>() ;
    }



    @FXML
    protected void Selection(ActionEvent event) {
        if (currentTags.isEmpty()) {
            FC.getExtensionFilters().add(new FileChooser.ExtensionFilter("All Images", "*.png", "*.jpg"));
        }


        File selectedFile = FC.showOpenDialog(null);
        if (selectedFile != null ) {
            nom = selectedFile.toURI().toString() ;
            l_Selection.setText("fichier selectionné");
            loadImage();
        } else {
            l_Selection.setText("pas de fichier selectionné");
        }
        FC.getExtensionFilters().clear();
    }
     protected void loadImage(){
        Image NewImage = new Image(nom) ;
        int index = S.findImage(nom) ;
        image1.setImage(NewImage);

        if (index == -1){
            tags = new ArrayList<String>() ;
            filters = new ArrayList<String>() ;
        }
        else {
            ArrayList<String> list = S.getSave().get(index).getFilters() ;
            tags = S.getSave().get(index).getTags() ;
            filters = new ArrayList<String>() ;

            for (String s : list) {
                switch (s) {
                    case "Sepia":
                        sepia.ReadIt(image1, filters);
                    case "Miror":
                        miror.ReadIt(image1, filters);
                    case "Composant":
                        composant.ReadIt(image1, filters);
                    case "BlackWhite":
                        blackWhite.ReadIt(image1, filters);
                    }
                }
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
