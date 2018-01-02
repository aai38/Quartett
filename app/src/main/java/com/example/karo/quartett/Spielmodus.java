package com.example.karo.quartett;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Spielmodus extends AppCompatActivity {

    Button runden;
    Button zeitlimit;
    Button death;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spielmodus);
        findViewById(R.id.spielmodus).setBackgroundColor(Color.parseColor("#FABE81"));
        runden = findViewById(R.id.runden);
        runden.setBackgroundColor(Color.parseColor("#F5CFAA"));
        zeitlimit = findViewById(R.id.zeitlimit);
        zeitlimit.setBackgroundColor(Color.parseColor("#F5CFAA"));
        death = findViewById(R.id.death);
        death.setBackgroundColor(Color.parseColor("#F5CFAA"));
        setTitle("Spielmodus");
    }
    @Override
    protected void onResume(){
        super.onResume();
        runden.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Spielmodus.this, Spiel.class);
                intent.putExtra("spielmodus", "runden");
                startActivity(intent);
            }
        });
        zeitlimit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Spielmodus.this, Spiel.class);
                intent.putExtra("spielmodus", "zeitlimit");
                startActivity(intent);
            }
        });
        death.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Spielmodus.this, Spiel.class);
                intent.putExtra("spielmodus", "death match");
                startActivity(intent);
            }
        });
    }
}
