package com.example.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DatabaseNotesManager {
    private Context context;
    private DatabaseNotesHelper dbNotesHelper;
    private SQLiteDatabase dbNotes;

    public DatabaseNotesManager(Context context) {
        this.context = context;
        dbNotesHelper = new DatabaseNotesHelper(context);
    }

    public void addNote(String titleNote, String textNote) {
        dbNotes = dbNotesHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(dbNotesHelper.TITLE_NOTE, titleNote);
        cv.put(dbNotesHelper.TEXT_NOTE, textNote);
        dbNotes.insert(dbNotesHelper.TABLE_NAME, null, cv);
    }


    public  Cursor readAllData() {
        String selectAll = "SELECT * FROM " + dbNotesHelper.TABLE_NAME;
        SQLiteDatabase db = dbNotesHelper.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(selectAll, null);

        }
        return  cursor;
    }

    void updateData(String row_id, String titleNote, String textNote) {
        SQLiteDatabase db = dbNotesHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(dbNotesHelper.TITLE_NOTE, titleNote);
        cv.put(dbNotesHelper.TEXT_NOTE, textNote);

        db.update(dbNotesHelper.TABLE_NAME, cv, "_id=?", new String[]{row_id});
    }

    void deleteOneRow (String row_id) {
        SQLiteDatabase db = dbNotesHelper.getWritableDatabase();
        db.delete(dbNotesHelper.TABLE_NAME,"_id=?", new String[]{row_id});
    }

}
