package com.example.studentutility;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class NoteListActivity extends AppCompatActivity {

    Button btn_add;
    ListView lv_notes;

    DataBaseHelper dataBaseHelper;
    static ArrayAdapter notesArrayAdapter;

    //List<String>allTitles = new ArrayList<>();

    //to move to next activity on adding a new note
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

        //dialog box to delete a note
        lv_notes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, View view, int i, long l) {
                final int itemToDelete = i;
                new AlertDialog.Builder(NoteListActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Sure?")
                        .setMessage("Do you want to delete?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                NotesModel clickedNote = (NotesModel) adapterView.getItemAtPosition(itemToDelete);
                                boolean success = dataBaseHelper.deleteOne(clickedNote);

                                if(success = true) {
                                    Toast.makeText(NoteListActivity.this, "Note Deleted", Toast.LENGTH_SHORT).show();
                                }

                                //allTitles.remove(i);
                                //notesArrayAdapter.notifyDataSetChanged();
                                showItemsInListView(dataBaseHelper);


                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                return true;
            }

        });

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
        List<NotesModel> allNotes;
        allNotes = dataBaseHelper.getAllNotes();

        /*
        for(NotesModel notes : allNotes) {
            allTitles.add(notes.getTitle());
        }
         */

        notesArrayAdapter = new ArrayAdapter(NoteListActivity.this,android.R.layout.simple_list_item_1,allNotes);

        lv_notes.setAdapter((notesArrayAdapter));
    }


}