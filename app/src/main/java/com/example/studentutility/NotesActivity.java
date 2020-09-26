
//Note writing Activity

package com.example.studentutility;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NotesActivity extends AppCompatActivity {

    EditText et_title,et_note;
    int clickedNoteID;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.note_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {

            case R.id.note_about :
                return true;

            //to add/save the note
            case R.id.note_save :
                NotesModel notesModel;
                DataBaseHelper dataBaseHelper = new DataBaseHelper(NotesActivity.this);

                //checking if trying to add a new note or editing a previously existing note.
                if(clickedNoteID == -1) {
                    try{
                        if (TextUtils.isEmpty(et_title.getText())) {
                            Toast.makeText(NotesActivity.this , "Can't save without title" , Toast.LENGTH_SHORT).show();
                        }
                        else{
                            notesModel = new NotesModel(-1, et_title.getText().toString(),et_note.getText().toString());

                            boolean success = dataBaseHelper.addOne(notesModel);
                            if(success) {
                                Toast.makeText(NotesActivity.this, "Note added", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }

                    }catch(Exception e) {
                        Toast.makeText(NotesActivity.this , "Error adding data" , Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    try{
                        if (TextUtils.isEmpty(et_title.getText())) {
                            Toast.makeText(NotesActivity.this , "Can't save without title" , Toast.LENGTH_SHORT).show();
                        }
                        else{
                            notesModel = new NotesModel(clickedNoteID, et_title.getText().toString(),et_note.getText().toString());

                            boolean success = dataBaseHelper.updateOne(notesModel);
                            if(success) {
                                Toast.makeText(NotesActivity.this, "Note updated", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }

                    }catch(Exception e) {
                        Toast.makeText(NotesActivity.this , "Error updating data" , Toast.LENGTH_SHORT).show();
                    }
                }

                return true;

            default: return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        et_title = findViewById(R.id.et_title);
        et_note = findViewById(R.id.et_note);

        Intent intent = getIntent();
        clickedNoteID = intent.getIntExtra("noteID",-1); //receving the noteID of clicked note NoteListActivity

        //checking whether this activity was opened for adding a new note of editing/viewing previous note.
        //if noteID = -1 that means a default value was recieved ie no note was clicked but new note was added.
        if (clickedNoteID != -1) {

            DataBaseHelper dataBaseHelper = new DataBaseHelper(NotesActivity.this);
            NotesModel clickedNote;
            clickedNote = dataBaseHelper.getOne(clickedNoteID);
            //int noteSelected = clickedNote.getId();
            et_title.setText(clickedNote.getTitle());
            et_note.setText(clickedNote.getNote());
            //Toast.makeText(NotesActivity.this, "item " + noteSelected, Toast.LENGTH_SHORT).show();

        }
    }
}