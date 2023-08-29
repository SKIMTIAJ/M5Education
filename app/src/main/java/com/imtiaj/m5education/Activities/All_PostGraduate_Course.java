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

public class All_PostGraduate_Course extends AppCompatActivity {

    private LinearLayout PostGraduateLayoutmain;
    private TextView PostGraduatecourse, PostGraduatecoursecriteria;
    private ListView PostGraduateList;
    private String AllPostGraduateDegreearray[],AllPostGraduateEligiblity[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__post_graduate__course);

        PostGraduateLayoutmain =(LinearLayout)findViewById(R.id.PostGraduateLayoutmain);

        PostGraduateList = (ListView)findViewById(R.id.PostGraduateList);
        AllPostGraduateDegreearray = getResources().getStringArray(R.array.PostGraduateStringArray);
        AllPostGraduateEligiblity = getResources().getStringArray(R.array.PostGraduateEligibleStringarray);

        AllPostGraduateAdapterClass allPostGraduateAdapterClass = new AllPostGraduateAdapterClass();
        PostGraduateList.setAdapter(allPostGraduateAdapterClass);

    }

    class AllPostGraduateAdapterClass extends BaseAdapter {


        @Override
        public int getCount() {
            return AllPostGraduateDegreearray.length;
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
            TextView HeadMaintext = (TextView)convertView.findViewById(R.id.PostCourseName);
            TextView eligiblityText = (TextView)convertView.findViewById(R.id.PostCourseEligible);

            HeadMaintext.setText(AllPostGraduateDegreearray[position]);
            eligiblityText.setText(AllPostGraduateEligiblity[position]);

            return convertView;
        }
    }

}
