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


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Date;
import java.sql.Time;
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
            fos.write(DefaultContent.default_students.getBytes());
            fos.close();
            return true;
        } catch (IOException e) {
            Toast.makeText(context, "No hay memoria suficiente", LENGTH_SHORT);
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
            fos.write(DefaultContent.default_exams.getBytes());
            fos.close();
            return true;
        }catch(IOException e){
            Toast.makeText(context, "No hay memoria suficiente", LENGTH_SHORT);
        }
        return false;
    }

    public List<Subject> fillSubjects(List<Subject> elements, Context context){

        Subject aux;
        JsonObject obj;

        try {
            JsonArray subjects = extract(context.getFilesDir() + "/subjects.json");
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
    public List<Exam> fillExams(List<Exam> elements, Context context){

        Exam aux;
        JsonObject obj;

        try{

            JsonArray exams = extract(context.getFilesDir()+"/exams.json");
            for(int i = 0; i<exams.size(); i++){
                aux = new Exam();
                obj = (JsonObject)exams.get(i);

                aux.subject = obj.get("subject_name").getAsString();
                aux.degree = obj.get("grado").getAsString();
                aux.fecha = Date.valueOf(
                        obj.get("año").getAsString() + "-"
                                + obj.get("mes").getAsString() + "-"
                                + obj.get("dia").getAsString());
                aux.hora = Time.valueOf(obj.get("hora").getAsString() + ":"
                        + obj.get("minuto").getAsString() + ":"
                        + obj.get("segundo").getAsString());
                aux.room = obj.get("aula").getAsString();

                elements.add(aux);
            }

        }catch(IOException e){
            e.printStackTrace();
            return null;
        }

        return elements;
    }
    public List<Student> fillStudents(List<Student> elements, Context context){

        Student aux;
        JsonObject obj;

        try{
            JsonArray students = extract(context.getFilesDir()+"/students.json");
            for(int i = 0; i<students.size(); i++){
                aux = new Student();
                obj = (JsonObject)students.get(i);

                aux.name = obj.get("name").getAsString();
                aux.degree = obj.get("degree").getAsString();
                aux.photoPath = obj.get("photoPath").getAsString();
                aux.gender = obj.get("gender").getAsString();
                aux.birthDate = Date.valueOf(
                        obj.get("birth_year").getAsString() + "-"
                                + obj.get("birth_month").getAsString() + "-"
                                + obj.get("birth_day").getAsString()
                );

                elements.add(aux);
            }
        }catch(IOException ie){
            ie.printStackTrace();
            return null;
        }

        return elements;
    }
    public void removeSubject(Subject s, Context context){
        try {
            Log.d("remove", "subject");
            JsonArray subjects = extract(context.getFilesDir()+"/subjects.json");
            JsonObject aux;
            for(int i = 0; i<subjects.size(); i++){
                aux = (JsonObject)subjects.get(i);
                if(aux.get("name").getAsString().equals(s.name)){
                    subjects.remove(i);
                    reWrite(subjects, context.getFilesDir() + "/subjects.json");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

    }
    public void removeStudent(Student s, Context context){
        try{
            JsonArray students = extract(context.getFilesDir()+"/students.json");
            JsonObject aux;
            for (int i = 0; i<students.size(); i++){
                aux = (JsonObject)students.get(i);
                if(aux.get("name").getAsString().equals(s.name)){
                    students.remove(i);
                    reWrite(students, context.getFilesDir()+"/students.json");
                    break;
                }
            }
            reWrite(students, context.getFilesDir()+"/students.json");
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public boolean exists(Student s){

        return false;
    }
    public boolean exists(Subject s){

        return false;
    }
    public boolean exists(Exam e){

        return false;
    }
    public void createStudent(Student s){

    }
    private JsonArray extract(String file) throws IOException{
        JsonParser parser = new JsonParser();
        JsonArray obj = (JsonArray) parser.parse(new FileReader(file));

        return obj;
    }
    private void reWrite(JsonArray ja, String path){
        try {
            FileOutputStream fos = new FileOutputStream(path, false);
            fos.write(ja.toString().getBytes());
            fos.close();
            Log.d("rewrite", "true");
        } catch (FileNotFoundException e) {
            Log.d("reWrite", "FileNotFound");
            e.printStackTrace();
        }catch (IOException ie){
            Log.d("reWrite", "IOException");
            ie.printStackTrace();
        }

    }
    public boolean pathChecker(String p){
        File f = new File(p);
        try{
            f.getCanonicalPath();
            return true;
        }catch(IOException e){
            return false;
        }
    }
}
