package com.stunny.vogel.campusvirtual.Actividades;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.stunny.vogel.campusvirtual.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //--Hacemos que la actividad de bienvenida se muestre en pantalla completa.--//
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);


        //--Asignamos el ic_launcher al ImageView del logo --//
        ImageView logo = (ImageView)findViewById(R.id.logo);
        logo.setImageResource(R.mipmap.ic_launcher);


    }
    @Override
    protected void onResume(){
        super.onResume();
        Intent i = new Intent(WelcomeActivity.this, LoginActivity.class);
        try {
            Thread.sleep(2000);
            startActivity(i);
            finish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
