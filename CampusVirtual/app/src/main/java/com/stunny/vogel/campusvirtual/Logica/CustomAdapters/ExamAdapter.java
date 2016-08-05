package com.stunny.vogel.campusvirtual.Logica.CustomAdapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.stunny.vogel.campusvirtual.Logica.FileManager;
import com.stunny.vogel.campusvirtual.Logica.ListElements.Exam;
import com.stunny.vogel.campusvirtual.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 5/08/16.
 */
public class ExamAdapter extends ArrayAdapter<Exam> {

    private List<Exam> elements;
    private File examsFile;

    public ExamAdapter(Context context){
        super(context, R.layout.subject);
        this.elements = new ArrayList<>();
        this.examsFile = new File("exams.json");
        populateList();
    }

    private void populateList(){
        FileManager fm = new FileManager();

        if(!this.examsFile.exists()){
            fm.createExamsFile(getContext());
        }

        elements = fm.fillExams(elements);
    }

}
