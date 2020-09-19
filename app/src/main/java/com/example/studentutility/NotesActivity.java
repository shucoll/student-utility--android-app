package com.example.studentutility;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NotesActivity extends AppCompatActivity {

    Button btn_save;
    EditText et_title,et_note;

    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        btn_save = findViewById(R.id.btn_save);
        et_title = findViewById(R.id.et_title);
        et_note = findViewById(R.id.et_note);

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

                        Toast.makeText(NotesActivity.this , "Add" + success , Toast.LENGTH_SHORT).show();
                    }

                }catch(Exception e) {
                    Toast.makeText(NotesActivity.this , "Error adding data" , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}