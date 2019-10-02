package com.kaipada.Lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView = (TextView) findViewById(R.id.textView);
    }

    public void privateShowMethod(View view){
        Toast.makeText(getApplicationContext(),"Clicked on private",Toast.LENGTH_SHORT).show();
    }

    public void publicShowMethod(View view){
        Toast.makeText(getApplicationContext(),"Clicked on public",Toast.LENGTH_SHORT).show();
    }

    public void backMethod(View view){
        Intent intent = new Intent(this, MainActivity.class); // go back
        startActivity(intent);
    }
}
