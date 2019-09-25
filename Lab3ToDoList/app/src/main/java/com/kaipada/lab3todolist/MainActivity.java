package com.kaipada.lab3todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button newButton = (Button) findViewById(R.id.newButton);
    ListView notesList = (ListView) findViewById(R.id.notesList);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
	ArrayList<String> titles = new ArrayList<>();
	ArrayAdapter adapter = new ArrayAdapter<String>(context:this,andriod.R.layout.somple_list_item_1,titles);
	notesList.setAdapter(adapter);
    }
}

public void newNote(View v) {
}
