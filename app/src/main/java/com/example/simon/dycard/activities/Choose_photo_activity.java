package com.example.simon.dycard.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.example.simon.dycard.R;

import java.io.IOException;

public class Choose_photo_activity extends AppCompatActivity {

    private ImageView imageView;

    private Bitmap bitmap;

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

    public void importPhoto(View v) {
        showFileChooser();
    }

    public void precedentImagesActivity(View v) {
        Intent intent = new Intent(Choose_photo_activity.this, Images_Activity.class);
        startActivity(intent);
    }
    public void suivantActivityTexte(View v) {
        Intent intent = new Intent(Choose_photo_activity.this, activity_texte.class);
        startActivity(intent);
    }
}
