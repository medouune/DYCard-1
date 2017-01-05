package com.example.simon.dycard.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.simon.dycard.R;

public class activity_ajoutdestinataire extends AppCompatActivity {
 // test 3 master
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajoutdestinataire);
    }

    public void ajouterCeDestinataire(View v) {
        Intent intent = new Intent(activity_ajoutdestinataire.this, activity_destinataire.class);
        startActivity(intent);
    }
}
