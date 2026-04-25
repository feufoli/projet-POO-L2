package com.example.app;

import java.util.ArrayList;

public class SavedImage {
    protected String nom ;
    protected ArrayList<String> tags ;
    protected ArrayList<String> filters;

    public SavedImage(){}

    public SavedImage(String nom, ArrayList<String> tags, ArrayList<String> filters){
        this.nom = nom ;
        this.tags = tags ;
        this.filters = filters ;
    }

    public void setTags(ArrayList<String> tag){ this.tags = new ArrayList<String>(tag) ; }

    public ArrayList<String> getTags() {return tags;}

    public ArrayList<String> getFilters() { return filters; }

    public void setFilters(ArrayList<String> filter) { this.filters = new ArrayList<String>(filter) ;}

    public String getNom() {return nom ;}

    public void setNom(String nom) {this.nom = nom ; }
}
