package com.stunny.vogel.campusvirtual.Actividades;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.stunny.vogel.campusvirtual.Logica.FileManager;
import com.stunny.vogel.campusvirtual.Logica.ListElements.Exam;
import com.stunny.vogel.campusvirtual.R;

import java.io.File;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddEditExam extends AppCompatActivity {

    private boolean _ADD;

    String eHora, eFecha, eSubj, eRoom, eDeg;

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

        Intent i = getIntent();
        Bundle b = i.getExtras();

        _ADD = b.getBoolean("ADD");

        if(!_ADD){
            eFecha = b.getString("fecha");
            dDate = Date.valueOf(eFecha);
            eHora = b.getString("hora");
            dTime = Time.valueOf(eHora);
            eSubj = b.getString("subj");
            eRoom = b.getString("aula");
            eDeg = b.getString("deg");
        }

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_hardware_keyboard_arrow_left);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        if(_ADD) getSupportActionBar().setSubtitle(R.string.exform);
        else getSupportActionBar().setSubtitle(R.string.exedit);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo);

        dfD = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        dfH = new SimpleDateFormat("HH:mm", Locale.getDefault());

        setViews();
        setDialogs();
    }

    public void onBackPressed(){
        exit();
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                exit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void exit(){
        if(everyThingClean()) super.onBackPressed();
        else confirmExit();
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
        if (_ADD )setCreateListener();
        if (!_ADD)setUpdateListener();

        if(!_ADD){
            ex_date_input.setText(dfD.format(Date.valueOf(eFecha)));
            ex_hour_input.setText(dfH.format(Time.valueOf(eHora)));
            ex_create.setText(R.string.save);

            ArrayAdapter a = (ArrayAdapter) ex_degree_sp.getAdapter();
            ArrayAdapter aa = (ArrayAdapter) ex_subj_sp.getAdapter();
            ArrayAdapter aaa = (ArrayAdapter) ex_room_sp.getAdapter();

            ex_degree_sp.setSelection(a.getPosition(eDeg));
            ex_subj_sp.setSelection(aa.getPosition(eSubj));
            ex_room_sp.setSelection(aaa.getPosition(eRoom));
        }
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
        ex_pickDate = new DatePickerDialog(AddEditExam.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar bdate = Calendar.getInstance();
                bdate.set(year, monthOfYear, dayOfMonth);
                mDate = dfD.format(bdate.getTime());
                dDate = Date.valueOf(year+"-"+monthOfYear+"-"+dayOfMonth);
                ex_date_input.setText(mDate);
            }
        }, date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH));

        ex_pickHour = new TimePickerDialog(AddEditExam.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                hour = String.format("%02d", hourOfDay)+':'+String.format("%02d", minute);
                dTime = Time.valueOf(hour+':'+"00");
                ex_hour_input.setText(hour);
            }
        }, date.get(Calendar.HOUR_OF_DAY), date.get(Calendar.MINUTE), true);
    }
    private void showDatePicker(){
        //ex_pickDate.setTitle("Fecha de examen");
        ex_pickDate.show();
    }
    private void showHourPicker(){
        //ex_pickHour.setTitle("Hora de examen");
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
    private void setUpdateListener(){
        ex_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });
    }
    private void update(){
        final FileManager fm = new FileManager();
        ex_create.setEnabled(false);

        final ProgressDialog pd = new ProgressDialog(AddEditExam.this, ProgressDialog.STYLE_SPINNER);
        pd.setMessage("Comprobando...");
        pd.show();

        if(!validate() || everyThingClean()){
            onCreateFailed(pd);
            return;
        }

        final Exam e = new Exam();
        e.room = room;
        e.fecha = dDate;
        e.hora = dTime;
        e.degree = degree;
        e.subject = subject;

        new android.os.Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        onCreateSuccess(e, fm);
                    }
                }, 3000);
    }
    private void create(){
        final FileManager fm = new FileManager();
        ex_create.setEnabled(false);

        final ProgressDialog pd = new ProgressDialog(AddEditExam.this, ProgressDialog.STYLE_SPINNER);
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
                Toast.makeText(AddEditExam.this, "El examen ya existe.", Toast.LENGTH_SHORT).show();
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
        Calendar c = Calendar.getInstance();
        String currentDate = Integer.toString(c.get(Calendar.YEAR))+'-'
                +Integer.toString(c.get(Calendar.MONTH))+'-'
                +Integer.toString(c.get(Calendar.DAY_OF_MONTH));
        Time currentHour = Time.valueOf(Integer.toString(c.get(Calendar.HOUR_OF_DAY))+':'
                +Integer.toString(c.get(Calendar.MINUTE))+':'
                +Integer.toString(c.get(Calendar.SECOND)));
        //--VALIDAR LOS DATOS INTRODUCIDOS

        if(ex_date_input.getText().toString().isEmpty()){
            ex_date_input.setError("Introduzca fecha de examen");
            Toast.makeText(AddEditExam.this, "Introduzca fecha de examen", Toast.LENGTH_SHORT).show();
            ok = false;
        }else if(dDate.before(Date.valueOf(currentDate))){
            ex_date_input.setError("Introduzca una fecha futura");
            Toast.makeText(AddEditExam.this, "Introduzca una fecha futura", Toast.LENGTH_SHORT).show();
            ok = false;
        }else{
            ex_date_input.setError(null);
        }
        if(ex_hour_input.getText().toString().isEmpty()){
            ex_hour_input.setError("Introduzca hora de examen");
            Toast.makeText(AddEditExam.this, "Introduzca hora de examen", Toast.LENGTH_SHORT).show();
            ok = false;
        }else if(dDate.compareTo(Date.valueOf(currentDate)) == 0 && dTime.compareTo(currentHour) < 0){
            ex_hour_input.setError("Introduzca una hora de examen futura");
            Toast.makeText(AddEditExam.this, "Introduzca una hora de examen futura", Toast.LENGTH_SHORT).show();
            ok = false;
        }else{
            ex_hour_input.setError(null);
        }
        if(ex_room_sp.getSelectedItemPosition() == 0){
            ok = false;
            TextView tag = (TextView)findViewById(R.id.ex_room_input);
            tag.setText(tag.getText().toString()+"*");
            tag.setTextColor(R.color.colorPrimaryDark);
        }else{
            TextView tag = (TextView)findViewById(R.id.ex_room_input);
            tag.setText(R.string.exroom);
            tag.setTextColor(R.color.formTextColor);
        }
        if(ex_degree_sp.getSelectedItemPosition() == 0){
            ok = false;
            TextView tag = (TextView)findViewById(R.id.ex_deg_input);
            tag.setText(tag.getText().toString()+"*");
            tag.setTextColor(R.color.colorPrimaryDark);
        }else{
            TextView tag = (TextView)findViewById(R.id.ex_deg_input);
            tag.setText(R.string.stdeg);
            tag.setTextColor(R.color.formTextColor);
        }
        if(ex_subj_sp.getSelectedItemPosition() == 0){
            ok = false;
            TextView tag = (TextView)findViewById(R.id.ex_subj_input);
            tag.setText(tag.getText().toString()+"*");
            tag.setTextColor(R.color.colorPrimaryDark);
        }else{
            TextView tag = (TextView)findViewById(R.id.ex_subj_input);
            tag.setText(R.string.exsubj);
            tag.setTextColor(R.color.formTextColor);
        }

        return ok;
    }
    private void onCreateFailed(ProgressDialog pd){
        pd.dismiss();
        ex_create.setEnabled(true);
    }
    private void onCreateSuccess(Exam e, FileManager fm){
        ex_create.setEnabled(true);
        if (_ADD)fm.createExam(e, getApplicationContext());
        if(!_ADD)fm.updateExam(e);
        Intent i = new Intent(AddEditExam.this, ExamsActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(i);
        finish();
    }
    private boolean everyThingClean(){
        boolean clean = true;
        ArrayAdapter a = (ArrayAdapter) ex_degree_sp.getAdapter();
        ArrayAdapter aa = (ArrayAdapter) ex_subj_sp.getAdapter();
        ArrayAdapter aaa = (ArrayAdapter) ex_room_sp.getAdapter();

        if(_ADD){
            clean = ex_date_input.getText().toString().isEmpty()
                    && ex_hour_input.getText().toString().isEmpty()
                    && ex_subj_sp.getSelectedItemPosition() == 0
                    && ex_room_sp.getSelectedItemPosition() == 0
                    && ex_degree_sp.getSelectedItemPosition() == 0;
        }
        if (!_ADD){
            clean = ex_date_input.getText().toString().equals(dfD.format(Date.valueOf(eFecha)))
                    && ex_hour_input.getText().toString().equals(dfH.format(Time.valueOf(eHora)))
                    && aa.getPosition(eSubj) == ex_subj_sp.getSelectedItemPosition()
                    && a.getPosition(eDeg) == ex_degree_sp.getSelectedItemPosition()
                    && aaa.getPosition(eRoom) == ex_room_sp.getSelectedItemPosition();
        }

        return clean;
    }
    private void confirmExit(){
        AlertDialog.Builder adb = new AlertDialog.Builder(AddEditExam.this);
        adb.setTitle("Salir");
        adb.setMessage("¿Desea descartar los cambios?");
        adb.setCancelable(true);

        adb.setPositiveButton(
                "Si",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AddEditExam.this.finish();
                        dialog.cancel();
                    }
                }
        );
        adb.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }
        );
        AlertDialog ad = adb.create();
        ad.show();
    }
}
