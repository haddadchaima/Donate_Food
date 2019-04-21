package com.example.chaima.donatefood.Donater;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chaima.donatefood.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentDonation.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentDonation#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentDonation extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    protected static final String ARG_PARAM1 = "param1";
    protected static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    protected String mParam1;
    protected String mParam2;

    TextView txtHello;

    RecyclerView donRecyclerView;

    ArrayList<Don> donList = new ArrayList<>();

    protected OnFragmentInteractionListener mListener;

    protected DatabaseReference databaseReference;
    protected DonRecyclerViewAdapter recyclerViewAdapter;
    // protected DonRecyclerViewAdapter adapter;

    //protected FloatingActionButton  fabAddDon;

    protected  View rootView ;

    public FragmentDonation() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentDonation.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentDonation newInstance(String param1, String param2) {
        FragmentDonation fragment = new FragmentDonation();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static FragmentDonation newInstance(){
        return new FragmentDonation();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
         rootView = inflater.inflate(R.layout.fragment_fragment_donation, container, false);

        //fabAddDon = (FloatingActionButton) rootView.findViewById(R.id.fabAdd);
        //
        databaseReference = FirebaseDatabase.getInstance().getReference("Don");

        // fltActBtnAddDon = (FloatingActionButton) rootView.findViewById(R.id.fab);


        donRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycle);

        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        donRecyclerView.setLayoutManager(recyclerLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(donRecyclerView.getContext(), recyclerLayoutManager.getOrientation());
        donRecyclerView.addItemDecoration(dividerItemDecoration);


        recyclerViewAdapter = new DonRecyclerViewAdapter(getDon(), getActivity());
        donRecyclerView.setAdapter(recyclerViewAdapter);




        return rootView;
    }


    protected ArrayList<Don> getDon() {

        donList = new ArrayList<Don>();
        getDonFromFirebase();
        return donList;
    }

   /* protected void fetchData(DataSnapshot dataSnapshot)
    {
        donList.clear();
        for (DataSnapshot donSnapshot : dataSnapshot.getChildren())
        {
            donList.add(donSnapshot.getValue(Don.class));
        }
    }*/



    /*protected void getAllTask(DataSnapshot dataSnapshot){
        for(DataSnapshot donSnapshot : dataSnapshot.getChildren()){

            donList.add(donSnapshot.getValue(Don.class));
        }
        DonRecyclerViewAdapter recyclerViewAdapter = new DonRecyclerViewAdapter(donList, getActivity());
        donRecyclerView.setAdapter(recyclerViewAdapter);

    }*/


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
      /*  if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    protected void getDonFromFirebase() {

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                for (DataSnapshot donSnapshot : dataSnapshot.getChildren()) {
                    //   donList.add(donSnapshot.getValue(Don.class));
                    Log.d("onDataChange", "onDataChange: " + donSnapshot.toString());

                }
                donList.add(dataSnapshot.getValue(Don.class));
                recyclerViewAdapter.notifyDataSetChanged();
               // while (donList.size())
                //recyclerViewAdapter.notifyItemInserted(donList.);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                for (DataSnapshot donSnapshot : dataSnapshot.getChildren()) {


                    /*String  lastKey = donSnapshot.getKey();
                    String lastDon = donSnapshot.getValue(String.class);*/
                }
                    donList.add(dataSnapshot.getValue(Don.class));
                    //Toast.makeText(getActivity(), "Success " + dataSnapshot.getValue(Don.class), Toast.LENGTH_SHORT).show();



                recyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {



            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.i(TAG, "Error trying to get classified ads for " + " " + databaseError);
                Toast.makeText(getActivity(), "Error trying to get don for ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*@Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }*/

}
