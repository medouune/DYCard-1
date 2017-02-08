package com.example.simon.dycard.util;

import android.app.Activity;
import android.app.IntentService;
import android.media.Image;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.simon.dycard.R;

/**
 * Created by Simon on 01/02/2017.
 */

public class CustomListFormeAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final Integer[] imgId;

    public CustomListFormeAdapter(Activity context, Integer[] imgId) {
        super(context, R.layout.forme_ligne_layout);

        this.context = context;
        this.imgId = imgId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.forme_ligne_layout, null);

        ImageView imageView = (ImageView)rowView.findViewById(R.id.forme);

        imageView.setImageResource(imgId[position]);

        return rowView;
    }
}
