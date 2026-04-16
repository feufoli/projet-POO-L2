package com.example.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView ;
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
            image1.setImage(new Image(selectedFile.toURI().toString())) ;
            l_Selection.setText("fichier selectionné");
        } else {
            l_Selection.setText("pas de fichier selectionné");
        }
    }


}
