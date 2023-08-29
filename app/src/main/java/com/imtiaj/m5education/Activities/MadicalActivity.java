package com.imtiaj.m5education.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.imtiaj.m5education.R;

public class MadicalActivity extends AppCompatActivity {

    private int medibuttonst;

    private LinearLayout MedicalScroll;
    private LinearLayout MedicineLayout,HealtLayout,PeramedicalLayout,BAListLay_under_frame;

    private String MedicalAltArray[];
    private String medicalStringArry[], healthService_stringarray[], PeramedicalStringArray[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_madical);

        MedicalScroll = (LinearLayout) findViewById(R.id.Intermediate_gellery_ScrollView_for_medicalTest);

        MedicineLayout = (LinearLayout)findViewById(R.id.MedicineLayouttest);
        HealtLayout = (LinearLayout)findViewById(R.id.HealtLayouttest);
        PeramedicalLayout = (LinearLayout)findViewById(R.id.PeramedicalLayouttest);

        medicalStringArry = getResources().getStringArray(R.array.medicalListView_for);
        healthService_stringarray = getResources().getStringArray(R.array.healthListView_for);
        PeramedicalStringArray = getResources().getStringArray(R.array.PeramedicalListView_for);



        ListView MediList = (ListView)findViewById(R.id.MedicalListone);
        ListView Helthlist = (ListView)findViewById(R.id.Health_serviceone);
        ListView peraList = (ListView)findViewById(R.id.PeramedicalListone);

        MedicalListAdapter classAdapter = new MedicalListAdapter();

        Bundle MediButton = getIntent().getExtras();
        medibuttonst = MediButton.getInt("forMedicine");








        switch (medibuttonst)
        {

            case 2:

                MedicalAltArray = medicalStringArry;
                MediList.setAdapter(classAdapter);

                MedicalScroll.setVisibility(View.VISIBLE);
                /*EngineeringScroll.setVisibility(View.GONE);
                TeachingScroll.setVisibility(View.GONE);
                DegreeScroll.setVisibility(View.GONE);
                OthersScroll.setVisibility(View.GONE);
                NDAScrool.setVisibility(View.GONE);*/

                MedicineLayout.setVisibility(View.VISIBLE);
                HealtLayout.setVisibility(View.GONE);
                PeramedicalLayout.setVisibility(View.GONE);

                break;

            case 3:

                MedicalAltArray = healthService_stringarray;
                Helthlist.setAdapter(classAdapter);


                MedicalScroll.setVisibility(View.VISIBLE);
                /*EngineeringScroll.setVisibility(View.GONE);
                TeachingScroll.setVisibility(View.GONE);
                DegreeScroll.setVisibility(View.GONE);
                OthersScroll.setVisibility(View.GONE);
                NDAScrool.setVisibility(View.GONE);*/

                MedicineLayout.setVisibility(View.GONE);
                HealtLayout.setVisibility(View.VISIBLE);
                PeramedicalLayout.setVisibility(View.GONE);

                break;

            case 4:

                MedicalAltArray = PeramedicalStringArray;
                peraList.setAdapter(classAdapter);


                MedicalScroll.setVisibility(View.VISIBLE);
               /* EngineeringScroll.setVisibility(View.GONE);
                TeachingScroll.setVisibility(View.GONE);
                DegreeScroll.setVisibility(View.GONE);
                OthersScroll.setVisibility(View.GONE);
                NDAScrool.setVisibility(View.GONE);*/

                MedicineLayout.setVisibility(View.GONE);
                HealtLayout.setVisibility(View.GONE);
                PeramedicalLayout.setVisibility(View.VISIBLE);

                break;
            default:

                MedicalAltArray = medicalStringArry;
                MediList.setAdapter(classAdapter);

                MedicalScroll.setVisibility(View.VISIBLE);
                /*EngineeringScroll.setVisibility(View.GONE);
                TeachingScroll.setVisibility(View.GONE);
                DegreeScroll.setVisibility(View.GONE);
                OthersScroll.setVisibility(View.GONE);
                NDAScrool.setVisibility(View.GONE);*/

                MedicineLayout.setVisibility(View.VISIBLE);
                HealtLayout.setVisibility(View.GONE);
                PeramedicalLayout.setVisibility(View.GONE);


        }

    }


    class MedicalListAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return MedicalAltArray.length ;
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

            convertView = getLayoutInflater().inflate(R.layout.medicallistrow,null);
            TextView medirowText = (TextView)convertView.findViewById(R.id.mainMedicalText);

            medirowText.setText(MedicalAltArray[position]);
            return convertView;
        }
    }

}
