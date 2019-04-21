package com.example.chaima.donatefood.Annoncer;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chaima.donatefood.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.widget.Toast.LENGTH_LONG;

public class UpdateNeedyActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener{

    EditText editTextDescription;
    EditText editTextHealthCondition;
    EditText editTextAdr;
    EditText editTextTel;

    TextView txtViewNumberPerson;

    Spinner spNumberPerson;
    String itemNumberPerson ;

    FloatingActionButton fabEditNeedy;

    RadioButton rdTemp, rdPeriod ;


    //Firebase
    DatabaseReference reference ;


    //Needy
    Needy needy ;

    boolean temp ;
    boolean period ;
    String id ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_needy);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Edit Information Needy");

        editTextDescription = (EditText) findViewById(R.id.ediTxtDescription);
        editTextHealthCondition = (EditText) findViewById(R.id.ediTxtHealthCondition);
        editTextAdr = (EditText) findViewById(R.id.ediTxtSetAdr);
        editTextTel = (EditText) findViewById(R.id.ediTxtTel);

        rdTemp = (RadioButton) findViewById(R.id.rd_temp);
        rdPeriod = (RadioButton) findViewById(R.id.rd_perid);


        rdTemp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                temp = rdTemp.isChecked();

            }
        });
        rdPeriod.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                period = rdPeriod.isChecked();

            }
        });

        spNumberPerson = (Spinner) findViewById(R.id.spinnerNumberPersonN);
        spNumberPerson.setOnItemSelectedListener(this);
        // spNumberPerson.setOnItemClickListener(this);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.numberPerson, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spNumberPerson.setAdapter(adapter);

        dataNeedy();

        reference = FirebaseDatabase.getInstance().getReference("Needy");

        fabEditNeedy = (FloatingActionButton) findViewById(R.id.btnSaveNeedy);

        id = getIntent().getExtras().getString("id");

        fabEditNeedy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               updateNeedy(editTextDescription.getText().toString().trim(), editTextHealthCondition.getText().toString().trim(), itemNumberPerson, editTextAdr.getText().toString().trim(), editTextTel.getText().toString().trim(), period, temp);
            }
        });
    }


    private void dataNeedy() {


        String desc = getIntent().getExtras().getString("Description");
        String healthCond = getIntent().getExtras().getString("HealthCondition");
        String nbPerson = getIntent().getExtras().getString("NumberOfPerson");
        String place = getIntent().getExtras().getString("Place");
        String tel = getIntent().getExtras().getString("Tel");
        String perd = getIntent().getExtras().getString("Perid");
        String tempo = getIntent().getExtras().getString("Temp");

        editTextDescription.setText(desc);
        editTextHealthCondition.setText(healthCond);
        //spNumberPerson.setSelection();
        editTextAdr.setText(place);
        editTextTel.setText(tel);


    }

    public void updateNeedy(String desc, String health, String nbPerson, String place, String tel, boolean period, boolean temp){

        int phone = Integer.parseInt(tel) ;

        if(!TextUtils.isEmpty(desc)) {
            if((rdTemp.isChecked() == true) || (rdPeriod.isChecked() == true)){
                if (isOnline()) {

                        needy = new Needy(id, desc, place, health, nbPerson, temp, period, tel);

                    if(tel == null){
                        needy = new Needy(id, desc, place, health, nbPerson, temp, period);
                    }

                    reference.child(id).setValue(needy).addOnCompleteListener(new OnCompleteListener<Void>() {
                        /*  private static final String TAG = "test";*/

                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(UpdateNeedyActivity.this, "The Information of Needy is updated" + itemNumberPerson, LENGTH_LONG).show();
                                Intent intentAnnoncer = new Intent(UpdateNeedyActivity.this, ProfileAnnoncer.class);
                                startActivity(intentAnnoncer);

                            } else {
                                Toast.makeText(UpdateNeedyActivity.this, "The Needy is not updated !!!", LENGTH_LONG).show();
                            }
                        }
                    });

                } else {
                    Toast.makeText(this, "You are not connected to Internet", LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(this, "it's needy temporay or peridocally"  , LENGTH_LONG ).show();
            }
        }else {
            Toast.makeText(this, "You Should write description about The Needy"  , LENGTH_LONG ).show();
        }



    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // On selecting a spinner item
        itemNumberPerson = parent.getItemAtPosition(pos).toString();

        // Showing selected spinner item
        //Toast.makeText(this, "selected" + itemNumberPerson , LENGTH_LONG ).show();



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
