package com.imtiaj.m5education.Activities;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.imtiaj.m5education.R;

public class DataPostfile extends AppCompatActivity {

    private ImageButton OnWayListupdate,OnCollegeListupdate,OnScholarshipUpdate, TalentExampAfterIntermediate,
            simpleCourseList,PostYoutubeVideo,PostSchoolListbtn,PostAfterTen,PostHOmeItemRe,ImageControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_postfile);


        OnCollegeListupdate = (ImageButton)findViewById(R.id.College_List_update);
        OnWayListupdate = (ImageButton)findViewById(R.id.Way_List_Update);
        OnScholarshipUpdate = (ImageButton)findViewById(R.id.Scholarship_List_update);
        TalentExampAfterIntermediate = (ImageButton)findViewById(R.id.Competetive_Examp_12);
        simpleCourseList = (ImageButton)findViewById(R.id.simple_course_List_update) ;
        PostYoutubeVideo = (ImageButton)findViewById(R.id.Youtube_Posting_page_image_btn) ;
        PostSchoolListbtn = (ImageButton)findViewById(R.id.School_List_update);
        PostAfterTen = (ImageButton) findViewById(R.id.AfterTen_Posting_page_image_btn);
        PostHOmeItemRe = (ImageButton)findViewById(R.id.PostRecycleItem_to_home_IMG);
        ImageControl = (ImageButton)findViewById(R.id.toImageControlPage);

        OnWayListupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent wayListupdate_Intent = new Intent(DataPostfile.this, postWayList.class);
                startActivity(wayListupdate_Intent);
            }
        });

        OnCollegeListupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent collegeListupdate_Intent = new Intent(DataPostfile.this, PostCollegeList.class);
                startActivity(collegeListupdate_Intent);
            }
        });


        OnScholarshipUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("Admin Panel","into the onclick listener");
                //Intent scholarshipListUpdate = new Intent(Admin_Panel.this,PostScholarship_list.class);
                //startActivity(scholarshipListUpdate);
                startActivity(new Intent(DataPostfile.this, PostScholarship_list.class));
                //Log.d("admin Panel","After PostScholarship fired");
            }
        });

        TalentExampAfterIntermediate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DataPostfile.this, Post_Intermediate_talent_List.class));
            }
        });

        simpleCourseList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DataPostfile.this, PostSimpleCourseActivity.class));
            }
        });

        PostYoutubeVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(DataPostfile.this, PostYoutubeVideoActivity.class));

            }
        });

        PostSchoolListbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(DataPostfile.this, PostSchoolList.class));
            }
        });


        PostAfterTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DataPostfile.this, PostAfterTen_IntermediateCourse.class));
            }
        });

        PostHOmeItemRe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DataPostfile.this, PostHomeItem_recycler.class));
            }
        });


        ImageControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DataPostfile.this,ImageControl.class));
            }
        });

    }
}
