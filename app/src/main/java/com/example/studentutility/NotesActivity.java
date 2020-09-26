
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
                try{
                    if (TextUtils.isEmpty(et_title.getText())) {
                        Toast.makeText(NotesActivity.this , "Can't save without title" , Toast.LENGTH_SHORT).show();
                    }
                    else{
                        notesModel = new NotesModel(-1, et_title.getText().toString(),et_note.getText().toString());

                        DataBaseHelper dataBaseHelper = new DataBaseHelper(NotesActivity.this);

                        boolean success = dataBaseHelper.addOne(notesModel);
                        if(success == true) {
                            Toast.makeText(NotesActivity.this, "Note added", Toast.LENGTH_SHORT).show();
                        }
                    }

                }catch(Exception e) {
                    Toast.makeText(NotesActivity.this , "Error adding data" , Toast.LENGTH_SHORT).show();
                }
                finish();
                return true;

            default: return false;
        }
    }

    //Button btn_save;

    //DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        //btn_save = findViewById(R.id.btn_save);
        et_title = findViewById(R.id.et_title);
        et_note = findViewById(R.id.et_note);

        /*

        //to add/save the note
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NotesModel notesModel;
                try{
                    if (TextUtils.isEmpty(et_title.getText())) {
                        Toast.makeText(NotesActivity.this , "Title is must" , Toast.LENGTH_SHORT).show();
                    }
                    else{
                        notesModel = new NotesModel(-1, et_title.getText().toString(),et_note.getText().toString());

                        DataBaseHelper dataBaseHelper = new DataBaseHelper(NotesActivity.this);

                        boolean success = dataBaseHelper.addOne(notesModel);
                        if(success = true) {
                            Toast.makeText(NotesActivity.this, "Note added", Toast.LENGTH_SHORT).show();
                        }
                    }

                }catch(Exception e) {
                    Toast.makeText(NotesActivity.this , "Error adding data" , Toast.LENGTH_SHORT).show();
                }
            }
        });

        */
    }
}