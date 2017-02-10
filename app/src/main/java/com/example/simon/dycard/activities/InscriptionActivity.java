package com.example.simon.dycard.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.simon.dycard.util.Captcha;
import com.example.simon.dycard.util.MathCaptcha;
import com.example.simon.dycard.util.MySingleton;
import com.example.simon.dycard.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class InscriptionActivity extends AppCompatActivity {

    private EditText Pseudo, Password, Password2, Email, ET;
    private String pseudo, password, password2, email, ansCaptcha, ansUser;
    private String REGISTER_URL = "http://10.20.42.184/DYCard/WebServiceDYCard/register.php";
    private AlertDialog.Builder builder;
    private Context mContext;
    private ImageView im;
    private Captcha c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        mContext = getApplicationContext();
        builder = new AlertDialog.Builder(mContext);

        Pseudo = (EditText)findViewById(R.id.inscription_editText_pseudo);
        Password = (EditText)findViewById(R.id.inscription_editText_password);
        Password2 = (EditText)findViewById(R.id.inscription_editText_password2);
        Email = (EditText)findViewById(R.id.inscription_editText_email);

        ET = (EditText) findViewById(R.id.ansUser);
        im = (ImageView) findViewById(R.id.captcha);

        majCaptcha();
    }

    public void sendInscription(View v){
        pseudo = Pseudo.getText().toString();
        password = Password.getText().toString();
        password2 = Password2.getText().toString();
        email = Email.getText().toString();

        if(pseudo.equals("") || email.equals("") || password.equals("") || password2.equals("")) {
            Toast.makeText(mContext, R.string.champsVides, Toast.LENGTH_SHORT).show();
        }
        else {
            if(!(password.equals(password2))) {
                Toast.makeText(mContext, R.string.mdpDifferents, Toast.LENGTH_SHORT).show();
                Password.setText("");
                Password2.setText("");
            }
            else {
                if(ET.getText().toString().equals("") ){
                    Toast.makeText(mContext, "Veuillez remplir le captcha", Toast.LENGTH_SHORT).show();
                }else{
                    ansUser = ET.getText().toString();
                    if(ansCaptcha.equals(ansUser)) {
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONArray jsonArray = new JSONArray(response);
                                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                                            String code = jsonObject.getString("code");
                                            if (code.equals("OK")) {
                                                Toast.makeText(mContext, R.string.inscriptionSucces, Toast.LENGTH_SHORT).show();
                                                finish();
                                            } else {
                                                Toast.makeText(mContext, R.string.inscriptionEchec, Toast.LENGTH_SHORT).show();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(mContext, R.string.erreurConnexion, Toast.LENGTH_SHORT).show();
                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params = new HashMap<>();
                                params.put("pseudo", pseudo);
                                params.put("password", password);
                                params.put("mail", email);
                                return params;
                            }
                        };
                        MySingleton.getInstance(mContext).addToRequestque(stringRequest);
                    } else {
                        Toast.makeText(InscriptionActivity.this, "Echec test robot", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    public void changer(View v){
        majCaptcha();
    }

    public void majCaptcha(){
        c = new MathCaptcha(300, 100, MathCaptcha.MathOptions.PLUS_MINUS_MULTIPLY);
        im.setImageBitmap(c.image);
        im.setLayoutParams(new LinearLayout.LayoutParams(c.width * 2, c.height * 2));
        ansCaptcha = c.answer;
    }

}
