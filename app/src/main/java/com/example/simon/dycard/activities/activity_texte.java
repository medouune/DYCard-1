package com.example.simon.dycard.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.simon.dycard.R;
import com.example.simon.dycard.model.Commande;
import com.example.simon.dycard.util.MySingleton;

public class activity_texte extends AppCompatActivity {

    private EditText Texte;
    private Commande commande;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_texte);

        Texte = (EditText) findViewById(R.id.activity_texte_texte);

        commande = MySingleton.getInstance(activity_texte.this).getCommande();

        if(commande.getTexte()!=null)
            Texte.setText(commande.getTexte());

    }

    public void texteSuivant(View v) {

        commande.setTexte(Texte.getText().toString());

        Intent intent = new Intent(activity_texte.this, activity_destinataire.class);
        startActivity(intent);
        finish();
    }

    public void dezoom(View v){
        float size = Texte.getTextSize();
        if(size - 1 > 0)
            Texte.setTextSize(size - (float)0.2);
    }

    public void zoom(View v){
        float size = Texte.getTextSize();
        Texte.setTextSize(size + (float)0.2);
    }

    public void textePrecedent(View v) {
        startActivity(new Intent(activity_texte.this, Choose_photo_activity.class));
        finish();
    }
}
