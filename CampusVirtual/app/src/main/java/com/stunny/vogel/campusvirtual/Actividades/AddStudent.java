package com.stunny.vogel.campusvirtual.Actividades;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.stunny.vogel.campusvirtual.Logica.FileManager;
import com.stunny.vogel.campusvirtual.Logica.ListElements.Student;
import com.stunny.vogel.campusvirtual.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddStudent extends AppCompatActivity {

    private EditText st_name_input;
    private EditText st_birth_input;
    private RadioGroup st_genre_input;
    private DatePickerDialog st_birthPicker;
    private SimpleDateFormat sdf;
    private Spinner sp_Deg;
    private ImageView st_selectedPhoto;
    private Button st_selectPhoto,
                    st_create;

    private String name, degree, genre, photoPath;
    private Uri outputURI;
    private File outputFile;

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
        st_name_input = (EditText)findViewById(R.id.st_name_input);
        sp_Deg = (Spinner)findViewById(R.id.st_degree_spinner);
        setSpinAdapter();
        sp_Deg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                degree = parent.getSelectedItem().toString();
                //Toast.makeText(getApplicationContext(), degree, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        st_birth_input = (EditText)findViewById(R.id.st_birth_input);
        st_birth_input.setInputType(InputType.TYPE_NULL);
        st_birth_input.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) showDatePicker();
                v.clearFocus();
            }
        });

        st_genre_input = (RadioGroup)findViewById(R.id.radiogenre);
        st_genre_input.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                genre = (String) ((RadioButton) findViewById(checkedId)).getText();
                //Toast.makeText(getApplicationContext(), genre, Toast.LENGTH_SHORT).show();
            }
        });

        st_selectedPhoto = (ImageView)findViewById(R.id.st_selectedphoto);
        st_selectedPhoto.setImageResource(R.drawable.ic_default_student);

        st_selectPhoto = (Button)findViewById(R.id.st_selectphoto);
        setSelectPhotoListener();

        st_create = (Button)findViewById(R.id.createStudent);
        setCreateStudentListener();
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
                st_birth_input.setText(sdf.format(bdate.getTime()));
            }
        }, date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH));
    }
    private void showDatePicker(){
        st_birthPicker.show();
    }
    private void setSelectPhotoListener(){
        st_selectPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchSelectPhotoIntent();
            }
        });
    }
    private void launchSelectPhotoIntent(){
        /*final File arr = new File(Environment.getExternalStorageDirectory()+File.separator+"dCampusIMG"+File.separator);
        arr.mkdirs();
        final String _imgName = "img_"+System.currentTimeMillis()+".jpg";
        final File _imgDir = new File(arr, _imgName);
        outputURI = Uri.fromFile(_imgDir);*/

        try{
            outputFile = File.createTempFile("tmp", ".jpg", getCacheDir());
        }catch(IOException e){
            e.printStackTrace();
        }

        outputURI = Uri.fromFile(outputFile);

        //--Capturar imagen desde la camara
        final List<Intent> cameraIntents = new ArrayList<Intent>();
        final Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        final PackageManager pm = getPackageManager();
        final List<ResolveInfo> camlist = pm.queryIntentActivities(captureIntent, 0);
        for(ResolveInfo ri : camlist){
            final String packname = ri.activityInfo.packageName;
            final Intent i = new Intent(captureIntent);
            i.setComponent(new ComponentName(ri.activityInfo.packageName, ri.activityInfo.name));
            i.setPackage(packname);
            //i.putExtra(MediaStore.EXTRA_OUTPUT, outputURI);
            cameraIntents.add(i);
        }

        //--Capturar imagen desde sistema de archivos
        final Intent iGal = new Intent();
        iGal.setType("image/*");
        iGal.setAction(Intent.ACTION_GET_CONTENT);

        //--Seleccionador de metodo
        final Intent pickImg = Intent.createChooser(iGal, "Seleccionar fuente");

        //--Añadimos la camara al seleccionador
        pickImg.putExtra(Intent.EXTRA_INITIAL_INTENTS,
                cameraIntents.toArray(new Parcelable[cameraIntents.size()]));

        startActivityForResult(pickImg, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                Bitmap bm = null;
                if (data.hasExtra("data")) {
                    Bundle extras = data.getExtras();
                    bm = (Bitmap) extras.get("data");
                } else {
                    AssetFileDescriptor afd = null;
                    try {
                        afd = getContentResolver().openAssetFileDescriptor(data.getData(), "r");
                    } catch (FileNotFoundException fe) {
                        fe.printStackTrace();
                    }
                    bm = BitmapFactory.decodeFileDescriptor(afd.getFileDescriptor());
                }
                try {
                    FileOutputStream fos = new FileOutputStream(new File(outputURI.getPath()));
                    bm.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    fos.flush();
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                loadImg(outputURI);
            }
        }
    }

    private void loadImg(Uri u){
        st_selectedPhoto.setImageURI(u);
        photoPath = u.getPath();
    }
    private void setCreateStudentListener(){
        st_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                create();
            }
        });
    }

    private void create(){
        final FileManager fm = new FileManager();
        st_create.setEnabled(false);
        name = st_name_input.getText().toString();

        final ProgressDialog pd = new ProgressDialog(AddStudent.this, ProgressDialog.STYLE_SPINNER);
        pd.setMessage("Comprobando...");
        pd.setIndeterminate(true);
        pd.show();

        if(!validate()){
            onCreateFailed(pd);
            return;
        }

        final Student s = new Student();
        s.photoPath = photoPath;

        String[] dates = st_birth_input.getText().toString().split("/");
        s.birthDate = java.sql.Date.valueOf(dates[2]+"-"+dates[1]+"-"+dates[0]);

        s.gender = genre;
        s.degree = degree;
        s.name = name;

        if(fm.exists(s, getApplicationContext())){
            Toast.makeText(getApplicationContext(),
                    "El alumno ya existe. Puebe a introducir el nombre completo.", Toast.LENGTH_SHORT).show();
            onCreateFailed(pd);
            return;
        }
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        onCreateSuccess(s, fm);
                        pd.dismiss();
                    }
                }, 3000);
    }

    private boolean validate(){
        boolean ok = true;
        String nombre = st_name_input.getText().toString();
        if(nombre.isEmpty() || !nombre.contains(" ")){
            st_name_input.setError("Introduzca un nombre completo");
            ok = false;
        }else{
            st_name_input.setError(null);
        }
        String nacim = st_birth_input.getText().toString();
        if(nacim.isEmpty()){
            st_birth_input.setError("Escoja la fecha de nacimiento");
            ok = false;
        }else{
            st_birth_input.setError(null);
        }
        TextView strgentag = (TextView)findViewById(R.id.strgentag);
        if(st_genre_input.getCheckedRadioButtonId()==-1){
            ok = false;
            strgentag.setText(strgentag.getText().toString()+"*");
            strgentag.setTextColor(Color.parseColor("#D32F2F"));
        }else{
            strgentag.setText("Sexo:");
            strgentag.setTextColor(Color.parseColor("#FF737373"));
        }

        return ok;
    }
    private void onCreateFailed(ProgressDialog pd){
        pd.dismiss();
        st_create.setEnabled(true);
    }
    private void onCreateSuccess(Student s, FileManager fm){
        st_create.setEnabled(true);
        fm.createStudent(s, getApplicationContext());
        Intent i = new Intent(AddStudent.this, StudentsActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }
}
