package com.kaipada.Lab4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText)findViewById(R.id.editText);

        // Permission is not granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, 5);
        }

        // Permission is not granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE}, 5);
        }

    }

    public void privateSaveMethod(View view){
        saveExternalStoragePrivateFile(editText.getText().toString());
        Toast.makeText(getApplicationContext(),"Data saved!",Toast.LENGTH_SHORT).show();
    }

    public void publicSaveMethod(View view){
        saveExternalStoragePublicFile(editText.getText().toString());
        Toast.makeText(getApplicationContext(),"Data saved!",Toast.LENGTH_SHORT).show();
    }

    public void viewDataMethod(View view){
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }

    public void saveExternalStoragePrivateFile(String data) {

        File file = new File(getExternalFilesDir(null), "private.txt");

        try {
            // Very simple code to copy a picture from the application's
            // resource into the external file.  Note that this code does
            // no error checking, and assumes the picture is small (does not
            // try to copy it in chunks).  Note that if external storage is
            // not currently mounted this will silently fail.
            FileOutputStream fOut = new FileOutputStream(file);
            OutputStreamWriter out = new OutputStreamWriter(fOut);
            out.write(data);
            out.close();
            fOut.flush();
            fOut.close();
            Log.w("ExternalStorage", "Finished writing " + file);
        } catch (IOException e) {
            // Unable to create file
            Log.w("ExternalStorage", "Error writing " + file, e);
        }
    }

    public void saveExternalStoragePublicFile(String data) {

        File file = new File(Environment.getExternalStorageDirectory(), "public.txt");

        try {
            // Very simple code to copy a picture from the application's
            // resource into the external file.  Note that this code does
            // no error checking, and assumes the picture is small (does not
            // try to copy it in chunks).  Note that if external storage is
            // not currently mounted this will silently fail.
            FileOutputStream fOut = new FileOutputStream(file);
            OutputStreamWriter out = new OutputStreamWriter(fOut);
            out.write(data);
            out.close();
            fOut.flush();
            fOut.close();
            Log.w("ExternalStorage", "Finished writing " + file);
        } catch (IOException e) {
            // Unable to create file
            Log.w("ExternalStorage", "Error writing " + file, e);
        }
    }

}
