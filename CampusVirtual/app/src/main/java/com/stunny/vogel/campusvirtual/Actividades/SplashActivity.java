package com.stunny.vogel.campusvirtual.Actividades;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.stunny.vogel.campusvirtual.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    private static final long WELCOME_ACTIVITY_DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SharedPreferences prefs = getApplicationContext().getSharedPreferences(getPackageName()+"_preferences", MODE_PRIVATE);
        final boolean session;

        if(!prefs.contains("logeado")) session = false;
        else{
            session = prefs.getBoolean("logeado",false);
        }

        //--Hacemos que la actividad de bienvenida se muestre en pantalla completa.--//
        getSupportActionBar().hide();

        //--Asignamos el ic_launcher al ImageView del logo --//
        ImageView logo = (ImageView)findViewById(R.id.logo);
        logo.setImageResource(R.mipmap.ic_launcher);

        //--Tras 2 segundos de espera mandamos al usuario a la pantalla de login--//

        TimerTask delay = new TimerTask() {
            @Override
            public void run() {
                Intent i;
                if(session){
                    i = new Intent(SplashActivity.this, MainMenuActivity.class);
                    startActivity(i);
                    finish();
                }else {
                    i = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        };

        //--Declaro un tempoorizador para retrasar el intent
        Timer timer = new Timer();
        timer.schedule(delay, WELCOME_ACTIVITY_DELAY);

    }




}
