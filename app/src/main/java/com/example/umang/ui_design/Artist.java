package com.example.umang.ui_design;

public class Artist {
    String id;
    String name;
    String generes;

    public Artist()
    {

    }

    public Artist(String id, String name, String generes) {
        this.id = id;
        this.name = name;
        this.generes = generes;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGeneres() {
        return generes;
    }
}
