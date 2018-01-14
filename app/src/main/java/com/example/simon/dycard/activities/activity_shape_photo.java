package com.example.simon.dycard.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.example.simon.dycard.model.Commande;
import com.github.siyamed.shapeimageview.ShapeImageView;

import com.example.simon.dycard.R;
import com.example.simon.dycard.util.MySingleton;
import com.github.siyamed.shapeimageview.mask.PorterImageView;
import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;

import java.io.IOException;

public class activity_shape_photo extends AppCompatActivity {

    private PorterImageView imageView;
    private Bitmap bitmap;
    private String choix;
    private double prix;
    private Commande commande;

    private int PICK_IMAGE_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_photo);

        commande = MySingleton.getInstance(activity_shape_photo.this).getCommande();

        imageView = (PorterShapeImageView)findViewById(R.id.photo);

        if(commande.getPhoto()!=null){
            bitmap = commande.getPhoto();
            imageView.setImageBitmap(bitmap);
        }


        if(commande.getFormat()!=null){
            choix = commande.getFormat();
        }

    }
    public void precedentActivityPhoto(View v) {
        startActivity(new Intent(activity_shape_photo.this, Choose_photo_activity.class));
        finish();
    }

    public void suivantActivityTexte(View v) {
        if(bitmap != null && choix != null) {
            Bitmap result = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
            commande.setPhoto(result);
            Intent intent = new Intent(activity_shape_photo.this, activity_texte.class);
            startActivity(intent);
            finish();
        } else if(bitmap == null){
            afficherAlerte(getResources().getString(R.string.alertePhoto));
        } else
            afficherAlerte(getResources().getString(R.string.alerteFormat));
    }

    public void afficherAlerte(String message){
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }
}
