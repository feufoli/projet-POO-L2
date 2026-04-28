package com.example.app;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Saver {

    protected ArrayList<SavedImage> save ;

    public Saver() {
        save = new ArrayList<SavedImage>() ;
        loadSave();
    }

    public void setSave(ArrayList<SavedImage> save) { this.save = save ;}

    public ArrayList<SavedImage> getSave() { return save ;}

    public void loadSave(){
        ObjectMapper mapper = new ObjectMapper() ;
        try {
            setSave(mapper.readValue(new File("data/save.json"), new TypeReference<ArrayList<SavedImage>>() {
            })) ;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int findImage(String nom){
        for( int i = 0 ; i < getSave().size() ; i++ ){
            if ( nom.equals( getSave().get(i).getNom() ) ) {
                return i ;
            }
        }
        return -1 ;
    }

    public void saveIt(String nom, ArrayList<String> tags, ArrayList<String> filters) {
        int index = findImage(nom) ;

        if (index == -1 )  {
            getSave().add(new SavedImage(nom, tags, filters)) ;
        }
        else {
            getSave().get(index).setTags(tags) ;
            getSave().get(index).setFilters(filters);
        }
        saveAll() ;
    }

    public void saveAll(){
        ObjectMapper mapper = new ObjectMapper() ;
        try {
            mapper.writeValue(new File("data/save.json"), save);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
