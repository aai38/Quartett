package com.example.karo.quartett;

import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.solver.widgets.Rectangle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Spiel extends AppCompatActivity {

    ListView listView;
    private List<valuepair> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spiel);
        findViewById(R.id.spiel).setBackgroundColor(Color.parseColor("#FABE81"));
        listView = findViewById(R.id.listView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        String value = intent.getStringExtra("spielmodus");
        list = new ArrayList<>();

        list.add(new valuepair("Wert 1", "Wert 1"));
        list.add(new valuepair("Wert 2", "Wert 2"));
        ListAdapter adapter = new ListAdapter(this, R.layout.list, list);

        listView.setAdapter(adapter);
        Set numberSet = new HashSet();
        while(numberSet.size()<=16){
            numberSet.add(getZuffalszahl());  //die Zahl wird nur hinzugefügt wenn sie noch nicht besteht
        }

        if(value.equals("runden")) {

        } else if(value.equals("zeitlimit")) {

        } else if(value.equals("death match")) {

        }
    }

    public static int getZuffalszahl( ){
        int zufallszahl;
        zufallszahl=(int)((Math.random())*31+1);
        return zufallszahl;}
}
