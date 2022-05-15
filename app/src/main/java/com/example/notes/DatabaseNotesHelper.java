package com.example.notes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseNotesHelper extends SQLiteOpenHelper {
    private Context context;
    public static final String DATABASE_NAME = "Notes.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "Note";
    public static final String ID_NOTE = "_id";
    public static final String TITLE_NOTE = "title";
    public static final String TEXT_NOTE = "text";

    public DatabaseNotesHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        ID_NOTE + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        TITLE_NOTE+ " TEXT, " + //DO: maybe Not Null do
                        TEXT_NOTE + " TEXT);";
        db.execSQL(SQL_CREATE);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String SQL_DELETE =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(SQL_DELETE);
        onCreate(db);
    }
}
