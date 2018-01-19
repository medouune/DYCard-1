package com.example.simon.dycard.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.simon.dycard.R;
import com.example.simon.dycard.model.Commande;
import com.example.simon.dycard.model.Destinataire;
import com.example.simon.dycard.util.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class activity_destinataire extends AppCompatActivity {

    private String RECUP_DEST_URL = "http://192.168.0.11/DYCard/WebServiceDYCard/recuperer_destinataires.php";
    private Context mContext;
    private ListView lv;
    private Commande commande;
    private EditText Prix;

    @Override
    //methode
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destinataire);

        mContext = getApplicationContext();

        commande = MySingleton.getInstance(mContext).getCommande();

        Prix = (EditText)findViewById(R.id.etPrix);
        Prix.setText(String.valueOf(commande.getPrix()));

        lv= (ListView) findViewById(R.id.lvDestinataires);

        recupDest();
    }

    public void majList(ArrayList<Destinataire> dest){
        ArrayAdapter<Destinataire> adapter = new ArrayAdapter<>(mContext, R.layout.layout_destinataire, dest);
        lv.setAdapter(adapter);
    }

    public void recupDest(){
        final ArrayList<Destinataire> destinataires = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, RECUP_DEST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONArray jsonArray = new JSONArray(response);
                            for(int i=0; i<jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Destinataire d = new Destinataire();
                                d.setId(jsonObject.getInt("id"));
                                d.setAdresse(jsonObject.getString("adresse"));
                                d.setPrenom(jsonObject.getString("prenom"));
                                d.setNom(jsonObject.getString("nom"));
                                destinataires.add(d);
                            }

                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                        majList(destinataires);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mContext, getResources().getString(R.string.erreurConnexion), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("idUser", String.valueOf(MySingleton.getInstance(mContext).getUser().getId()));
                return params;
            }
        };
        MySingleton.getInstance(mContext).addToRequestque(stringRequest);
    }

    public void ajouterUnNouveauDestinataire(View v) {
        Intent intent = new Intent(activity_destinataire.this, activity_ajoutdestinataire.class);
        startActivity(intent);
    }

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

    public void precedent(View v) {
        startActivity(new Intent(activity_destinataire.this, activity_texte.class));
        finish();
    }

    public void suivantActivityDestinataire(View v) {
        Intent intentSuivant = new Intent(activity_destinataire.this, activity_validation.class);
        startActivity(intentSuivant);
    }

}
