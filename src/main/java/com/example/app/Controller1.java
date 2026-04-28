package com.example.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;

public class Controller1 {

    Sepia sepia = new Sepia() ;
    Miror miror = new Miror() ;
    BlackWhite blackWhite = new BlackWhite() ;
    Composant composant = new Composant() ;
    Filtre filtre = new Filtre() ;

    Saver S = new Saver() ;
    Securite Sec = new Securite("mot de passe") ;
    byte[] mdp = Sec.getMdp_int() ;

    protected String nom = "@welcome.jpg" ;
    protected ArrayList<String> tags  = new ArrayList<String>() ;
    protected ArrayList<String> filters = new ArrayList<String>() ;


    FileChooser FC = new FileChooser();
    FileChooser saveChooser = new FileChooser();
    ArrayList<String> currentTags = new ArrayList<String>() ;

    File selectedFile ;
    File src = new File(System.getProperty("user.dir"), "projet-POO-L2/data") ;



    @FXML
    protected void SaveIT(){
        S.saveIt(nom, tags, filters);
    }

    @FXML
    private Label l_Selection;

    @FXML
    protected ImageView image1 ;

    Image imageBase  ;



    @FXML
    protected void F_sepia(ActionEvent event) { image1.setImage( sepia.ReadIt(image1, filters) );}

    @FXML
    protected void F_blackWhite(ActionEvent event) {
        image1.setImage(blackWhite.ReadIt(image1, filters) );
    }

    @FXML
    protected void F_miror(ActionEvent event) {
        image1.setImage( miror.ReadIt(image1, filters) );
    }

    @FXML
    protected void F_composant(ActionEvent event) {
        image1.setImage( composant.ReadIt(image1, filters) ) ;
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



    public int[] shuffle_list(int n){

        int[] ordre = new int[n] ;
        for (int i = 0; i < n; i++) {
            ordre[i] = i;
        }

        SecureRandom R = null;
        try {
            R = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        R.setSeed(mdp);

        for (int i = 0 ; i < n ; i++){
            int j = R.nextInt(n) ;

            int temp = ordre[j] ;
            ordre[j] = ordre[i] ;
            ordre[i] = temp ;
        }
        return ordre ;
    }

    @FXML
    protected void shuffle(){
        Image img = imageBase ;
        PixelReader PR = img.getPixelReader();
        int width = (int) img.getWidth() ;
        int height = (int) img.getHeight() ;

        BufferedImage buf = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB) ;

        int[] ordre = shuffle_list(width * height) ;
        int n ;
        int w ;
        int h ;

        for (int y = 0 ; y < height ; y++){
            for (int x = 0 ; x < width ; x++){
                n = ordre[y * width +x] ;
                h = n/ width ;
                w = n % width ;

                buf.setRGB(x, y, PR.getArgb(w, h) );
            }
        }
        if (selectedFile != null) {
            try {
                ImageIO.write(buf, "png", selectedFile) ;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @FXML
    protected void unShuffle(){
        Image img = image1.getImage() ;
        PixelReader PR = img.getPixelReader();
        int width = (int) img.getWidth() ;
        int height = (int) img.getHeight() ;

        WritableImage output = filtre.CopieConversion(img, width, height) ;
        PixelWriter PW =  output.getPixelWriter() ;

        int[] ordre = shuffle_list(width * height) ;
        int n ;
        int w ;
        int h ;

        for (int y = 0 ; y < height ; y++){
            for (int x = 0 ; x < width ; x++){
                n = ordre[y * width +x] ;
                h = n/ width ;
                w = n % width ;

                PW.setColor(w, h, PR.getColor(x, y) );
            }
        }
        image1.setImage(output);
    }

    @FXML
    protected void Selection(ActionEvent event) {

        FC.getExtensionFilters().add(new FileChooser.ExtensionFilter("All Images", "*.png", "*.jpg"));
        FC.setInitialDirectory(src) ;

        File selectedFile = FC.showOpenDialog(null);
        if (selectedFile != null ) {
            this.selectedFile =selectedFile ;
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
        imageBase = NewImage ;
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
                        break;
                    case "Miror":
                        miror.ReadIt(image1, filters);
                        break;
                    case "Composant":
                        composant.ReadIt(image1, filters);
                        break;
                    case "BlackWhite":
                        blackWhite.ReadIt(image1, filters);
                        break;
                    }
                }
            }
    }






}
