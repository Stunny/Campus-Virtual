package com.stunny.vogel.campusvirtual.Actividades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.stunny.vogel.campusvirtual.R;

public class LoginActivity extends AppCompatActivity {


    EditText _emailInput = (EditText) findViewById(R.id.email_input),
             _passwordInput = (EditText) findViewById(R.id.password_input);
    Button   _loginButton = (Button) findViewById(R.id.btn_log);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //--ESCONDEMOS LA ACTION BAR--//
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        //-- Escuchamos al boton de entrar --//
        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

    public void login() {

    }

}
