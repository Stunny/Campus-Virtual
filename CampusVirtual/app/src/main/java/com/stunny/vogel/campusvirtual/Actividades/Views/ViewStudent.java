
package com.stunny.vogel.campusvirtual.Actividades.Views;

import android.app.ActionBar;
import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.stunny.vogel.campusvirtual.Logica.FileManager;
import com.stunny.vogel.campusvirtual.R;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ViewStudent extends AppCompatActivity {

    private String name, degree, gender, photoPath;
    private Date birthDate;

    private ImageView vst_photo;
    private TextView vst_name, vst_stdeg, vst_stBdate, vst_stgender, vst_subjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_hardware_keyboard_arrow_left);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setSubtitle(R.string.stview);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo);

        Bundle b = getIntent().getExtras();
        name = b.getString("name");
        degree = b.getString("degree");
        gender = b.getString("gender");
        photoPath = b.getString("photoPath");
        birthDate = Date.valueOf(b.getString("birthDate"));

        setViews();
    }

    private void setViews(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        FileManager fm = new FileManager();

        vst_photo = (ImageView)findViewById(R.id.vst_photo);
        vst_name = (TextView)findViewById(R.id.vst_name);
        vst_stdeg = (TextView)findViewById(R.id.vst_stdeg);
        vst_stBdate = (TextView)findViewById(R.id.vst_stBdate);
        vst_stgender = (TextView)findViewById(R.id.vst_stgender);
        vst_subjects = (TextView)findViewById(R.id.vst_subjects);

        File imFile = new File(photoPath);
        if(imFile.exists()){
            Uri imgUri = Uri.fromFile(imFile);
            vst_photo.setImageURI(imgUri);
        }else{
            vst_photo.setImageResource(R.drawable.ic_default_student);
        }

        vst_name.setText(name);
        vst_stdeg.setText("Especialidad: "+degree);
        vst_stBdate.setText(sdf.format(birthDate));
        vst_stgender.setText(gender);

        ArrayList<String> subjs = fm.findStudentSubjects(name, getApplicationContext());
        vst_subjects.setText(subjs.get(0));
        for(int i = 1; i<subjs.size();i++)
            vst_subjects.setText(vst_subjects.getText().toString()+ "\n"+subjs.get(i));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
