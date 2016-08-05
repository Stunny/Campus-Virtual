package com.stunny.vogel.campusvirtual.Logica.CustomAdapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.stunny.vogel.campusvirtual.Logica.FileManager;
import com.stunny.vogel.campusvirtual.Logica.ListElements.Student;
import com.stunny.vogel.campusvirtual.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 5/08/16.
 */
public class StudentAdapter extends ArrayAdapter<Student>{

    private List<Student> elements;
    private File studentsFile;

    public StudentAdapter(Context context){
        super(context, R.layout.subject);
        this.elements = new ArrayList<>();
        this.studentsFile = new File("students.json");
        populateList();
    }

    private void populateList(){
        FileManager fm = new FileManager();

        if(!this.studentsFile.exists()) fm.createStudentsFile(getContext());

        elements = fm.fillStudents(elements);
    }

    @Override
    public int getCount(){
        return elements.size();
    }

    @Override
    public Student getItem(int pos){
        return elements.get(pos);
    }
}
