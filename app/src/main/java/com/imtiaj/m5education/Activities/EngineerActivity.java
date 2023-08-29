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

public class EngineerActivity extends AppCompatActivity {

    private LinearLayout EngineerActivityMainLay;
    private LinearLayout EngineerListLay;
    private String EngineerinListArray[];
    private ListView EngineeringListOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_engineer);

        EngineerActivityMainLay = (LinearLayout)findViewById(R.id.Intermediate_gellery_ScrollView_for_Engineering);
        EngineerListLay = (LinearLayout)findViewById(R.id.Engineering_List_Layout);

        EngineerinListArray = getResources().getStringArray(R.array.EngineeringView_for);
        EngineeringListOne = (ListView)findViewById(R.id.EngineerListone);

        EngineerListAdapter engineerListAdapter = new EngineerListAdapter();
        EngineeringListOne.setAdapter(engineerListAdapter);

    }

    class EngineerListAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return EngineerinListArray.length ;
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

            medirowText.setText(EngineerinListArray[position]);
            return convertView;
        }
    }

}
