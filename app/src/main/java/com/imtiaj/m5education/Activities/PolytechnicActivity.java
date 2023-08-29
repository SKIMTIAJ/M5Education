package com.imtiaj.m5education.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.imtiaj.m5education.R;

public class PolytechnicActivity extends AppCompatActivity {


    private String PolytechStringarray[];
    private LinearLayout PolyechnicmainLayout ;
    private ListView PolytechListView;
    private AdView PolitecAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polytechnic);

        PolitecAdView = findViewById(R.id.PolitecnicadView);
        PolitecAdView.loadAd(new AdRequest.Builder().build());

        PolyechnicmainLayout = (LinearLayout)findViewById(R.id.Ploytechnic_Main_Lay);

        PolytechStringarray = getResources().getStringArray(R.array.PolytechnicListArray_for);

        PolytechListView = (ListView)findViewById(R.id.PolytechnicListView);

        View footerView =  ((LayoutInflater)PolytechnicActivity.this.getSystemService(PolytechnicActivity.this.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.footer_layout_forlist, null, false);
        PolytechListView.addFooterView(footerView);

        PoytechListadapterClass poytechListadapterClass = new PoytechListadapterClass();
        PolytechListView.setAdapter(poytechListadapterClass);

        PolytechListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Object ListItem = PolytechListView.getItemPosition(position);
                //int itemmm = Integer.parseInt((String) ListItem);
                //System.out.println(ListItem);
                //Toast.makeText(PolytechnicActivity.this,,Toast.LENGTH_SHORT).show();

                switch(position){

                    case 3:

                        Toast.makeText(PolytechnicActivity.this,"you have clicked on Electrical and electronic Eng",Toast.LENGTH_SHORT).show();
                        break;
                }



            }
        });

    }


    class PoytechListadapterClass extends BaseAdapter {


        @Override
        public int getCount() {
            return PolytechStringarray.length;
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
            TextView polyList_for_text = (TextView)convertView.findViewById(R.id.mainMedicalText);

            polyList_for_text.setText(PolytechStringarray[position]);
            return convertView;
        }
    }

}
