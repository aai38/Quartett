package com.example.karo.quartett;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by karo on 18.12.17.
 */

public class Kartenansicht extends AppCompatActivity {

    private ListView listView;
    private List list;
    String value;
    InputStream fis;
    ArrayList<HashMap<String, String>> cardList;
    ArrayList<HashMap<String, String>> propertyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kartenansicht);
        findViewById(R.id.kartenansicht).setBackgroundColor(Color.parseColor("#FABE81"));
        listView = findViewById(R.id.listView2);
        Intent intent = getIntent();
        value = intent.getStringExtra("deck");

    }

    @Override
    protected void onResume() {
        super.onResume();

        list = new ArrayList<>();
        cardList = new ArrayList<>();
        propertyList = new ArrayList<>();

        if (value.equals("Bikes")) {
            this.setTitle("Bikes");
            try {
                cardList = getCards( "bikes.json");
                propertyList = getPropertys( "bikes.json");
            } catch (IOException e) {
                e.printStackTrace();
            }

            for(int k = 0; k < propertyList.size(); k++) {
                String s = "propertyId" + String.valueOf(k);
                String text = "text" + String.valueOf(k);
                list.add(new valuepair(propertyList.get(k).get(text), cardList.get(0).get(s)));
            }
            ListAdapter adapter = new ListAdapter(this, R.layout.list, list);
            listView.setAdapter(adapter);

        } else if (value.equals("Tuning")) {
            this.setTitle("Tuning");
            try {
                cardList = getCards( "tuning.json");
                propertyList = getPropertys("tuning.json");
            } catch (IOException e) {
                e.printStackTrace();
            }

            for(int k = 0; k < propertyList.size(); k++) {
                String s = "propertyId" + String.valueOf(k);
                String text = "text" + String.valueOf(k);
                list.add(new valuepair(propertyList.get(k).get(text), cardList.get(0).get(s)));
            }
            ListAdapter adapter = new ListAdapter(this, R.layout.list, list);
            listView.setAdapter(adapter);
        }
    }

    protected ArrayList<HashMap<String, String>> getCards(String datei) throws IOException {
        JsonReader jsonReader = new JsonReader();
        AssetManager assetManager = getAssets();
        fis = assetManager.open(datei);
        //fis = new FileInputStream(datei);
        String jsonStr = null;
        try {
            jsonStr = jsonReader.readJsonStream(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.e("json", "Response from url: " + jsonStr);

        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);

                // Getting JSON Array node
                JSONArray cards = jsonObj.getJSONArray("cards");



                // looping through All Cards
                for (int i = 0; i < cards.length(); i++) {
                    JSONObject c = cards.getJSONObject(i);

                    JSONArray values = c.getJSONArray("values");
                    JSONArray images = c.getJSONArray("images");

                    String name = c.getString("name");
                    String id = c.getString("id");
                    String description = c.getString("description");

                    // tmp hash map for single value (propertyId => value)
                    HashMap<String, String> card = new HashMap<>();
                    for(int v = 0; v < values.length(); v++) {
                        JSONObject e = values.getJSONObject(v);
                        String propertyID = "propertyId" + e.getString("propertyId");
                        card.put(propertyID, e.getString("value"));
                    }

                    // tmp hash map for single image (id => filename)
                    for ( int j = 0; j < images.length(); j++)  {
                        JSONObject img = images.getJSONObject(j);
                        String imageId = "id" + img.getString("id");
                        card.put(imageId, img.getString("filename"));
                    }

                    // adding each child node to HashMap key => value
                    card.put("id", id);
                    card.put("name", name);
                    card.put("description", description);

                    // adding card to card list
                    cardList.add(card);

                }
                return cardList;

            } catch (final JSONException e) {
                Log.e("error", "Json parsing error: " + e.getMessage());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Json parsing error: " + e.getMessage(),
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }
        } else {
            Log.e("error", "Couldn't get json from server.");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(),
                            "Couldn't get json from server. Check LogCat for possible errors!",
                            Toast.LENGTH_LONG)
                            .show();
                }
            });

        }
        return null;

    }

    protected ArrayList<HashMap<String, String>> getPropertys(String datei) throws IOException {
        JsonReader jsonReader = new JsonReader();
        try {
            fis = getAssets().open(datei);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String jsonStr = null;

        try {
            jsonStr = jsonReader.readJsonStream(fis);
            Log.d("json", jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.e("json", "Response from url: " + jsonStr);

        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);

                // Getting JSON Array node
                JSONArray properties = jsonObj.getJSONArray("properties");


                HashMap<String, String> property = new HashMap<>();
                // looping through properties
                for(int l = 0; l < properties.length(); l++) {
                    JSONObject prop = properties.getJSONObject(l);
                    String text = "text" + l;
                    property.put(text, prop.getString("text"));
                    String compare = "compare" + l;
                    property.put(compare, prop.getString("compare"));
                    String id = "id" + l;
                    property.put(id, prop.getString("id"));
                    String unit = "unit" + l;
                    property.put(unit, prop.getString("unit"));
                    String precision = "presicion" + l;
                    property.put(precision, prop.getString("precision"));

                    propertyList.add(property);
                }



                return propertyList;

            } catch (final JSONException e) {
                Log.e("error", "Json parsing error: " + e.getMessage());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Json parsing error: " + e.getMessage(),
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }
        } else {
            Log.e("error", "Couldn't get json from server.");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(),
                            "Couldn't get json from server. Check LogCat for possible errors!",
                            Toast.LENGTH_LONG)
                            .show();
                }
            });

        }
        return null;


    }
}


