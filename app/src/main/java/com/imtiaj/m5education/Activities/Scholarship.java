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
import com.imtiaj.m5education.R;
import com.imtiaj.m5education.AdapterClass.Scholarship_adapter;
import com.imtiaj.m5education.modelClass.uploadScholarship_pojoClass;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Scholarship extends AppCompatActivity {

    private int checkFlag;
    private boolean doubleBackToExitPressedOnce = false;
    private RecyclerView ScholarshipRecycle, SearchRecyclerView;
    private ArrayList<uploadScholarship_pojoClass> upload_scholarship_List,upload_scholarship_FilterL;
    private ArrayList<String> ScholarshipKey;
    private Scholarship_adapter scholarshipListadapter;
    private DatabaseReference ScholarshipDatabase;
    private ArrayList<String> scholarshipName, scholarshipOrganization;

    private Query filterQuery;
    private String Checkquery="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scholarship);


        ScholarshipRecycle = (RecyclerView)findViewById(R.id.scholarship_RecyclerView);
        SearchRecyclerView = (RecyclerView)findViewById(R.id.scholarship_Search_RecyclerView);

        upload_scholarship_List = new ArrayList<uploadScholarship_pojoClass>();
        upload_scholarship_FilterL = new ArrayList<>();
        ScholarshipKey = new ArrayList<>();
        scholarshipName = new ArrayList<>();
        scholarshipOrganization = new ArrayList<>();
        //Alldata = new ArrayList<>();

        //CollegeDatabase = FirebaseDatabase.getInstance().getReference("CreerL/College");
        ScholarshipDatabase = FirebaseDatabase.getInstance().getReference("CreerL/Scholarship");




        ScholarshipDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                uploadScholarship_pojoClass uploadCol = dataSnapshot.getValue(uploadScholarship_pojoClass.class);
                upload_scholarship_List.add(uploadCol);
                String mKey = dataSnapshot.getKey();
                ScholarshipKey.add(mKey);
                scholarshipName.add(uploadCol.getUploadName());
                scholarshipOrganization.add(uploadCol.getOrganization());
                scholarshipListadapter = new Scholarship_adapter(Scholarship.this,upload_scholarship_List);

                ScholarshipRecycle.setAdapter(scholarshipListadapter);
                ScholarshipRecycle.setLayoutManager(new LinearLayoutManager(Scholarship.this));


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                uploadScholarship_pojoClass uploadCol = dataSnapshot.getValue(uploadScholarship_pojoClass.class);
                String ChangeKey = dataSnapshot.getKey();
                int index = ScholarshipKey.indexOf(ChangeKey);
                upload_scholarship_List.set(index,uploadCol);
                scholarshipName.set(index,uploadCol.getUploadName());
                scholarshipOrganization.set(index,uploadCol.getOrganization());
                scholarshipListadapter = new Scholarship_adapter(Scholarship.this,upload_scholarship_List);

                ScholarshipRecycle.setAdapter(scholarshipListadapter);
                ScholarshipRecycle.setLayoutManager(new LinearLayoutManager(Scholarship.this));


            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                String RemoveKey = dataSnapshot.getKey();
                int index = ScholarshipKey.indexOf(RemoveKey);
                upload_scholarship_List.remove(index);
                ScholarshipKey.remove(index);
                scholarshipName.remove(index);
                scholarshipOrganization.remove(index);
                scholarshipListadapter = new Scholarship_adapter(Scholarship.this,upload_scholarship_List);

                ScholarshipRecycle.setAdapter(scholarshipListadapter);
                ScholarshipRecycle.setLayoutManager(new LinearLayoutManager(Scholarship.this));

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Set<String> removedupSchoName = new HashSet<>(scholarshipName);
        scholarshipName.clear();
        scholarshipName.addAll(removedupSchoName);

        Set<String> removedupSchoOrganization = new HashSet<>(scholarshipOrganization);
        scholarshipOrganization.clear();
        scholarshipOrganization.addAll(removedupSchoOrganization);

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

        ArrayAdapter<String> newsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, scholarshipName);
        searchAutoComplete.setAdapter(newsAdapter);

        // Listen to search view item on click event.
        searchAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int itemIndex, long id) {
                String queryString=(String)adapterView.getItemAtPosition(itemIndex);
                searchAutoComplete.setText("" + queryString);
                Toast.makeText(Scholarship.this, "you clicked " + queryString, Toast.LENGTH_LONG).show();
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

                ScholarshipRecycle.setVisibility(View.GONE);
                SearchRecyclerView.setVisibility(View.VISIBLE);


                filterQuery = FirebaseDatabase.getInstance().getReference("CreerL/Scholarship");

                filterQuery.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                            upload_scholarship_FilterL.clear();
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                                uploadScholarship_pojoClass uploadCol = snapshot.getValue(uploadScholarship_pojoClass.class);

                                if (Checkquery.equals(uploadCol.getUploadName()) || Checkquery.equals(uploadCol.getOrganization())){

                                    upload_scholarship_FilterL.add(uploadCol);
                                    scholarshipListadapter = new Scholarship_adapter(Scholarship.this,upload_scholarship_FilterL);
                                    SearchRecyclerView.setAdapter(scholarshipListadapter);
                                    SearchRecyclerView.setLayoutManager(new LinearLayoutManager(Scholarship.this));
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

                                ScholarshipRecycle.setVisibility(View.VISIBLE);
                                SearchRecyclerView.setVisibility(View.GONE);

                                Toast.makeText(Scholarship.this,"No Match Found",Toast.LENGTH_SHORT).show();

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

        ScholarshipRecycle.setVisibility(View.VISIBLE);
        SearchRecyclerView.setVisibility(View.GONE);

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
