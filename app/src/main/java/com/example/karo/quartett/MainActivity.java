package com.example.karo.quartett;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button weiter;
    EditText name_textfield;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.constraint).setBackgroundColor(Color.parseColor("#FABE81"));

        weiter = findViewById(R.id.weiter_button);
        weiter.setBackgroundColor(Color.parseColor("#F5CFAA"));
        name_textfield = findViewById(R.id.name_textfield);
    }

    @Override
    protected void onResume() {
        super.onResume();
        weiter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(name_textfield.getText().toString().isEmpty()) {
                    Context context = getApplicationContext();
                    CharSequence text = "Bitte Namen eingeben";
                    int duration = Toast.LENGTH_SHORT;


                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else {
                    Intent intent = new Intent(MainActivity.this, Hauptmenue.class);
                    intent.putExtra("name", name_textfield.getText().toString());
                    startActivity(intent);
                }

            }

        });

    }
}
