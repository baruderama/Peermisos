package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {


    private ImageButton photosButton;
    int MY_PERMISSIONS_REQUEST_READ_CONTACTS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void permisoContactos(View v){
        // Here, thisActivity is the current activity
        Intent intent = new Intent(v.getContext(), ContactsActivity.class);
        //intent.putExtra("permiso",permissionCheck);
        startActivity(intent);
    }

    public void permisoCamara(View v){
        Intent intent=new Intent(v.getContext(),ActividadCamara.class);
        startActivity(intent);

    }

}
