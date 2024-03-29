package com.example.studentutility;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;

public class MainActivity extends AppCompatActivity {

    public void goToNotes(View view) {
        Intent intent = new Intent(getApplicationContext(),NoteListActivity.class);

        startActivity(intent);
    }

    public void goToTimer(View view) {
        Intent intent = new Intent(getApplicationContext(),TimerActivity.class);

        startActivity(intent);
    }

    public void goToGpa(View view) {
        Intent intent = new Intent(getApplicationContext(), GPAActivity.class);

        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}