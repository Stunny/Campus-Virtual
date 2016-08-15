package com.stunny.vogel.campusvirtual.Actividades.Views;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stunny.vogel.campusvirtual.Logica.CustomAdapters.SimpleStudentAdapter;
import com.stunny.vogel.campusvirtual.Logica.FileManager;
import com.stunny.vogel.campusvirtual.Logica.ListElements.Subject;
import com.stunny.vogel.campusvirtual.R;

import java.util.ArrayList;

public class ViewSubject extends AppCompatActivity {

    private Subject s;
    private ArrayList<String> students, themes;
    private String name, descrip, iconPath;

    private FileManager fm;

    private TextView sbtitle, sbdesc, sbThemes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_subject);

        fm = new FileManager();

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_hardware_keyboard_arrow_left);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setSubtitle(R.string.sbview);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo);

        Bundle b = getIntent().getExtras();
        students = b.getStringArrayList("students");
        themes = b.getStringArrayList("themes");
        name = b.getString("name");
        descrip = b.getString("descrip");
        iconPath = b.getString("iconPath");

        setViews();
    }

    private void setViews(){
        sbtitle = (TextView)findViewById(R.id.sbtitle);
        sbdesc = (TextView)findViewById(R.id.sbdesc);
        sbThemes = (TextView)findViewById(R.id.sb_themeList);

        sbtitle.setText(name);
        sbdesc.setText(descrip);

        sbThemes.setText(themes.get(0));
        for(int i = 1; i<themes.size(); i++) sbThemes.setText(sbThemes.getText()+"\n"+themes.get(i));

        ViewGroup insertStudents = (ViewGroup)findViewById(R.id.vsb_students);

        SimpleStudentAdapter ssa = new SimpleStudentAdapter(getApplicationContext(), this, students);
        View aux;
        for(int j = 0; j<ssa.getCount();j++){
            aux = ssa.getView(j,null,insertStudents);
            insertStudents.addView(aux, j);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.sb_action_bar_menu, menu);

        return super .onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.delete:
                borrarAsignatura();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void borrarAsignatura(){
        AlertDialog.Builder adb = new AlertDialog.Builder(ViewSubject.this);
        adb.setMessage("¿Desea eliminar esta asignatura?");
        adb.setTitle("Eliminar Asignatura");
        adb.setCancelable(true);

        adb.setPositiveButton(
                "Si",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final ProgressDialog pd = new ProgressDialog(ViewSubject.this, ProgressDialog.STYLE_SPINNER);
                        pd.setMessage("Eliminando...");
                        pd.show();
                        new android.os.Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                delete();
                                pd.dismiss();
                            }
                        }, 3500);
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
        AlertDialog del = adb.create();
        del.show();
    }
    private void delete(){
        fm.removeSubject(name, getApplicationContext());
        ViewSubject.this.finish();
    }
}
