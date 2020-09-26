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

    //to add a new note to database
    public boolean addOne(NotesModel notesModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(NOTE_TITLE,notesModel.getTitle());
        cv.put(NOTE_BODY,notesModel.getNote());

        long insert = db.insert(NOTES_TABLE,null,cv);

        if(insert == -1) return false;
        else return true;
    }

    //to update a existing note.
    public boolean updateOne(NotesModel notesModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(NOTE_TITLE,notesModel.getTitle());
        cv.put(NOTE_BODY,notesModel.getNote());

        String updateID = Integer.toString(notesModel.getId());

        long update = db.update(NOTES_TABLE,cv, NOTE_ID + " = ?",new String[] {updateID});

        if(update == 1) return true;
        else return false;
    }


    //to return the note id of clicked note to pass it to the NotesActivity
    public int getNoteID(NotesModel notesModel) {

        return notesModel.getId();
    }

    //to get the details of the clicked note to display it in the NotesActivity
    public NotesModel getOne(int clickedNoteID) {
        String queryString = "SELECT * FROM " + NOTES_TABLE + " WHERE " + NOTE_ID + " = " + clickedNoteID;

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString,null);

        NotesModel clickedNote = new NotesModel();

        if(cursor.moveToFirst()) {
            int notesID = cursor.getInt(0);
            String noteTitle = cursor.getString(1);
            String noteBody = cursor.getString(2);

            clickedNote = new NotesModel(notesID,noteTitle,noteBody);
        }
        cursor.close();
        db.close();

        return clickedNote;
    }

    //deleting a note from database
    public boolean deleteOne(NotesModel notesModel) {

        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + NOTES_TABLE + " WHERE " + NOTE_ID + " = " + notesModel.getId();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()) {
            return false;
        }
        else return true;
    }



    public List<NotesModel> getAllNotes() {
        List<NotesModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + NOTES_TABLE;

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()) {
            do {
                int notesID = cursor.getInt(0);
                String noteTitle = cursor.getString(1);
                String noteBody = cursor.getString(2);

                NotesModel newNote = new NotesModel(notesID,noteTitle,noteBody);
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
