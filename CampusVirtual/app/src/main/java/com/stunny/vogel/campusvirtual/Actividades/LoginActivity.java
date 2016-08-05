package com.stunny.vogel.campusvirtual.Actividades;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.stunny.vogel.campusvirtual.R;

public class LoginActivity extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //--ESCONDEMOS LA ACTION BAR--//
        getSupportActionBar().hide();

        //-- Escuchamos al boton de entrar --//
        Button _loginButton = (Button) findViewById(R.id.btn_log);

        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

    @Override
    protected void onPause(){
        finish();
        System.exit(0);
    }

    public void login() {

        EditText _emailInput = (EditText) findViewById(R.id.email_input);
        EditText _passwordInput = (EditText) findViewById(R.id.password_input);
        Button _loginButton = (Button) findViewById(R.id.btn_log);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this, ProgressDialog.STYLE_SPINNER);


        if (!validate()) {
            onLoginFailed(progressDialog);
            return;
        }

        _loginButton.setEnabled(false);

        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Autenticando...");
        progressDialog.show();

        String email = _emailInput.getText().toString();
        String password = _passwordInput.getText().toString();

        if(!email.equals(getString(R.string.user_email)) || !password.equals(getString(R.string.user_password))){
            if(!email.equals(getString(R.string.user_email))){
                _emailInput.setError(getString(R.string.wrong_email));
            }
            if(!email.equals(getString(R.string.user_password))){
                _passwordInput.setError(getString(R.string.wrong_pass));
            }
            onLoginFailed(progressDialog);
            return;
        }

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        Button _loginButton = (Button) findViewById(R.id.btn_log);
        _loginButton.setEnabled(true);

        Intent i = new Intent(LoginActivity.this, MainMenuActivity.class);
        startActivity(i);
    }

    public void onLoginFailed(ProgressDialog pd) {
        Button _loginButton = (Button) findViewById(R.id.btn_log);
        pd.dismiss();

        _loginButton.setEnabled(true);
    }
    public boolean validate(){

        boolean ok = true;

        EditText _emailInput = (EditText) findViewById(R.id.email_input);
        EditText _passwordInput = (EditText) findViewById(R.id.password_input);

        String email = _emailInput.getText().toString(),
               password = _passwordInput.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

            _emailInput.setError("Introduce una dirección de email");
            ok = false;

        } else {

            _emailInput.setError(null);

        }

        if (password.isEmpty() || password.length() < 6) {

            _passwordInput.setError("La contraseña debe ser de mínimo 6 carácteres.");
            ok = false;

        } else {

            _passwordInput.setError(null);

        }

        return ok;

    }

}
