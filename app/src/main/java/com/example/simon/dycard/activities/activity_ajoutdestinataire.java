package com.example.simon.dycard.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class activity_ajoutdestinataire extends AppCompatActivity{

    private String AJOUT_DEST_URL = "http://192.168.1.10/WebServiceDYCard/enregistrer_destinataire.php";
//déclaration des variables pour récupérer nom,prénom, adresse,code potal, ville, pays du destinataire

    private EditText Nom, Prenom, Adresse, CodePostal, Ville, Pays;
    private String nom, prenom, adresse, codePostal, ville, pays;
    private Context mContext;
    private AlertDialog.Builder builder;
    private Button btnAjout;

    @Override
    //initialise l'activité et
    // permet de désérialiser le fichier xml d'affichage afin d'en récupérer les vues qui le composent
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajoutdestinataire);

        mContext = getApplicationContext();
        builder = new AlertDialog.Builder(mContext);

        Nom = (EditText)findViewById(R.id.destinataireNom);
        Prenom = (EditText)findViewById(R.id.destinatairePrenom);
        Adresse = (EditText)findViewById(R.id.destinataireAdresse);
        CodePostal = (EditText)findViewById(R.id.destinataireCodePostal);
        Ville = (EditText)findViewById(R.id.destinataireVille);
        Pays = (EditText)findViewById(R.id.destinatairePays);

        btnAjout = new Button(this);
        btnAjout = (Button)findViewById(R.id.btn_addRecipient);
        //btnAjout.setOnClickListener(this);
    }

    public void ajouterCeDestinataire(View v) {

        nom = Nom.getText().toString();
        prenom = Prenom.getText().toString();
        adresse = Adresse.getText().toString();
        codePostal = CodePostal.getText().toString();
        ville = Ville.getText().toString();
        pays = Pays.getText().toString();

//Si un des champs n'est pas rempli on a ce message
        if(nom.equals("") || prenom.equals("") || adresse.equals("") || codePostal.equals("") ||
                ville.equals("") || pays.equals("")) {
            Toast.makeText(mContext, getResources().getString(R.string.alerteDestinataire), Toast.LENGTH_SHORT).show();
        } else {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, AJOUT_DEST_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //
                            try{
                                JSONArray jsonArray = new JSONArray(response);
                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                String code = jsonObject.getString("status");
                                //Si le statut est ok, le destinataire est ajouté à la bdd
                                if(code.equals("OK")) {
                                    Toast.makeText(activity_ajoutdestinataire.this, R.string.ajoutDestSucces, Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                                else {
                                    //si il existe déjà dans la bdd
                                    Toast.makeText(mContext, getResources().getString(R.string.ajoutDestEchec), Toast.LENGTH_SHORT).show();
                                }
                            }catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                //Si il n' ya pas de connexion avec la base de données, on a cette erreur qui s'affiche
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(mContext, getResources().getString(R.string.erreurConnexion), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                //récupérer les différents paramètres dans une liste
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("nom", nom);
                    params.put("prenom", prenom);
                    params.put("adresse", adresse);
                    params.put("codePostal", codePostal);
                    params.put("ville", ville);
                    params.put("pays", pays);
                    params.put("idUser", String.valueOf(MySingleton.getInstance(mContext).getUser().getId()));
                    return params;
                }
            };
            MySingleton.getInstance(mContext).addToRequestque(stringRequest);

        }


    }
//permet de créer une alerte confirmant l'envoi des données
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

    public void onClick(View v) {
        if(v==btnAjout) {
            Toast.makeText(this, "action bouton", Toast.LENGTH_SHORT).show();
        }
    }
}
