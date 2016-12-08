package com.example.simon.dycard;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ConnexionActivity extends AppCompatActivity {

    private EditText Pseudo, Password;
    private String pseudo, password;
    private String login_url = "/login.php";
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        builder = new AlertDialog.Builder(this);
        Pseudo = (EditText)findViewById(R.id.etConPseudo);
        Password = (EditText)findViewById(R.id.etConPassword);
    }

    public void login(View v) {
        pseudo = Pseudo.getText().toString();
        password = Password.getText().toString();

        if(pseudo.equals("") || password.equals("")) {
            builder.setTitle(getString(R.string.erreurFormulaire));
            displayAlert(getString(R.string.champsVides));
        }
        else {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, login_url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try{
                                JSONArray jsonArray = new JSONArray(response);
                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                String code = jsonObject.getString("code");

                                if(code.equals("login_failed")){
                                    builder.setTitle(getString(R.string.erreurLogin));
                                    displayAlert(jsonObject.getString("message"));
                                }
                                else
                                {
                                    Intent intent = new Intent(ConnexionActivity.this, Etape1_Activity.class);
                                    startActivity(intent);
                                    Toast.makeText(ConnexionActivity.this, getString(R.string.connectionReussie), Toast.LENGTH_SHORT).show();
                                }
                            }catch(JSONException e){
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(ConnexionActivity.this, getString(R.string.erreur), Toast.LENGTH_SHORT).show();
                    error.printStackTrace();
                }
            })
            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("pseudo", pseudo);
                    params.put("password", password);
                    return params;
                }
            };
            MySingleton.getInstance(ConnexionActivity.this).addToRequestque(stringRequest);
        }
    }

    public void displayAlert(String message) {
        builder.setMessage(message);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Pseudo.setText("");
                Password.setText("");
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void mdp_oublie(View v) {
        Intent intent = new Intent(ConnexionActivity.this, activity_mail.class);
        startActivity(intent);
    }

    public void suivant(View v) {
        Intent intent = new Intent(ConnexionActivity.this, Etape1_Activity.class);
        startActivity(intent);
    }
}
