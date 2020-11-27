package com.example.studentutility;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class NoteListActivity extends AppCompatActivity {

    ListView lv_notes;
    DataBaseHelper dataBaseHelper;
    static ArrayAdapter notesArrayAdapter;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.note_list_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {


            case R.id.note_list_add :
                //to move to next activity to add a new note
                Intent intent = new Intent(getApplicationContext(),NotesActivity.class);

                startActivity(intent);
                return true;

            default: return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        lv_notes = findViewById(R.id.lv_notes);

        dataBaseHelper = new DataBaseHelper(NoteListActivity.this);

        showItemsInListView(dataBaseHelper);

        //to open a note by passing its id to the NotesActivity
        lv_notes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                NotesModel clickedNote = (NotesModel) adapterView.getItemAtPosition(i);
                int receivedID = dataBaseHelper.getNoteID(clickedNote);

                Intent intent = new Intent(getApplicationContext(),NotesActivity.class);
                intent.putExtra("noteID",receivedID); //passing the noteID of clicked note to next activity.
                startActivity(intent);
            }
        });

        //dialog box to delete a note
        lv_notes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, View view, int i, long l) {
                final int itemToDelete = i;
                new AlertDialog.Builder(NoteListActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Delete Note")
                        .setMessage("Do you want to delete?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                NotesModel clickedNote = (NotesModel) adapterView.getItemAtPosition(itemToDelete);
                                boolean success = dataBaseHelper.deleteOne(clickedNote);

                                if(success) {
                                    Toast.makeText(NoteListActivity.this, "Note Deleted", Toast.LENGTH_SHORT).show();
                                }

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

        notesArrayAdapter = new ArrayAdapter(NoteListActivity.this,android.R.layout.simple_list_item_1,allNotes);

        lv_notes.setAdapter((notesArrayAdapter));
    }


}