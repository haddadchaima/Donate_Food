package com.example.chaima.donatefood;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.prefs.Preferences;

public class Main2Activity extends AppCompatActivity {

    private RecyclerView recyclerViewNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_gouver);

        ArrayList<String> mOrderList = new ArrayList<String>();

       // mOrderList= Preferences.getArrayPrefs("GouverChecked",this);

        SharedPreferences prefs = this.getSharedPreferences("MyPref", 0);
       String from = prefs.getString("from", null);
        String to =prefs.getString("to", null);
      // Boolean gouv =  prefs.getBoolean("every_gouver", false );
       String gouvchk =  prefs.getString("Gouver", null );

        Toast.makeText(Main2Activity.this, "data" + from + " " +to +" " +gouvchk, Toast.LENGTH_LONG).show();


        /*recyclerViewNames = (RecyclerView) findViewById(R.id.recycler_view_gouver);

        recyclerViewNames.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewNames.setHasFixedSize(true);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);
        recyclerViewNames.addItemDecoration(dividerItemDecoration);
        try {
            String jsonValue = getCountriesFromAssetJSONFile("citiesByGouvernorat.json", this);
            ArrayList<Gouvernorat> gouverArrayList = new ArrayList<>();
            JSONArray jsonArrayGouver = new JSONArray(jsonValue);
            for (int i = 0; i < jsonArrayGouver.length(); i++) {
                JSONObject jsonObject = jsonArrayGouver.getJSONObject(i);
                Gouvernorat gouvernorat = new Gouvernorat();
                //gouvernorat.setGouver_id(jsonObject.getString("capital"));
                gouvernorat.setGouver_Name(jsonObject.getString("gouvernorat"));
                gouverArrayList.add(gouvernorat);
                Log.d("json", "json: " + "reusssite");
            }
            GouvernoratAdapter gouverAdapter = new GouvernoratAdapter(gouverArrayList);
            gouverAdapter.setOnItemClickListener(new GouvernoratAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Gouvernorat gouver) {
                    Toast.makeText(Main2Activity.this, gouver.getGouver_Name() , Toast.LENGTH_SHORT).show();
                    //Log.d("json", "json: " + "reusssite");
                }
            });
            recyclerViewNames.setAdapter(gouverAdapter);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        recyclerViewNames.setHasFixedSize(true);
        recyclerViewNames.setLayoutManager(new LinearLayoutManager(this));*/
    }

    /*public static String getCountriesFromAssetJSONFile(String filename, Context context) throws IOException {
        AssetManager manager = context.getAssets();
        InputStream file = manager.open(filename);
        byte[] formArray = new byte[file.available()];
        file.read(formArray);
        file.close();
        return new String(formArray);
    }*/


    public static ArrayList<String> getArrayPrefs(String arrayName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("MyPref", 0);
        int size = prefs.getInt(arrayName + "_size", 0);
        ArrayList<String> array = new ArrayList<>(size);
        for(int i=0;i<size;i++)
            array.add(prefs.getString(arrayName + "_" + i, null));
        return array;
    }
}
