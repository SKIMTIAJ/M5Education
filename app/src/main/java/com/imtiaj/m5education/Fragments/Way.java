package com.imtiaj.m5education.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.imtiaj.m5education.AdapterClass.MyfragAdepter;
import com.imtiaj.m5education.R;
import com.imtiaj.m5education.modelClass.way;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Way extends Fragment {

    private RecyclerView r1;
    /*String s1[],s2[];
    int imageresource[] = {R.drawable.student2,R.drawable.sports2,R.drawable.acting1,R.drawable.art1};*/
    private ArrayList<way> wayListView;
    private  ArrayList<String> WayKey;
    private DatabaseReference dbRefForwayListView;
    private MyfragAdepter adepter;
    private ProgressDialog BlankFragProgress;



    public Way() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_way, container, false);

        r1 = (RecyclerView) view.findViewById(R.id.RecyleFrag);
        r1.setHasFixedSize(true);
        BlankFragProgress = new ProgressDialog(getActivity());
        BlankFragProgress.setProgress(100);
        BlankFragProgress.show();

        Log.d("Blankfragment","into the oncreatview 1st massege");
        /*s1 = getResources().getStringArray(R.array.titelFragmenList);
        s2 = getResources().getStringArray(R.array.textFragmentList);*/

        wayListView = new ArrayList<way>();
        WayKey = new ArrayList<>();
        dbRefForwayListView = FirebaseDatabase.getInstance().getReference().child("CreerL/way");
        Log.d("Blankfragment","After creating Database referance");

        dbRefForwayListView.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                way w = dataSnapshot.getValue(way.class);
                Log.d("Blankfragment","till now is showing");
                wayListView.add(w);
                String mkey = dataSnapshot.getKey();
                WayKey.add(mkey);
                adepter = new MyfragAdepter(getActivity(),wayListView);
                Log.d("Blankfragment","seting a MyfragmentAdapter constractor");
                r1.setAdapter(adepter);
                r1.setLayoutManager(new LinearLayoutManager(getActivity()));
                BlankFragProgress.dismiss();



            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                way waychild = dataSnapshot.getValue(way.class);
                Log.d("Blankfragment","till now is showing");

                String chengeKey = dataSnapshot.getKey();

                int index = WayKey.indexOf(chengeKey);

                wayListView.set(index,waychild);

                adepter = new MyfragAdepter(getActivity(),wayListView);
                Log.d("Blankfragment","seting a MyfragmentAdapter constractor");
                r1.setAdapter(adepter);
                r1.setLayoutManager(new LinearLayoutManager(getActivity()));
                BlankFragProgress.dismiss();
              /*  wayListView.add(w);
                Log.d("Blankfragment","end of the blankFragment");
                adepter = new MyfragAdepter(getActivity(),wayListView);
                Log.d("Blankfragment","seting a MyfragmentAdapter constractor");
                r1.setAdapter(adepter);
                r1.setLayoutManager(new LinearLayoutManager(getActivity()));*/
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                way waychild = dataSnapshot.getValue(way.class);
                String RemoveKey = dataSnapshot.getKey();
                int index = WayKey.indexOf(RemoveKey);

                wayListView.remove(index);
                WayKey.remove(index);
                adepter = new MyfragAdepter(getActivity(),wayListView);
                r1.setAdapter(adepter);
                r1.setLayoutManager(new LinearLayoutManager(getActivity()));
                BlankFragProgress.dismiss();


            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        /*dbRefForwayListView.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("Blankfragment","into the onDataChange 1st massege");
                for(DataSnapshot wayListDatasnapshot:dataSnapshot.getChildren()){
                    Log.d("Blankfragment",wayListDatasnapshot.getValue().toString());
                    way w = wayListDatasnapshot.getValue(way.class);
                    Log.d("Blankfragment","till now is showing");
                    wayListView.add(w);
                }
                Log.d("Blankfragment","end of the blankFragment");
                adepter = new MyfragAdepter(getActivity(),wayListView);
                Log.d("Blankfragment","seting a MyfragmentAdapter constractor");
                r1.setAdapter(adepter);
                r1.setLayoutManager(new LinearLayoutManager(getActivity()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                //Toast.makeText(BlankFragment.this, databaseError.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });*/


        return view;
    }

}
