package com.example.simon.dycard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

public class Images_Activity extends AppCompatActivity {

    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images_);

        seekBar = (SeekBar)findViewById(R.id.seekBar);
        seekBar.setEnabled(false);
    }

    public void imagePrecedent(View v) {
        finish();
    }

    public void imageSuivant(View v) {
        Intent intent = new Intent(Images_Activity.this, PhotoActivity.class);
        startActivity(intent);
    }
}
