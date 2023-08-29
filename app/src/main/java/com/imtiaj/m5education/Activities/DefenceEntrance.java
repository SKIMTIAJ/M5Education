package com.imtiaj.m5education.Activities;

import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.imtiaj.m5education.R;

public class DefenceEntrance extends AppCompatActivity {

    private int MedicalDataCheck = 0;

    private Button MaritimeUniverisityButton,NavyBtechButton,NavySailorButton,ArmyTechnicianButton,NavalAcademyButton;

    private LinearLayout MaritimeLay,NavyBtechLay,NavySailorLay,ArmyTechLay,NavalAcademyLay;
    private TextView MedicalEntranceButton;

    private LinearLayout MedicalmainLay,MedicalDataLay;
    private ScrollView DefenceMainScroll;
    private ListView MedicalEntranceList;
    private int FromDefence_and_medical_switch;
    private String MedicalName[],MedicalWebsite[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defence_entrance);


        MaritimeUniverisityButton = (Button)findViewById(R.id.IndianMaritimeButtonid);
        NavyBtechButton = (Button)findViewById(R.id.NavyBtechEntryButtonid);
        NavySailorButton = (Button)findViewById(R.id.NavySailorsButtonid);
        ArmyTechnicianButton = (Button)findViewById(R.id.ArmyTechnicianButtonid);
        NavalAcademyButton = (Button)findViewById(R.id.NavalAcademyButtonid);

        MedicalEntranceButton = (TextView)findViewById(R.id.MedicalEntranceButtonid);
        MedicalDataLay =  (LinearLayout)findViewById(R.id.MedicalEntranceLayDataid);


        MaritimeLay = (LinearLayout)findViewById(R.id.IndianMaritimeLayDataid);
        NavyBtechLay = (LinearLayout)findViewById(R.id.NavyBtechEntryLayDataid);
        NavySailorLay = (LinearLayout)findViewById(R.id.NavySailorsLayDataid);
        ArmyTechLay = (LinearLayout)findViewById(R.id.ArmyTechnicianLayDataid);
        NavalAcademyLay = (LinearLayout)findViewById(R.id.NavalAcademyLayDataid);


        MedicalmainLay = (LinearLayout)findViewById(R.id.MedicalEntranceMainLay);
        DefenceMainScroll = (ScrollView)findViewById(R.id.DefenceEntranceScrollid);
        MedicalEntranceList = (ListView)findViewById(R.id.MedicalEntranceListView);

        MedicalName = getResources().getStringArray(R.array.MedicalEntranceName);
        MedicalWebsite = getResources().getStringArray(R.array.MedicalEntranceWeb);


        Bundle DefenceBundle = getIntent().getExtras();
        FromDefence_and_medical_switch = DefenceBundle.getInt("ForDefenceClass");

        MedicalEntranceAdapterClass medicalEntranceAdapterClass = new MedicalEntranceAdapterClass();


        MedicalEntranceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (MedicalDataCheck == 0) {
                    MedicalDataLay.setVisibility(View.VISIBLE);
                    MedicalDataCheck = 1;
                }
                else {
                    MedicalDataLay.setVisibility(View.GONE);
                    MedicalDataCheck = 0;
                }
            }
        });


        switch (FromDefence_and_medical_switch){

            case 1:

                MedicalmainLay.setVisibility(View.VISIBLE);
                DefenceMainScroll.setVisibility(View.GONE);
                MedicalEntranceList.setAdapter(medicalEntranceAdapterClass);
                break;

            case 2:
                DefenceMainScroll.setVisibility(View.VISIBLE);
                MedicalmainLay.setVisibility(View.GONE);
                break;

        }
        MaritimeUniverisityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MedicalmainLay.setVisibility(View.GONE);
                DefenceMainScroll.setVisibility(View.VISIBLE);
                MaritimeLay.setVisibility(View.VISIBLE);
                NavyBtechLay.setVisibility(View.GONE);
                NavySailorLay.setVisibility(View.GONE);
                ArmyTechLay.setVisibility(View.GONE);
                NavalAcademyLay.setVisibility(View.GONE);
            }
        });


        NavyBtechButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MedicalmainLay.setVisibility(View.GONE);
                DefenceMainScroll.setVisibility(View.VISIBLE);
                MaritimeLay.setVisibility(View.GONE);
                NavyBtechLay.setVisibility(View.VISIBLE);
                NavySailorLay.setVisibility(View.GONE);
                ArmyTechLay.setVisibility(View.GONE);
                NavalAcademyLay.setVisibility(View.GONE);
            }
        });


        NavySailorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MedicalmainLay.setVisibility(View.GONE);
                DefenceMainScroll.setVisibility(View.VISIBLE);
                MaritimeLay.setVisibility(View.GONE);
                NavyBtechLay.setVisibility(View.GONE);
                NavySailorLay.setVisibility(View.VISIBLE);
                ArmyTechLay.setVisibility(View.GONE);
                NavalAcademyLay.setVisibility(View.GONE);
            }
        });


        ArmyTechnicianButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MedicalmainLay.setVisibility(View.GONE);
                DefenceMainScroll.setVisibility(View.VISIBLE);
                MaritimeLay.setVisibility(View.GONE);
                NavyBtechLay.setVisibility(View.GONE);
                NavySailorLay.setVisibility(View.GONE);
                ArmyTechLay.setVisibility(View.VISIBLE);
                NavalAcademyLay.setVisibility(View.GONE);
            }
        });


        NavalAcademyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MedicalmainLay.setVisibility(View.GONE);
                DefenceMainScroll.setVisibility(View.VISIBLE);
                MaritimeLay.setVisibility(View.GONE);
                NavyBtechLay.setVisibility(View.GONE);
                NavySailorLay.setVisibility(View.GONE);
                ArmyTechLay.setVisibility(View.GONE);
                NavalAcademyLay.setVisibility(View.VISIBLE);
            }
        });

    }


    @Override
    public void onBackPressed() {
        MedicalDataCheck = 0;
        super.onBackPressed();
    }

    class MedicalEntranceAdapterClass extends BaseAdapter {


        @Override
        public int getCount() {
            return MedicalName.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView =getLayoutInflater().inflate(R.layout.postgraduate_list_layout,null);
            TextView MediName = (TextView)convertView.findViewById(R.id.PostCourseName);
            TextView MediWeb = (TextView)convertView.findViewById(R.id.PostCourseEligible);

            MediName.setText(MedicalName[position]);
            MediWeb.setText(MedicalWebsite[position]);

            MediWeb.setTextColor(ContextCompat.getColor(DefenceEntrance.this, R.color.ChooseQualifition));
            MediName.setTextColor(ContextCompat.getColor(DefenceEntrance.this, R.color.TitleColor));
            MediWeb.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.fab_margin));
            MediName.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.EntranceName));

            return convertView;
        }
    }

}
