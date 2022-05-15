package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class UpdateNoteActivity extends AppCompatActivity {
    EditText titleNote;
    EditText textNote;
    FloatingActionButton saveButton;
    String id, title, text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);

        titleNote = findViewById(R.id.edTitleNote2);
        textNote = findViewById(R.id.edTextNote2);
        saveButton = findViewById(R.id.saveButton2);
        getAndSetIntentDate();
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseNotesManager dbNotesManager = new DatabaseNotesManager(UpdateNoteActivity.this);
                title = titleNote.getText().toString().trim();
                text = textNote.getText().toString().trim();
                dbNotesManager.updateData(id, title, text);
                finish();
            };
        });



    }

    void getAndSetIntentDate() {
        if(getIntent().hasExtra("id") &&
                getIntent().hasExtra("title") &&
                getIntent().hasExtra("text"))
        {
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            text = getIntent().getStringExtra("text");

            titleNote.setText(title);
            textNote.setText(text);

        } else {
            Toast.makeText(this, "нет данных", Toast.LENGTH_LONG).show();
        }

    }

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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.note_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.del) {
            confirmDialogDel() ;

        }
        return super.onOptionsItemSelected(item);
    }

    void  confirmDialogDel() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Удаление заметки");
        builder.setMessage("Вы уверены, что хотите удалить запись?");
        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseNotesManager dbNoteManager = new DatabaseNotesManager(UpdateNoteActivity.this);
                dbNoteManager.deleteOneRow(id);
                finish();

            }
        });
        builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
