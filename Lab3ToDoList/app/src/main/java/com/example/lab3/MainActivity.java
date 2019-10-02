package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    Button newNoteBtn;
    ListView notesList;
    NotesDB notes;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newNoteBtn = (Button) findViewById(R.id.newNoteBtn);
        notesList = (ListView) findViewById(R.id.notesList);

        ArrayList<String> titles = new ArrayList<>();
        notes = new NotesDB(this);
        titles = notes.getTitles();
        // adapter is like what connects UI to data source
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titles);
        notesList.setAdapter(adapter);
        notesList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick (AdapterView<?> parent, View view, int position, long id)
            {
                String title = (String) parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, ViewNote.class);
                intent.putExtra("title", title);
                intent.putExtra("old", "old");
                startActivityForResult(intent, 1000);
            }
        });


    }

    public void newNote(View v)
    {
        Intent intent = new Intent(this, Note.class);
        startActivity(intent);
    }
}

