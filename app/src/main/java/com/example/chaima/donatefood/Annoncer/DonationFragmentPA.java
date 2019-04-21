package com.example.chaima.donatefood.Annoncer;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chaima.donatefood.Donater.FragmentDonation;

/**
 * A simple {@link Fragment} subclass.
 */
public class DonationFragmentPA extends FragmentDonation {
    private FloatingActionButton fabAddDon;

    //FloatingActionButton  fabAddDon;

    public DonationFragmentPA() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

         return  rootView ;
    }



}
