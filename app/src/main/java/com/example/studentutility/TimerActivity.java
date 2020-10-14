package com.example.studentutility;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaParser;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class TimerActivity extends AppCompatActivity {

    TextView et_count;
    SeekBar seek_Timer;
    Boolean countActive = false;
    Boolean isPlaying = false;
    Button btn_Go;
    CountDownTimer countDownTimer;
    MediaPlayer endSound;

    public void resetTimer() {
        et_count.setText("0:30");
        seek_Timer.setProgress(30);
        seek_Timer.setEnabled(true);
        countDownTimer.cancel();
        btn_Go.setText("GO");
        countActive = false;
        isPlaying = false;
    }

    public void goClicked(View view) {

        if(isPlaying) {

            if(endSound != null) {
                endSound.stop();
                endSound.release();
                endSound = null;
            }

            resetTimer();
        }

        else if (countActive) {
            resetTimer();
        }

        else {
            countActive = true;
            seek_Timer.setEnabled(false);
            btn_Go.setText("STOP");

            countDownTimer = new CountDownTimer( seek_Timer.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long l) {
                    updateTimer((int)l / 1000);
                }

                @Override
                public void onFinish() {

                    endSound = MediaPlayer.create(getApplicationContext(),R.raw.timersound);
                    endSound.start();
                    isPlaying = true;
                    //resetTimer();
                }
            }.start();
        }

    }

    public void updateTimer( int secLeft) {
        int min = secLeft / 60;         //getting the minutes
        int sec = secLeft - (min * 60); //getting the seconds

        String secString = Integer.toString(sec);

        if(sec <= 9) {
            secString = "0" + secString;
        }

        et_count.setText(Integer.toString(min) + ":" + secString);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        seek_Timer = findViewById(R.id.seek_Timer);
        et_count = findViewById(R.id.et_count);
        btn_Go = findViewById(R.id.btn_Go);

        seek_Timer.setMax(1800);
        seek_Timer.setProgress(30);

        seek_Timer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}