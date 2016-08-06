package com.stunny.vogel.campusvirtual.Logica;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.stunny.vogel.campusvirtual.Logica.ListElements.Exam;
import com.stunny.vogel.campusvirtual.Logica.ListElements.Student;
import com.stunny.vogel.campusvirtual.Logica.ListElements.Subject;



import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by alex on 5/08/16.
 */
public class FileManager {

    private Gson gson;

    public FileManager(){this.gson = new Gson();}

    public boolean createStudentsFile(Context context){
        try {
            FileOutputStream fos = context.openFileOutput("students.json", Context.MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            osw.write(DefaultContent.default_students);
            osw.close();
            return true;
        } catch (IOException e) {
            Toast.makeText(context,"No hay memoria suficiente", LENGTH_SHORT);
        }
        return false;
    }

    public boolean createSubjectsFile(Context context){
        try {
            FileOutputStream fos = context.openFileOutput("subjects.json", Context.MODE_PRIVATE);
            fos.write(DefaultContent.default_subjects.getBytes());
            fos.close();
            Log.d("tag", "NUevo archivo rellenado");
            return true;
        } catch (IOException e) {
            Toast.makeText(context,"No hay memoria suficiente", LENGTH_SHORT);
            e.printStackTrace();
        }
        return false;
    }

    public boolean createExamsFile(Context context){
        try{
            FileOutputStream fos = context.openFileOutput("exams.json", Context.MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            osw.write(DefaultContent.default_exams);
            osw.close();
            return true;
        }catch(IOException e){
            Toast.makeText(context,"No hay memoria suficiente", LENGTH_SHORT);
        }
        return false;
    }

    public List<Subject> fillSubjects(List<Subject> elements, Context context){

        Subject aux;
        JsonObject obj;

        try {
            JsonArray subjects = extract(context.getFilesDir()+"/subjects.json");
            for(int i = 0; i<subjects.size(); i++){
                aux = new Subject();
                obj = (JsonObject) subjects.get(i);

                aux.name = obj.get("name").getAsString();
                Log.d("Nombre", aux.name);
                aux.iconPath = obj.get("iconPath").getAsString();
                aux.description = obj.get("descripcion").getAsString();

                elements.add(aux);
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return elements;
    }
    public List<Exam> fillExams(List<Exam> elements){

        return elements;
    }
    public List<Student> fillStudents(List<Student> elements){

        return elements;
    }
    public void removeSubject(Subject s){

    }
    public void removeStudent(Student s){

    }
    private JsonArray extract(String file) throws IOException{
        JsonParser parser = new JsonParser();
        JsonArray obj = (JsonArray) parser.parse(new FileReader(file));

        return obj;
    }
}
