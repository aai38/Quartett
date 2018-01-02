package com.example.karo.quartett;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Thema extends AppCompatActivity {

    private List list;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thema);

        findViewById(R.id.thema).setBackgroundColor(Color.parseColor("#FABE81"));
        listView = findViewById(R.id.listView2);
    }

    protected void onResume() {
        super.onResume();
        list = new ArrayList<>();

        list.add("Bikes");
        list.add("Tuning");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list );

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
        {
            Intent intent = new Intent(Thema.this, Kartenansicht.class);
            Log.d("mylog", listView.getAdapter().getItem(arg2).toString());

            intent.putExtra("deck", listView.getAdapter().getItem(arg2).toString());
            startActivity(intent);
        }
    });
    }
}
