package com.example.chaima.donatefood;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.InputStream;
import java.util.Queue;

public class ReadCities {


    /*private final Queue<String> countryListArray;

   try {
        JSONArray jArray = new JSONArray(readJSONFromAsset());
        for (int i = 0; i < jArray.length(); ++i) {
            String name = jArray.getJSONObject(i).getString("name");// name of the country
            String dial_code = jArray.getJSONObject(i).getString("dial_code"); // dial code of the country
            String code = jArray.getJSONObject(i).getString("code"); // code of the country
            countryListArray.add(dial_code + "  (" + name + ")");
        }
    } catch (JSONException e) {
        e.printStackTrace();
    }

    public String readJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("country_list.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }*/
}
