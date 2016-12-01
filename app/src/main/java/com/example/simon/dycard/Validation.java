package com.example.simon.dycard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Validation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validation);

    }
    public void valider(View v){
        Intent intent = new Intent(this, activity_payment.class);
        startActivity(intent);
    }
    public void modifier(View v) {
        Intent intent = new Intent(this, activity_choose_form.class);
        startActivity(intent);
    }
}
