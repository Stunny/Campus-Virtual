package com.stunny.vogel.campusvirtual.Logica.CustomAdapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.stunny.vogel.campusvirtual.Logica.FileManager;
import com.stunny.vogel.campusvirtual.Logica.ListElements.Subject;
import com.stunny.vogel.campusvirtual.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 4/08/16.
 */
public class SubjectAdapter extends ArrayAdapter<Subject> {

    private List<Subject> elements;
    private File subjectsFile;

    public SubjectAdapter(Context context){
        super(context, R.layout.subject);
        this.elements = new ArrayList<Subject>();
        this.subjectsFile = new File("subjects.json");
        populateList();
    }

    private void populateList(){
        FileManager fm = new FileManager();

        if(!this.subjectsFile.exists()){
            fm.createSubjectsFile(getContext());
        }

        elements = fm.fillSubjects(elements);
    }

}
