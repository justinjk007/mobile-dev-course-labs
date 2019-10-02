package com.kaipada.Lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main2Activity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView = (TextView) findViewById(R.id.textView);
    }

    public void privateShowMethod(View view){
        String data =null;
        FileReader fileReader = null;
        try {
            // FileReader reads text files in the default encoding.
            fileReader = new FileReader(getExternalFilesDir(null)+ "/private.txt");

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((data = bufferedReader.readLine()) != null) {
                textView.setText(data); // Lol can only set one line, it will overwrite it everytime
            }

            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            Log.w("ExternalStorage", "File not found" + fileReader, ex);
        }
        catch(IOException ex) {
            Log.w("ExternalStorage", "Error reading " + fileReader, ex);
        }
    }

    public void publicShowMethod(View view){
        String data =null;
        FileReader fileReader = null;
        try {
            // FileReader reads text files in the default encoding.
            fileReader = new FileReader(Environment.getExternalStorageDirectory()+ "/public.txt");

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((data = bufferedReader.readLine()) != null) {
                textView.setText(data); // Lol can only set one line, it will overwrite it everytime
            }

            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            Log.w("ExternalStorage", "File not found" + fileReader, ex);
        }
        catch(IOException ex) {
            Log.w("ExternalStorage", "Error reading " + fileReader, ex);
        }
    }

    public void backMethod(View view){
        Intent intent = new Intent(this, MainActivity.class); // go back
        startActivity(intent);
    }

}
