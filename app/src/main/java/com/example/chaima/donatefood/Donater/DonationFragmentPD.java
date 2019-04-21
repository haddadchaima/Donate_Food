package com.example.chaima.donatefood.Donater;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chaima.donatefood.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DonationFragmentPD extends FragmentDonation {

    FloatingActionButton  fabAddDon;

    public DonationFragmentPD() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState) ;
        //rootView = inflater.inflate(R.layout.fragment_fragment_donation, container, false);

        fabAddDon = (FloatingActionButton) rootView.findViewById(R.id.fabAdd);
        fabAddDon.show();


        fabAddDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*FragmentManager fragmentMg = getFragmentManager() ;
                FragmentTransaction fragmentTrs= fragmentMg.beginTransaction();
                fragmentTrs.add(R.id.FrameLayout_save_don, FragmentSaveDon.newInstance());
                fragmentTrs.addToBackStack(null);
                fragmentTrs.commit();*/
                Intent intent = new Intent(getActivity(), ActivitySaveDon.class);
                getActivity().startActivity(intent);

            }
        });
        return  rootView ;
    }

}
