package com.imtiaj.m5education.Activities;

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

public class More_Course_Activity extends AppCompatActivity {

    private Button UnderGraduateButton,IntegratedButton,PostGraduateButton,MoreButton;
    private LinearLayout UndergraduateLay,IntegratedLay,PostGraduatedLay,MoreLay;
    private ListView UndergraduateList, IntegratedList, PostGraduateList,MoreList;

    private ArrayList<Upload_Talent_Examp_After_inter> NarmalCourseFromDataBase;
    private ArrayList<Upload_Talent_Examp_After_inter> IntegratedCourseArrayList;
    private ArrayList<Upload_Talent_Examp_After_inter> PostgraduateArrayList;
    private ArrayList<Upload_Talent_Examp_After_inter> MoreArrayList;
    private  ArrayList<String> UndergraduaeKeyList,IntegratedKeyList,PostGaduateKeyList,MoreKeyList;
    private DatabaseReference UnderGraduteRef,Integratedref,PostGraduateref,Moreref;

    private Upload_Talent_Examp_After_inter Listuploadobj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more__course_);



        UnderGraduateButton = (Button)findViewById(R.id.CourseUndergraduateButtonid);
        IntegratedButton = (Button)findViewById(R.id.CourseIntegratedButtonid);
        PostGraduateButton = (Button)findViewById(R.id.CoursePostGraduateButtonid);
        MoreButton = (Button)findViewById(R.id.CourseMoreBttonid);

        UndergraduateLay = (LinearLayout)findViewById(R.id.CoursesUnderGraduateLayid);
        IntegratedLay = (LinearLayout)findViewById(R.id.CoursesIntegratedLayid);
        PostGraduatedLay = (LinearLayout)findViewById(R.id.CoursesPostGraduateLayid);
        MoreLay = (LinearLayout)findViewById(R.id.CoursesMorelayid);

        UndergraduateList = (ListView)findViewById(R.id.CoursesListViewUnderGraduateid);
        IntegratedList = (ListView)findViewById(R.id.CoursesListViewIntegratedid);
        PostGraduateList = (ListView)findViewById(R.id.CoursesListViewPostGraduateid);
        MoreList = (ListView)findViewById(R.id.CoursesListViewMoreid);

        NarmalCourseFromDataBase = new ArrayList<Upload_Talent_Examp_After_inter>();
        IntegratedCourseArrayList = new ArrayList<>();
        PostgraduateArrayList = new ArrayList<>();
        MoreArrayList = new ArrayList<>();
        UndergraduaeKeyList = new ArrayList<>();
        IntegratedKeyList = new ArrayList<>();
        PostGaduateKeyList = new ArrayList<>();
        MoreKeyList = new ArrayList<>();

        UnderGraduteRef = FirebaseDatabase.getInstance().getReference("CreerL/UnderGraduateSipmleCoursePage");
        Integratedref = FirebaseDatabase.getInstance().getReference("CreerL/IntegratedSimpleCouragePage");
        PostGraduateref = FirebaseDatabase.getInstance().getReference("CreerL/PostGraduateSimpleCoursesPage");
        Moreref = FirebaseDatabase.getInstance().getReference("CreerL/MoreSimpleCouragePage");








        UndergraduateLay.setVisibility(View.VISIBLE);
        IntegratedLay.setVisibility(View.GONE);
        PostGraduatedLay.setVisibility(View.GONE);
        MoreLay.setVisibility(View.GONE);

        UndergraduateList.setVisibility(View.VISIBLE);
        IntegratedList.setVisibility(View.GONE);
        PostGraduateList.setVisibility(View.GONE);
        MoreList.setVisibility(View.GONE);

        NarmalCourseFromDataBase.clear();
        UndergraduaeKeyList.clear();

        UnderGraduteRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Listuploadobj  = dataSnapshot.getValue(Upload_Talent_Examp_After_inter.class);
                NarmalCourseFromDataBase.add(Listuploadobj);
                UndergraduaeKeyList.add(dataSnapshot.getKey());

                SimpleCourseAdapterClass SimpleAdapterClassObj = new SimpleCourseAdapterClass(NarmalCourseFromDataBase);
                UndergraduateList.setAdapter(SimpleAdapterClassObj);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Listuploadobj  = dataSnapshot.getValue(Upload_Talent_Examp_After_inter.class);
                String ChangeKey = dataSnapshot.getKey();
                int index = UndergraduaeKeyList.indexOf(ChangeKey);

                NarmalCourseFromDataBase.set(index,Listuploadobj);

                SimpleCourseAdapterClass SimpleAdapterClassObj = new SimpleCourseAdapterClass(NarmalCourseFromDataBase);
                UndergraduateList.setAdapter(SimpleAdapterClassObj);

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                /*String RemoveKey = dataSnapshot.getKey();
                int index = UndergraduaeKeyList.indexOf(RemoveKey);
                NarmalCourseFromDataBase.remove(index);
                UndergraduaeKeyList.remove(index);
                SimpleCourseAdapterClass SimpleAdapterClassObj = new SimpleCourseAdapterClass(NarmalCourseFromDataBase);
                UndergraduateList.setAdapter(SimpleAdapterClassObj);
*/
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });















        UnderGraduateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UndergraduateLay.setVisibility(View.VISIBLE);
                IntegratedLay.setVisibility(View.GONE);
                PostGraduatedLay.setVisibility(View.GONE);
                MoreLay.setVisibility(View.GONE);

                UndergraduateList.setVisibility(View.VISIBLE);
                IntegratedList.setVisibility(View.GONE);
                PostGraduateList.setVisibility(View.GONE);
                MoreList.setVisibility(View.GONE);

                NarmalCourseFromDataBase.clear();
                UndergraduaeKeyList.clear();

                UnderGraduteRef.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        Listuploadobj  = dataSnapshot.getValue(Upload_Talent_Examp_After_inter.class);
                        NarmalCourseFromDataBase.add(Listuploadobj);
                        UndergraduaeKeyList.add(dataSnapshot.getKey());

                        SimpleCourseAdapterClass SimpleAdapterClassObj = new SimpleCourseAdapterClass(NarmalCourseFromDataBase);
                        UndergraduateList.setAdapter(SimpleAdapterClassObj);

                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        Listuploadobj  = dataSnapshot.getValue(Upload_Talent_Examp_After_inter.class);
                        String ChangeKey = dataSnapshot.getKey();
                        int index = UndergraduaeKeyList.indexOf(ChangeKey);

                        NarmalCourseFromDataBase.set(index,Listuploadobj);

                        SimpleCourseAdapterClass SimpleAdapterClassObj = new SimpleCourseAdapterClass(NarmalCourseFromDataBase);
                        UndergraduateList.setAdapter(SimpleAdapterClassObj);

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                       /* String RemoveKey = dataSnapshot.getKey();
                        int index = UndergraduaeKeyList.indexOf(RemoveKey);
                        NarmalCourseFromDataBase.remove(index);
                        UndergraduaeKeyList.remove(index);
                        SimpleCourseAdapterClass SimpleAdapterClassObj = new SimpleCourseAdapterClass(NarmalCourseFromDataBase);
                        UndergraduateList.setAdapter(SimpleAdapterClassObj);*/

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

        IntegratedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UndergraduateLay.setVisibility(View.GONE);
                IntegratedLay.setVisibility(View.VISIBLE);
                PostGraduatedLay.setVisibility(View.GONE);
                MoreLay.setVisibility(View.GONE);

                UndergraduateList.setVisibility(View.GONE);
                IntegratedList.setVisibility(View.VISIBLE);
                PostGraduateList.setVisibility(View.GONE);
                MoreList.setVisibility(View.GONE);

                IntegratedCourseArrayList.clear();
                IntegratedKeyList.clear();

                Integratedref.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        Listuploadobj  = dataSnapshot.getValue(Upload_Talent_Examp_After_inter.class);
                        IntegratedCourseArrayList.add(Listuploadobj);
                        IntegratedKeyList.add(dataSnapshot.getKey());


                        SimpleCourseAdapterClass SimpleAdapterClassObjforIntegrated = new SimpleCourseAdapterClass(IntegratedCourseArrayList);
                        IntegratedList.setAdapter(SimpleAdapterClassObjforIntegrated);

                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        Listuploadobj  = dataSnapshot.getValue(Upload_Talent_Examp_After_inter.class);
                        String ChangeKey = dataSnapshot.getKey();
                        int index = IntegratedKeyList.indexOf(ChangeKey);
                        IntegratedCourseArrayList.set(index,Listuploadobj);

                        SimpleCourseAdapterClass SimpleAdapterClassObjforIntegrated = new SimpleCourseAdapterClass(IntegratedCourseArrayList);
                        IntegratedList.setAdapter(SimpleAdapterClassObjforIntegrated);

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                       /* String RemoveKey = dataSnapshot.getKey();
                        int index = IntegratedKeyList.indexOf(RemoveKey);
                        IntegratedCourseArrayList.remove(index);
                        IntegratedKeyList.remove(index);
                        SimpleCourseAdapterClass SimpleAdapterClassObjforIntegrated = new SimpleCourseAdapterClass(IntegratedCourseArrayList);
                        IntegratedList.setAdapter(SimpleAdapterClassObjforIntegrated);*/
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

        PostGraduateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UndergraduateLay.setVisibility(View.GONE);
                IntegratedLay.setVisibility(View.GONE);
                PostGraduatedLay.setVisibility(View.VISIBLE);
                MoreLay.setVisibility(View.GONE);

                UndergraduateList.setVisibility(View.GONE);
                IntegratedList.setVisibility(View.GONE);
                PostGraduateList.setVisibility(View.VISIBLE);
                MoreList.setVisibility(View.GONE);

                PostgraduateArrayList.clear();
                PostGaduateKeyList.clear();

                PostGraduateref.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        Listuploadobj  = dataSnapshot.getValue(Upload_Talent_Examp_After_inter.class);
                        PostgraduateArrayList.add(Listuploadobj);
                        PostGaduateKeyList.add(dataSnapshot.getKey());

                        SimpleCourseAdapterClass SimpleAdapterClassObjPostGraduate = new SimpleCourseAdapterClass(PostgraduateArrayList);
                        PostGraduateList.setAdapter(SimpleAdapterClassObjPostGraduate);


                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        Listuploadobj  = dataSnapshot.getValue(Upload_Talent_Examp_After_inter.class);
                        String ChangeKey = dataSnapshot.getKey();
                        int index = PostGaduateKeyList.indexOf(ChangeKey);
                        PostgraduateArrayList.set(index,Listuploadobj);

                        SimpleCourseAdapterClass SimpleAdapterClassObjPostGraduate = new SimpleCourseAdapterClass(PostgraduateArrayList);
                        PostGraduateList.setAdapter(SimpleAdapterClassObjPostGraduate);

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                        /*String RemoveKey = dataSnapshot.getKey();
                        int index = PostGaduateKeyList.indexOf(RemoveKey);
                        PostgraduateArrayList.remove(index);
                        PostGaduateKeyList.remove(index);

                        SimpleCourseAdapterClass SimpleAdapterClassObjPostGraduate = new SimpleCourseAdapterClass(PostgraduateArrayList);
                        PostGraduateList.setAdapter(SimpleAdapterClassObjPostGraduate);*/
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

        MoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UndergraduateLay.setVisibility(View.GONE);
                IntegratedLay.setVisibility(View.GONE);
                PostGraduatedLay.setVisibility(View.GONE);
                MoreLay.setVisibility(View.VISIBLE);

                UndergraduateList.setVisibility(View.GONE);
                IntegratedList.setVisibility(View.GONE);
                PostGraduateList.setVisibility(View.GONE);
                MoreList.setVisibility(View.VISIBLE);

                MoreArrayList.clear();
                MoreKeyList.clear();

                Moreref.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        Listuploadobj  = dataSnapshot.getValue(Upload_Talent_Examp_After_inter.class);
                        MoreArrayList.add(Listuploadobj);
                        MoreKeyList.add(dataSnapshot.getKey());

                        SimpleCourseAdapterClass SimpleAdapterClassObjMore = new SimpleCourseAdapterClass(MoreArrayList);
                        MoreList.setAdapter(SimpleAdapterClassObjMore);

                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        Listuploadobj  = dataSnapshot.getValue(Upload_Talent_Examp_After_inter.class);
                        String ChangeKey = dataSnapshot.getKey();
                        int index = MoreKeyList.indexOf(ChangeKey);
                        MoreArrayList.set(index,Listuploadobj);

                        SimpleCourseAdapterClass SimpleAdapterClassObjMore = new SimpleCourseAdapterClass(MoreArrayList);
                        MoreList.setAdapter(SimpleAdapterClassObjMore);

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                        /*String RemoveKey = dataSnapshot.getKey();
                        int index = MoreKeyList.indexOf(RemoveKey);
                        MoreArrayList.remove(index);
                        MoreKeyList.remove(index);

                        SimpleCourseAdapterClass SimpleAdapterClassObjMore = new SimpleCourseAdapterClass(MoreArrayList);
                        MoreList.setAdapter(SimpleAdapterClassObjMore);*/

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                }) ;

            }
        });





    }


    class SimpleCourseAdapterClass extends BaseAdapter {

        private ArrayList<Upload_Talent_Examp_After_inter> AdapterClassArrayList;

        private SimpleCourseAdapterClass(ArrayList<Upload_Talent_Examp_After_inter> adapterClassArrayList) {

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

    /*class IntegratedCourseAdapterClass extends BaseAdapter {

        private ArrayList<Upload_Talent_Examp_After_inter> AdapterClassArrayList;

        private IntegratedCourseAdapterClass(ArrayList<Upload_Talent_Examp_After_inter> adapterClassArrayList) {

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
    class PostgraduateCourseAdapterClass extends BaseAdapter {

        private ArrayList<Upload_Talent_Examp_After_inter> AdapterClassArrayList;

        private PostgraduateCourseAdapterClass(ArrayList<Upload_Talent_Examp_After_inter> adapterClassArrayList) {

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

    class MoreCourseAdapterClass extends BaseAdapter {

        private ArrayList<Upload_Talent_Examp_After_inter> AdapterClassArrayList;

        private MoreCourseAdapterClass(ArrayList<Upload_Talent_Examp_After_inter> adapterClassArrayList) {

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
    }*/

}
