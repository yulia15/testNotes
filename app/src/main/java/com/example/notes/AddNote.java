package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddNote extends AppCompatActivity {
    EditText titleNote;
    EditText textNote;
    FloatingActionButton saveButton;


    @Override
    public boolean onSupportNavigateUp() {
        confirmDialog();
        return true;
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        confirmDialog();
    }

    void  confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Выход из редактора заметки");
        builder.setMessage("Вы уверены, что хотите выйти? Данные не будут сохранены");
        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
   }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        titleNote = findViewById(R.id.edTitleNote);
        textNote = findViewById(R.id.edTextNote);
        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!titleNote.getText().toString().trim().equals("") || !textNote.getText().toString().trim().equals("")) {
                    DatabaseNotesManager dbNotesManager = new DatabaseNotesManager(AddNote.this);
                    dbNotesManager.addNote(titleNote.getText().toString().trim(),
                            textNote.getText().toString().trim());
                    Toast.makeText(AddNote.this,"Заметка сохранена", Toast.LENGTH_SHORT).show();}
                else {
                    Toast.makeText(AddNote.this,"Пустая заметка не сохранена", Toast.LENGTH_SHORT).show();}
                finish();
            }

        });
    }
}