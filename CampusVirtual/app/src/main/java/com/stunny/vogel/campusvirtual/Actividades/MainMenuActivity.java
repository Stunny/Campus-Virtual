package com.stunny.vogel.campusvirtual.Actividades;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.stunny.vogel.campusvirtual.Logica.FontManager;
import com.stunny.vogel.campusvirtual.R;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo);

        //--Asignación de iconos--//
        TextView subjectIcon = (TextView) findViewById(R.id.manageSubject_icon),
                studentsIcon = (TextView) findViewById(R.id.manageStudents_icon),
                examsIcon = (TextView) findViewById(R.id.exams_icon),
                logoutIcon = (TextView) findViewById(R.id.logout_icon);

        Typeface fa = FontManager.getTypeface(getApplicationContext(), FontManager.FONT_AWESOME);
        FontManager.markAsIconContainer(studentsIcon, fa);
        FontManager.markAsIconContainer(subjectIcon, fa);
        FontManager.markAsIconContainer(examsIcon, fa);
        FontManager.markAsIconContainer(logoutIcon, fa);

        //--Listeners de las opciones del menu principal--//
        setMenuListeners();


    }

    public void setMenuListeners(){
        findViewById(R.id.manageSubject).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainMenuActivity.this, SubjectsActivity.class);
                startActivity(i);
            }
        });
        findViewById(R.id.manageStudents).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainMenuActivity.this, StudentsActivity.class);
                startActivity(i);
            }
        });
        findViewById(R.id.exams).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainMenuActivity.this, ExamsActivity.class);
                startActivity(i);
            }
        });
        findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getApplicationContext().getSharedPreferences(getPackageName()+"_preferences", MODE_PRIVATE);
                prefs.edit().putBoolean("logeado", false).commit();
                Intent i = new Intent(MainMenuActivity.this, LoginActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.main_menu_action_bar_menu, menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch(item.getItemId()){
            case R.id.new_student:
                Intent i = new Intent(MainMenuActivity.this, AddStudent.class);
                startActivity(i);
                return true;

            case R.id.new_subject:
                //Intent ns = new Intent(MainMenuActivity.this, AddSubject.class);
                //startActivity(ns);
                return true;

            case R.id.new_exam:
                Intent ne = new Intent(MainMenuActivity.this, AddEditExam.class);
                ne.putExtra("ADD", true);
                startActivity(ne);
                return true;

            case R.id.quit:
                finish();
                System.exit(0);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
