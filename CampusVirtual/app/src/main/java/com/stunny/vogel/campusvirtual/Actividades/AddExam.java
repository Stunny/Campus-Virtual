package com.stunny.vogel.campusvirtual.Actividades;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.stunny.vogel.campusvirtual.Logica.FileManager;
import com.stunny.vogel.campusvirtual.Logica.ListElements.Exam;
import com.stunny.vogel.campusvirtual.R;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddExam extends AppCompatActivity {

    private EditText ex_date_input;
    private EditText ex_hour_input;
    private Spinner ex_degree_sp;
    private Spinner ex_subj_sp;
    private Spinner ex_room_sp;

    private DatePickerDialog ex_pickDate;
    private TimePickerDialog ex_pickHour;
    private Date dDate;
    private Time dTime;

    private Button ex_create;

    private String degree, subject, room, mDate, hour;

    private SimpleDateFormat dfD, dfH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exam);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_hardware_keyboard_arrow_left);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setSubtitle(R.string.exform);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo);

        dfD = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        dfH = new SimpleDateFormat("HH:mm", Locale.getDefault());

        setViews();
        setDialogs();
    }

    private void setViews(){
        ex_degree_sp = (Spinner)findViewById(R.id.ex_degree_spinner);
        ex_subj_sp = (Spinner)findViewById(R.id.ex_subj_spinner);
        ex_room_sp = (Spinner)findViewById(R.id.ex_room_spinner);
        setupSpinners();

        ex_date_input = (EditText)findViewById(R.id.ex_date_input);
        ex_hour_input = (EditText)findViewById(R.id.ex_hour_input);
        setupEdits();

        ex_create = (Button)findViewById(R.id.createExam);
        setCreateListener();
    }
    private void setupSpinners(){
        ArrayAdapter<CharSequence> degAdap = ArrayAdapter.createFromResource(this,
                R.array.degrees, android.R.layout.simple_spinner_dropdown_item);
        degAdap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ex_degree_sp.setAdapter(degAdap);

        ArrayAdapter<CharSequence> subjAdap = ArrayAdapter.createFromResource(this,
                R.array.subjects, android.R.layout.simple_spinner_dropdown_item);
        degAdap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ex_subj_sp.setAdapter(subjAdap);

        ArrayAdapter<CharSequence> roomAdap = ArrayAdapter.createFromResource(this,
                R.array.rooms, android.R.layout.simple_spinner_dropdown_item);
        degAdap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ex_room_sp.setAdapter(roomAdap);

        setSpinnerListeners();
    }
    private void setSpinnerListeners(){
        ex_degree_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                degree = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        ex_subj_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                subject = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        ex_room_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                room = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    private void setupEdits(){
        ex_date_input.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) showDatePicker();
                v.clearFocus();
            }
        });
        ex_hour_input.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) showHourPicker();
                v.clearFocus();
            }
        });
    }
    private void setDialogs(){
        Calendar date = Calendar.getInstance();
        ex_pickDate = new DatePickerDialog(AddExam.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar bdate = Calendar.getInstance();
                bdate.set(year, monthOfYear, dayOfMonth);
                mDate = dfD.format(bdate.getTime());
                dDate = Date.valueOf(year+"-"+monthOfYear+"-"+dayOfMonth);
                ex_date_input.setText(mDate);
            }
        }, date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH));

        ex_pickHour = new TimePickerDialog(AddExam.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                hour = Integer.toString(hourOfDay)+':'+Integer.toString(minute);
                dTime = Time.valueOf(hour+':'+"00");
                ex_hour_input.setText(hour);
            }
        }, date.get(Calendar.HOUR_OF_DAY), date.get(Calendar.MINUTE), true);
    }
    private void showDatePicker(){
        ex_pickDate.setTitle("Fecha de examen");
        ex_pickDate.show();
    }
    private void showHourPicker(){
        ex_pickHour.setTitle("Hora de examen");
        ex_pickHour.show();
    }

    private void setCreateListener(){
        ex_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                create();
            }
        });
    }
    private void create(){
        final FileManager fm = new FileManager();
        ex_create.setEnabled(false);

        final ProgressDialog pd = new ProgressDialog(AddExam.this, ProgressDialog.STYLE_SPINNER);
        pd.setMessage("Comprobando...");
        pd.show();

        if(!validate()){
            onCreateFailed(pd);
            return;
        }

        //Comprobaciones de existencia
        final Exam e = new Exam();
        e.room = room;
        e.fecha = dDate;
        e.hora = dTime;
        e.degree = degree;
        e.subject = subject;

        if(fm.exists(e)){
                onCreateFailed(pd);
                Toast.makeText(AddExam.this, "El examen ya existe.", Toast.LENGTH_SHORT).show();
            return;
        }

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        onCreateSuccess(e, fm);
                        pd.dismiss();
                    }
                }, 3000);
    }
    private boolean validate(){
        boolean ok = true;
        //--VALIDAR LOS DATOS INTRODUCIDOS
        return ok;
    }
    private void onCreateFailed(ProgressDialog pd){
        pd.dismiss();
        ex_create.setEnabled(true);
    }
    private void onCreateSuccess(Exam e, FileManager fm){
        ex_create.setEnabled(true);
        //fm.createExam(e, getApplicationContext());
        Intent i = new Intent(AddExam.this, ExamsActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //startActivity(i);
    }
}
