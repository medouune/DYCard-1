package com.example.simon.dycard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void inscription(View v) {
        Intent intent = new Intent(this, InscriptionActivity.class);
        startActivity(intent);
    }

    public void connection(View v) {
        Intent intent = new Intent(this, ConnexionActivity.class);
        startActivity(intent);
    }

    public void connexionFacebook(View v) {
        Intent intent = new Intent(this, activity_destinataire.class);
        startActivity(intent);
    }
}
