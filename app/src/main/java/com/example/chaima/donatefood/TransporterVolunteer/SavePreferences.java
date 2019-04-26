package com.example.chaima.donatefood.TransporterVolunteer;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.chaima.donatefood.Donater.ActivitySaveDon;
import com.example.chaima.donatefood.Gouvernorat;
import com.example.chaima.donatefood.GouvernoratDialogFragment;
import com.example.chaima.donatefood.Main2Activity;
import com.example.chaima.donatefood.R;

import java.lang.reflect.Array;
import java.util.Calendar;

public class SavePreferences extends AppCompatActivity {

    EditText editTextTimeFrom, editTextTimeTo ;
    Button btnChooseCity, btnChooseGouver;
    FloatingActionButton btnSavePerf ;
    CheckBox everyTime, everyCity, everyGouver ;

    TimePickerDialog timePickerDialog;
    Calendar calendar;
    int currentHour;
    int currentMinute;
    String amPm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_preferences);

        editTextTimeFrom = (EditText)findViewById(R.id.editTxt_from);
        editTextTimeTo = (EditText) findViewById(R.id.editTxt_to);
        btnChooseCity = (Button) findViewById(R.id.btn_chooseCity);
        btnChooseGouver = (Button)findViewById(R.id.btn_chooseGouver);
        btnSavePerf = (FloatingActionButton) findViewById(R.id.btn_savePerf);
        everyTime = (CheckBox) findViewById(R.id.chx_everyTime);
        everyCity = (CheckBox) findViewById(R.id.chx_everyCity);
        everyGouver = (CheckBox) findViewById(R.id.chx_everyGouv);

        editTextTimeFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);


                timePickerDialog = new TimePickerDialog(SavePreferences.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay >= 12) {
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }
                        editTextTimeFrom.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);
                    }
                }, currentHour, currentMinute, false);

                timePickerDialog.show();
            }
        });

        editTextTimeTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);


                timePickerDialog = new TimePickerDialog(SavePreferences.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay >= 12) {
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }
                        editTextTimeTo.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);
                    }
                }, currentHour, currentMinute, false);

                timePickerDialog.show();
            }
        });

        btnChooseGouver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Dialog dialog = new Dialog(SavePreferences.this);
                /*Intent in = new Intent(SavePreferences.this, Main2Activity.class);
                startActivity(in);*/


               /* FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                GouvernoratDialogFragment newFragment = new GouvernoratDialogFragment();
// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack
                transaction.replace(R.id.container_fragment, newFragment);
                transaction.addToBackStack(null);

// Commit the transaction
                transaction.commit();*/
               FragmentManager fm = getSupportFragmentManager();
                GouvernoratDialogFragment alertDialog = GouvernoratDialogFragment.newInstance("Some title");
                alertDialog.show(fm, "fragment_alert");
            }
        });

        btnSavePerf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gouvernorat gouver = new Gouvernorat();

                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                SharedPreferences.Editor editor = pref.edit();

                if(everyTime.isChecked() == true){
                    editor.putBoolean("every_time", true);
                }else{
                    editor.putString("from", editTextTimeFrom.getText().toString());
                    editor.putString("to", editTextTimeTo.getText().toString());
                }
                if(everyGouver.isChecked() == true){
                    editor.putBoolean("every_gouver", true);
               }else{
                    /*StringBuilder sb = new StringBuilder();

                    for (int i = 0; i < gouver.getGouver_Name().length(); i++) {
                        if (gouver.isSelected()) {
                            sb.append(gouver.getGouver_Name()).append(",");
                        }
                    }*/

                    if(gouver.isSelected()== true) {
                        editor.putString("Gouver", gouver.getGouver_Name());
                    }
                }
                if(everyCity.isChecked() == true){
                    editor.putBoolean("every_city", true);
                }else{

                }
                editor.commit();
                Intent act2 = new Intent(SavePreferences.this, Main2Activity.class);
                startActivity(act2);
            }
        });


    }


}
