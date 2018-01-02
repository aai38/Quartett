package com.example.karo.quartett;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by karo on 14.11.17.
 */

public class ListAdapter extends ArrayAdapter<valuepair> {
    private List<valuepair> lists;
    public ListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ListAdapter(Context context, int resource, List<valuepair> items) {
        super(context, resource, items);
        this.lists = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.list, null);
        }

        valuepair p = lists.get(position);

        if (p != null) {
            TextView tt1 =  v.findViewById(R.id.edt);
            TextView tt2 =  v.findViewById(R.id.txt);

            if (tt1 != null) {
                tt1.setText(lists.get(position).value);
            }

            if (tt2 != null) {
                tt2.setText(lists.get(position).value_name);
            }

        }

        return v;
    }

}



