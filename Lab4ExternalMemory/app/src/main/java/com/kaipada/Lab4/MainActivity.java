package com.kaipada.Lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText)findViewById(R.id.editText);
    }

    public void privateSaveMethod(View view){
        Toast.makeText(getApplicationContext(),"Clicked on private",Toast.LENGTH_SHORT).show();
    }

    public void publicSaveMethod(View view){
        Toast.makeText(getApplicationContext(),"Clicked on public",Toast.LENGTH_SHORT).show();
    }

    public void viewDataMethod(View view){
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }

}
