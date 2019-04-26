package com.example.chaima.donatefood;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import static android.content.ContentValues.TAG;

public class GouvernoratDialogFragment extends DialogFragment {

    private RecyclerView recyclerViewNames;
    ArrayList<Gouvernorat> gouverArrayList;
    JSONArray jsonArrayGouver ;
    Gouvernorat gouvernorat ;

    public GouvernoratDialogFragment() {
        // Empty constructor required for DialogFragment
    }

    public static GouvernoratDialogFragment newInstance(String title) {
        GouvernoratDialogFragment frag = new GouvernoratDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }



    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        recyclerViewNames = new RecyclerView(getContext());
        // you can use LayoutInflater.from(getContext()).inflate(...) if you have xml layout
        recyclerViewNames.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL);
        recyclerViewNames.addItemDecoration(dividerItemDecoration);
        try {
            String jsonValue = getCountriesFromAssetJSONFile("citiesByGouvernorat.json", getActivity());
             gouverArrayList = new ArrayList<>();
             ArrayList<Gouvernorat> gouverArrayList0 = new ArrayList<>();
             jsonArrayGouver = new JSONArray(jsonValue);


            for (int i = 0; i < jsonArrayGouver.length(); i++) {
                JSONObject jsonObject = jsonArrayGouver.getJSONObject(i);
                gouvernorat = new Gouvernorat();
                gouvernorat.setGouver_Name(jsonObject.getString("gouvernorat"));
//                if(!gouvernorat.getGouver_Name().contains(jsonObject.getString("gouvernorat"))) {

                    gouverArrayList.add(gouvernorat);


                    Log.d("duplicateee", "duplicateee: " + gouverArrayList.toString());
               // }
              //  ArrayList l1 = new ArrayList();
                /*ArrayList l2 = new ArrayList();

                Iterator iterator = gouverArrayList0.iterator();

                while (iterator.hasNext())
                {
                    Gouvernorat o = (Gouvernorat) iterator.next();
                    if(!gouverArrayList.contains(o.getGouver_Name())) gouverArrayList.add(o);    //Log.d("duplicateee", "duplicateee: " + l2.toString());

                }*/
                /*for (int j = 0; j < gouverArrayList0.size(); j++) {
                    for (int x = j + 1; x < gouverArrayList0.size(); x++) {
                        if(gouverArrayList0.get(j).getGouver_Name() != gouverArrayList0.get(x).getGouver_Name()){
                            Log.d("duplicateee", "duplicateee: " + gouverArrayList0.get(x).getGouver_Name());
                        }

                        // if(!gouverArrayList0.get(i).getGouver_Name().contains(gouvernorat.getGouver_Name())){
                        //Log.d("duplicateee", "duplicateee: " + gouverArrayList0.get(i).getGouver_Name());
                        //  Toast.makeText(getActivity(), gouvernorat.getGouver_Name(), Toast.LENGTH_SHORT).show();
                        //  gouverArrayList.remove(i);
                    }
                }*/

                /*HashSet hs = new HashSet();
                hs.addAll(gouverArrayList);
                gouverArrayList.clear();
                gouverArrayList.addAll(hs);*/

            }
            GouvernoratAdapter gouverAdapter = new GouvernoratAdapter(gouverArrayList);
            gouverAdapter.setOnItemClickListener(new GouvernoratAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Gouvernorat gouver) {
                    Toast.makeText(getActivity(), gouver.getGouver_Name(), Toast.LENGTH_SHORT).show();
                    Log.d("onDataChange", "onDataChange: " + "reusssite");
                }
            });
            recyclerViewNames.setAdapter(gouverAdapter);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        recyclerViewNames.setHasFixedSize(true);
        recyclerViewNames.setLayoutManager(new LinearLayoutManager(getActivity()));


                AlertDialog.Builder dialog =  new AlertDialog.Builder(getActivity()) ;
                    dialog.setTitle("Select Gouvernorate");
                    dialog.setView(recyclerViewNames);
                    dialog.setPositiveButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {

                                    // do something
                                    /*CheckBox chk = (CheckBox) recyclerViewNames.getRootView().findViewById(R.id.chkbox_gouver);
                                    if(chk.isChecked()){
                                       // int j = 0;
                                        //if(chkItemGouver.isChecked()){
                                        gouvernorat.setSelected(true);
                                        Toast.makeText(getActivity(), "item checked:"+ chk.getText() , Toast.LENGTH_LONG).show();
                                      //  j++ ;
                                        // }
                                    }*/
                                }
                            }
                    );
                    dialog.setPositiveButton("OK",  new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            final CheckBox chk = (CheckBox) recyclerViewNames.getRootView().findViewById(R.id.chkbox_gouver);

                            

                            chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                    Boolean check = chk.isChecked() ;
                                    gouvernorat.setSelected(true);
                                    Toast.makeText(getActivity(), "item checked:"+ chk.getText() , Toast.LENGTH_LONG).show();
                                }
                            });


                           // if(chk.isChecked()){
                                // int j = 0;
                                //if(chkItemGouver.isChecked()){
                                //gouvernorat.setSelected(true);
                                Toast.makeText(getActivity(), "item checked:"+ chk.getText() , Toast.LENGTH_LONG).show();
                                //  j++ ;
                                // }
                          //  }

                        }
                    });
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (dialog != null ) {
                    dialog.dismiss();
                }
            }

        });

                    return dialog.create();
        }

    public static String getCountriesFromAssetJSONFile(String filename, Context context) throws IOException {
        AssetManager manager = context.getAssets();
        InputStream file = manager.open(filename);
        byte[] formArray = new byte[file.available()];
        file.read(formArray);
        file.close();
        return new String(formArray);
    }

    public boolean isExist(String strNama) throws JSONException {

        for (int j = 0; j < gouverArrayList.size(); j++) {
            if (gouverArrayList.get(j).equals(strNama)) {
                return true;
            }
        }

        return false;
    }

    public static boolean hasDuplicates(ArrayList<Gouvernorat> list) {
        for (int i = 0; i < list.size(); i++) {
            // Loop over all following elements.
            for (int x = i + 1; x < list.size(); x++) {
                // If two elements equal, there is a duplicate.
                if (list.get(i) == list.get(x)) {
                    return true;
                }
            }

        }
        // No duplicates found.
        return false;
    }
    
}


