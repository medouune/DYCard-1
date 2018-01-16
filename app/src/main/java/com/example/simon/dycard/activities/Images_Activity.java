package com.example.simon.dycard.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.simon.dycard.R;
import com.example.simon.dycard.model.Commande;
import com.example.simon.dycard.util.MySingleton;

public class Images_Activity extends AppCompatActivity {

    private Commande commande;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images_);


        commande = MySingleton.getInstance(getApplicationContext()).getCommande();

    }

    public void diamant(View v){
        commande.setForme("diamant");
    }

    public void etoile(View v){
        commande.setForme("etoile");
    }

    public void coeur(View v){
        commande.setForme("coeur");
    }

    public void octo(View v){
        commande.setForme("octo");
    }

    public void cadreplantes(View v){
        commande.setForme("cadre_plantes");
    }

    public void chat(View v){
        commande.setForme("chat");
    }

    public void ovale(View v){
        commande.setForme("ovale");
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
