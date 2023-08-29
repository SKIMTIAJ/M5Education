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

public class PeramedicalActivity extends AppCompatActivity {

    private String PeramedicalStringarray[];
    private LinearLayout PeramedicalmainLayout ;
    private ListView PeramedicalListView;
    private AdView PeramedicalAdview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peramedical);

        PeramedicalAdview = findViewById(R.id.PeramedicaladView);
        PeramedicalAdview.loadAd(new AdRequest.Builder().build());

        PeramedicalStringarray = getResources().getStringArray(R.array.PeramedicalListArray_for);
        PeramedicalmainLayout = (LinearLayout)findViewById(R.id.Peramedical_Main_Lay);
        PeramedicalListView = (ListView)findViewById(R.id.PeramedicalListView);

        View footerView =  ((LayoutInflater)PeramedicalActivity.this.getSystemService(PeramedicalActivity.this.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.footer_layout_forlist, null, false);
        PeramedicalListView.addFooterView(footerView);

        PeramidicalAdapterClass peramidicalAdapterClass = new PeramidicalAdapterClass();

        PeramedicalListView.setAdapter(peramidicalAdapterClass);
    }

    class PeramidicalAdapterClass extends BaseAdapter {


        @Override
        public int getCount() {
            return PeramedicalStringarray.length;
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

            convertView = getLayoutInflater().inflate(R.layout.medicallistrow,null );
            TextView PeramedicalText = (TextView)convertView.findViewById(R.id.mainMedicalText);

            PeramedicalText.setText(PeramedicalStringarray[position]);
            return convertView;
        }
    }

}
