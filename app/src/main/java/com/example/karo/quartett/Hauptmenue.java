package com.example.karo.quartett;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;

public class Hauptmenue extends AppCompatActivity {

    TextView textView;
    ImageButton imageButton;
    private static final int PICK_IMAGE = 100;
    Bitmap bitMap;
    Button spielen;
    Button rangliste;
    Button karten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hauptmenue);
        findViewById(R.id.hauptmenue).setBackgroundColor(Color.parseColor("#FABE81"));
        textView = findViewById(R.id.textView);
        imageButton = findViewById(R.id.imageButton);
        spielen = findViewById(R.id.spielen_button);
        spielen.setBackgroundColor(Color.parseColor("#F5CFAA"));
        rangliste = findViewById(R.id.rangliste_button);
        rangliste.setBackgroundColor(Color.parseColor("#F5CFAA"));
        karten = findViewById(R.id.karten_button);
        karten.setBackgroundColor(Color.parseColor("#F5CFAA"));
        setTitle("Hauptmenue");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        String value = intent.getStringExtra("name");
        textView.setText(value);
        imageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openGallery();
            }
        });
        spielen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Hauptmenue.this, Spielmodus.class));
            }
        });
        rangliste.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });
        karten.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Hauptmenue.this, Thema.class));
            }
        });
    }
    public void openGallery(){
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, PICK_IMAGE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            try {
                bitMap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            imageButton.setImageBitmap(bitMap);


        }
    }
}
