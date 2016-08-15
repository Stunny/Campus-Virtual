package com.stunny.vogel.campusvirtual.Logica.CustomAdapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.stunny.vogel.campusvirtual.Actividades.ViewStudent;
import com.stunny.vogel.campusvirtual.Logica.FileManager;
import com.stunny.vogel.campusvirtual.Logica.ListElements.Student;
import com.stunny.vogel.campusvirtual.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 15/08/16.
 */
public class SimpleStudentAdapter extends ArrayAdapter<Student> {

    private Activity acref;
    private List<Student> elements;
    private File studentsFile;
    private FileManager fm;
    private ArrayList<String> names;

    public SimpleStudentAdapter(Context context, Activity ref){
        super(context, R.layout.simplestudent);
        this.acref = ref;
        this.fm = new FileManager();
        this.elements = new ArrayList<>();
        this.studentsFile = new File("students.json");
        populateList();
    }

    public SimpleStudentAdapter(Context context, Activity ref, ArrayList<String> names){
        super(context, R.layout.simplestudent);
        this.acref = ref;
        this.fm = new FileManager();
        this.elements = new ArrayList<>();
        this.names = names;
        this.studentsFile = new File("students.json");
        populateByName();
    }

    private void populateByName(){
        if(!this.studentsFile.exists()) fm.createStudentsFile(getContext());

        elements = fm.fillStudents(elements, names, getContext());
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
            row = inflater.inflate(R.layout.simplestudent, null);

            row.setClickable(true);
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(acref, ViewStudent.class);
                    i.putExtra("name", elements.get(position).name);
                    i.putExtra("birthDate", elements.get(position).birthDate.toString());
                    i.putExtra("degree", elements.get(position).degree);
                    i.putExtra("gender", elements.get(position).gender);
                    i.putExtra("photoPath", elements.get(position).photoPath);
                    acref.startActivity(i);
                }
            });
        }

        TextView nombre = (TextView)row.findViewById(R.id.simple_stname),
                deg = (TextView)row.findViewById(R.id.simple_stdeg);
        ImageView iv = (ImageView)row.findViewById(R.id.simple_stphoto);

        nombre.setText(elements.get(position).name);
        deg.setText("Especialidad: "+elements.get(position).degree);

        String imPath = elements.get(position).photoPath;
        File imFile = new File(imPath);
        if(imFile.exists()){
            Uri imgUri = Uri.fromFile(imFile);
            iv.setImageURI(imgUri);
        }else{
            iv.setImageResource(R.drawable.ic_default_student);
        }

        return row;
    }
}
