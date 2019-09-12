package com.example.lab1_calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText number1,number2;
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = (TextView)findViewById(R.id.result);
        number1 = (EditText)findViewById(R.id.number1);
        number2 = (EditText)findViewById(R.id.number2);
    }

    public void addMethod(View view){
        float n1 = Float.parseFloat(number1.getText().toString());
        float n2 = Float.parseFloat(number2.getText().toString());
        result.setText(Float.toString(n1+n2));
    }

    public void substractMethod(View view){
        float n1 = Float.parseFloat(number1.getText().toString());
        float n2 = Float.parseFloat(number2.getText().toString());
        result.setText(Float.toString(n1-n2));
    }

    public void divideMethod(View view){
        float n1 = Float.parseFloat(number1.getText().toString());
        float n2 = Float.parseFloat(number2.getText().toString());
        result.setText(Float.toString(n1/n2));
    }

    public void multiplyMethod(View view){
        float n1 = Float.parseFloat(number1.getText().toString());
        float n2 = Float.parseFloat(number2.getText().toString());
        result.setText(Float.toString(n1*n2));
    }
}
