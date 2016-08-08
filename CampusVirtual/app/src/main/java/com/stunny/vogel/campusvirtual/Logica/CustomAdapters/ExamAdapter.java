package com.stunny.vogel.campusvirtual.Logica.CustomAdapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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
    private FileManager fm;

    public ExamAdapter(Context context){
        super(context, R.layout.subject);
        this.fm = new FileManager();
        this.elements = new ArrayList<>();
        this.examsFile = new File("exams.json");
        populateList();
    }

    private void populateList(){

        if(!this.examsFile.exists()){
            fm.createExamsFile(getContext());
        }

        elements = fm.fillExams(elements, getContext());
    }

    @Override
    public int getCount(){
        return elements.size();
    }

    @Override
    public Exam getItem(int pos){
        return elements.get(pos);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        View row = convertView;
        if(row == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.exam, null);

            row.setClickable(true);
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent();
                    //startActivity(i); Go to exam page
                }
            });
        }

        TextView date = (TextView)row.findViewById(R.id.exam_fecha),
                hora = (TextView)row.findViewById(R.id.exam_hora),
                asig = (TextView)row.findViewById(R.id.exam_subject),
                aula = (TextView)row.findViewById(R.id.exam_aula);

        date.setText(elements.get(position).fecha.toString());
        hora.setText(elements.get(position).hora.toString());
        asig.setText("Asignatura: "+elements.get(position).subject);
        aula.setText(elements.get(position).room);

        return row;
    }
}
