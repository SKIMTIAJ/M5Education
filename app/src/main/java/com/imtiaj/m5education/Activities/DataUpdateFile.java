package com.imtiaj.m5education.Activities;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.imtiaj.m5education.R;

public class DataUpdateFile extends AppCompatActivity implements View.OnClickListener {

    private TextView collegeUpdate, schoolUpdate, wayUpdate, scholarshipUpdate, talentTestaftertwoelves, simpleCourseUpdate,
    intermediateAftertenUpdate, youtubeVideoupdate;

    private String Updateoption = "doUpdate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_update_file);

        collegeUpdate = (TextView)findViewById(R.id.updateCollegetxt);
        schoolUpdate = (TextView)findViewById(R.id.updateSchooltxt);
        wayUpdate = (TextView)findViewById(R.id.updateWaytxt);
        scholarshipUpdate = (TextView)findViewById(R.id.updateScholarshiptxt);
        talentTestaftertwoelves = (TextView)findViewById(R.id.updateExamp12);
        simpleCourseUpdate = (TextView)findViewById(R.id.updateSimpleCourse);
        intermediateAftertenUpdate = (TextView)findViewById(R.id.UpdatePostInterMediate);
        youtubeVideoupdate = (TextView)findViewById(R.id.UpdateYoutubeVideo);


        collegeUpdate.setOnClickListener(this);
        schoolUpdate.setOnClickListener(this);
        wayUpdate.setOnClickListener(this);
        scholarshipUpdate.setOnClickListener(this);
        talentTestaftertwoelves.setOnClickListener(this);
        simpleCourseUpdate.setOnClickListener(this);
        intermediateAftertenUpdate.setOnClickListener(this);
        youtubeVideoupdate.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

           case  R.id.updateCollegetxt:

               Intent updateClgintent = new Intent(this,College.class);
               updateClgintent.putExtra("updateKey",Updateoption);
               startActivity(updateClgintent);

               break;
            case  R.id.updateSchooltxt:

                break;
            case  R.id.updateWaytxt:

                break;
            case  R.id.updateScholarshiptxt:

                break;
            case  R.id.updateExamp12:

                break;
            case  R.id.updateSimpleCourse:

                break;
            case  R.id.UpdatePostInterMediate:

                break;
            case  R.id.UpdateYoutubeVideo:

                break;

        }

    }
}
