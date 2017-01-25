package com.example.simon.dycard.activities;

import android.content.DialogInterface;
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
import com.example.simon.dycard.util.MySingleton;
import com.example.simon.dycard.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class InscriptionActivity extends AppCompatActivity {

    private EditText Pseudo, Password, Password2, Email;
    private String pseudo, password, password2, email;
    private String REGISTER_URL = "http://192.168.1.34/DYCard/WebServiceDYCard/register";
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        Pseudo = (EditText)findViewById(R.id.inscription_editText_pseudo);
        Password = (EditText)findViewById(R.id.inscription_editText_password);
        Password2 = (EditText)findViewById(R.id.inscription_editText_password2);
        Email = (EditText)findViewById(R.id.inscription_editText_email);
        builder = new AlertDialog.Builder(InscriptionActivity.this);

    }

    public void sendInscription(View v){
        pseudo = Pseudo.getText().toString();
        password = Password.getText().toString();
        password2 = Password2.getText().toString();
        email = Email.getText().toString();

        if(pseudo.equals("") || email.equals("") || password.equals("") || password2.equals("")) {
            builder.setTitle(getString(R.string.erreurFormulaire));
            displayAlert(getString(R.string.champsVides));
        }
        else {
            if(!(password.equals(password2))) {
                builder.setTitle(getString(R.string.erreurFormulaire));
                displayAlert(getString(R.string.mdpDifferents));
            }
            else {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try{
                                    JSONArray jsonArray = new JSONArray(response);
                                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                                    String code = jsonObject.getString("code");
                                    if(code.equals("OK")) {
                                        Toast.makeText(InscriptionActivity.this, R.string.inscriptionSucces, Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                    else {
                                        displayAlert(getResources().getString(R.string.inscriptionEchec));
                                    }
                                }catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("pseudo", pseudo);
                        params.put("password", password);
                        params.put("mail", email);
                        return params;
                    }
                };
                MySingleton.getInstance(InscriptionActivity.this).addToRequestque(stringRequest);
            }
        }
    }

    public void displayAlert(final String message) {
        builder.setMessage(message);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Pseudo.setText("");
                Password.setText("");
                Password2.setText("");
                Email.setText("");
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
