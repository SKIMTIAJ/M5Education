package com.imtiaj.m5education.Activities;

import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.imtiaj.m5education.R;

public class FashionDesign_Entrance extends AppCompatActivity {


    private int CheckFashionint = 0;

    private TextView FashionMainButtonid;
    private LinearLayout FashionDataLayid;
    private ListView FashioSchoolList;
    private String FashionschoolName[],FashionSchoolWeb[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fashion_design__entrance);

        FashioSchoolList =(ListView)findViewById(R.id.FashionDesignListView);

        FashionschoolName = getResources().getStringArray(R.array.FashionDesignSchoolnameStringarray);
        FashionSchoolWeb  = getResources().getStringArray(R.array.FashionDesignSchoolwebStringarray);

        FashionMainButtonid = (TextView)findViewById(R.id.FashionDesignButtonid);
        FashionDataLayid = (LinearLayout) findViewById(R.id.FashionDesignLayDataid);

        FashionDesignAdapterClass fashionDesignAdapterClass = new  FashionDesignAdapterClass();
        FashioSchoolList.setAdapter(fashionDesignAdapterClass);

        FashionMainButtonid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckFashionint == 0 ){
                    FashionDataLayid.setVisibility(View.VISIBLE);
                    CheckFashionint = 1;
                }
                else{
                    FashionDataLayid.setVisibility(View.GONE);
                    CheckFashionint = 0;
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        CheckFashionint = 0;
        super.onBackPressed();
    }

    class FashionDesignAdapterClass extends BaseAdapter {


        @Override
        public int getCount() {
            return FashionschoolName.length;
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
            TextView FashionName = (TextView)convertView.findViewById(R.id.PostCourseName);
            TextView FashionWeb = (TextView)convertView.findViewById(R.id.PostCourseEligible);

            FashionName.setText(FashionschoolName[position]);
            FashionWeb.setText(FashionSchoolWeb[position]);

            FashionWeb.setTextColor(ContextCompat.getColor(FashionDesign_Entrance.this,R.color.ChooseQualifition));
            FashionName.setTextColor(ContextCompat.getColor(FashionDesign_Entrance.this,R.color.TitleColor));
            FashionWeb.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimension(R.dimen.fab_margin));
            FashionName.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimension(R.dimen.EntranceName));

            return convertView;
        }
    }


}
