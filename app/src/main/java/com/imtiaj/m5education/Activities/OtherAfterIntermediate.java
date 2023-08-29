package com.imtiaj.m5education.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.imtiaj.m5education.R;

public class OtherAfterIntermediate extends AppCompatActivity {

    private int IntermediateOtherListChooser;

    private LinearLayout MainOtherLinearLay;
    private Button Designing,flimMaking,Management;
    private ListView mainList,DesigneList,FilmMakingList,ManagementList;
    private String MainStringarray[],DesigningStringarray[],FilmMakinStringArray[],ManagementStringArray[];
    private String IntermediateOtherTempArray[];
    AfterIntermediateOtherAdapter afterIntermediateOtherAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_after_intermediate);

        Designing = (Button)findViewById(R.id.AfterIntermediate_Othres_Designing_Button);
        flimMaking = (Button)findViewById(R.id.AfterIntermediate_Othres_flim_Button);
        Management = (Button)findViewById(R.id.AfterIntermediate_Othres_Management_Button);

        MainOtherLinearLay =(LinearLayout)findViewById(R.id.Intermediate_gellery_ScrollView_for_Others);
        Designing = (Button)findViewById(R.id.AfterIntermediate_Othres_Designing_Button);
        flimMaking = (Button)findViewById(R.id.AfterIntermediate_Othres_flim_Button);
        Management = (Button)findViewById(R.id.AfterIntermediate_Othres_Management_Button);

        mainList = (ListView)findViewById(R.id.Other_main_list);
        DesigneList = (ListView)findViewById(R.id.Other_Designe_list);
        FilmMakingList = (ListView)findViewById(R.id.Other_flim_making_list);
        ManagementList = (ListView)findViewById(R.id.Other_Management_list);

        MainStringarray = getResources().getStringArray(R.array.Aftre_intermediate_Other_mainlist_for);
        DesigningStringarray = getResources().getStringArray(R.array.Aftre_intermediate_Other_Designing_for);
        FilmMakinStringArray = getResources().getStringArray(R.array.Aftre_intermediate_Other_Film_for);
        ManagementStringArray = getResources().getStringArray(R.array.Aftre_intermediate_Other_Management_for);

        afterIntermediateOtherAdapter = new AfterIntermediateOtherAdapter();



        Designing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntermediateOtherListChooser = 2;

                IntermediateOtherTempArray = DesigningStringarray;
                DesigneList.setAdapter(afterIntermediateOtherAdapter);

                mainList.setVisibility(View.GONE);
                DesigneList.setVisibility(View.VISIBLE);
                FilmMakingList.setVisibility(View.GONE);
                ManagementList.setVisibility(View.GONE);
            }
        });

        flimMaking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntermediateOtherListChooser = 3;
                IntermediateOtherTempArray = FilmMakinStringArray;
                FilmMakingList.setAdapter(afterIntermediateOtherAdapter);

                mainList.setVisibility(View.GONE);
                DesigneList.setVisibility(View.GONE);
                FilmMakingList.setVisibility(View.VISIBLE);
                ManagementList.setVisibility(View.GONE);

            }
        });

        Management.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntermediateOtherListChooser = 4;
                IntermediateOtherTempArray = ManagementStringArray;
                ManagementList.setAdapter(afterIntermediateOtherAdapter);

                mainList.setVisibility(View.GONE);
                DesigneList.setVisibility(View.GONE);
                FilmMakingList.setVisibility(View.GONE);
                ManagementList.setVisibility(View.VISIBLE);

            }
        });

        switch (IntermediateOtherListChooser){

            case 2:

                break;

            case 3:


                break;

            case 4:

                break;

            default:

                IntermediateOtherTempArray = MainStringarray;
                mainList.setAdapter(afterIntermediateOtherAdapter);

                mainList.setVisibility(View.VISIBLE);
                DesigneList.setVisibility(View.GONE);
                FilmMakingList.setVisibility(View.GONE);
                ManagementList.setVisibility(View.GONE);


        }


    }

    class AfterIntermediateOtherAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return IntermediateOtherTempArray.length;
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

            convertView = getLayoutInflater().inflate(R.layout.after_intermediate_other_list_row,null);
            TextView OtherRowtext = (TextView)convertView.findViewById(R.id.AfterIntermediate_Other_row_Text);

            OtherRowtext.setText(IntermediateOtherTempArray[position]);
            return convertView;
        }
    }

}
