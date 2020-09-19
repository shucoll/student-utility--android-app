package com.example.studentutility;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class NoteListActivity extends AppCompatActivity {

    Button btn_add;
    ListView lv_notes;

    DataBaseHelper dataBaseHelper;
    static ArrayAdapter notesArrayAdapter;
    List<NotesModel> allNotes;
    List<String> allTitles;

    public void addNote(View view) {
        Intent intent = new Intent(getApplicationContext(),NotesActivity.class);

        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        btn_add = findViewById(R.id.btn_add);
        lv_notes = findViewById(R.id.lv_notes);

        dataBaseHelper = new DataBaseHelper(NoteListActivity.this);

        showItemsInListView(dataBaseHelper);

    }

    //to update the list view after coming back from other activity
    @Override
    protected void onResume() {
        super.onResume();
        dataBaseHelper = new DataBaseHelper(NoteListActivity.this);

        showItemsInListView(dataBaseHelper);

    }

    //function to display all element of the database in list view
    private void showItemsInListView(DataBaseHelper dataBaseHelper) {
        allNotes = dataBaseHelper.getAllNotes();

        for(NotesModel notes : allNotes) {
            allTitles.add(notes.displayAllTitles());
        }

        notesArrayAdapter = new ArrayAdapter(NoteListActivity.this,android.R.layout.simple_list_item_1,allTitles);

        lv_notes.setAdapter((notesArrayAdapter));
    }

}