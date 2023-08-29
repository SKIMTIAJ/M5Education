package com.imtiaj.m5education.Activities;

import android.app.ProgressDialog;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.imtiaj.m5education.R;
import com.imtiaj.m5education.modelClass.YoutubeUploadPOjo;

import java.util.ArrayList;

public class PostYoutubeVideoActivity extends AppCompatActivity {

    private EditText YoutubeUrl,YoutubeText,YoutubeDec;
    private Button YoutubeSubmitButton;
    private TextView YoutubeHeadText;
    private ArrayList<YoutubeUploadPOjo> YoutubeArrayListPojoClassSent;

    private DatabaseReference YoutubeDataPostRef;
    private ProgressDialog YoutubePoastingProgress;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_youtube_video);


        YoutubeText = (EditText)findViewById(R.id.YoutubeVideoTextid);
        YoutubeDec = (EditText)findViewById(R.id.YoutubeVideoDesid);
        YoutubeUrl = (EditText)findViewById(R.id.YoutubeVideoUrlid);

        YoutubePoastingProgress = new ProgressDialog(this);

        YoutubeSubmitButton = (Button)findViewById(R.id.YoutubeVideoButtonid);

        YoutubeDataPostRef = FirebaseDatabase.getInstance().getReference("CreerL/YoutubeData");

        YoutubeSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoutubeDataPosting();
            }
        });


    }



    private void YoutubeDataPosting(){

        YoutubePoastingProgress.setMessage("Data is Posting ....");
        YoutubePoastingProgress.show();

        if(!TextUtils.isEmpty(YoutubeUrl.getText().toString())){

            YoutubeUploadPOjo YoutubePojoClassObj = new YoutubeUploadPOjo(YoutubeUrl.getText().toString().trim(),
                    YoutubeText.getText().toString().trim(),YoutubeDec.getText().toString().trim());

            String UploadYoutubedata = YoutubeDataPostRef.push().getKey();

            YoutubeDataPostRef.child(UploadYoutubedata).setValue(YoutubePojoClassObj);

            YoutubePoastingProgress.dismiss();
            Snackbar.make(YoutubeSubmitButton,"Data is Saved",Snackbar.LENGTH_SHORT).show();


        }


    }



}
