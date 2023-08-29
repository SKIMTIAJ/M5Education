package com.imtiaj.m5education.Activities;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
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

public class Intermediate_gellery extends AppCompatActivity {

    private int SENT_CHECK_TO_NETX_ACTIVITY;

    private Button AirtsButton,CommerceButton,ScienceButton,SearchButton;
    private LinearLayout AirtsLay, CommerceLay,ScienceLay,SearchLay;
    private ListView AirtsList, CommerceList, ScienceList, SearchList;
    private Button AirtsCombinationBtn,ScienceCombinationBtn;

    private ArrayList<Upload_Talent_Examp_After_inter> IntermediateCourseFromDataBase;
    private ArrayList<String> AirtsKeyList,ScienceKeyList,CommerceKeyList;
    private DatabaseReference AirtsRef,Commerceref,Scienceref,Searchref;

    private Upload_Talent_Examp_After_inter Listuploadobj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermediate_gellery);


        AirtsButton = (Button)findViewById(R.id.AfterTenAirtsButtonid);
        CommerceButton = (Button)findViewById(R.id.AfterTenCommerceButtonid);
        ScienceButton = (Button)findViewById(R.id.AfterTenScienceButtonid);
        //SearchButton = (Button)findViewById(R.id.AfterTenSearchBttonid);

        AirtsLay = (LinearLayout)findViewById(R.id.AfterTenAirtsLayid);
        CommerceLay = (LinearLayout)findViewById(R.id.AfterTenCommerceLayid);
        ScienceLay = (LinearLayout)findViewById(R.id.AfterTenScienceLayid);
        //SearchLay = (LinearLayout)findViewById(R.id.AfterTenSearchlayid);

        AirtsList = (ListView)findViewById(R.id.AfterTenAirtsListViewid);
        CommerceList = (ListView)findViewById(R.id.AfterTenCommerceListViewid);
        ScienceList = (ListView)findViewById(R.id.AfterTenScienceListViewid);
        //SearchList = (ListView)findViewById(R.id.AfterTenSearchListViewid);

        AirtsCombinationBtn = (Button)findViewById(R.id.AirtsCombinationbtnid);
        ScienceCombinationBtn = (Button)findViewById(R.id.ScienceCombinationbtnid);

        IntermediateCourseFromDataBase = new ArrayList<Upload_Talent_Examp_After_inter>();
        AirtsKeyList = new ArrayList<>();
        ScienceKeyList = new ArrayList<>();
        CommerceKeyList = new ArrayList<>();

        AirtsRef = FirebaseDatabase.getInstance().getReference("CreerL/IntermediateAirtsCoursePage");
        Commerceref = FirebaseDatabase.getInstance().getReference("CreerL/IntermediateCommerceCouragePage");
        Scienceref = FirebaseDatabase.getInstance().getReference("CreerL/IntermediateScienceCoursesPage");
        //Searchref = FirebaseDatabase.getInstance().getReference("CreerL/MoreSimpleCouragePage");



        AirtsCombinationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SENT_CHECK_TO_NETX_ACTIVITY = 1;
                Intent AritsCombination = new Intent(Intermediate_gellery.this, DetailIntermediateAbout_Combination.class);
                AritsCombination.putExtra("checkTypeView",SENT_CHECK_TO_NETX_ACTIVITY);
                startActivity(AritsCombination);

            }
        });

        ScienceCombinationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SENT_CHECK_TO_NETX_ACTIVITY = 2;
                Intent ScienceCombination = new Intent(Intermediate_gellery.this,DetailIntermediateAbout_Combination.class);
                ScienceCombination.putExtra("checkTypeView",SENT_CHECK_TO_NETX_ACTIVITY);
                startActivity(ScienceCombination);
            }
        });



        AirtsLay.setVisibility(View.VISIBLE);
        CommerceLay.setVisibility(View.GONE);
        ScienceLay.setVisibility(View.GONE);
        //SearchLay.setVisibility(View.GONE);

        AirtsList.setVisibility(View.VISIBLE);
        CommerceList.setVisibility(View.GONE);
        ScienceList.setVisibility(View.GONE);
        //SearchList.setVisibility(View.GONE);

        IntermediateCourseFromDataBase.clear();

        AirtsRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Listuploadobj  = dataSnapshot.getValue(Upload_Talent_Examp_After_inter.class);
                IntermediateCourseFromDataBase.add(Listuploadobj);
                AirtsKeyList.add(dataSnapshot.getKey());

                IntermediateGelleryCourseAdapterClass SimpleAdapterClassObj = new IntermediateGelleryCourseAdapterClass(IntermediateCourseFromDataBase);
                AirtsList.setAdapter(SimpleAdapterClassObj);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Listuploadobj  = dataSnapshot.getValue(Upload_Talent_Examp_After_inter.class);
                String ChangeKey = dataSnapshot.getKey();
                int index = AirtsKeyList.indexOf(ChangeKey);

                IntermediateCourseFromDataBase.set(index,Listuploadobj);

                IntermediateGelleryCourseAdapterClass SimpleAdapterClassObj = new IntermediateGelleryCourseAdapterClass(IntermediateCourseFromDataBase);
                AirtsList.setAdapter(SimpleAdapterClassObj);

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });















        AirtsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AirtsLay.setVisibility(View.VISIBLE);
                CommerceLay.setVisibility(View.GONE);
                ScienceLay.setVisibility(View.GONE);
                //SearchLay.setVisibility(View.GONE);

                AirtsList.setVisibility(View.VISIBLE);
                CommerceList.setVisibility(View.GONE);
                ScienceList.setVisibility(View.GONE);
                //SearchList.setVisibility(View.GONE);



                IntermediateCourseFromDataBase.clear();

                AirtsRef.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        Listuploadobj  = dataSnapshot.getValue(Upload_Talent_Examp_After_inter.class);
                        IntermediateCourseFromDataBase.add(Listuploadobj);
                        AirtsKeyList.add(dataSnapshot.getKey());

                        IntermediateGelleryCourseAdapterClass SimpleAdapterClassObj = new IntermediateGelleryCourseAdapterClass(IntermediateCourseFromDataBase);
                        AirtsList.setAdapter(SimpleAdapterClassObj);

                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        Listuploadobj  = dataSnapshot.getValue(Upload_Talent_Examp_After_inter.class);
                        String ChangeKey = dataSnapshot.getKey();
                        int index = AirtsKeyList.indexOf(ChangeKey);

                        IntermediateCourseFromDataBase.set(index,Listuploadobj);

                        IntermediateGelleryCourseAdapterClass SimpleAdapterClassObj = new IntermediateGelleryCourseAdapterClass(IntermediateCourseFromDataBase);
                        AirtsList.setAdapter(SimpleAdapterClassObj);

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });

        CommerceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AirtsLay.setVisibility(View.GONE);
                CommerceLay.setVisibility(View.VISIBLE);
                ScienceLay.setVisibility(View.GONE);
                //SearchLay.setVisibility(View.GONE);

                AirtsList.setVisibility(View.GONE);
                CommerceList.setVisibility(View.VISIBLE);
                ScienceList.setVisibility(View.GONE);
                //SearchList.setVisibility(View.GONE);

                IntermediateCourseFromDataBase.clear();

                Commerceref.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        Listuploadobj  = dataSnapshot.getValue(Upload_Talent_Examp_After_inter.class);
                        IntermediateCourseFromDataBase.add(Listuploadobj);
                        CommerceKeyList.add(dataSnapshot.getKey());

                        IntermediateGelleryCourseAdapterClass SimpleAdapterClassObj = new IntermediateGelleryCourseAdapterClass(IntermediateCourseFromDataBase);
                        CommerceList.setAdapter(SimpleAdapterClassObj);

                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        Listuploadobj  = dataSnapshot.getValue(Upload_Talent_Examp_After_inter.class);
                        String changekey = dataSnapshot.getKey();
                        int index = CommerceKeyList.indexOf(changekey);

                        IntermediateCourseFromDataBase.set(index,Listuploadobj);

                        IntermediateGelleryCourseAdapterClass SimpleAdapterClassObj = new IntermediateGelleryCourseAdapterClass(IntermediateCourseFromDataBase);
                        CommerceList.setAdapter(SimpleAdapterClassObj);

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        ScienceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AirtsLay.setVisibility(View.GONE);
                CommerceLay.setVisibility(View.GONE);
                ScienceLay.setVisibility(View.VISIBLE);
                //SearchLay.setVisibility(View.GONE);

                AirtsList.setVisibility(View.GONE);
                CommerceList.setVisibility(View.GONE);
                ScienceList.setVisibility(View.VISIBLE);
                //SearchList.setVisibility(View.GONE);

                IntermediateCourseFromDataBase.clear();

                Scienceref.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        Listuploadobj  = dataSnapshot.getValue(Upload_Talent_Examp_After_inter.class);
                        IntermediateCourseFromDataBase.add(Listuploadobj);
                        ScienceKeyList.add(dataSnapshot.getKey());

                        IntermediateGelleryCourseAdapterClass SimpleAdapterClassObj = new IntermediateGelleryCourseAdapterClass(IntermediateCourseFromDataBase);
                        ScienceList.setAdapter(SimpleAdapterClassObj);


                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        Listuploadobj  = dataSnapshot.getValue(Upload_Talent_Examp_After_inter.class);
                        String ChangeKey = dataSnapshot.getKey();
                        int index = ScienceKeyList.indexOf(ChangeKey);
                        IntermediateCourseFromDataBase.set(index,Listuploadobj);

                        IntermediateGelleryCourseAdapterClass SimpleAdapterClassObj = new IntermediateGelleryCourseAdapterClass(IntermediateCourseFromDataBase);
                        ScienceList.setAdapter(SimpleAdapterClassObj);

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

       /* SearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AirtsLay.setVisibility(View.GONE);
                CommerceLay.setVisibility(View.GONE);
                ScienceLay.setVisibility(View.GONE);
                SearchLay.setVisibility(View.VISIBLE);

                AirtsList.setVisibility(View.GONE);
                CommerceList.setVisibility(View.GONE);
                ScienceList.setVisibility(View.GONE);
                SearchList.setVisibility(View.VISIBLE);

                IntermediateCourseFromDataBase.clear();

                Searchref.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        Listuploadobj  = dataSnapshot.getValue(Upload_Talent_Examp_After_inter.class);
                        IntermediateCourseFromDataBase.add(Listuploadobj);

                        IntermediateGelleryCourseAdapterClass SimpleAdapterClassObj = new IntermediateGelleryCourseAdapterClass(IntermediateCourseFromDataBase);
                        SearchList.setAdapter(SimpleAdapterClassObj);

                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        Listuploadobj  = dataSnapshot.getValue(Upload_Talent_Examp_After_inter.class);
                        IntermediateCourseFromDataBase.add(Listuploadobj);

                        IntermediateGelleryCourseAdapterClass SimpleAdapterClassObj = new IntermediateGelleryCourseAdapterClass(IntermediateCourseFromDataBase);
                        SearchList.setAdapter(SimpleAdapterClassObj);

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                }) ;

            }
        });*/





    }



    class IntermediateGelleryCourseAdapterClass extends BaseAdapter {

        private ArrayList<Upload_Talent_Examp_After_inter> AdapterClassArrayList;

        public IntermediateGelleryCourseAdapterClass(ArrayList<Upload_Talent_Examp_After_inter> adapterClassArrayList) {

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
            convertView = getLayoutInflater().inflate(R.layout.medicallistrow,null);
            TextView SimpleText = (TextView)convertView.findViewById(R.id.mainMedicalText);

            SimpleText.setText(AdapterClassArrayList.get(position).getNameExmap());

            return convertView;
        }
    }


}
