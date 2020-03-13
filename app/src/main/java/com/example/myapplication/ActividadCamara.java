package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ActividadCamara extends AppCompatActivity {

    private static final int IMAGE_PICKER_REQUEST=5;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_camara);
        image=findViewById(R.id.galeriaImg);

        requestPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE,"Es necesario permiso para poder elegir una imagen", IMAGE_PICKER_REQUEST);


    }

    private void requestPermission(Activity context,
                                   String permiso, String justificacion, int idCode) {

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) { // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // Show an expanation to the user *asynchronously*
                Toast.makeText(context, justificacion, Toast.LENGTH_LONG).show();
            }

            ActivityCompat.requestPermissions(context,
                    new String[]{permiso}, idCode);


        }
        else {

        }
    }

    public void abrirGaleria(View v){
        Intent pickImage=new Intent(Intent.ACTION_PICK);
        pickImage.setType("image/*");
        startActivityForResult(pickImage,IMAGE_PICKER_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case IMAGE_PICKER_REQUEST:
                if(resultCode == RESULT_OK){
                    try {
                        final Uri imageUri = data.getData();
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        image.setImageBitmap(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
        }
    }
}
