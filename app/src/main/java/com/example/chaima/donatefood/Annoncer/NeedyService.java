package com.example.chaima.donatefood.Annoncer;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class NeedyService {

    ArrayList<Needy> needyLst = new ArrayList<Needy>();

    public DatabaseReference databaseReference;
    private Needy needyL;

    public NeedyService() {
    }



    public void getNeedyFromFirebase(ArrayList<Needy> needyList) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Needy");

       // needyLst = needyList ;
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
             //  for (DataSnapshot needySnapshot : dataSnapshot.getChildren()) {
                  needyL = (dataSnapshot.getValue(Needy.class));

              //  }

                needyLst.add(needyL);

              //  Log.d("onDataChangeNeedy", "onDataChangeNeedy: " + dataSnapshot.getValue(Needy.class));
                //needyRecyclerViewAdapter.notifyDataSetChanged();
                // while (donList.size())
                //recyclerViewAdapter.notifyItemInserted(donList.);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                // for (DataSnapshot needySnapshot : dataSnapshot.getChildren()) {

                needyLst.add(dataSnapshot.getValue(Needy.class));


                //  }

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {



            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });
    }
}
