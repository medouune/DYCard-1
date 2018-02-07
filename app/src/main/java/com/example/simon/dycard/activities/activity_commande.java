package com.example.simon.dycard.activities;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class activity_commande extends AppCompatActivity {

    private String RECUP_CMD_URL = "http://dycardfrcw.cluster023.hosting.ovh.net/WebServiceDYCard/enregistrer_commande.php";
    private Commande commande;
    private EditText Prix;
    private ImageView faceAvant, formeFinale;
    private ListView listView;
    private TextView text;
    private EditText Photo, Texte, Forme, Format, ET;
    private String pseudo, texte, forme, ansCaptcha, ansUser;
    private AlertDialog.Builder builder;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commande);

        commande = MySingleton.getInstance(activity_commande.this).getCommande();
        //int forme pour récupérer la forme R.drawable en fonction de ce que la personne a choisi
        //bcommande.getforme(lien vers la forme choisie au départ)

        faceAvant = (ImageView) findViewById(R.id.activity_validation_photo);
        formeFinale = (ImageView) findViewById(R.id.forme);
        text = (TextView) findViewById(R.id.activity_validation_texte);

        //récupérer la commande finale (par rapport à la forme
        Bitmap shape = BitmapFactory.decodeResource(getResources(), commande.getFormes());
        //redonner une taille à un bitmap
        shape = Bitmap.createScaledBitmap(shape, 255, 255, false);

        Bitmap image = Bitmap.createScaledBitmap(commande.getPhoto(), 255, 255, false);
        faceAvant.setBackground(new BitmapDrawable(getResources(), image));
        faceAvant.setImageBitmap(shape);
       // text.setTexte(commande.getTexte());
        mContext = getApplicationContext();
        builder = new AlertDialog.Builder(mContext);

        Photo = (EditText)findViewById(R.id.inscription_editText_pseudo);
        Texte = (EditText)findViewById(R.id.inscription_editText_password);
        Forme = (EditText)findViewById(R.id.inscription_editText_password2);
        Format = (EditText)findViewById(R.id.inscription_editText_email);

        ET = (EditText) findViewById(R.id.ansUser);

    }
    public void modifierparametres(View v) {
        Intent intent = new Intent(activity_commande.this, Images_Activity.class);
        startActivity(intent);
    }

    public void recupCmd(){
        final ArrayList<Commande> commandes = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, RECUP_CMD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONArray jsonArray = new JSONArray(response);
                            for(int i=0; i<jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Commande c = new Commande();
                                //c.setPhoto(jsonObject.getInt("id"));
                                c.setTexte(jsonObject.getString("texte"));
                                c.setForme(jsonObject.getString("forme"));
                                c.setFormat(jsonObject.getString("format"));
                                Log.e("log pour dest",c.toString());
                                commandes.add(c);
                            }

                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                       // majList(commandes);
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

    public void envoyer(View view) {


    }

}
