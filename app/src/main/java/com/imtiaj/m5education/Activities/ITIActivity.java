package com.imtiaj.m5education.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.imtiaj.m5education.R;

public class ITIActivity extends AppCompatActivity {

    private String ITIStringarray[];
    private LinearLayout ITImainLayout ;
    private ListView ITIListView;
    private AdView ItiAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iti);

        ItiAdView = (AdView)findViewById(R.id.ItiadView);
        ItiAdView.loadAd(new AdRequest.Builder().build());

        ITImainLayout = (LinearLayout)findViewById(R.id.ITI_Main_Lay);
        ITIListView  = (ListView)findViewById(R.id.ITIListView);
        ITIStringarray = getResources().getStringArray(R.array.ITIListStringArray);

        ITIListClassAdapter iTIListClassAdapter = new ITIListClassAdapter();
        ITIListView.setAdapter(iTIListClassAdapter);

        View footerView =  ((LayoutInflater)ITIActivity.this.getSystemService(ITIActivity.this.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.footer_layout_forlist, null, false);
        ITIListView.addFooterView(footerView);

        ITIListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {

                switch(position){

                    case 3:
                        view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                PopupMenu ITIMechanicalPopup = new PopupMenu(ITIActivity.this,view);
                                ITIMechanicalPopup.getMenuInflater().inflate(R.menu.peramedical_menu,ITIMechanicalPopup.getMenu());

                                ITIMechanicalPopup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                    @Override
                                    public boolean onMenuItemClick(MenuItem item) {
                                        return true;
                                    }
                                });
                                ITIMechanicalPopup.show();
                            }
                        });

                    break;
                }
            }
        });


    }

    class ITIListClassAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return ITIStringarray.length;
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
            TextView ITItextView = (TextView)convertView.findViewById(R.id.mainMedicalText);

            ITItextView.setText(ITIStringarray[position]);
            return convertView;
        }
    }

}
