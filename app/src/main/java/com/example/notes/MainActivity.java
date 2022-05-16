package com.example.notes;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton addButton;
    DatabaseNotesManager dbNotesManager;
    ArrayList<String> listIdNote, listTitleNote, listTextNote;
    NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        addButton = findViewById(R.id.floatingActionButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddNote.class);
                startActivity(intent);

            }
        });
        dbNotesManager = new DatabaseNotesManager(MainActivity.this);
        listIdNote = new ArrayList<>();
        listTextNote = new ArrayList<>();
        listTitleNote = new ArrayList<>();
        storeDataInArray();
        noteAdapter = new NoteAdapter(MainActivity.this, MainActivity.this, listIdNote, listTitleNote, listTextNote);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(noteAdapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1) {
            //recreate();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }


    public void  storeDataInArray() {
        Cursor cursor = dbNotesManager.readAllData();
        if(cursor.getCount() == 0) {
            Toast.makeText(this, "Нет заметок", Toast.LENGTH_LONG).show();
        } else {
            while (cursor.moveToNext()) {
                listIdNote.add(cursor.getString(0));
                listTitleNote.add(cursor.getString(1));
                listTextNote.add(cursor.getString(2));
            }

        }
    }


   @Override
    protected void onRestart() {
        super.onRestart();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}