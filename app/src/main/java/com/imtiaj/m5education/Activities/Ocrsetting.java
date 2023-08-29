package com.imtiaj.m5education.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.imtiaj.m5education.R;

public class Ocrsetting extends AppCompatActivity {

    private SeekBar Pitch_bar, Speed_bar;
    private float pitch,speed;
    private Button VolumeAdjust;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocrsetting);

        Pitch_bar = (SeekBar)findViewById(R.id.seek_bar_pitch);
        Speed_bar = (SeekBar)findViewById(R.id.seek_bar_speed);
        VolumeAdjust = findViewById(R.id.OcrVolumeAdjust);


        ready_Pitch_Speed();

        VolumeAdjust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent OcrVolumeIntent = new Intent(Ocrsetting.this,OcrCaptureActivity.class);
                OcrVolumeIntent.putExtra("MesurePtch",pitch);
                OcrVolumeIntent.putExtra("MesureSpeed",speed);
                startActivity(OcrVolumeIntent);
            }
        });

    }

    private void ready_Pitch_Speed() {

        pitch  = (float)Pitch_bar.getProgress()/50;
        if(pitch<0.1) pitch = 0.1f;
        speed = (float)Speed_bar.getProgress()/50;
        if(speed<0.1) speed = 0.1f;

    }

}
