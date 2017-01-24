package com.example.simon.dycard.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.example.simon.dycard.R;
import com.example.simon.dycard.util.MySingleton;

import java.io.IOException;

public class Choose_photo_activity extends AppCompatActivity {

    private ImageView imageView;
    private Bitmap bitmap;
    private String choix;

    private int PICK_IMAGE_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_photo);

        imageView = (ImageView)findViewById(R.id.photo);
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
            case R.id.format1:
                if(checked)
                    choix = "format1";
                break;
            case R.id.format2:
                if(checked)
                    choix = "format2";
                break;
            case R.id.format3:
                if(checked)
                    choix = "format3";
                break;
        }
    }

    public void importPhoto(View v) {
        showFileChooser();
    }

    public void precedentImagesActivity(View v) {
        finish();
    }

    public void suivantActivityTexte(View v) {
        if(bitmap != null && choix != null) {
            MySingleton.getInstance(Choose_photo_activity.this).getCommande().setFormat(choix);
            MySingleton.getInstance(Choose_photo_activity.this).getCommande().setPhoto(bitmap);
            Intent intent = new Intent(Choose_photo_activity.this, activity_texte.class);
            startActivity(intent);
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
