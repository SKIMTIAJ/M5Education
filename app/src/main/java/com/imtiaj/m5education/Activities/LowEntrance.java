package com.imtiaj.m5education.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.imtiaj.m5education.R;

public class LowEntrance extends AppCompatActivity {

    private int For_Lowentrance_Page;

    private Button CommonLawAdmissionButton,LawSchoolButton,ALLETButton,LETButton;
    private LinearLayout CommonLawAdmissionLay,LawSchoolLay,ALLETLay,LETLay;

    private Button AgricultureButton,HotelManagementButton;
    private LinearLayout AgricultureLay,HotelmanagementLay;

    private ScrollView LawScroll,AgricultureScroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_low_entrance);


        CommonLawAdmissionButton = (Button)findViewById(R.id.CommonLawEntranceButtonid);
        LawSchoolButton = (Button)findViewById(R.id.LawSchoolButtonid);
        ALLETButton = (Button)findViewById(R.id.ALLETEntranceButtonid);
        LETButton = (Button)findViewById(R.id.LETEntranceButtonid);

        CommonLawAdmissionLay = (LinearLayout)findViewById(R.id.CommonLawEntranceLayDataid);
        LawSchoolLay = (LinearLayout)findViewById(R.id.LawSchoolLayDataid);
        ALLETLay = (LinearLayout)findViewById(R.id.ALLETEntranceLayDataid);
        LETLay = (LinearLayout)findViewById(R.id.LETEntranceLayDataid);

        AgricultureButton = (Button)findViewById(R.id.AgricultureResearchButtonid);
        HotelManagementButton = (Button)findViewById(R.id.HotelManagementEntranceButtonid);

        AgricultureLay = (LinearLayout)findViewById(R.id.AgricultureResearchLayDataid);
        HotelmanagementLay = (LinearLayout)findViewById(R.id.HotelManagementEntranceLayDataid);

        LawScroll = (ScrollView)findViewById(R.id.LawEntranceScrollid);
        AgricultureScroll = (ScrollView)findViewById(R.id.AgricultureScrollid);

        Bundle getUpcommingintent = getIntent().getExtras();
        For_Lowentrance_Page = getUpcommingintent.getInt("ForLowEntrance");

        switch (For_Lowentrance_Page){

            case 1:

                LawScroll.setVisibility(View.VISIBLE);
                AgricultureScroll.setVisibility(View.GONE);

                break;

            case 2:

                LawScroll.setVisibility(View.GONE);
                AgricultureScroll.setVisibility(View.VISIBLE);

                break;
        }



        CommonLawAdmissionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LawScroll.setVisibility(View.VISIBLE);
                AgricultureScroll.setVisibility(View.GONE);
                CommonLawAdmissionLay.setVisibility(View.VISIBLE);
                LawSchoolLay.setVisibility(View.GONE);
                ALLETLay.setVisibility(View.GONE);
                LETLay.setVisibility(View.GONE);

            }
        });

        LawSchoolButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LawScroll.setVisibility(View.VISIBLE);
                AgricultureScroll.setVisibility(View.GONE);
                CommonLawAdmissionLay.setVisibility(View.GONE);
                LawSchoolLay.setVisibility(View.VISIBLE);
                ALLETLay.setVisibility(View.GONE);
                LETLay.setVisibility(View.GONE);

            }
        });

        ALLETButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LawScroll.setVisibility(View.VISIBLE);
                AgricultureScroll.setVisibility(View.GONE);
                CommonLawAdmissionLay.setVisibility(View.GONE);
                LawSchoolLay.setVisibility(View.GONE);
                ALLETLay.setVisibility(View.VISIBLE);
                LETLay.setVisibility(View.GONE);

            }
        });

        LETButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LawScroll.setVisibility(View.VISIBLE);
                AgricultureScroll.setVisibility(View.GONE);
                CommonLawAdmissionLay.setVisibility(View.GONE);
                LawSchoolLay.setVisibility(View.GONE);
                ALLETLay.setVisibility(View.GONE);
                LETLay.setVisibility(View.VISIBLE);

            }
        });

        AgricultureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LawScroll.setVisibility(View.GONE);
                AgricultureScroll.setVisibility(View.VISIBLE);
                AgricultureLay.setVisibility(View.VISIBLE);
                HotelmanagementLay.setVisibility(View.GONE);

            }
        });

        HotelManagementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LawScroll.setVisibility(View.GONE);
                AgricultureScroll.setVisibility(View.VISIBLE);
                AgricultureLay.setVisibility(View.GONE);
                HotelmanagementLay.setVisibility(View.VISIBLE);

            }
        });


    }
}
