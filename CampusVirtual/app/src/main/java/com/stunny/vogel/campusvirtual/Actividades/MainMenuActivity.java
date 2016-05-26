package com.stunny.vogel.campusvirtual.Actividades;

import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.stunny.vogel.campusvirtual.Logica.FontManager;
import com.stunny.vogel.campusvirtual.R;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);




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
