package com.imtiaj.m5education.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.imtiaj.m5education.R;

public class ShortCourseActivity extends AppCompatActivity {

    private String ShortCourseStringarray[];
    private LinearLayout ShortCoursemainLayout ;
    private ListView ShortCourseListView;
    private AdView adview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_short_course);
        adview =(AdView)findViewById(R.id.ShortCourseadView);
        adview.loadAd(new AdRequest.Builder().build());

        ShortCoursemainLayout = (LinearLayout)findViewById(R.id.ShortCourse_Main_Lay);
        ShortCourseStringarray = getResources().getStringArray(R.array.ShortTermCourseListArray_for);
        ShortCourseListView =(ListView)findViewById(R.id.ShortCourseListView);

        ShortCourseAdapterClass shortCourseAdapterClass = new ShortCourseAdapterClass();
        ShortCourseListView.setAdapter(shortCourseAdapterClass);

        View footerView =  ((LayoutInflater)ShortCourseActivity.this.getSystemService(ShortCourseActivity.this.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.footer_layout_forlist, null, false);
        ShortCourseListView.addFooterView(footerView);


    }

    class ShortCourseAdapterClass extends BaseAdapter {


        @Override
        public int getCount() {
            return ShortCourseStringarray.length;
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
            TextView ShortcourseText = (TextView)convertView.findViewById(R.id.mainMedicalText);

            ShortcourseText.setText(ShortCourseStringarray[position]);
            return convertView;
        }
    }

}
