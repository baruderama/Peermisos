package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class ContactsActivity extends MainActivity {

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 5;
    String [] mProjection;
    Cursor mCursor;
    ContactsAdapter mContactsAdapter;
    ListView mlistaContactos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
    mlistaContactos=findViewById(R.id.list);

        requestPermission(this,Manifest.permission.READ_CONTACTS,"Es necesario para que la aplicaciòn muestre los contactos", MY_PERMISSIONS_REQUEST_READ_CONTACTS);
        mProjection = new String[]{ContactsContract.Profile._ID,ContactsContract.Profile.DISPLAY_NAME_PRIMARY};
        mContactsAdapter = new ContactsAdapter(this, null, 0);
        mlistaContactos.setAdapter(mContactsAdapter);
        initView();
    }

    public void initView(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)== PackageManager.PERMISSION_GRANTED){
            mCursor=getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, mProjection, null, null, null);
            mContactsAdapter.changeCursor(mCursor);
        }else {

        }
    }




    private void requestPermission(Activity context,
                                   String permiso, String justificacion, int idCode) {

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) { // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {
                // Show an expanation to the user *asynchronously*
                Toast.makeText(context, justificacion, Toast.LENGTH_LONG).show();
            }

                ActivityCompat.requestPermissions(context,
                        new String[]{permiso}, idCode);

            //Toast.makeText(context, "denegado", Toast.LENGTH_LONG).show();

            // request the permission.

        }
        else {

        }
    }

        // El contexto es la actividad principal


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[],@NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //initView();

            switch (requestCode) {
                case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                    if (grantResults.length > 0
                            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // permission was granted, continue with task related to permission
                        Toast toast = new Toast(getApplicationContext());
                        toast.makeText(getApplicationContext(),"Concedido", Toast.LENGTH_LONG).show();
                        initView();
                    } else {
            // permission denied, disable functionality that depends on this permission.
                        Toast toast = new Toast(getApplicationContext());
                        toast.makeText(getApplicationContext(),"Denegado", Toast.LENGTH_LONG).show();
                    }
                    return; }
            // other 'case' lines to check for other
            // permissions this app might request
            }

    }
     /*
    static final int PERMISSION_CONTACTS_ID = 5;
    TextView tvPermissionState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        //tvPermissionState = findViewById(R.id.tvPermissionState);
        requestPermission(this, Manifest.permission.WRITE_CONTACTS, "Es necesario para mostrar los contactos", PERMISSION_CONTACTS_ID );
        initViews();
    }

    public void initViews(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)== PackageManager.PERMISSION_GRANTED){
            tvPermissionState.setText(getResources().getString(R.string.permissionOK));
            tvPermissionState.setTextColor(Color.GREEN);
        }else {
            tvPermissionState.setText(getResources().getString(R.string.permissionKO));
            tvPermissionState.setTextColor(Color.RED);
        }
    }

    /**
     * Metodo para solicitar un permiso
     * @param context actividad actual
     * @param permission permiso que se desea solicitar
     * @param just justificacion para el permiso
     * @param id identificador con el se marca la solicitud y se captura el callback de respuesta
     */
     /*
    public void requestPermission(Activity context, String permission, String just, int id) {
        if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(context, permission)) {
                // Show an expanation to the user *asynchronously*Â  Â
                Toast.makeText(context, just, Toast.LENGTH_LONG).show();
            }
            // request the permission.Â  Â
            ActivityCompat.requestPermissions(context, new String[]{permission}, id);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        initViews();
        //Si se tienen varios casos
//        switch(requestCode){
//            case PERMISSION_CONTACTS_ID: {
//
//                return;
//            }
//        }
    }

      */
}
