package com.example.chaima.donatefood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.chaima.donatefood.Donater.ProfileDonater;
import com.example.chaima.donatefood.TransporterVolunteer.ProfileTransporter;

public class ChooseProfil extends AppCompatActivity {

    Button btnDonater, btnAnnoncer, btnTransporter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_profil);

        btnDonater = (Button) findViewById(R.id.btnDonater);
        btnAnnoncer = (Button) findViewById(R.id.btnAnnoncer);
        btnTransporter = (Button) findViewById(R.id.btnTronsporter);

        btnDonater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDonater = new Intent(ChooseProfil.this, com.example.chaima.donatefood.Donater.ProfileDonater.class);
                startActivity(intentDonater);
            }
        });

       btnAnnoncer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAnnoncer = new Intent(ChooseProfil.this, com.example.chaima.donatefood.Annoncer.ProfileAnnoncer.class );
                startActivity(intentAnnoncer);
            }
        });
        btnTransporter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTransporter = new Intent(ChooseProfil.this, ProfileTransporter.class);
                startActivity(intentTransporter);
            }
        });

    }

}
