package com.stunny.vogel.campusvirtual.Actividades;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.stunny.vogel.campusvirtual.R;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends AppCompatActivity {

    private static final long WELCOME_ACTIVITY_DELAY = 3000;

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

        //--Tras 2 segundos de espera mandamos al usuario a la pantalla de login--//

        TimerTask delay = new TimerTask() {
            @Override
            public void run() {
                //--Accion de enviar a la siguiente actividad--//
                Intent i = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        };

        //--Declaro un tempoorizador para retrasar el intent
        Timer timer = new Timer();
        timer.schedule(delay, WELCOME_ACTIVITY_DELAY);

    }




}
