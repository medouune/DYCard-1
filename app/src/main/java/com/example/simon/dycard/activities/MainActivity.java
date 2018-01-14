package com.example.simon.dycard.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.simon.dycard.R;

public class MainActivity extends AppCompatActivity {

    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        builder = new AlertDialog.Builder(this);
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

    public void cg(View v){
        displayAlert(getResources().getString(R.string.termsOfUse));
    }

    public void displayAlert(String message) {
        builder.setMessage(message);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
