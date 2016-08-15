package com.stunny.vogel.campusvirtual.Logica.CustomAdapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.stunny.vogel.campusvirtual.Actividades.Add.AddEditExam;
import com.stunny.vogel.campusvirtual.Logica.FileManager;
import com.stunny.vogel.campusvirtual.Logica.ListElements.Exam;
import com.stunny.vogel.campusvirtual.R;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by alex on 5/08/16.
 */
public class ExamAdapter extends ArrayAdapter<Exam> {

    private List<Exam> elements;
    private File examsFile;
    private FileManager fm;
    private Context c;

    public ExamAdapter(Context context){
        super(context, R.layout.subject);
        this.fm = new FileManager();
        this.c = context;
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

        final Date dHora = elements.get(position).hora,
                dFecha = elements.get(position).fecha;
        final String  subj = elements.get(position).subject,
                room = elements.get(position).room,
                eDeg = elements.get(position).degree;


        View row = convertView;
        if(row == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.examv2, null);

            row.setClickable(true);
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("click", "oya oya");
                    Intent i = new Intent(c, AddEditExam.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("ADD", false);
                    i.putExtra("hora", dHora.toString());
                    i.putExtra("fecha", dFecha.toString());
                    i.putExtra("subj", subj);
                    i.putExtra("aula", room);
                    i.putExtra("deg", eDeg);
                    c.startActivity(i);
                }
            });
        }

        TextView date = (TextView)row.findViewById(R.id.exam_fecha),
                hora = (TextView)row.findViewById(R.id.exam_hora),
                asig = (TextView)row.findViewById(R.id.exam_subject),
                aula = (TextView)row.findViewById(R.id.exam_aula),
                students = (TextView)row.findViewById(R.id.exam_students);

        DateFormat form = new SimpleDateFormat("HH:mm");
        String hour = form.format(dHora);

        hora.setText("Hora: "+hour);
        form = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = form.format(dFecha);

        date.setText(fecha);
        asig.setText("Asignatura: "+subj);
        aula.setText(room);

        int qSts = fm.examStudentsCount(subj, getContext());
        if(qSts == 1) students.setText("1 alumno");
        else{
            students.setText(R.string.students);
            students.setText(Integer.toString(qSts)+" "+students.getText().toString());
        }

        return row;
    }

}

