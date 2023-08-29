package com.imtiaj.m5education.Activities;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.imtiaj.m5education.R;

public class Admin_Panel extends AppCompatActivity implements View.OnClickListener {


    private ImageButton OnWayListupdate,OnCollegeListupdate,OnScholarshipUpdate, TalentExampAfterIntermediate,
            simpleCourseList,PostYoutubeVideo,PostSchoolListbtn,PostAfterTen;

    private LinearLayout UploadLinear, UpdateLinear, DeleteLinear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__panel);


        UploadLinear = (LinearLayout)findViewById(R.id.UploadlayouttextMain);
        UpdateLinear = (LinearLayout)findViewById(R.id.UpdatelayouttextMain);
        DeleteLinear = (LinearLayout)findViewById(R.id.DeletelayouttextMain);


        UploadLinear.setOnClickListener(this);
        UpdateLinear.setOnClickListener(this);
        DeleteLinear.setOnClickListener(this);

/*
        OnCollegeListupdate = (ImageButton)findViewById(R.id.College_List_update);
        OnWayListupdate = (ImageButton)findViewById(R.id.Way_List_Update);
        OnScholarshipUpdate = (ImageButton)findViewById(R.id.Scholarship_List_update);
        TalentExampAfterIntermediate = (ImageButton)findViewById(R.id.Competetive_Examp_12);
        simpleCourseList = (ImageButton)findViewById(R.id.simple_course_List_update) ;
        PostYoutubeVideo = (ImageButton)findViewById(R.id.Youtube_Posting_page_image_btn) ;
        PostSchoolListbtn = (ImageButton)findViewById(R.id.School_List_update);
        PostAfterTen = (ImageButton) findViewById(R.id.AfterTen_Posting_page_image_btn);

        OnWayListupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent wayListupdate_Intent = new Intent(Admin_Panel.this,postWayList.class);
                startActivity(wayListupdate_Intent);
            }
        });

       OnCollegeListupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent collegeListupdate_Intent = new Intent(Admin_Panel.this,PostCollegeList.class);
                startActivity(collegeListupdate_Intent);
            }
        });


        OnScholarshipUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("Admin Panel","into the onclick listener");
                //Intent scholarshipListUpdate = new Intent(Admin_Panel.this,PostScholarship_list.class);
                //startActivity(scholarshipListUpdate);
                startActivity(new Intent(Admin_Panel.this,PostScholarship_list.class));
                //Log.d("admin Panel","After PostScholarship fired");
            }
        });

        TalentExampAfterIntermediate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin_Panel.this,Post_Intermediate_talent_List.class));
            }
        });

        simpleCourseList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin_Panel.this,PostSimpleCourseActivity.class));
            }
        });

        PostYoutubeVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Admin_Panel.this,PostYoutubeVideoActivity.class));

            }
        });

        PostSchoolListbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Admin_Panel.this,PostSchoolList.class));
            }
        });


        PostAfterTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin_Panel.this,PostAfterTen_IntermediateCourse.class));
            }
        });*/

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.UploadlayouttextMain:
                startActivity(new Intent(Admin_Panel.this, DataPostfile.class));
                break;
            case R.id.UpdatelayouttextMain:
                startActivity(new Intent(Admin_Panel.this,DataUpdateFile.class));;
                break;
            case R.id.DeletelayouttextMain:
                Toast.makeText(this,"Delete is clicked", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
