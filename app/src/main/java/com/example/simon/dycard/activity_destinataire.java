package com.example.simon.dycard;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class activity_destinataire extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destinataire);
    }
    public void ajouterUnNouveauDestinataire(View v) {
        Intent intent = new Intent(activity_destinataire.this, activity_ajoutdestinataire.class);
        startActivity(intent);
    }
    /*public void envoyerDestinataire(View v) {
        Intent intent = new Intent(activity_destinataire.this, activity_recap.class);
        startActivity(intent);
    }*/

    public void supprimerDestinataireHistorique(View v) {
        new AlertDialog.Builder(this)
                .setMessage(R.string.popupSupprimerDestinataire)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }

    public void envoyerDestinataire(View v) {
        new AlertDialog.Builder(this)
                .setMessage(R.string.popupDestinataire)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }


    public void precedent(View v) {
        Intent intent = new Intent(activity_destinataire.this, activity_texte.class);
        startActivity(intent);
    }




}
