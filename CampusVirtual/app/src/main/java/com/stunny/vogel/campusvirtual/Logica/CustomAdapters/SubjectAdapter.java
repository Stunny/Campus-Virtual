package com.stunny.vogel.campusvirtual.Logica.CustomAdapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

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
    private FileManager fm;

    public SubjectAdapter(Context context){
        super(context, R.layout.subject);
        this.fm = new FileManager();
        this.elements = new ArrayList<Subject>();
        this.subjectsFile = new File("subjects.json");
        Log.d("tag","RELLENANDO LISTA");
        populateList();
    }

    private void populateList(){

        if(!this.subjectsFile.exists()){
            Log.d("tag", "Creo archivo");
            fm.createSubjectsFile(getContext());
        }

        elements = fm.fillSubjects(elements, getContext());
    }

    @Override
    public int getCount(){
        Log.d("tag", "HACIENDO UN GETCOUNT");
        return this.elements.size();
    }

    @Override
    public Subject getItem(int pos){
        return elements.get(pos);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.subject, null);

            row.setClickable(true);
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent();
                    //startActivity(i); Go to subject page
                }
            });
        }

        TextView titulo = (TextView) row.findViewById(R.id.titulo_asignatura),
                desc = (TextView) row.findViewById(R.id.desc_asignatura);

        titulo.setText(elements.get(position).name);
        desc.setText(elements.get(position).description);
        //SET FOTO

        Button delete = (Button)row.findViewById(R.id.deleteSubject);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builderConf = new AlertDialog.Builder(getContext());
                builderConf.setMessage("¿Desea eliminar esta asignatura?");
                builderConf.setTitle("Eliminar Asignatura");

                builderConf.setPositiveButton(
                        "Si",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                elements.remove(position);
                                fm.removeSubject(elements.get(position), getContext());
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
