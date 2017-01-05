package com.example.simon.dycard.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.simon.dycard.R;
import com.example.simon.dycard.model.Commande;
import com.example.simon.dycard.model.User;
import com.example.simon.dycard.util.MySingleton;

public class activity_validation extends AppCompatActivity {

    private ImageView faceAvant;
    private TextView texte;
    private User user;
    private Commande commande;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validation);

        user = MySingleton.getInstance(activity_validation.this).getUser();
        commande = MySingleton.getInstance(activity_validation.this).getCommande();

        faceAvant = (ImageView) findViewById(R.id.activity_validation_photo);
        texte = (TextView) findViewById(R.id.activity_validation_texte);

        faceAvant.setImageBitmap(commande.getPhoto());
        texte.setText(commande.getTexte());
    }
}
