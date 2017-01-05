package com.example.simon.dycard.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.simon.dycard.R;
import com.example.simon.dycard.model.Commande;
import com.example.simon.dycard.util.MySingleton;

public class Etape1_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etape1_);
    }

    public void creerCarte(View v) {
        Commande commande = new Commande();
        MySingleton.getInstance(Etape1_Activity.this).setCommande(commande);
        Intent intent = new Intent(Etape1_Activity.this, Images_Activity.class);
        startActivity(intent);
    }
}
