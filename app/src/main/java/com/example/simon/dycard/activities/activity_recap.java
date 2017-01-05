package com.example.simon.dycard.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.simon.dycard.R;
import com.example.simon.dycard.model.Commande;
import com.example.simon.dycard.model.User;
import com.example.simon.dycard.util.MySingleton;

public class activity_recap extends AppCompatActivity {

    private TextView idUser;
    private ImageView photo;
    private User user;
    private Commande commande;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recap);

        idUser = (TextView) findViewById(R.id.activity_recap_idUser);
        photo = (ImageView) findViewById(R.id.activity_recap_photo);

        user = MySingleton.getInstance(activity_recap.this).getUser();
        commande = MySingleton.getInstance(activity_recap.this).getCommande();

        idUser.setText(String.valueOf(user.getId()));
        photo.setImageBitmap(commande.getPhoto());

    }
}
