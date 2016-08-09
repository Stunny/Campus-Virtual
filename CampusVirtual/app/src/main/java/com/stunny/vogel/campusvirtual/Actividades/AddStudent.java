package com.stunny.vogel.campusvirtual.Actividades;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.stunny.vogel.campusvirtual.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddStudent extends AppCompatActivity {

    private EditText stname;
    private EditText stbirth;
    private DatePickerDialog stbirthPicker;
    private SimpleDateFormat sdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_hardware_keyboard_arrow_left);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setSubtitle(R.string.stform);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo);

        sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        setViews();
        setDateDialog();
    }

    private void setViews(){
        stname = (EditText)findViewById(R.id.stname_input);

        stbirth = (EditText)findViewById(R.id.stbirth_input);
        stbirth.setInputType(InputType.TYPE_NULL);
        stbirth.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) showDatePicker();
                v.clearFocus();
            }
        });

    }
    private void setDateDialog(){
        Calendar date = Calendar.getInstance();
        stbirthPicker = new DatePickerDialog(AddStudent.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar bdate = Calendar.getInstance();
                bdate.set(year, monthOfYear, dayOfMonth);
                stbirth.setText(sdf.format(bdate.getTime()));
            }
        }, date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH));
    }
    private void showDatePicker(){
        stbirthPicker.show();
    }
}
