package com.stunny.vogel.campusvirtual.Logica;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.stunny.vogel.campusvirtual.Logica.ListElements.Exam;
import com.stunny.vogel.campusvirtual.Logica.ListElements.Student;
import com.stunny.vogel.campusvirtual.Logica.ListElements.Subject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

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
            Toast.makeText(context,"No hay memoria suficiente", Toast.LENGTH_SHORT);
        }
        return false;
    }

    public boolean createSubjectsFile(Context context){
        try {
            FileOutputStream fos = context.openFileOutput("subjects.json", Context.MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            osw.write(DefaultContent.default_subjects);
            osw.close();
            return true;
        } catch (IOException e) {
            Toast.makeText(context,"No hay memoria suficiente", Toast.LENGTH_SHORT);
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
            Toast.makeText(context,"No hay memoria suficiente", Toast.LENGTH_SHORT);
        }
        return false;
    }

    public List<Subject> fillSubjects(List<Subject> elements){

        return elements;
    }
    public List<Exam> fillExams(List<Exam> elements){

        return elements;
    }
    public List<Student> fillStudents(List<Student> elements){

        return elements;
    }
}
