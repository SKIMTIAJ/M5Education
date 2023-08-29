package com.imtiaj.m5education.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.imtiaj.m5education.R;

public class ImageControl extends AppCompatActivity {

    private RadioGroup CollegeRadioGroup,SchoolRadiogroup,ScholarshipRadioGroup;
    private RadioButton clgHideBtn,clgShowBtn, schoolHideBtn, schoolShowBtn, scholarshipHideBtn, scholashipShowBtn;

    private DatabaseReference imageControlDatabase;
    private String ImgClgString,ImgScholString,ImgSchlarshipString;
    private String ImgClgStringid,ImgScholStringid,ImgSchlarshipStringid;


    private LinearLayout banneForHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_control);

        CollegeRadioGroup = (RadioGroup)findViewById(R.id.College_Image_radiogroup);
        ScholarshipRadioGroup = (RadioGroup)findViewById(R.id.Scholarship_Image_radiogroup);
        SchoolRadiogroup = (RadioGroup)findViewById(R.id.School_Image_radiogroup);

        clgHideBtn = (RadioButton)findViewById(R.id.College_Image_radiobtn_Hide);
        clgShowBtn = (RadioButton)findViewById(R.id.College_Image_radiobtn_Show);

        schoolHideBtn = (RadioButton)findViewById(R.id.School_Image_radiobtn_Hide);
        schoolShowBtn = (RadioButton)findViewById(R.id.School_Image_radiobtn_Show);

        scholarshipHideBtn = (RadioButton)findViewById(R.id.Scholarship_Image_radiobtn_Hide);
        scholashipShowBtn = (RadioButton)findViewById(R.id.Scholarship_Image_radiobtn_Show);

        banneForHome = (LinearLayout)findViewById(R.id.bannerAtHomeid);

        imageControlDatabase = FirebaseDatabase.getInstance().getReference().child("CreerL").child("Imgcontrol");


        banneForHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


    public void OnImgRadioBtnClick(View view) {

        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()){

            case R.id.College_Image_radiobtn_Hide:
                ImgClgString = "Hide";
                ImgClgStringid = "Clg";

                break;

            case R.id.College_Image_radiobtn_Show:

                ImgClgString = "Show";
                ImgClgStringid = "Clg";

                break;

            case R.id.School_Image_radiobtn_Hide:

                ImgScholString = "Hide";
                ImgScholStringid = "School";

                break;

            case R.id.School_Image_radiobtn_Show:

                ImgScholString = "Show";
                ImgScholStringid = "School";

                break;

            case R.id.Scholarship_Image_radiobtn_Hide:

                ImgSchlarshipString = "Hide";
                ImgSchlarshipStringid = "Scholarship";

                break;

            case R.id.Scholarship_Image_radiobtn_Show:

                ImgSchlarshipString = "Show";
                ImgSchlarshipStringid = "Scholarship";

                break;

        }
        Starposting();
    }

    private void Starposting() {


    }
}
