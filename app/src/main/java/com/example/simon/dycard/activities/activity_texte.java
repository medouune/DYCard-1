package com.example.simon.dycard.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.simon.dycard.R;
import com.example.simon.dycard.util.MySingleton;

public class activity_texte extends AppCompatActivity {

    private EditText texte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_texte);

        texte = (EditText) findViewById(R.id.activity_texte_texte);
    }

    public void texteSuivant(View v) {

        MySingleton.getInstance(activity_texte.this).getCommande().setTexte(texte.getText().toString());

        Intent intent = new Intent(activity_texte.this, activity_destinataire.class);
        startActivity(intent);
    }
    public void textePrecedent(View v) {
        finish();
    }
}
