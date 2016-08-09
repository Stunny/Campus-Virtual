package com.stunny.vogel.campusvirtual.Actividades;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.stunny.vogel.campusvirtual.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddStudent extends AppCompatActivity {

    private EditText st_name;
    private EditText st_birth;
    private RadioGroup st_genre;
    private DatePickerDialog st_birthPicker;
    private SimpleDateFormat sdf;
    private Spinner sp_Deg;

    private String name, birthdate, degree, genre, photoPath;

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
        st_name = (EditText)findViewById(R.id.st_name_input);
        sp_Deg = (Spinner)findViewById(R.id.st_degree_spinner);
        setSpinAdapter();
        sp_Deg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                degree = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        st_birth = (EditText)findViewById(R.id.st_birth_input);
        st_birth.setInputType(InputType.TYPE_NULL);
        st_birth.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) showDatePicker();
                v.clearFocus();
            }
        });

        st_genre = (RadioGroup)findViewById(R.id.radiogenre);
        st_genre.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                genre =(String) ((RadioButton)findViewById(checkedId)).getText();
                //Toast.makeText(getApplicationContext(), genre, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setSpinAdapter(){
        ArrayAdapter<CharSequence> sa = ArrayAdapter.createFromResource(this,
                R.array.degrees, android.R.layout.simple_spinner_dropdown_item);
        sa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_Deg.setAdapter(sa);
    }
    private void setDateDialog(){
        Calendar date = Calendar.getInstance();
        st_birthPicker = new DatePickerDialog(AddStudent.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar bdate = Calendar.getInstance();
                bdate.set(year, monthOfYear, dayOfMonth);
                st_birth.setText(sdf.format(bdate.getTime()));
            }
        }, date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH));
    }
    private void showDatePicker(){
        st_birthPicker.show();
    }
}
