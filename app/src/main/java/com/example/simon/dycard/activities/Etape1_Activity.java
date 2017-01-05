package com.example.simon.dycard.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.simon.dycard.R;

public class Etape1_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etape1_);
    }

    public void creerCarte(View v) {
        Intent intent = new Intent(Etape1_Activity.this, Images_Activity.class);
        startActivity(intent);
    }
}
