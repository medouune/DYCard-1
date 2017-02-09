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

public class Choose_photo_activity extends AppCompatActivity {

    private PorterImageView imageView;
    private Bitmap bitmap;
    private String choix;
    private double prix;
    private Commande commande;
    private RadioButton polaroid, A6, A4;

    private int PICK_IMAGE_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_photo);

        commande = MySingleton.getInstance(Choose_photo_activity.this).getCommande();

        imageView = (PorterShapeImageView)findViewById(R.id.photo);

        polaroid = (RadioButton)findViewById(R.id.polaroid);
        A6 = (RadioButton)findViewById(R.id.A6);
        A4 = (RadioButton)findViewById(R.id.A4);

        if(commande.getPhoto()!=null){
            bitmap = commande.getPhoto();
            imageView.setImageBitmap(bitmap);
        }


        if(commande.getFormat()!=null){
            String format = commande.getFormat();
            if(format.equals("Polaroïd"))
                polaroid.setChecked(true);
            else if(format.equals("A4"))
                A4.setChecked(true);
            else if(format.equals("A6"))
                A6.setChecked(true);
        }

    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null &&
                data.getData() != null) {
            Uri filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);

            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void choixFormat(View v) {
        boolean checked = ((RadioButton) v).isChecked();

        switch(v.getId()) {
            case R.id.polaroid:
                if(checked){
                    choix = "Polaroïd";
                    prix = 2.39;
                }
                break;
            case R.id.A6:
                if(checked){
                    choix = "A6";
                    prix = 3.39;
                }
                break;
            case R.id.A4:
                if(checked){
                    choix = "A4";
                    prix = 4.39;
                }
                break;
        }
    }

    public void importPhoto(View v) {
        showFileChooser();
    }

    public void precedentImagesActivity(View v) {
        startActivity(new Intent(Choose_photo_activity.this, Images_Activity.class));
        finish();
    }

    public void suivantActivityTexte(View v) {
        if(bitmap != null && choix != null) {
            Bitmap result = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
            commande.setFormat(choix);
            commande.setPhoto(result);
            commande.setPrix(prix);
            Intent intent = new Intent(Choose_photo_activity.this, activity_texte.class);
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
