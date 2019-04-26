package com.example.chaima.donatefood.Annoncer;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.chaima.donatefood.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NeedyFragmentPA extends NeedyFragment {


    public NeedyFragmentPA() {
        // Required empty public constructor
    }
  //  FloatingActionButton fabAdd, fabAddNeedy  ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        fabAddNeedy = (FloatingActionButton) rootView.findViewById(R.id.fabAddNeedy);

        fabAddNeedy.show();
        fabAddNeedy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SaveNeedyActivity.class);
                getActivity().startActivity(intent);
            }
        });

        return rootView ;
    }

}
