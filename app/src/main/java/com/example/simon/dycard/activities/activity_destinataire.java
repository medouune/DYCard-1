package com.example.simon.dycard.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class activity_destinataire extends AppCompatActivity {

    private String RECUP_DEST_URL = "http://192.168.1.34/DYCard/WebServiceDYCard/recuperer_destinataires.php";
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destinataire);

        mContext = getApplicationContext();

        new GetContacts().execute();
    }

    private List<Destinataire> recupDestinataire(int idUser){
        List<Destinataire> destinataires = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, RECUP_DEST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String code = jsonObject.getString("code");
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }

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

        return destinataires;
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

    private class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(mContext, R.string.chargementDest, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

}
