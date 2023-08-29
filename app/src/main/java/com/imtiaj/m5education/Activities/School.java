package com.imtiaj.m5education.Activities;

import android.graphics.Color;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuItemCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.imtiaj.m5education.AdapterClass.College_Scholarship_list_Adapter;
import com.imtiaj.m5education.R;
import com.imtiaj.m5education.modelClass.uploadSchool_pojo;

import java.util.ArrayList;

public class School extends AppCompatActivity {

    private int Checkfragment;

    private RecyclerView SchoolRecyclerView, SchoolSearchRecycleView;
    private ArrayList<uploadSchool_pojo> upload_school_List,upload_scholarship_FilterL;
    private ArrayList<String> Schoolkey;
    private ArrayList<String> SchoolNameArryList;
    private College_Scholarship_list_Adapter scholarshipListadapter;
    private DatabaseReference SchoolDatabase;
    private boolean doubleBackToExitPressedOnce = false;

    private Query filterQuery;
    private String Checkquery = "";
    private int checkFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_scholarship);

        Checkfragment = 1;

        SchoolRecyclerView = (RecyclerView)findViewById(R.id.SchoolRecyclerView);
        SchoolSearchRecycleView = (RecyclerView)findViewById(R.id.SchoolRecyclerView_forSearch);
        upload_school_List = new ArrayList<>();
        SchoolNameArryList = new ArrayList<>();
        upload_scholarship_FilterL = new ArrayList<>();
        Schoolkey = new ArrayList<>();

        SchoolDatabase = FirebaseDatabase.getInstance().getReference("CreerL/School");

        SchoolDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                uploadSchool_pojo SchoolList = dataSnapshot.getValue(uploadSchool_pojo.class);
                upload_school_List.add(SchoolList);
                String mkey = dataSnapshot.getKey();
                Schoolkey.add(mkey);
                SchoolNameArryList.add(SchoolList.getUploadName());
                College_Scholarship_list_Adapter SchoolAdapterlist = new College_Scholarship_list_Adapter(School.this,upload_school_List);
                SchoolRecyclerView.setAdapter(SchoolAdapterlist);
                SchoolRecyclerView.setLayoutManager(new LinearLayoutManager(School.this));

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                uploadSchool_pojo SchoolList = dataSnapshot.getValue(uploadSchool_pojo.class);
                String ChangeKey = dataSnapshot.getKey();
                int index = Schoolkey.indexOf(ChangeKey);
                upload_school_List.set(index,SchoolList);
                SchoolNameArryList.set(index,SchoolList.getUploadName());
                College_Scholarship_list_Adapter SchoolAdapterlist = new College_Scholarship_list_Adapter(School.this,upload_school_List);
                SchoolRecyclerView.setAdapter(SchoolAdapterlist);
                SchoolRecyclerView.setLayoutManager(new LinearLayoutManager(School.this));

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                String RemoveKey = dataSnapshot.getKey();
                int index = Schoolkey.indexOf(RemoveKey);
                upload_school_List.remove(index);
                Schoolkey.remove(index);
                SchoolNameArryList.remove(index);
                College_Scholarship_list_Adapter SchoolAdapterlist = new College_Scholarship_list_Adapter(School.this,upload_school_List);
                SchoolRecyclerView.setAdapter(SchoolAdapterlist);
                SchoolRecyclerView.setLayoutManager(new LinearLayoutManager(School.this));

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_menu,menu);
        MenuItem searchMenu = menu.findItem(R.id.app_bar_search_menu);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchMenu);

        final SearchView.SearchAutoComplete searchAutoComplete = (SearchView.SearchAutoComplete)searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        searchAutoComplete.setBackgroundColor(Color.WHITE);
        searchAutoComplete.setTextColor(Color.rgb(00,85,77));
        searchAutoComplete.setDropDownBackgroundResource(R.color.colorPrimary);

        ArrayAdapter<String> newsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, SchoolNameArryList);
        searchAutoComplete.setAdapter(newsAdapter);

        // Listen to search view item on click event.
        searchAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int itemIndex, long id) {
                String queryString=(String)adapterView.getItemAtPosition(itemIndex);
                searchAutoComplete.setText("" + queryString);
                Toast.makeText(School.this, "you clicked " + queryString, Toast.LENGTH_LONG).show();
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

                SchoolRecyclerView.setVisibility(View.GONE);
                SchoolSearchRecycleView.setVisibility(View.VISIBLE);


                filterQuery = FirebaseDatabase.getInstance().getReference("CreerL/School");

                filterQuery.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                            upload_scholarship_FilterL.clear();
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                                uploadSchool_pojo uploadCol = snapshot.getValue(uploadSchool_pojo.class);

                                if (Checkquery.equals(uploadCol.getUploadName()) || Checkquery.equals(uploadCol.getUploadLocation())){

                                    upload_scholarship_FilterL.add(uploadCol);
                                    scholarshipListadapter = new College_Scholarship_list_Adapter(School.this,upload_scholarship_FilterL);
                                    SchoolSearchRecycleView.setAdapter(scholarshipListadapter);
                                    SchoolSearchRecycleView.setLayoutManager(new LinearLayoutManager(School.this));
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

                                SchoolRecyclerView.setVisibility(View.VISIBLE);
                                SchoolSearchRecycleView.setVisibility(View.GONE);

                                Toast.makeText(School.this,"No Match Found",Toast.LENGTH_SHORT).show();

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

        return true;
    }

    @Override
    public void onBackPressed() {

        SchoolRecyclerView.setVisibility(View.VISIBLE);
        SchoolSearchRecycleView.setVisibility(View.GONE);

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


}
