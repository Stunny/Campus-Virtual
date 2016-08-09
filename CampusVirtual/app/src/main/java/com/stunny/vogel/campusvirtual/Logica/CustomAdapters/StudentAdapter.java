package com.stunny.vogel.campusvirtual.Logica.CustomAdapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
    private FileManager fm;
    private Activity activityRef;

    public StudentAdapter(Context context, Activity ref){
        super(context, R.layout.subject);
        this.activityRef = ref;
        this.fm = new FileManager();
        this.elements = new ArrayList<>();
        this.studentsFile = new File("students.json");
        populateList();
    }

    private void populateList(){

        if(!this.studentsFile.exists()) fm.createStudentsFile(getContext());

        elements = fm.fillStudents(elements, getContext());
    }

    @Override
    public int getCount(){
        return elements.size();
    }

    @Override
    public Student getItem(int pos){
        return elements.get(pos);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        View row = convertView;
        if(row == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.student, null);

            row.setClickable(true);
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent();
                    //startActivity(i); Go to student page
                }
            });
        }

        TextView nombre = (TextView)row.findViewById(R.id.nombre_student),
                edad = (TextView)row.findViewById(R.id.edad_student),
                espec = (TextView)row.findViewById(R.id.especialidad_student);

        nombre.setText(elements.get(position).name);
        edad.setText("Edad: "+Integer.toString(elements.get(position).getAge()));
        espec.setText("Especialidad: "+elements.get(position).degree);

        //SET FOTO
        ImageView iv = (ImageView) row.findViewById(R.id.icono_student);
        if(elements.get(position).photoPath.equals("")){
            iv.setImageResource(R.drawable.ic_default_student);
        }

        Button delete = (Button)row.findViewById(R.id.deleteStudent);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builderConf = new AlertDialog.Builder(activityRef);
                builderConf.setTitle("Eliminar alumno");
                builderConf.setMessage("¿Desea eliminar este alumno?");
                builderConf.setCancelable(true);

                builderConf.setPositiveButton(
                        "Si",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                elements.remove(position);
                                fm.removeStudent(elements.get(position), getContext());
                                notifyDataSetChanged();
                                dialog.cancel();
                            }
                        }
                );
                builderConf.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }
                );
                AlertDialog conf = builderConf.create();
                conf.show();
            }
        });

        return row;
    }
}
