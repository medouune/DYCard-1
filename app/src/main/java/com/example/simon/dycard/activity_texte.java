package com.example.simon.dycard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class activity_texte extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_texte);
    }

    public void precedent(View v) {
        Intent intent = new Intent(this, activity_choose_photo.class);
        startActivity(intent);

    }
        public void suivant(View v){
            Intent intent = new Intent(this, activity_destinataire.class);
            startActivity(intent);

        }
}
