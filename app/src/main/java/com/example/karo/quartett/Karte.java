package com.example.karo.quartett;

import android.widget.ImageView;

/**
 * Created by karo on 14.12.17.
 */

public class Karte {
    String name;
    String[] value_name;
    String[] value;
    ImageView imageView;
}

class valuepair {
    String value_name;
    String value;
    public valuepair (String value_name, String value) {
        this.value = value;
        this.value_name = value_name;
    }
}
