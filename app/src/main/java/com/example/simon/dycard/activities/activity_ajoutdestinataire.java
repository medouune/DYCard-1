package com.example.simon.dycard.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.simon.dycard.R;
import com.example.simon.dycard.model.Destinataire;
import com.example.simon.dycard.util.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class activity_ajoutdestinataire extends AppCompatActivity {

    private String AJOUT_DEST_URL = "http://192.168.0.37/DYCard/WebServiceDYCard/enregistrer_destinataire";
    private EditText Nom, Prenom, Adresse, CodePostal, Ville, Pays;
    private String nom, prenom, adresse, codePostal, ville, pays;
    private AlertDialog.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajoutdestinataire);

        Nom = (EditText)findViewById(R.id.destinataireNom);
        Prenom = (EditText)findViewById(R.id.destinatairePrenom);
        Adresse = (EditText)findViewById(R.id.destinataireAdresse);
        CodePostal = (EditText)findViewById(R.id.destinataireCodePostal);
        Ville = (EditText)findViewById(R.id.destinataireVille);
        Pays = (EditText)findViewById(R.id.destinatairePays);
    }

    public void ajouterCeDestinataire(View v) {

        nom = Nom.getText().toString();
        prenom = Prenom.getText().toString();
        adresse = Adresse.getText().toString();
        codePostal = CodePostal.getText().toString();
        ville = Ville.getText().toString();
        pays = Pays.getText().toString();


        if(nom.equals("") || prenom.equals("") || adresse.equals("") || codePostal.equals("") ||
                ville.equals("") || pays.equals("")) {

            displayAlert(getResources().getString(R.string.alerteDestinataire));

        } else {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, AJOUT_DEST_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try{
                                JSONArray jsonArray = new JSONArray(response);
                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                String code = jsonObject.getString("status");
                                if(code.equals("OK")) {
                                    Toast.makeText(activity_ajoutdestinataire.this, R.string.ajoutDestSucces, Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                                else {
                                    displayAlert(getResources().getString(R.string.ajoutDestEchec));
                                }
                            }catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    displayAlert(getResources().getString(R.string.erreurConnexion));
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("nom", nom);
                    params.put("prenom", prenom);
                    params.put("adresse", adresse);
                    params.put("codePostal", codePostal);
                    params.put("ville", ville);
                    params.put("pays", pays);
                    params.put("idUser", String.valueOf(MySingleton.getInstance(activity_ajoutdestinataire.this).getUser().getId()));
                    return params;
                }
            };
            MySingleton.getInstance(activity_ajoutdestinataire.this).addToRequestque(stringRequest);

        }


    }

    public void displayAlert(final String message) {
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
