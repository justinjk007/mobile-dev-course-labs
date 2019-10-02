package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Note extends AppCompatActivity
{
    Button saveB;
    EditText titleT;
    EditText contentT;
    NotesDB notesDB;
    NoteClass note;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        // will crash if not instantiated inside onCreate, as there will not be any item
        //created yet with the requested IDs
        saveB = (Button) findViewById(R.id.saveBtn);
        titleT = (EditText) findViewById(R.id.titleText);
        contentT = (EditText) findViewById(R.id.contentText);
        notesDB = new NotesDB(this);
    }

    public void save(View v)
    {
        String title = titleT.getText().toString();
        String content = contentT.getText().toString();
        note = new NoteClass(title, content);
        notesDB.addNote(note);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
