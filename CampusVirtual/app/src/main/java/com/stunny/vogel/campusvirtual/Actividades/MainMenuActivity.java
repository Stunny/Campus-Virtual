package com.stunny.vogel.campusvirtual.Actividades;

import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.stunny.vogel.campusvirtual.Logica.FontManager;
import com.stunny.vogel.campusvirtual.R;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


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

            }
        });
        findViewById(R.id.manageStudents).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        findViewById(R.id.exams).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.action_bar_menu, menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch(item.getItemId()){
            case R.id.new_student:

                return true;

            case R.id.new_subject:

                return true;

            case R.id.new_exam:

                return true;

            case R.id.quit:

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
