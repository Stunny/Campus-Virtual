package com.stunny.vogel.campusvirtual.Logica.ListElements;

import java.util.ArrayList;

/**
 * Created by alex on 4/08/16.
 */
public class Subject {
    public String name;
    public String description;
    public String iconPath;
    public ArrayList<String> themes;
    public ArrayList<String> students;

    public Subject(){
        this.themes = new ArrayList<>();
        this.students = new ArrayList<>();
    }

}
