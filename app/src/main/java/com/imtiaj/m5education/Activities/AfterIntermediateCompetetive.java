package com.imtiaj.m5education.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.imtiaj.m5education.R;
import com.imtiaj.m5education.modelClass.Upload_Talent_Examp_After_inter;

import java.util.ArrayList;

public class AfterIntermediateCompetetive extends AppCompatActivity {

    private LinearLayout IntermediateCompHeadLay ;
    private ListView IntermediateCompListView;
    private ArrayList<Upload_Talent_Examp_After_inter> InterCompExamp;

    private  AfterInterCompExampAdapterClass afterInterCompExampAdapterClass;
    private DatabaseReference gainFromExmapDatabase;
    private ArrayList<String> ListKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_intermediate_competetive);

        IntermediateCompHeadLay = (LinearLayout)findViewById(R.id.IntermediateCompMainLay);
        IntermediateCompListView = (ListView)findViewById(R.id.IntermediateCompetetive);
        ListKey = new ArrayList<>();

        InterCompExamp = new ArrayList<Upload_Talent_Examp_After_inter>();

        gainFromExmapDatabase = FirebaseDatabase.getInstance().getReference("CreerL/competetive_Examp_After Intermediate");

        gainFromExmapDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Upload_Talent_Examp_After_inter ExampListForm  = dataSnapshot.getValue(Upload_Talent_Examp_After_inter.class);
                InterCompExamp.add(ExampListForm);
                String mKey = dataSnapshot.getKey();
                ListKey.add(mKey);

                afterInterCompExampAdapterClass = new  AfterInterCompExampAdapterClass(InterCompExamp);
                IntermediateCompListView.setAdapter(afterInterCompExampAdapterClass);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Upload_Talent_Examp_After_inter ExampListForm  = dataSnapshot.getValue(Upload_Talent_Examp_After_inter.class);
                String ChangeKey = dataSnapshot.getKey();
                int index = ListKey.indexOf(ChangeKey);
                InterCompExamp.set(index,ExampListForm);
                afterInterCompExampAdapterClass = new  AfterInterCompExampAdapterClass(InterCompExamp);
                IntermediateCompListView.setAdapter(afterInterCompExampAdapterClass);

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                String RemoveKey = dataSnapshot.getKey();
                int index = ListKey.indexOf(RemoveKey);
                InterCompExamp.remove(index);
                ListKey.remove(index);
                afterInterCompExampAdapterClass = new  AfterInterCompExampAdapterClass(InterCompExamp);
                IntermediateCompListView.setAdapter(afterInterCompExampAdapterClass);

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    class AfterInterCompExampAdapterClass extends BaseAdapter {

        private ArrayList<Upload_Talent_Examp_After_inter> AdapterClassArrayList;

        public AfterInterCompExampAdapterClass(ArrayList<Upload_Talent_Examp_After_inter> adapterClassArrayList) {
            AdapterClassArrayList = adapterClassArrayList;
        }

        @Override
        public int getCount() {
            return AdapterClassArrayList.size();
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

            convertView = getLayoutInflater().inflate(R.layout.competetive_examp_after_intermediate,null);

            TextView NameOfExamp = (TextView)convertView.findViewById(R.id.NameOfCompExampAfIntermediate);
            TextView ConductOfExamp = (TextView)convertView.findViewById(R.id.ConductOfCompExampAfIntermediate);
            TextView ApplicationOfExamp = (TextView)convertView.findViewById(R.id.InventionOfCompExampAfIntermediate);
            TextView NotificationOfExamp = (TextView)convertView.findViewById(R.id.NotificationOfCompExampAfIntermediate);
            TextView ModeOfExamp = (TextView)convertView.findViewById(R.id.ModeOfCompExampAfIntermediate);
            TextView WebOfExamp = (TextView)convertView.findViewById(R.id.WebOfCompExampAfIntermediate);

            NameOfExamp.setText(AdapterClassArrayList.get(position).getNameExmap());
            ConductOfExamp.setText(AdapterClassArrayList.get(position).getConductName());
            ApplicationOfExamp.setText(AdapterClassArrayList.get(position).getInventedName());
            NotificationOfExamp.setText(AdapterClassArrayList.get(position).getNotificationName());
            ModeOfExamp.setText(AdapterClassArrayList.get(position).getModeName());
            WebOfExamp.setText(AdapterClassArrayList.get(position).getWebName());

            return convertView;
        }
    }

}
