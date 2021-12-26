package com.example.chaima.donatefood.TransporterVolunteer;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.chaima.donatefood.Annoncer.Needy;
import com.example.chaima.donatefood.Donater.Don;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class DonAffectedService {

    public DatabaseReference databaseReferenceDon;
    public DatabaseReference databaseReferenceNeedy;
    public DatabaseReference databaseReferenceDonAffectedNeedy;
    // FirebaseFirestore db ;
    final ArrayList<String> donValue = new ArrayList<>();

    ValueEventListener eventListenerNeedy;
    ValueEventListener eventListenerDon;

    Thread dataDon;
    ArrayList<Don> donList = new ArrayList<>();
    ArrayList<Needy> needyList = new ArrayList<>();

    public DonAffectedService() {

        databaseReferenceDonAffectedNeedy = FirebaseDatabase.getInstance().getReference("Don_affected");
        databaseReferenceDon = FirebaseDatabase.getInstance().getReference("Don");
        /*FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
         db = FirebaseFirestore.getInstance();
        db.setFirestoreSettings(settings);*/

        databaseReferenceDon.keepSynced(true);
        databaseReferenceNeedy = FirebaseDatabase.getInstance().getReference("Needy");
    }

    public DonAffectedService(DatabaseReference databaseReference) {
        this.databaseReferenceDon = databaseReference;

    }


    public void getDon() {


        databaseReferenceDon.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot donSnapshot : snapshot.getChildren()) {
                    Don donValue = donSnapshot.getValue(Don.class);
                   /* String id = (String) donSnapshot.getValue(Don.class).getIdDon();
                    String title = (String) donSnapshot.getValue(Don.class).getTitle();
                    String desc = (String) donSnapshot.getValue(Don.class).getDescription();
                    String health = (String) donSnapshot.getValue(Don.class).getHealthCondition();*/

                    donList.add(donValue);


                }
             //   Log.d("msg", "onDataChange: " + donList.size());
               getNeedy();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void getNeedy() {
        databaseReferenceNeedy.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot needySnapshot : snapshot.getChildren()) {
                    Needy needyValue = needySnapshot.getValue(Needy.class);
                   /* String id = (String) donSnapshot.getValue(Don.class).getIdDon();
                    String title = (String) donSnapshot.getValue(Don.class).getTitle();
                    String desc = (String) donSnapshot.getValue(Don.class).getDescription();
                    String health = (String) donSnapshot.getValue(Don.class).getHealthCondition();
*/
                    needyList.add(needyValue);


                }
                Log.d("msg_needy", "onDataChange: " + needyList.size());
                compareList();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void compareList() {
        for(int y=0; y<needyList.size() ; y++) {
            for (int x = 0; x < donList.size(); x++) {

                if (donList.get(x).getNumberOfPerson().equalsIgnoreCase(needyList.get(y).getNumberOfPersonNeedy())) {

                    Log.d("test1", "test1: " + donList.get(x).getNumberOfPerson() + " == " + needyList.get(y).getNumberOfPersonNeedy());
                    break;
                }/*else{
                   Log.d("test1", "test1: no matching");
                 // break;
             //   }*/

          }

        }

    }


    public void affectDonToNeedy() {

        /*databaseReferenceDon = FirebaseDatabase.getInstance().getReference("Don");
        databaseReferenceNeedy = FirebaseDatabase.getInstance().getReference("Needy");*/


        Don don = new Don();
        Needy needy = new Needy();
        databaseReferenceDonAffectedNeedy = FirebaseDatabase.getInstance().getReference("Don_affected");
        final ArrayList arrayList = new ArrayList();

        /* new Thread(new Runnable() {
            @Override
            public void run() {*/

        getDon();
        // databaseReferenceDon.addValueEventListener(eventListenerDon);
        // databaseReferenceNeedy.addValueEventListener(eventListenerNeedy);
        FirebaseDatabase db = databaseReferenceDon.getDatabase();


        // for(int i=0; i<donList.size(); i++) {

        // }

        //ArrayList<Don> donL = donList ;
            /*}
        }).start();*/


        /*databaseReferenceDon = FirebaseDatabase.getInstance().getReference("Don");
        databaseReferenceNeedy = FirebaseDatabase.getInstance().getReference("Needy");*/
        //log.d("Test","msg test" +" "+needyList.size() );
        /*donList.add(new Don("123","title1", "desc1", "no","megrine", "3-4","12.00AM",true));
        needyList.add(new Needy("133", "desc1","tunis","no", "3-4",false,true));*/



       /* for(int i=0; i<donList.size(); i++){
            Log.d("Test","msg test" +" "+donList.size() );*/

          /*  for(int j=0; j<needyList.size(); j++){

                if(donList.get(i).getNumberOfPerson() == needyList.get(j).getNumberOfPersonNeedy()){

                    databaseReferenceDonAffectedNeedy.child(donList.get(i).getIdDon()).child(needyList.get(j).getIdNeedy()).setValue(true);
                }
            }*/
    }
        /*ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

              *//*  for(DataSnapshot donSnapshot : dataSnapshot.getChildren()) {

                        DonAffected donValue = donSnapshot.child("Don_affected").getValue(DonAffected.class);
                        Don don = donSnapshot.child("Don").getValue(Don.class);
                        Log.d("Test","msg test" +" " +don);

                }*//*
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };*/
}




