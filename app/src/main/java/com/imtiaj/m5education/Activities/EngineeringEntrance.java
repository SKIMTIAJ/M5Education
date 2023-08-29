package com.imtiaj.m5education.Activities;

import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;

import com.imtiaj.m5education.R;

public class EngineeringEntrance extends AppCompatActivity {

    private Button MainJEEButton,AdvanceJEEButton,BitstButton,VITbutton;
    private LinearLayout MainJEELay,AdvanceJEELay,BitstLay,VitLay;
    private ScrollView MainScrollView;

    private Switch EntranceSwitch;

    private ListView EngineeringList;

    private String EntraceName[], Entraneweb[];
    EngineeringEntranceAdapterClass engineeringEntranceAdapterClass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_engineering_entrance);

        MainJEEButton = (Button)findViewById(R.id.mainJEEExampButtonid);
        AdvanceJEEButton = (Button)findViewById(R.id.AdvanceJEEExampButtonid);
        BitstButton = (Button)findViewById(R.id.BitsatExampButtonid);
        VITbutton = (Button)findViewById(R.id.VITExampButtonid);

        MainScrollView = (ScrollView)findViewById(R.id.EngineeringEntranceScrollid);

        MainJEELay = (LinearLayout)findViewById(R.id.MainJEEExampLayDataid);
        AdvanceJEELay = (LinearLayout)findViewById(R.id.AdvanceJEEExampLayDataid);
        BitstLay = (LinearLayout)findViewById(R.id.BitsatExampLayDataid);
        VitLay = (LinearLayout)findViewById(R.id.VITExampLayDataid);

        EntranceSwitch = (Switch)findViewById(R.id.EngineeringListSwitch);
        EngineeringList = (ListView)findViewById(R.id.EngineeringEntranceListView);

        EntraceName = getResources().getStringArray(R.array.EngineeringEntranceName);
        Entraneweb = getResources().getStringArray(R.array.EngineeringEntranceWeb);

        engineeringEntranceAdapterClass = new EngineeringEntranceAdapterClass();

        EntranceSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true)
                {
                    MainScrollView.setVisibility(View.GONE);
                    EngineeringList.setVisibility(View.VISIBLE);
                    EngineeringList.setAdapter(engineeringEntranceAdapterClass);

                }
                else
                    EngineeringList.setVisibility(View.GONE);
                MainScrollView.setVisibility(View.VISIBLE);

            }
        });




        MainJEEButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainJEELay.setVisibility(View.VISIBLE);
                AdvanceJEELay.setVisibility(View.GONE);
                BitstLay.setVisibility(View.GONE);
                VitLay.setVisibility(View.GONE);
            }
        });

        AdvanceJEEButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainJEELay.setVisibility(View.GONE);
                AdvanceJEELay.setVisibility(View.VISIBLE);
                BitstLay.setVisibility(View.GONE);
                VitLay.setVisibility(View.GONE);
            }
        });

        BitstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainJEELay.setVisibility(View.GONE);
                AdvanceJEELay.setVisibility(View.GONE);
                BitstLay.setVisibility(View.VISIBLE);
                VitLay.setVisibility(View.GONE);
            }
        });

        VITbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainJEELay.setVisibility(View.GONE);
                AdvanceJEELay.setVisibility(View.GONE);
                BitstLay.setVisibility(View.GONE);
                VitLay.setVisibility(View.VISIBLE);

            }
        });

    }



    class EngineeringEntranceAdapterClass extends BaseAdapter {


        @Override
        public int getCount() {
            return EntraceName.length;
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

            convertView = getLayoutInflater().inflate(R.layout.postgraduate_list_layout,null);
            TextView entranceName = (TextView)convertView.findViewById(R.id.PostCourseName);
            TextView entranceweb = (TextView)convertView.findViewById(R.id.PostCourseEligible);

            entranceName.setText(EntraceName[position]);
            entranceweb.setText(Entraneweb[position]);
            entranceweb.setTextColor(ContextCompat.getColor(EngineeringEntrance.this, R.color.ChooseQualifition));
            entranceName.setTextColor(ContextCompat.getColor(EngineeringEntrance.this, R.color.TitleColor));
            entranceweb.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.fab_margin));
            entranceName.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.EntranceName));

            return convertView;
        }
    }

}
