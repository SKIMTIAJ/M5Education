package com.imtiaj.m5education.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuItemCompat;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.ShareActionProvider;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.imtiaj.m5education.AdapterClass.College_Adapter_only;
import com.imtiaj.m5education.R;
import com.imtiaj.m5education.modelClass.upload_college_scholarship;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class College extends AppCompatActivity {

    private ValueEventListener valueEventListener;

    private int isChildbeVisible = 0,isClgTypeCome, checkFlag;
    private RecyclerView college_Scholarship,searchRecyclerView;
    private ArrayList<upload_college_scholarship> upload_college_List,upload_college_FilterL;
    private ArrayList<String> CollegeKey;
    private College_Adapter_only collegeListadapter;
    private DatabaseReference CollegeDatabase;
    private boolean doubleBackToExitPressedOnce = false;

    private ScrollView FilterScroll;
    private AutoCompleteTextView CollegeName, areaName, universityName;
    private Button FilterApplyBtn;
    private upload_college_scholarship valutaken;

    private String CheckIfAllValuCome= " ",Checkquery="";

    private ArrayList<String> CollegeNameCollectionList,CollegeAreaCollectionList,CollegeAffilitationCollectionList,
                               CollegeTypeCollectionList,CollegeUnderCollectionList;

    private List<String> AllDataListMarge;

    private String Checkfragment = "";
    private String FilterResNameClg =" ", FilterResAffiliatedClg =" ",FilterResLocationClg =" ",FilterResTypeClg=" "
            ,FilterResUnderClg=" ",FilterResQualityClg=" ";

    private Query filterQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Search College");

        Bundle BFilterClgeRes = getIntent().getExtras();
        if (BFilterClgeRes!=null){
            Checkfragment = BFilterClgeRes.getString("updateKey");
        }

       /* if (BFilterClgeRes!=null){

            isClgTypeCome = BFilterClgeRes.getInt("typeCheck");

            if (isClgTypeCome==2){

                FilterResNameClg = BFilterClgeRes.getString("CollegeNameRes");
                FilterResAffiliatedClg = BFilterClgeRes.getString("CollegeAffiliatedRes");
                FilterResLocationClg = BFilterClgeRes.getString("CollegeLocationRes");
                isClgTypeCome = BFilterClgeRes.getInt("typeCheck");
                FilterResUnderClg = BFilterClgeRes.getString("CollegeUnderRes");
                FilterResTypeClg = BFilterClgeRes.getString("CollegeTypeRes");
                FilterResQualityClg = BFilterClgeRes.getString("CollegeQualityRes");
                isChildbeVisible = BFilterClgeRes.getInt("ForChildGone");

                Log.d("CollegeName",FilterResNameClg.toString());
                Log.d("CollegeAffiliated",FilterResAffiliatedClg.toString());
                Log.d("CollegeLocation",FilterResLocationClg.toString());
                Log.d("CollegeUnder",FilterResUnderClg);
                Log.d("CollegeQuality",FilterResQualityClg);
                Log.d("CollegeType",FilterResTypeClg);

            }
            else {

                FilterResNameClg = BFilterClgeRes.getString("CollegeNameRes");
                FilterResAffiliatedClg = BFilterClgeRes.getString("CollegeAffiliatedRes");
                FilterResLocationClg = BFilterClgeRes.getString("CollegeLocationRes");
                FilterResUnderClg = BFilterClgeRes.getString("CollegeUnderRes");
                FilterResQualityClg = BFilterClgeRes.getString("CollegeQualityRes");
                isChildbeVisible = BFilterClgeRes.getInt("ForChildGone");

                Log.d("CollegeName",FilterResNameClg.toString());
                Log.d("CollegeAffiliated",FilterResAffiliatedClg.toString());
                Log.d("CollegeLocation",FilterResLocationClg.toString());
                Log.d("CollegeUnder",FilterResUnderClg);
                Log.d("CollegeQuality",FilterResQualityClg);

            }

        }*/




       // FilterScroll = (ScrollView)findViewById(R.id.ClgSearchScrollid);
        /*CollegeName = (AutoCompleteTextView)findViewById(R.id.search_collegeBy_Name);
        areaName = (AutoCompleteTextView)findViewById(R.id.search_collegeBy_Location);
        universityName = (AutoCompleteTextView)findViewById(R.id.search_collegeBy_Affiliation);*/
        FilterApplyBtn = (Button) findViewById(R.id.ClgSreachApplyBtn);


        //Checkfragment = 2;

        college_Scholarship = (RecyclerView)findViewById(R.id.recyclerView);
        searchRecyclerView = (RecyclerView)findViewById(R.id.recyclerViewforsearch);
        upload_college_List = new ArrayList<upload_college_scholarship>();
        upload_college_FilterL = new ArrayList<>();
        CollegeKey = new ArrayList<>();
        CollegeNameCollectionList = new ArrayList<>();
        CollegeAreaCollectionList = new ArrayList<>();
        CollegeAffilitationCollectionList = new ArrayList<>();
        CollegeTypeCollectionList = new ArrayList<>();
        CollegeUnderCollectionList = new ArrayList<>();
        AllDataListMarge = new ArrayList<>();
        CollegeDatabase = FirebaseDatabase.getInstance().getReference("CreerL/College");
        // ScholarshipDatabase = FirebaseDatabase.getInstance().getReference("CreerL/Scholarship");
/*
        if (!FilterResNameClg.isEmpty() && !FilterResAffiliatedClg.isEmpty() && !FilterResLocationClg.isEmpty() && !FilterResTypeClg.isEmpty()
        && !FilterResUnderClg.isEmpty() && !FilterResQualityClg.isEmpty()){

            CheckIfAllValuCome = "allIsComming";
           *//* filterQuery = FirebaseDatabase.getInstance().getReference("CreerL/College")
                    .orderByChild("uploadName").equalTo(FilterResNameClg).orderByChild("uploadAffiliated").equalTo(FilterResAffiliatedClg)
                    .orderByChild("uploadLocation").equalTo(FilterResLocationClg).orderByChild("uploadType").equalTo(FilterResTypeClg)
                    .orderByChild("uploadUnder").equalTo(FilterResUnderClg).orderByChild("uploadRating").equalTo(FilterResQualityClg);*//*
        }
        if (FilterResNameClg.isEmpty() && !FilterResAffiliatedClg.isEmpty() && !FilterResLocationClg.isEmpty() && !FilterResTypeClg.isEmpty()
                && !FilterResUnderClg.isEmpty() && !FilterResQualityClg.isEmpty()){

            CheckIfAllValuCome = "allButNotNameIsComming";

            *//*filterQuery = FirebaseDatabase.getInstance().getReference("CreerL/College")
                    .orderByChild("uploadAffiliated").orderByChild("uploadLocation").orderByChild("uploadType")
                    .orderByChild("uploadUnder").orderByChild("uploadRating")
                    .equalTo(FilterResAffiliatedClg).equalTo(FilterResLocationClg)
                    .equalTo(FilterResTypeClg).equalTo(FilterResUnderClg).equalTo(FilterResQualityClg);*//*

        }
        if (FilterResNameClg.isEmpty() && FilterResAffiliatedClg.isEmpty() && !FilterResLocationClg.isEmpty() && !FilterResTypeClg.isEmpty()
                && !FilterResUnderClg.isEmpty() && !FilterResQualityClg.isEmpty()){

            CheckIfAllValuCome = "exceptNameAndAffilIsComming";

            *//*filterQuery = FirebaseDatabase.getInstance().getReference("CreerL/College")
                    .orderByChild("uploadLocation").orderByChild("uploadType")
                    .orderByChild("uploadUnder").orderByChild("uploadRating")
                    .equalTo(FilterResLocationClg).equalTo(FilterResTypeClg).equalTo(FilterResUnderClg)
                    .equalTo(FilterResQualityClg);*//*

        }
        if (FilterResNameClg.isEmpty() && FilterResAffiliatedClg.isEmpty() && FilterResLocationClg.isEmpty() && !FilterResTypeClg.isEmpty()
                && !FilterResUnderClg.isEmpty() && !FilterResQualityClg.isEmpty()){

            CheckIfAllValuCome = "justType&Under&QualityIsComming";

            *//*filterQuery = FirebaseDatabase.getInstance().getReference("CreerL/College")
                    .orderByChild("uploadType")
                    .orderByChild("uploadUnder").orderByChild("uploadRating")
                    .equalTo(FilterResTypeClg).equalTo(FilterResUnderClg)
                    .equalTo(FilterResQualityClg);*//*

        }
        if (FilterResNameClg.isEmpty() && FilterResAffiliatedClg.isEmpty() && FilterResLocationClg.isEmpty() && FilterResTypeClg.isEmpty()
                && !FilterResUnderClg.isEmpty() && !FilterResQualityClg.isEmpty()){

            CheckIfAllValuCome = "under&QualityIsComming";

           *//*filterQuery = FirebaseDatabase.getInstance().getReference("CreerL/College")
                    .orderByChild("uploadUnder").orderByChild("uploadRating")
                    .equalTo(FilterResUnderClg).equalTo(FilterResQualityClg);*//*

        }
        if (FilterResNameClg.isEmpty() && FilterResAffiliatedClg.isEmpty() && FilterResLocationClg.isEmpty() && FilterResTypeClg.isEmpty()
                && FilterResUnderClg.isEmpty() && !FilterResQualityClg.isEmpty()){

            CheckIfAllValuCome = "qualityIsComming";

            *//*filterQuery = FirebaseDatabase.getInstance().getReference("CreerL/College")
                    .orderByChild("uploadRating")
                    .equalTo(FilterResQualityClg);*//*

        }*/



       /*if (isChildbeVisible == 2){*/
       /* if (updatedate.equals("doUpdate")){

           *//*Log.d("CollegeName",FilterResNameClg.toString());
           Log.d("CollegeAffiliated",FilterResAffiliatedClg.toString());
           Log.d("CollegeLocation",FilterResLocationClg.toString());
            Log.d("CollegeUnder",FilterResUnderClg);
            Log.d("CollegeQuality",FilterResQualityClg);
            Log.d("checktype", getString(isClgTypeCome));*//*

            Checkfragment = 2;

            CollegeDatabase.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    upload_college_scholarship uploadCol = dataSnapshot.getValue(upload_college_scholarship.class);
                    upload_college_List.add(uploadCol);
                    collegeListadapter = new College_Adapter_only(Checkfragment,College.this,upload_college_List);
                    college_Scholarship.setAdapter(collegeListadapter);
                    college_Scholarship.setLayoutManager(new LinearLayoutManager(College.this));

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


           *//* CollegeDatabase.addListenerForSingleValueEvent(valueEventListener);
           valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if (dataSnapshot.exists()){
                        if (CheckIfAllValuCome.equals("allIsComming"){


                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };*//*

        }else {*/

           CollegeDatabase.addChildEventListener(new ChildEventListener() {
               @Override
               public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                   //upload_college_List.clear();
                   upload_college_scholarship uploadCol = dataSnapshot.getValue(upload_college_scholarship.class);
                   upload_college_List.add(uploadCol);
                   String mkey = dataSnapshot.getKey();
                   CollegeKey.add(mkey);
                   AllDataListMarge.add(uploadCol.getUploadName());
                   AllDataListMarge.add(uploadCol.getUploadLocation());
                   //......................................................
                   CollegeNameCollectionList.add(uploadCol.getUploadName());
                   CollegeAreaCollectionList.add(uploadCol.getUploadLocation());
                   CollegeAffilitationCollectionList.add(uploadCol.getUploadAffiliated());
                   CollegeTypeCollectionList.add(uploadCol.getUploadType());
                   CollegeUnderCollectionList.add(uploadCol.getUploadUnder());
                   collegeListadapter = new College_Adapter_only(Checkfragment,College.this,CollegeKey,upload_college_List);
                   college_Scholarship.setAdapter(collegeListadapter);
                   college_Scholarship.setLayoutManager(new LinearLayoutManager(College.this));

               }



               @Override
               public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                   upload_college_scholarship uploadCol = dataSnapshot.getValue(upload_college_scholarship.class);

                   String ChangeKey = dataSnapshot.getKey();
                   int index = CollegeKey.indexOf(ChangeKey);
                   upload_college_List.set(index,uploadCol);
                   CollegeNameCollectionList.set(index,uploadCol.getUploadName());
                   CollegeAreaCollectionList.set(index,uploadCol.getUploadLocation());
                   CollegeAffilitationCollectionList.set(index,uploadCol.getUploadAffiliated());
                   CollegeTypeCollectionList.set(index,uploadCol.getUploadType());
                   CollegeUnderCollectionList.set(index,uploadCol.getUploadUnder());
                   collegeListadapter = new College_Adapter_only(Checkfragment,College.this,CollegeKey,upload_college_List);
                   college_Scholarship.setAdapter(collegeListadapter);
                   college_Scholarship.setLayoutManager(new LinearLayoutManager(College.this));

               }

               @Override
               public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                   String RemoveKey = dataSnapshot.getKey();
                   int index = CollegeKey.indexOf(RemoveKey);
                   upload_college_List.remove(index);
                   CollegeKey.remove(index);
                   CollegeNameCollectionList.remove(index);
                   CollegeAreaCollectionList.remove(index);
                   CollegeAffilitationCollectionList.remove(index);
                   CollegeTypeCollectionList.remove(index);
                   CollegeUnderCollectionList.remove(index);
                   collegeListadapter = new College_Adapter_only(Checkfragment,College.this,CollegeKey,upload_college_List);

                   college_Scholarship.setAdapter(collegeListadapter);
                   college_Scholarship.setLayoutManager(new LinearLayoutManager(College.this));


               }

               @Override
               public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


               }

               @Override
               public void onCancelled(@NonNull DatabaseError databaseError) {


               }
           });

      //}




      /*  FilterApplyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(College.this,CollegeFilterActivity.class));

            }
        });*/

        Set<String> removedupName = new HashSet<>(CollegeNameCollectionList);
        CollegeNameCollectionList.clear();
        CollegeNameCollectionList.addAll(removedupName);

        Set<String> removedupLocation = new HashSet<>(CollegeAreaCollectionList);
        CollegeAreaCollectionList.clear();
        CollegeAreaCollectionList.addAll(removedupLocation);

        Set<String> removedupAffiliation = new HashSet<>(CollegeAffilitationCollectionList);
        CollegeAffilitationCollectionList.clear();
        CollegeAffilitationCollectionList.addAll(removedupAffiliation);

        List<String> MrgeAll = new ArrayList<>();

        Set<String> removedupAll = new HashSet<>(AllDataListMarge);
        AllDataListMarge.clear();
        AllDataListMarge.addAll(removedupAll);


        /*AllDataListMarge.addAll(CollegeNameCollectionList);
        AllDataListMarge.addAll(CollegeAreaCollectionList);
        AllDataListMarge.addAll(CollegeAffilitationCollectionList);*/

        Log.d("Marged ArrayList",AllDataListMarge.toString());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.college_filter,menu);

        // Get the search men
        MenuItem searchMenu = menu.findItem(R.id.app_bar_menu_search);
        // Get SearchView object.
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchMenu);

        /*List<String> MrgeAll = new ArrayList<>();
        MrgeAll.addAll(CollegeNameCollectionList);
        MrgeAll.addAll(CollegeAreaCollectionList);
        MrgeAll.addAll(CollegeAffilitationCollectionList);*/
        // Get SearchView autocomplete object.
        final SearchView.SearchAutoComplete searchAutoComplete = (SearchView.SearchAutoComplete)searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        searchAutoComplete.setBackgroundColor(Color.WHITE);
        searchAutoComplete.setTextColor(Color.rgb(00,85,77));
        searchAutoComplete.setDropDownBackgroundResource(R.color.colorPrimary);

        // Create a new ArrayAdapter and add data to search auto complete object
        ArrayAdapter<String> newsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, AllDataListMarge);
        searchAutoComplete.setAdapter(newsAdapter);

        // Listen to search view item on click event.
        searchAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int itemIndex, long id) {
                String queryString=(String)adapterView.getItemAtPosition(itemIndex);
                searchAutoComplete.setText("" + queryString);
                Toast.makeText(College.this, "you clicked " + queryString, Toast.LENGTH_LONG).show();
            }
        });

        // Below event is triggered when submit search query.
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
               /*AlertDialog alertDialog = new AlertDialog.Builder(College.this).create();
                alertDialog.setMessage("Search keyword is " + query);
                alertDialog.show();*/

                Checkquery = query;
                checkFlag = 0;

                searchRecyclerView.setVisibility(View.VISIBLE);
                college_Scholarship.setVisibility(View.GONE);

                filterQuery = FirebaseDatabase.getInstance().getReference("CreerL/College");

                filterQuery.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                            upload_college_FilterL.clear();
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                                upload_college_scholarship uploadCol = snapshot.getValue(upload_college_scholarship.class);

                                if (Checkquery.equals(uploadCol.getUploadName()) || Checkquery.equals(uploadCol.getUploadLocation())){

                                    upload_college_FilterL.add(uploadCol);
                                    collegeListadapter = new College_Adapter_only(Checkfragment,College.this,CollegeKey,upload_college_FilterL);
                                    searchRecyclerView.setAdapter(collegeListadapter);
                                    searchRecyclerView.setLayoutManager(new LinearLayoutManager(College.this));
                                    checkFlag = 1;

                                }
                                /*if (checkFlag != 1){

                                    if (Checkquery.compareTo(uploadCol.getUploadName()) > 0 || Checkquery.compareTo(uploadCol.getUploadLocation()) < 0){

                                        searchRecyclerView.setVisibility(View.GONE);
                                        college_Scholarship.setVisibility(View.VISIBLE);

                                        Toast.makeText(College.this,"No Match Found",Toast.LENGTH_SHORT).show();

                                    }
                                }*/

                            }

                            if (checkFlag != 1){

                                        searchRecyclerView.setVisibility(View.GONE);
                                        college_Scholarship.setVisibility(View.VISIBLE);

                                        Toast.makeText(College.this,"No Match Found",Toast.LENGTH_SHORT).show();

                                }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        // Get the share menu item.
        MenuItem shareMenuItem = menu.findItem(R.id.app_bar_menu_share);
        // Because it's actionProviderClass is ShareActionProvider, so after below settings
        // when click this menu item A sharable applications list will popup.
        // User can choose one application to share.
        ShareActionProvider shareActionProvider = (ShareActionProvider)MenuItemCompat.getActionProvider(shareMenuItem);
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/*");
        shareActionProvider.setShareIntent(shareIntent);



        return true;
    }

    @Override
    public void onBackPressed() {

        searchRecyclerView.setVisibility(View.GONE);
        college_Scholarship.setVisibility(View.VISIBLE);

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.college_filter_id:
                //startActivity(new Intent(College.this,CollegeFilterActivity.class));

                Intent filerClgIntent = new Intent(College.this, CollegeFilterActivity.class);
                filerClgIntent.putExtra("TakenClgName",CollegeNameCollectionList);
                filerClgIntent.putExtra("TakenClgLocation",CollegeAreaCollectionList);
                filerClgIntent.putExtra("TakenClgAffilited",CollegeAffilitationCollectionList);
                filerClgIntent.putExtra("TakenClgType",CollegeTypeCollectionList);
                filerClgIntent.putExtra("TakenClgUnder",CollegeUnderCollectionList);
                startActivity(filerClgIntent);
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
