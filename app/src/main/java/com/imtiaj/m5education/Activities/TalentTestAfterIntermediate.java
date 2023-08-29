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

public class TalentTestAfterIntermediate extends AppCompatActivity {

    private String TalentSchoolLavelNameArray[],TalantarrayEligiblity[],TalentSyllabusarray[],TalentWebsiteArray[];


    private LinearLayout SchoolLaveTalentLay;
    private TextView SchoolHeadTextName ;
    private ListView SchoolTestList  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talent_test_after_intermediate);


        TalentSchoolLavelNameArray = getResources().getStringArray(R.array.TalentSchoolLevelNameStringarray);

        TalantarrayEligiblity = getResources().getStringArray(R.array.TalentSchoolLevelEligiblityStringarray);
        TalentSyllabusarray = getResources().getStringArray(R.array.TalentSchoolLevelSyllabusStringarray);
        TalentWebsiteArray = getResources().getStringArray(R.array.TalentSchoolLevelWebsiteStringarray);

        SchoolLaveTalentLay = (LinearLayout)findViewById(R.id.TalentTestIntermediateHeadLinear);
        SchoolHeadTextName = (TextView)findViewById(R.id.TalentTestIntermediateHeaText);
        SchoolTestList = (ListView)findViewById(R.id.telentintermediateListView);

        TalentSchoolAdapterClass talentSchoolAdapterClass = new TalentSchoolAdapterClass();
        SchoolTestList.setAdapter(talentSchoolAdapterClass);


    }


    class TalentSchoolAdapterClass extends BaseAdapter {


        @Override
        public int getCount() {
            return TalentSchoolLavelNameArray.length;
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

            convertView = getLayoutInflater().inflate(R.layout.telentintermediate_list_row,null);
            TextView TalentSchoolName = (TextView)convertView.findViewById(R.id.TelentIntermediateExampName);
            TextView TalentSchoolEligiblity = (TextView)convertView.findViewById(R.id.TelentIntermediateEligiblity);
            TextView TalentSchoolSyllabus = (TextView)convertView.findViewById(R.id.TelentIntermediateSyllabus);
            TextView TalentSchoolWebsite = (TextView)convertView.findViewById(R.id.TelentIntermediateWebsite);

            TalentSchoolName.setText(TalentSchoolLavelNameArray[position]);
            TalentSchoolEligiblity.setText(TalantarrayEligiblity[position]);
            TalentSchoolSyllabus.setText(TalentSyllabusarray[position]);
            TalentSchoolWebsite.setText(TalentWebsiteArray[position]);


            return convertView;
        }
    }


}
