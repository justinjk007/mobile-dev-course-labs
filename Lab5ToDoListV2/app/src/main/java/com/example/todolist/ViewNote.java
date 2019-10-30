package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ViewNote extends AppCompatActivity {

    EditText title;
    EditText content;
    Button back;
    Intent intent;
    NotesDB notesDB;
    String old_title;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);
        intent = getIntent();
        title = (EditText) findViewById(R.id.title);
        content = (EditText) findViewById(R.id.content);
        back = (Button) findViewById(R.id.back);
        old_title = intent.getStringExtra("title");
        NoteClass note = new NoteClass();
        notesDB = new NotesDB(this);
        note = notesDB.getNote(old_title);
        title.setText(note.getTitle());
        content.setText(note.getContent());
    }
    public void back(View v)
    {
        setResult(RESULT_OK,intent);
        finish();
    }

    public void delete(View v)
    {
        notesDB.deleteNote(title.getText().toString());
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void update(View v)
    {
        notesDB.updateNote(title.getText().toString(), content.getText().toString(), old_title);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
