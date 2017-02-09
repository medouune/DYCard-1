package com.example.simon.dycard.activities;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import com.example.simon.dycard.R;
import com.example.simon.dycard.util.CustomListFormeAdapter;

public class Images_Activity extends AppCompatActivity {

    private ListView listeForme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images_);
    }


    public void imagePrecedent(View v) {
        startActivity(new Intent(Images_Activity.this, Etape1_Activity.class));
        finish();
    }

    public void imageSuivant(View v) {
        Intent intent = new Intent(Images_Activity.this, Choose_photo_activity.class);
        startActivity(intent);
        finish();
    }
}
