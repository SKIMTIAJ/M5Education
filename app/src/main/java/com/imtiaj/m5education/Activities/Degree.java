package com.imtiaj.m5education.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.imtiaj.m5education.R;

public class Degree extends AppCompatActivity {

    private int Degreeall,ProfessionDegreeAll;
    private TextView CPTAbout,CPTEligiblity,CPTApplicationMode,CPTTestMode,CPTSourceMode;

    private ScrollView ProfetionalMainScroll_under_frame;
    private LinearLayout BAListLay_under_frame, BScListLay_under_frame, BComListLay_under_frame, OtherDegreListLay_under_frame, DualDegreListLay_under_frame
            ,CALay_Under_Frame;

    private String BAStringArray[], BScStringArray[], BComStringArray[], OtherCorseStringArray[], DualDegreeStrinArray[];
    private String AnewArrayholder[];

    private Button CPTBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_degree);

        CPTBTN = (Button)findViewById(R.id.ProfetionalListLay_under_frame_BtnText);

        BAListLay_under_frame = (LinearLayout) findViewById(R.id.BAListLay_under_frame);
        BScListLay_under_frame = (LinearLayout) findViewById(R.id.BScListLay_under_frame);
        BComListLay_under_frame = (LinearLayout) findViewById(R.id.BComListLay_under_frame);
        OtherDegreListLay_under_frame = (LinearLayout) findViewById(R.id.OtherDegreListLay_under_frame);
        DualDegreListLay_under_frame = (LinearLayout) findViewById(R.id.DualDegreListLay_under_frame);
        CALay_Under_Frame = (LinearLayout)findViewById(R.id.ProfetionalListLay_under_frame_CALay);

        ProfetionalMainScroll_under_frame = (ScrollView) findViewById(R.id.ProfetionalMainScroll_under_frame);


        BAStringArray = getResources().getStringArray(R.array.BAListView_for);
        BScStringArray = getResources().getStringArray(R.array.BScListView_for);
        BComStringArray = getResources().getStringArray(R.array.BComListView_for);
        OtherCorseStringArray = getResources().getStringArray(R.array.OtherDListView_for);
        DualDegreeStrinArray = getResources().getStringArray(R.array.DualDListView_for);

        CPTAbout = (TextView)findViewById(R.id.About_CPT_ExampDetailsid);
        CPTApplicationMode = (TextView)findViewById(R.id.About_CPT_AplicationmodeDetailsid);
        CPTEligiblity = (TextView)findViewById(R.id.About_CPT_EligiblityDetailsid);
        CPTTestMode = (TextView)findViewById(R.id.About_CPT_TestDetailsid);
        CPTSourceMode = (TextView)findViewById(R.id.About_CPT_MoreDetailsid);


        ListView BAList = (ListView) findViewById(R.id.BAList);
        ListView BScList = (ListView) findViewById(R.id.BScList);
        ListView BComList = (ListView) findViewById(R.id.BComList);
        ListView OtherList = (ListView) findViewById(R.id.OtherDegreList);
        ListView DualDList = (ListView) findViewById(R.id.DualDegreList);


        DegreeallAdapter DegreeListAdapterClass = new DegreeallAdapter();

        Bundle degreeAll = getIntent().getExtras();
        Degreeall = degreeAll.getInt("Degreerespons");

        Bundle professionDegreeAll = getIntent().getExtras();
        ProfessionDegreeAll = professionDegreeAll.getInt("ProfessionalDegree");

        switch (Degreeall) {

            case 1:

                AnewArrayholder = BAStringArray;
                BAList.setAdapter(DegreeListAdapterClass);

                /*MedicalScroll.setVisibility(View.GONE);
                EngineeringScroll.setVisibility(View.GONE);
                TeachingScroll.setVisibility(View.GONE);
                DegreeScroll.setVisibility(View.VISIBLE);
                OthersScroll.setVisibility(View.GONE);
                NDAScrool.setVisibility(View.GONE);*/

                BAListLay_under_frame.setVisibility(View.VISIBLE);
                BScListLay_under_frame.setVisibility(View.GONE);
                BComListLay_under_frame.setVisibility(View.GONE);
                OtherDegreListLay_under_frame.setVisibility(View.GONE);

                break;

            case 2:

                AnewArrayholder = BScStringArray;
                BScList.setAdapter(DegreeListAdapterClass);

               /* MedicalScroll.setVisibility(View.GONE);
                EngineeringScroll.setVisibility(View.GONE);
                TeachingScroll.setVisibility(View.GONE);
                DegreeScroll.setVisibility(View.VISIBLE);
                OthersScroll.setVisibility(View.GONE);
                NDAScrool.setVisibility(View.GONE);*/

                BAListLay_under_frame.setVisibility(View.GONE);
                BScListLay_under_frame.setVisibility(View.VISIBLE);
                BComListLay_under_frame.setVisibility(View.GONE);
                OtherDegreListLay_under_frame.setVisibility(View.GONE);


                break;

            case 3:

                AnewArrayholder = BComStringArray;
                BComList.setAdapter(DegreeListAdapterClass);
/*
                MedicalScroll.setVisibility(View.GONE);
                EngineeringScroll.setVisibility(View.GONE);
                TeachingScroll.setVisibility(View.GONE);
                DegreeScroll.setVisibility(View.VISIBLE);
                OthersScroll.setVisibility(View.GONE);
                NDAScrool.setVisibility(View.GONE);*/

                BAListLay_under_frame.setVisibility(View.GONE);
                BScListLay_under_frame.setVisibility(View.GONE);
                BComListLay_under_frame.setVisibility(View.VISIBLE);
                OtherDegreListLay_under_frame.setVisibility(View.GONE);


                break;

            case 4:

                AnewArrayholder = OtherCorseStringArray;
                OtherList.setAdapter(DegreeListAdapterClass);
/*
                MedicalScroll.setVisibility(View.GONE);
                EngineeringScroll.setVisibility(View.GONE);
                TeachingScroll.setVisibility(View.GONE);
                DegreeScroll.setVisibility(View.VISIBLE);
                OthersScroll.setVisibility(View.GONE);
                NDAScrool.setVisibility(View.GONE);*/

                BAListLay_under_frame.setVisibility(View.GONE);
                BScListLay_under_frame.setVisibility(View.GONE);
                BComListLay_under_frame.setVisibility(View.GONE);
                OtherDegreListLay_under_frame.setVisibility(View.VISIBLE);
                break;

            case 5:


                AnewArrayholder = DualDegreeStrinArray;
                DualDList.setAdapter(DegreeListAdapterClass);
                /*MedicalScroll.setVisibility(View.GONE);
                EngineeringScroll.setVisibility(View.GONE);
                TeachingScroll.setVisibility(View.GONE);
                DegreeScroll.setVisibility(View.VISIBLE);
                OthersScroll.setVisibility(View.GONE);
                NDAScrool.setVisibility(View.GONE);*/

                BAListLay_under_frame.setVisibility(View.GONE);
                BScListLay_under_frame.setVisibility(View.GONE);
                BComListLay_under_frame.setVisibility(View.GONE);
                OtherDegreListLay_under_frame.setVisibility(View.GONE);
                DualDegreListLay_under_frame.setVisibility(View.VISIBLE);

                break;

            default:

                AnewArrayholder = BAStringArray;
                BAList.setAdapter(DegreeListAdapterClass);

               /* MedicalScroll.setVisibility(View.GONE);
                EngineeringScroll.setVisibility(View.GONE);
                TeachingScroll.setVisibility(View.GONE);
                DegreeScroll.setVisibility(View.VISIBLE);
                OthersScroll.setVisibility(View.GONE);
                NDAScrool.setVisibility(View.GONE);
*/
                BAListLay_under_frame.setVisibility(View.VISIBLE);
                BScListLay_under_frame.setVisibility(View.GONE);
                BComListLay_under_frame.setVisibility(View.GONE);
                OtherDegreListLay_under_frame.setVisibility(View.GONE);
                DualDegreListLay_under_frame.setVisibility(View.GONE);

                break;


        }


        switch (ProfessionDegreeAll){
            case 1:
                /*MedicalScroll.setVisibility(View.GONE);
                EngineeringScroll.setVisibility(View.GONE);
                TeachingScroll.setVisibility(View.GONE);
                DegreeScroll.setVisibility(View.VISIBLE);
                OthersScroll.setVisibility(View.GONE);
                NDAScrool.setVisibility(View.GONE);*/

                BAListLay_under_frame.setVisibility(View.GONE);
                BScListLay_under_frame.setVisibility(View.GONE);
                BComListLay_under_frame.setVisibility(View.GONE);
                OtherDegreListLay_under_frame.setVisibility(View.GONE);
                DualDegreListLay_under_frame.setVisibility(View.GONE);

                ProfetionalMainScroll_under_frame.setVisibility(View.VISIBLE);
                CALay_Under_Frame.setVisibility(View.VISIBLE);
                CPTBTN.setVisibility(View.VISIBLE);

                break;

            case 2:

              /*  MedicalScroll.setVisibility(View.GONE);
                EngineeringScroll.setVisibility(View.GONE);
                TeachingScroll.setVisibility(View.GONE);
                DegreeScroll.setVisibility(View.VISIBLE);
                OthersScroll.setVisibility(View.GONE);
                NDAScrool.setVisibility(View.GONE);*/

                BAListLay_under_frame.setVisibility(View.GONE);
                BScListLay_under_frame.setVisibility(View.GONE);
                BComListLay_under_frame.setVisibility(View.GONE);
                OtherDegreListLay_under_frame.setVisibility(View.GONE);
                DualDegreListLay_under_frame.setVisibility(View.GONE);

                ProfetionalMainScroll_under_frame.setVisibility(View.VISIBLE);
                CPTBTN.setVisibility(View.GONE);
                CALay_Under_Frame.setVisibility(View.GONE);

                break;
            case 3:

                break;
            case 4:

                break;
            default:


        }

    }

    class DegreeallAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return AnewArrayholder.length;
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

            convertView = getLayoutInflater().inflate(R.layout.medicallistrow, null);
            TextView PeramedicalrowText = (TextView) convertView.findViewById(R.id.mainMedicalText);

            PeramedicalrowText.setText(AnewArrayholder[position]);
            return convertView;

        }
    }


}
