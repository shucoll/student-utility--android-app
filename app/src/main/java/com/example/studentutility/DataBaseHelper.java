package com.example.studentutility;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String NOTE_BODY = "NOTE";
    public static final String NOTES_TABLE = "NOTES_TABLE";
    public static final String NOTE_ID = "ID";
    public static final String NOTE_TITLE = "TITLE";

    public DataBaseHelper(@Nullable Context context) {super(context, "notes.db", null, 1); }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + NOTES_TABLE + " (" + NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NOTE_TITLE + " TEXT, " + NOTE_BODY + " TEXT)";
        db.execSQL(createTableStatement);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOne(NotesModel notesModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(NOTE_TITLE,notesModel.getTitle());
        cv.put(NOTE_BODY,notesModel.getNote());

        long insert = db.insert(NOTES_TABLE,null,cv);

        if(insert == -1) return false;
        else return true;
    }

    public List<NotesModel> getAllNotes() {
        List<NotesModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + NOTES_TABLE;

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()) {
            do {
                //int notesID = cursor.getInt(0);
                String noteTitle = cursor.getString(1);
                //String noteBody = cursor.getString(2);

                NotesModel newNote = new NotesModel(noteTitle);
                returnList.add(newNote);
            }while(cursor.moveToNext());
        }
        else {

        }
        cursor.close();
        db.close();

        return returnList;
    }
}
