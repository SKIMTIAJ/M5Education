package com.imtiaj.m5education.Activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.imtiaj.m5education.AdapterClass.College_Adapter_only;
import com.imtiaj.m5education.R;
import com.imtiaj.m5education.modelClass.upload_college_scholarship;

import java.util.ArrayList;

public class FilterdeActivity extends AppCompatActivity {

    private int isChildbeVisible = 0,isClgTypeCome = 0;
    private ArrayList<upload_college_scholarship> upload_college_FilterL;
    private College_Adapter_only collegeListadapter;

    private RecyclerView recyclerView;
    private String FilterResNameClg ="", FilterResAffiliatedClg ="",FilterResLocationClg ="",FilterResTypeClg=""
            ,FilterResUnderClg="",FilterResQualityClg="";

    private String CheckIfAllValuCome= "",Checkfragment;

    private Query filterQuery;

    //private ProgressDialog filterPd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filterde);


        Bundle BFilterClgeRes = getIntent().getExtras();
        if (BFilterClgeRes!=null){

            isClgTypeCome = BFilterClgeRes.getInt("typeCheck");

            if (isClgTypeCome==2){

                FilterResNameClg = BFilterClgeRes.getString("CollegeNameRes");
                FilterResAffiliatedClg = BFilterClgeRes.getString("CollegeAffiliatedRes");
                FilterResLocationClg = BFilterClgeRes.getString("CollegeLocationRes");
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

        }

        recyclerView = (RecyclerView)findViewById(R.id.FilterRecyclerView);
        upload_college_FilterL = new ArrayList<>();
        //filterPd = new ProgressDialog(this);

        if(FilterResUnderClg.equals("AllChecked")){

            if (!FilterResNameClg.isEmpty() && !FilterResAffiliatedClg.isEmpty() && !FilterResLocationClg.isEmpty() && !FilterResTypeClg.isEmpty()){

                CheckIfAllValuCome = "allIsCommingNoUnder";

            }

            if (FilterResNameClg.isEmpty() && FilterResAffiliatedClg.isEmpty() && FilterResLocationClg.isEmpty() && !FilterResTypeClg.isEmpty()){

                CheckIfAllValuCome = "justTypeIsCommingnoUnder";

            }


            if (FilterResNameClg.isEmpty() && FilterResAffiliatedClg.isEmpty() && !FilterResLocationClg.isEmpty() && FilterResTypeClg.isEmpty()){

                CheckIfAllValuCome = "SearchByLocation";

            }

            if (FilterResNameClg.isEmpty() && !FilterResAffiliatedClg.isEmpty() && FilterResLocationClg.isEmpty() && FilterResTypeClg.isEmpty()){

                CheckIfAllValuCome = "SearchByAffiliation";

            }

            if (!FilterResNameClg.isEmpty() && FilterResAffiliatedClg.isEmpty() && FilterResLocationClg.isEmpty() && FilterResTypeClg.isEmpty()){

                CheckIfAllValuCome = "SearchByName";

            }

            if (FilterResNameClg.isEmpty() && FilterResAffiliatedClg.isEmpty() && !FilterResLocationClg.isEmpty() && !FilterResTypeClg.isEmpty()){

                CheckIfAllValuCome = "SearchByLocationAndTypeNoUnder";

            }

            if (FilterResNameClg.isEmpty() && !FilterResAffiliatedClg.isEmpty() && !FilterResLocationClg.isEmpty() && FilterResTypeClg.isEmpty()){

                CheckIfAllValuCome = "SearchByAffilitionAndLocationNoUnder";

            }

            if (!FilterResNameClg.isEmpty() && !FilterResAffiliatedClg.isEmpty() && FilterResLocationClg.isEmpty() && FilterResTypeClg.isEmpty()){

                CheckIfAllValuCome = "SearchByNameAndAffiliationNoUnder";

            }

            if (FilterResNameClg.isEmpty() && !FilterResAffiliatedClg.isEmpty() && FilterResLocationClg.isEmpty() && !FilterResTypeClg.isEmpty()){

                CheckIfAllValuCome = "SearchByAffiliationaAndTypeNoUnder";

            }

            if (!FilterResNameClg.isEmpty()  && !FilterResTypeClg.isEmpty() && FilterResAffiliatedClg.isEmpty() && FilterResLocationClg.isEmpty()){

                CheckIfAllValuCome = "SearchByNameAndTypeNoUnder";

            }

            if (!FilterResNameClg.isEmpty()  && FilterResTypeClg.isEmpty() && FilterResAffiliatedClg.isEmpty() && !FilterResLocationClg.isEmpty()){

                CheckIfAllValuCome = "SearchByNameAndTLocationNoUnder";

            }

            if (FilterResNameClg.isEmpty() && !FilterResAffiliatedClg.isEmpty() && !FilterResLocationClg.isEmpty() && !FilterResTypeClg.isEmpty()){

                CheckIfAllValuCome = "allButNotNameIsCommingNounder";

            }

            if (!FilterResNameClg.isEmpty() && FilterResAffiliatedClg.isEmpty() && !FilterResLocationClg.isEmpty() && !FilterResTypeClg.isEmpty()){

                CheckIfAllValuCome = "allButNotAffiliationIsCommingNounder";

            }

            if (!FilterResNameClg.isEmpty() && !FilterResAffiliatedClg.isEmpty() && !FilterResLocationClg.isEmpty() && FilterResTypeClg.isEmpty()){

                CheckIfAllValuCome = "allButNotTypeIsCommingNounder";

            }
            if (!FilterResNameClg.isEmpty() && !FilterResAffiliatedClg.isEmpty() && FilterResLocationClg.isEmpty() && !FilterResTypeClg.isEmpty()){

                CheckIfAllValuCome = "allButNotLocationIsCommingNounder";

            }
if (FilterResNameClg.isEmpty() && FilterResAffiliatedClg.isEmpty() && !FilterResLocationClg.isEmpty() && !FilterResTypeClg.isEmpty()){

                CheckIfAllValuCome = "exceptNameAndAffilIsCommingnounder";

            }



            if (FilterResNameClg.isEmpty() && FilterResAffiliatedClg.isEmpty() && FilterResLocationClg.isEmpty() && FilterResTypeClg.isEmpty()
                    && !FilterResUnderClg.isEmpty()){

                CheckIfAllValuCome = "underIsCommingJustAll";

                ProgressDialog filterPd = new ProgressDialog(FilterdeActivity.this);
                filterPd.setTitle("Searching...");
                filterPd.setMessage("can't select only All");
                filterPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                filterPd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //dialog.cancel();
                        finish();
                        startActivity(new Intent(FilterdeActivity.this, College.class));
                    }
                });
                filterPd.show();


               // Toast.makeText(FilterdeActivity.this,"You Cant Select All Only",Toast.LENGTH_LONG).show();

            }

        }else {

            if (!FilterResNameClg.isEmpty() && !FilterResAffiliatedClg.isEmpty() && !FilterResLocationClg.isEmpty() && !FilterResTypeClg.isEmpty()
                    && !FilterResUnderClg.isEmpty()){

                CheckIfAllValuCome = "allIsComming";

            }

            if (FilterResNameClg.isEmpty() && FilterResAffiliatedClg.isEmpty() && FilterResLocationClg.isEmpty() && FilterResTypeClg.isEmpty()
                    && !FilterResUnderClg.isEmpty()){

                CheckIfAllValuCome = "underIsComming";

            }

            if (FilterResNameClg.isEmpty() && FilterResAffiliatedClg.isEmpty() && FilterResLocationClg.isEmpty() && !FilterResTypeClg.isEmpty()
                    && !FilterResUnderClg.isEmpty()){

                CheckIfAllValuCome = "justTypeandUnderIsComming";


            }

            if (FilterResNameClg.isEmpty() && FilterResAffiliatedClg.isEmpty() && !FilterResLocationClg.isEmpty() && FilterResTypeClg.isEmpty()
                    && !FilterResUnderClg.isEmpty()){

                CheckIfAllValuCome = "SearchByLocationAndUnder";

            }

            if (!FilterResNameClg.isEmpty() && !FilterResAffiliatedClg.isEmpty() && FilterResLocationClg.isEmpty() && FilterResTypeClg.isEmpty()
                    && FilterResUnderClg.isEmpty()){

                CheckIfAllValuCome = "SearchBynameAndAffiliation";

            }

            if (FilterResNameClg.isEmpty() && !FilterResAffiliatedClg.isEmpty() && !FilterResLocationClg.isEmpty() && FilterResTypeClg.isEmpty()
                    && FilterResUnderClg.isEmpty()){

                CheckIfAllValuCome = "SearchByAffiliationAndLocation";

            }

            if (FilterResNameClg.isEmpty() && FilterResAffiliatedClg.isEmpty() && !FilterResLocationClg.isEmpty() && !FilterResTypeClg.isEmpty()
                    && FilterResUnderClg.isEmpty()){

                CheckIfAllValuCome = "SearchByLocationAndType";

            }

            if (FilterResNameClg.isEmpty() && !FilterResAffiliatedClg.isEmpty() && FilterResLocationClg.isEmpty() && !FilterResTypeClg.isEmpty()
                    && FilterResUnderClg.isEmpty()){

                CheckIfAllValuCome = "SearchByAffiliationAndType";

            }

            if (!FilterResNameClg.isEmpty() && FilterResAffiliatedClg.isEmpty() && FilterResLocationClg.isEmpty() && !FilterResTypeClg.isEmpty()
                    && FilterResUnderClg.isEmpty()){

                CheckIfAllValuCome = "SearchByNameAndType";

            }

            if (FilterResNameClg.isEmpty() && !FilterResAffiliatedClg.isEmpty() && FilterResLocationClg.isEmpty() && FilterResTypeClg.isEmpty()
                    && !FilterResUnderClg.isEmpty()){

                CheckIfAllValuCome = "SearchByAffiliationAndUnder";

            }

            if (!FilterResNameClg.isEmpty() && FilterResAffiliatedClg.isEmpty() && FilterResLocationClg.isEmpty() && FilterResTypeClg.isEmpty()
                    && !FilterResUnderClg.isEmpty()){

                CheckIfAllValuCome = "SearchByNameAndUnder";
            }

            if (!FilterResNameClg.isEmpty() && FilterResAffiliatedClg.isEmpty() && !FilterResLocationClg.isEmpty() && FilterResTypeClg.isEmpty()
                    && FilterResUnderClg.isEmpty()){

                CheckIfAllValuCome = "SearchByNameAndLocation";
            }

            if (FilterResNameClg.isEmpty() && FilterResAffiliatedClg.isEmpty() && !FilterResLocationClg.isEmpty() && !FilterResTypeClg.isEmpty()
                    && !FilterResUnderClg.isEmpty()){

                CheckIfAllValuCome = "SearchByLocationAndTypeAndUnder";

            }

            if (FilterResNameClg.isEmpty() && !FilterResAffiliatedClg.isEmpty() && !FilterResLocationClg.isEmpty() && FilterResTypeClg.isEmpty()
                    && !FilterResUnderClg.isEmpty()){

                CheckIfAllValuCome = "SearchByAffiliationAndLocationAndUnder";

            }
            if (!FilterResNameClg.isEmpty() && !FilterResAffiliatedClg.isEmpty() && !FilterResLocationClg.isEmpty() && FilterResTypeClg.isEmpty()
                    && FilterResUnderClg.isEmpty()){

                CheckIfAllValuCome = "SearchByNameAndAffiliationAndLocation";

            }

            if (!FilterResNameClg.isEmpty() && !FilterResAffiliatedClg.isEmpty() && FilterResLocationClg.isEmpty() && FilterResTypeClg.isEmpty()
                    && !FilterResUnderClg.isEmpty()){

                CheckIfAllValuCome = "SearchByNameAndAffiliationAndUnder";

            }

            if (!FilterResNameClg.isEmpty() && !FilterResAffiliatedClg.isEmpty() && FilterResLocationClg.isEmpty() && !FilterResTypeClg.isEmpty()
                    && FilterResUnderClg.isEmpty()){

                CheckIfAllValuCome = "SearchByNameAndAffiliationAndType";

            }
            if (FilterResNameClg.isEmpty() && !FilterResAffiliatedClg.isEmpty() && !FilterResLocationClg.isEmpty() && !FilterResTypeClg.isEmpty()
                    && FilterResUnderClg.isEmpty()){

                CheckIfAllValuCome = "SearchByAffiliationAndLocationAndType";

            }
            if (!FilterResNameClg.isEmpty() && FilterResAffiliatedClg.isEmpty() && FilterResLocationClg.isEmpty() && !FilterResTypeClg.isEmpty()
                    && !FilterResUnderClg.isEmpty()){

                CheckIfAllValuCome = "SearchByNameAndTypeAndUnder";
            }

            if (FilterResNameClg.isEmpty() && !FilterResAffiliatedClg.isEmpty() && FilterResLocationClg.isEmpty() && !FilterResTypeClg.isEmpty()
                    && !FilterResUnderClg.isEmpty()){

                CheckIfAllValuCome = "SearchByAffiliationAndTypeAndUnder";

            }
            if (!FilterResNameClg.isEmpty() && FilterResAffiliatedClg.isEmpty() && !FilterResLocationClg.isEmpty() && FilterResTypeClg.isEmpty()
                    && !FilterResUnderClg.isEmpty()){

                CheckIfAllValuCome = "SearchByNameAndLocationAndUnder";

            }

            if (FilterResNameClg.isEmpty() && !FilterResAffiliatedClg.isEmpty() && !FilterResLocationClg.isEmpty() && !FilterResTypeClg.isEmpty()
                    && !FilterResUnderClg.isEmpty()){

                CheckIfAllValuCome = "allButNotNameIsComming";

            }

            if (!FilterResNameClg.isEmpty() && !FilterResAffiliatedClg.isEmpty() && !FilterResLocationClg.isEmpty() && !FilterResTypeClg.isEmpty()
                    && FilterResUnderClg.isEmpty()){

                CheckIfAllValuCome = "SearchByNALT";

            }

            if (!FilterResNameClg.isEmpty() && !FilterResAffiliatedClg.isEmpty() && FilterResLocationClg.isEmpty() && !FilterResTypeClg.isEmpty()
                    && !FilterResUnderClg.isEmpty()){

                CheckIfAllValuCome = "SearchByNAUT";

            }

            if (!FilterResNameClg.isEmpty() && !FilterResAffiliatedClg.isEmpty() && !FilterResLocationClg.isEmpty() && !FilterResTypeClg.isEmpty()
                    && FilterResUnderClg.isEmpty()){

                CheckIfAllValuCome = "SearchByNLUT";

            }

            if (!FilterResNameClg.isEmpty() && !FilterResAffiliatedClg.isEmpty() && !FilterResLocationClg.isEmpty() && FilterResTypeClg.isEmpty()
                    && !FilterResUnderClg.isEmpty()){

                CheckIfAllValuCome = "SearchByNALU";

            }

  if (FilterResNameClg.isEmpty() && FilterResAffiliatedClg.isEmpty() && !FilterResLocationClg.isEmpty() && !FilterResTypeClg.isEmpty()
                    && !FilterResUnderClg.isEmpty()){

                CheckIfAllValuCome = "exceptNameAndAffilIsComming";


            }


        }

        if (FilterResQualityClg.equals("Best")){

            filterQuery = FirebaseDatabase.getInstance().getReference("CreerL/College")
                    .orderByChild("uploadRating")
                    .startAt(4);


        }
        if (FilterResQualityClg.equals("Medium")){

            filterQuery = FirebaseDatabase.getInstance().getReference("CreerL/College")
                    .orderByChild("uploadRating")
                    .startAt(3);

        }
        if (FilterResQualityClg.equals("Worst")){

            filterQuery = FirebaseDatabase.getInstance().getReference("CreerL/College")
                    .orderByChild("uploadRating")
                    .endAt(3);

        }

        filterQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){

 for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        upload_college_scholarship uploadCol = snapshot.getValue(upload_college_scholarship.class);
                        boolean flag = true;
                        if (!FilterResNameClg.isEmpty()){
                            if (!FilterResNameClg.equals(uploadCol.getUploadName())){
                                flag = false;
                               // continue;
                            }
                        }
                        if (!FilterResAffiliatedClg.isEmpty()){
                            if (!FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())){
                                flag = false;
                               // continue;
                            }

                        }
                        if (!FilterResLocationClg.isEmpty()){
                            if (!FilterResLocationClg.equals(uploadCol.getUploadLocation())){
                                flag = false;
                                //continue;
                            }

                        }
                        if (!FilterResUnderClg.isEmpty()){
                            if (!FilterResUnderClg.equals(uploadCol.getUploadUnder())){
                                flag = false;
                                //continue;
                            }

                        }
                        if (!FilterResTypeClg.isEmpty()){
                            if (!FilterResTypeClg.equals(uploadCol.getUploadType())){
                                flag = false;
                               // continue;
                            }

                        }

                        if (flag){

                            upload_college_FilterL.add(uploadCol);
                            collegeListadapter = new College_Adapter_only(Checkfragment,FilterdeActivity.this,upload_college_FilterL);
                            recyclerView.setAdapter(collegeListadapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(FilterdeActivity.this));

                        }else {

                            ProgressDialog filterPd = new ProgressDialog(FilterdeActivity.this);
                            filterPd.setTitle("No Match Found...");
                            filterPd.setMessage("Entered input are not matching");
                            filterPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                            filterPd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //dialog.cancel();
                                    finish();
                                    startActivity(new Intent(FilterdeActivity.this,College.class));
                                }
                            });
                            filterPd.show();

                        }
                    }


                    if (CheckIfAllValuCome.equals("allIsComming")){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                            upload_college_scholarship uploadCol = snapshot.getValue(upload_college_scholarship.class);

                            if (FilterResNameClg.equals(uploadCol.getUploadName()) && FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())
                                    && FilterResLocationClg.equals(uploadCol.getUploadLocation()) && FilterResUnderClg.equals(uploadCol.getUploadUnder())
                                    && FilterResTypeClg.equals(uploadCol.getUploadType())){

                                upload_college_FilterL.add(uploadCol);
                                collegeListadapter = new College_Adapter_only(Checkfragment,FilterdeActivity.this,upload_college_FilterL);
                                recyclerView.setAdapter(collegeListadapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(FilterdeActivity.this));

                            }
                           if (FilterResNameClg.equals(uploadCol.getUploadName()) && FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())
                                   && FilterResLocationClg.equals(uploadCol.getUploadLocation()) && FilterResUnderClg.equals(uploadCol.getUploadUnder())
                                   && !FilterResTypeClg.equals(uploadCol.getUploadType())){

                               ProgressDialog filterPd = new ProgressDialog(FilterdeActivity.this);
                               filterPd.setTitle("No Match Found...");
                               filterPd.setMessage("Entered input are not matching");
                               filterPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                               filterPd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                                   @Override
                                   public void onClick(DialogInterface dialog, int which) {
                                       //dialog.cancel();
                                       finish();
                                       startActivity(new Intent(FilterdeActivity.this,College.class));
                                   }
                               });
                               filterPd.show();


                           }
                            if (FilterResNameClg.equals(uploadCol.getUploadName()) && FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())
                                    && FilterResLocationClg.equals(uploadCol.getUploadLocation()) && !FilterResUnderClg.equals(uploadCol.getUploadUnder())
                                    && FilterResTypeClg.equals(uploadCol.getUploadType())){

                                ProgressDialog filterPd = new ProgressDialog(FilterdeActivity.this);
                                filterPd.setTitle("No Match Found...");
                                filterPd.setMessage("Entered input are not matching");
                                filterPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                filterPd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.cancel();
                                        finish();
                                        startActivity(new Intent(FilterdeActivity.this,College.class));
                                    }
                                });
                                filterPd.show();


                            }
                            if (FilterResNameClg.equals(uploadCol.getUploadName()) && FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())
                                    && !FilterResLocationClg.equals(uploadCol.getUploadLocation()) && FilterResUnderClg.equals(uploadCol.getUploadUnder())
                                    && FilterResTypeClg.equals(uploadCol.getUploadType())){

                                ProgressDialog filterPd = new ProgressDialog(FilterdeActivity.this);
                                filterPd.setTitle("No Match Found...");
                                filterPd.setMessage("Entered input are not matching");
                                filterPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                filterPd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.cancel();
                                        finish();
                                        startActivity(new Intent(FilterdeActivity.this,College.class));
                                    }
                                });
                                filterPd.show();


                            }
                            if (FilterResNameClg.equals(uploadCol.getUploadName()) && !FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())
                                    && FilterResLocationClg.equals(uploadCol.getUploadLocation()) && FilterResUnderClg.equals(uploadCol.getUploadUnder())
                                    && FilterResTypeClg.equals(uploadCol.getUploadType())){

                                ProgressDialog filterPd = new ProgressDialog(FilterdeActivity.this);
                                filterPd.setTitle("No Match Found...");
                                filterPd.setMessage("Entered input are not matching");
                                filterPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                filterPd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.cancel();
                                        finish();
                                        startActivity(new Intent(FilterdeActivity.this,College.class));
                                    }
                                });
                                filterPd.show();


                            }
                            if (!FilterResNameClg.equals(uploadCol.getUploadName()) && FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())
                                    && FilterResLocationClg.equals(uploadCol.getUploadLocation()) && FilterResUnderClg.equals(uploadCol.getUploadUnder())
                                    && FilterResTypeClg.equals(uploadCol.getUploadType())){

                                ProgressDialog filterPd = new ProgressDialog(FilterdeActivity.this);
                                filterPd.setTitle("No Match Found...");
                                filterPd.setMessage("Entered input are not matching");
                                filterPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                filterPd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.cancel();
                                        finish();
                                        startActivity(new Intent(FilterdeActivity.this,College.class));
                                    }
                                });
                                filterPd.show();


                            }

                            //............................................................................................

                            if (FilterResNameClg.equals(uploadCol.getUploadName()) && FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())
                                    && FilterResLocationClg.equals(uploadCol.getUploadLocation()) && !FilterResUnderClg.equals(uploadCol.getUploadUnder())
                                    && !FilterResTypeClg.equals(uploadCol.getUploadType())){

                                ProgressDialog filterPd = new ProgressDialog(FilterdeActivity.this);
                                filterPd.setTitle("No Match Found...");
                                filterPd.setMessage("Entered input are not matching");
                                filterPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                filterPd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.cancel();
                                        finish();
                                        startActivity(new Intent(FilterdeActivity.this,College.class));
                                    }
                                });
                                filterPd.show();


                            }
                            if (FilterResNameClg.equals(uploadCol.getUploadName()) && FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())
                                    && !FilterResLocationClg.equals(uploadCol.getUploadLocation()) && !FilterResUnderClg.equals(uploadCol.getUploadUnder())
                                    && FilterResTypeClg.equals(uploadCol.getUploadType())){

                                ProgressDialog filterPd = new ProgressDialog(FilterdeActivity.this);
                                filterPd.setTitle("No Match Found...");
                                filterPd.setMessage("Entered input are not matching");
                                filterPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                filterPd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.cancel();
                                        finish();
                                        startActivity(new Intent(FilterdeActivity.this,College.class));
                                    }
                                });
                                filterPd.show();


                            }
                            if (FilterResNameClg.equals(uploadCol.getUploadName()) && !FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())
                                    && !FilterResLocationClg.equals(uploadCol.getUploadLocation()) && FilterResUnderClg.equals(uploadCol.getUploadUnder())
                                    && FilterResTypeClg.equals(uploadCol.getUploadType())){

                                ProgressDialog filterPd = new ProgressDialog(FilterdeActivity.this);
                                filterPd.setTitle("No Match Found...");
                                filterPd.setMessage("Entered input are not matching");
                                filterPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                filterPd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.cancel();
                                        finish();
                                        startActivity(new Intent(FilterdeActivity.this,College.class));
                                    }
                                });
                                filterPd.show();


                            }
                            if (!FilterResNameClg.equals(uploadCol.getUploadName()) && !FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())
                                    && FilterResLocationClg.equals(uploadCol.getUploadLocation()) && FilterResUnderClg.equals(uploadCol.getUploadUnder())
                                    && FilterResTypeClg.equals(uploadCol.getUploadType())){

                                ProgressDialog filterPd = new ProgressDialog(FilterdeActivity.this);
                                filterPd.setTitle("No Match Found...");
                                filterPd.setMessage("Entered input are not matching");
                                filterPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                filterPd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.cancel();
                                        finish();
                                        startActivity(new Intent(FilterdeActivity.this,College.class));
                                    }
                                });
                                filterPd.show();


                            }

                            //  *......................................................................................................................

                            if (FilterResNameClg.equals(uploadCol.getUploadName()) && FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())
                                    && !FilterResLocationClg.equals(uploadCol.getUploadLocation()) && FilterResUnderClg.equals(uploadCol.getUploadUnder())
                                    && !FilterResTypeClg.equals(uploadCol.getUploadType())){

                                ProgressDialog filterPd = new ProgressDialog(FilterdeActivity.this);
                                filterPd.setTitle("No Match Found...");
                                filterPd.setMessage("Entered input are not matching");
                                filterPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                filterPd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.cancel();
                                        finish();
                                        startActivity(new Intent(FilterdeActivity.this,College.class));
                                    }
                                });
                                filterPd.show();


                            }
                            if (FilterResNameClg.equals(uploadCol.getUploadName()) && !FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())
                                    && FilterResLocationClg.equals(uploadCol.getUploadLocation()) && FilterResUnderClg.equals(uploadCol.getUploadUnder())
                                    && !FilterResTypeClg.equals(uploadCol.getUploadType())){

                                ProgressDialog filterPd = new ProgressDialog(FilterdeActivity.this);
                                filterPd.setTitle("No Match Found...");
                                filterPd.setMessage("Entered input are not matching");
                                filterPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                filterPd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.cancel();
                                        finish();
                                        startActivity(new Intent(FilterdeActivity.this,College.class));
                                    }
                                });
                                filterPd.show();


                            }
                            if (!FilterResNameClg.equals(uploadCol.getUploadName()) && FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())
                                    && FilterResLocationClg.equals(uploadCol.getUploadLocation()) && FilterResUnderClg.equals(uploadCol.getUploadUnder())
                                    && !FilterResTypeClg.equals(uploadCol.getUploadType())){

                                ProgressDialog filterPd = new ProgressDialog(FilterdeActivity.this);
                                filterPd.setTitle("No Match Found...");
                                filterPd.setMessage("Entered input are not matching");
                                filterPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                filterPd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.cancel();
                                        finish();
                                        startActivity(new Intent(FilterdeActivity.this,College.class));
                                    }
                                });
                                filterPd.show();


                            }

                            //  ...............................................................................................................

                            if (FilterResNameClg.equals(uploadCol.getUploadName()) && !FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())
                                    && FilterResLocationClg.equals(uploadCol.getUploadLocation()) && !FilterResUnderClg.equals(uploadCol.getUploadUnder())
                                    && FilterResTypeClg.equals(uploadCol.getUploadType())){

                                ProgressDialog filterPd = new ProgressDialog(FilterdeActivity.this);
                                filterPd.setTitle("No Match Found...");
                                filterPd.setMessage("Entered input are not matching");
                                filterPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                filterPd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.cancel();
                                        finish();
                                        startActivity(new Intent(FilterdeActivity.this,College.class));
                                    }
                                });
                                filterPd.show();


                            }
                            if (!FilterResNameClg.equals(uploadCol.getUploadName()) && FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())
                                    && FilterResLocationClg.equals(uploadCol.getUploadLocation()) && !FilterResUnderClg.equals(uploadCol.getUploadUnder())
                                    && FilterResTypeClg.equals(uploadCol.getUploadType())){

                                ProgressDialog filterPd = new ProgressDialog(FilterdeActivity.this);
                                filterPd.setTitle("No Match Found...");
                                filterPd.setMessage("Entered input are not matching");
                                filterPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                filterPd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.cancel();
                                        finish();
                                        startActivity(new Intent(FilterdeActivity.this,College.class));
                                    }
                                });
                                filterPd.show();


                            }
                            if (!FilterResNameClg.equals(uploadCol.getUploadName()) && FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())
                                    && !FilterResLocationClg.equals(uploadCol.getUploadLocation()) && FilterResUnderClg.equals(uploadCol.getUploadUnder())
                                    && FilterResTypeClg.equals(uploadCol.getUploadType())){

                                ProgressDialog filterPd = new ProgressDialog(FilterdeActivity.this);
                                filterPd.setTitle("No Match Found...");
                                filterPd.setMessage("Entered input are not matching");
                                filterPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                filterPd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.cancel();
                                        finish();
                                        startActivity(new Intent(FilterdeActivity.this,College.class));
                                    }
                                });
                                filterPd.show();


                            }

                            //  ..................................................................................................................................

                            if (FilterResNameClg.equals(uploadCol.getUploadName()) && FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())
                                    && !FilterResLocationClg.equals(uploadCol.getUploadLocation()) && !FilterResUnderClg.equals(uploadCol.getUploadUnder())
                                    && !FilterResTypeClg.equals(uploadCol.getUploadType())){

                                ProgressDialog filterPd = new ProgressDialog(FilterdeActivity.this);
                                filterPd.setTitle("No Match Found...");
                                filterPd.setMessage("Entered input are not matching");
                                filterPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                filterPd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.cancel();
                                        finish();
                                        startActivity(new Intent(FilterdeActivity.this,College.class));
                                    }
                                });
                                filterPd.show();


                            }
                            if (FilterResNameClg.equals(uploadCol.getUploadName()) && !FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())
                                    && !FilterResLocationClg.equals(uploadCol.getUploadLocation()) && !FilterResUnderClg.equals(uploadCol.getUploadUnder())
                                    && FilterResTypeClg.equals(uploadCol.getUploadType())){

                                ProgressDialog filterPd = new ProgressDialog(FilterdeActivity.this);
                                filterPd.setTitle("No Match Found...");
                                filterPd.setMessage("Entered input are not matching");
                                filterPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                filterPd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.cancel();
                                        finish();
                                        startActivity(new Intent(FilterdeActivity.this,College.class));
                                    }
                                });
                                filterPd.show();


                            }
                            if (!FilterResNameClg.equals(uploadCol.getUploadName()) && !FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())
                                    && !FilterResLocationClg.equals(uploadCol.getUploadLocation()) && FilterResUnderClg.equals(uploadCol.getUploadUnder())
                                    && FilterResTypeClg.equals(uploadCol.getUploadType())){

                                ProgressDialog filterPd = new ProgressDialog(FilterdeActivity.this);
                                filterPd.setTitle("No Match Found...");
                                filterPd.setMessage("Entered input are not matching");
                                filterPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                filterPd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.cancel();
                                        finish();
                                        startActivity(new Intent(FilterdeActivity.this,College.class));
                                    }
                                });
                                filterPd.show();


                            }
                            if (!FilterResNameClg.equals(uploadCol.getUploadName()) && !FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())
                                    && FilterResLocationClg.equals(uploadCol.getUploadLocation()) && !FilterResUnderClg.equals(uploadCol.getUploadUnder())
                                    && FilterResTypeClg.equals(uploadCol.getUploadType())){

                                ProgressDialog filterPd = new ProgressDialog(FilterdeActivity.this);
                                filterPd.setTitle("No Match Found...");
                                filterPd.setMessage("Entered input are not matching");
                                filterPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                filterPd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.cancel();
                                        finish();
                                        startActivity(new Intent(FilterdeActivity.this,College.class));
                                    }
                                });
                                filterPd.show();


                            }
                            if (!FilterResNameClg.equals(uploadCol.getUploadName()) && !FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())
                                    && FilterResLocationClg.equals(uploadCol.getUploadLocation()) && FilterResUnderClg.equals(uploadCol.getUploadUnder())
                                    && !FilterResTypeClg.equals(uploadCol.getUploadType())){

                                ProgressDialog filterPd = new ProgressDialog(FilterdeActivity.this);
                                filterPd.setTitle("No Match Found...");
                                filterPd.setMessage("Entered input are not matching");
                                filterPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                filterPd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.cancel();
                                        finish();
                                        startActivity(new Intent(FilterdeActivity.this,College.class));
                                    }
                                });
                                filterPd.show();


                            }
                            if (FilterResNameClg.equals(uploadCol.getUploadName()) && !FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())
                                    && !FilterResLocationClg.equals(uploadCol.getUploadLocation()) && FilterResUnderClg.equals(uploadCol.getUploadUnder())
                                    && !FilterResTypeClg.equals(uploadCol.getUploadType())){

                                ProgressDialog filterPd = new ProgressDialog(FilterdeActivity.this);
                                filterPd.setTitle("No Match Found...");
                                filterPd.setMessage("Entered input are not matching");
                                filterPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                filterPd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.cancel();
                                        finish();
                                        startActivity(new Intent(FilterdeActivity.this,College.class));
                                    }
                                });
                                filterPd.show();


                            }
                            if (!FilterResNameClg.equals(uploadCol.getUploadName()) && FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())
                                    && FilterResLocationClg.equals(uploadCol.getUploadLocation()) && !FilterResUnderClg.equals(uploadCol.getUploadUnder())
                                    && !FilterResTypeClg.equals(uploadCol.getUploadType())){

                                ProgressDialog filterPd = new ProgressDialog(FilterdeActivity.this);
                                filterPd.setTitle("No Match Found...");
                                filterPd.setMessage("Entered input are not matching");
                                filterPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                filterPd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.cancel();
                                        finish();
                                        startActivity(new Intent(FilterdeActivity.this,College.class));
                                    }
                                });
                                filterPd.show();


                            }
                            if (FilterResNameClg.equals(uploadCol.getUploadName()) && !FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())
                                    && FilterResLocationClg.equals(uploadCol.getUploadLocation()) && !FilterResUnderClg.equals(uploadCol.getUploadUnder())
                                    && !FilterResTypeClg.equals(uploadCol.getUploadType())){

                                ProgressDialog filterPd = new ProgressDialog(FilterdeActivity.this);
                                filterPd.setTitle("No Match Found...");
                                filterPd.setMessage("Entered input are not matching");
                                filterPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                filterPd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.cancel();
                                        finish();
                                        startActivity(new Intent(FilterdeActivity.this,College.class));
                                    }
                                });
                                filterPd.show();


                            }
                            if (!FilterResNameClg.equals(uploadCol.getUploadName()) && FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())
                                    && !FilterResLocationClg.equals(uploadCol.getUploadLocation()) && !FilterResUnderClg.equals(uploadCol.getUploadUnder())
                                    && FilterResTypeClg.equals(uploadCol.getUploadType())){

                                ProgressDialog filterPd = new ProgressDialog(FilterdeActivity.this);
                                filterPd.setTitle("No Match Found...");
                                filterPd.setMessage("Entered input are not matching");
                                filterPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                filterPd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.cancel();
                                        finish();
                                        startActivity(new Intent(FilterdeActivity.this,College.class));
                                    }
                                });
                                filterPd.show();


                            }

                            // ..............................................................................................................................

                            if (FilterResNameClg.equals(uploadCol.getUploadName()) && !FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())
                                    && !FilterResLocationClg.equals(uploadCol.getUploadLocation()) && !FilterResUnderClg.equals(uploadCol.getUploadUnder())
                                    && !FilterResTypeClg.equals(uploadCol.getUploadType())){

                                ProgressDialog filterPd = new ProgressDialog(FilterdeActivity.this);
                                filterPd.setTitle("No Match Found...");
                                filterPd.setMessage("Entered input are not matching");
                                filterPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                filterPd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.cancel();
                                        finish();
                                        startActivity(new Intent(FilterdeActivity.this,College.class));
                                    }
                                });
                                filterPd.show();


                            }
                            if (!FilterResNameClg.equals(uploadCol.getUploadName()) && !FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())
                                    && !FilterResLocationClg.equals(uploadCol.getUploadLocation()) && !FilterResUnderClg.equals(uploadCol.getUploadUnder())
                                    && FilterResTypeClg.equals(uploadCol.getUploadType())){

                                ProgressDialog filterPd = new ProgressDialog(FilterdeActivity.this);
                                filterPd.setTitle("No Match Found...");
                                filterPd.setMessage("Entered input are not matching");
                                filterPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                filterPd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.cancel();
                                        finish();
                                        startActivity(new Intent(FilterdeActivity.this,College.class));
                                    }
                                });
                                filterPd.show();


                            }
                            if (!FilterResNameClg.equals(uploadCol.getUploadName()) && !FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())
                                    && !FilterResLocationClg.equals(uploadCol.getUploadLocation()) && FilterResUnderClg.equals(uploadCol.getUploadUnder())
                                    && !FilterResTypeClg.equals(uploadCol.getUploadType())){

                                ProgressDialog filterPd = new ProgressDialog(FilterdeActivity.this);
                                filterPd.setTitle("No Match Found...");
                                filterPd.setMessage("Entered input are not matching");
                                filterPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                filterPd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.cancel();
                                        finish();
                                        startActivity(new Intent(FilterdeActivity.this,College.class));
                                    }
                                });
                                filterPd.show();


                            }
                            if (!FilterResNameClg.equals(uploadCol.getUploadName()) && !FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())
                                    && FilterResLocationClg.equals(uploadCol.getUploadLocation()) && !FilterResUnderClg.equals(uploadCol.getUploadUnder())
                                    && !FilterResTypeClg.equals(uploadCol.getUploadType())){

                                ProgressDialog filterPd = new ProgressDialog(FilterdeActivity.this);
                                filterPd.setTitle("No Match Found...");
                                filterPd.setMessage("Entered input are not matching");
                                filterPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                filterPd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.cancel();
                                        finish();
                                        startActivity(new Intent(FilterdeActivity.this,College.class));
                                    }
                                });
                                filterPd.show();


                            }
                            if (!FilterResNameClg.equals(uploadCol.getUploadName()) && FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())
                                    && !FilterResLocationClg.equals(uploadCol.getUploadLocation()) && !FilterResUnderClg.equals(uploadCol.getUploadUnder())
                                    && !FilterResTypeClg.equals(uploadCol.getUploadType())){

                                ProgressDialog filterPd = new ProgressDialog(FilterdeActivity.this);
                                filterPd.setTitle("No Match Found...");
                                filterPd.setMessage("Entered input are not matching");
                                filterPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                filterPd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.cancel();
                                        finish();
                                        startActivity(new Intent(FilterdeActivity.this,College.class));
                                    }
                                });
                                filterPd.show();


                            }



                        }

                    }

                    if (CheckIfAllValuCome.equals("allIsCommingNoUnder")){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                            upload_college_scholarship uploadCol = snapshot.getValue(upload_college_scholarship.class);

                            if (FilterResNameClg.equals(uploadCol.getUploadName()) && FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())
                                    && FilterResLocationClg.equals(uploadCol.getUploadLocation())&& FilterResTypeClg.equals(uploadCol.getUploadType())){

                                upload_college_FilterL.add(uploadCol);
                                collegeListadapter = new College_Adapter_only(Checkfragment,FilterdeActivity.this,upload_college_FilterL);
                                recyclerView.setAdapter(collegeListadapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(FilterdeActivity.this));

                            }

                            if (FilterResNameClg.equals(uploadCol.getUploadName())){
                                if (!FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())
                                       ||!FilterResLocationClg.equals(uploadCol.getUploadLocation()) || !FilterResTypeClg.equals(uploadCol.getUploadType())){

                                    ProgressDialog filterPd = new ProgressDialog(FilterdeActivity.this);
                                    filterPd.setTitle("No Match Found...");
                                    filterPd.setMessage("Entered input are not matching");
                                    filterPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                    filterPd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            //dialog.cancel();
                                            finish();
                                            startActivity(new Intent(FilterdeActivity.this,College.class));
                                        }
                                    });
                                    filterPd.show();

                                }
                            }
                            if (FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())){
                                if (!FilterResNameClg.equals(uploadCol.getUploadName())
                                        || !FilterResLocationClg.equals(uploadCol.getUploadLocation()) || !FilterResTypeClg.equals(uploadCol.getUploadType())){

                                    ProgressDialog filterPd = new ProgressDialog(FilterdeActivity.this);
                                    filterPd.setTitle("No Match Found...");
                                    filterPd.setMessage("Entered input are not matching");
                                    filterPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                    filterPd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            //dialog.cancel();
                                            finish();
                                            startActivity(new Intent(FilterdeActivity.this,College.class));
                                        }
                                    });
                                    filterPd.show();

                                }
                            }
                            if (FilterResLocationClg.equals(uploadCol.getUploadLocation())){
                                if (!FilterResNameClg.equals(uploadCol.getUploadName())
                                        || !FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated()) || !FilterResTypeClg.equals(uploadCol.getUploadType())){

                                    ProgressDialog filterPd = new ProgressDialog(FilterdeActivity.this);
                                    filterPd.setTitle("No Match Found...");
                                    filterPd.setMessage("Entered input are not matching");
                                    filterPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                    filterPd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            //dialog.cancel();
                                            finish();
                                            startActivity(new Intent(FilterdeActivity.this,College.class));
                                        }
                                    });
                                    filterPd.show();

                                }
                            }

                            if (FilterResTypeClg.equals(uploadCol.getUploadType())){
                                if (!FilterResNameClg.equals(uploadCol.getUploadName())
                                        || !FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated()) || !FilterResLocationClg.equals(uploadCol.getUploadLocation())){

                                    ProgressDialog filterPd = new ProgressDialog(FilterdeActivity.this);
                                    filterPd.setTitle("No Match Found...");
                                    filterPd.setMessage("Entered input are not matching");
                                    filterPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                    filterPd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            //dialog.cancel();
                                            finish();
                                            startActivity(new Intent(FilterdeActivity.this,College.class));
                                        }
                                    });
                                    filterPd.show();

                                }
                            }
    if (FilterResNameClg.equals(uploadCol.getUploadName()) && FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())
                                    && FilterResLocationClg.equals(uploadCol.getUploadLocation())&& !FilterResTypeClg.equals(uploadCol.getUploadType())){

                                ProgressDialog filterPd = new ProgressDialog(FilterdeActivity.this);
                                filterPd.setTitle("No Match Found...");
                                filterPd.setMessage("Entered input are not matching");
                                filterPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                filterPd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.cancel();
                                        finish();
                                        startActivity(new Intent(FilterdeActivity.this,College.class));
                                    }
                                });
                                filterPd.show();
                            }
                            if (FilterResNameClg.equals(uploadCol.getUploadName()) && FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())
                                    && !FilterResLocationClg.equals(uploadCol.getUploadLocation())&& FilterResTypeClg.equals(uploadCol.getUploadType())){

                                ProgressDialog filterPd = new ProgressDialog(FilterdeActivity.this);
                                filterPd.setTitle("No Match Found...");
                                filterPd.setMessage("Entered input are not matching");
                                filterPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                filterPd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.cancel();
                                        finish();
                                        startActivity(new Intent(FilterdeActivity.this,College.class));
                                    }
                                });
                                filterPd.show();
                            }
                            if (FilterResNameClg.equals(uploadCol.getUploadName()) && !FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())
                                    && FilterResLocationClg.equals(uploadCol.getUploadLocation())&& FilterResTypeClg.equals(uploadCol.getUploadType())){

                                ProgressDialog filterPd = new ProgressDialog(FilterdeActivity.this);
                                filterPd.setTitle("No Match Found...");
                                filterPd.setMessage("Entered input are not matching");
                                filterPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                filterPd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.cancel();
                                        finish();
                                        startActivity(new Intent(FilterdeActivity.this,College.class));
                                    }
                                });
                                filterPd.show();
                            }
                            if (!FilterResNameClg.equals(uploadCol.getUploadName()) && FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())
                                    && FilterResLocationClg.equals(uploadCol.getUploadLocation())&& FilterResTypeClg.equals(uploadCol.getUploadType())){

                                ProgressDialog filterPd = new ProgressDialog(FilterdeActivity.this);
                                filterPd.setTitle("No Match Found...");
                                filterPd.setMessage("Entered input are not matching");
                                filterPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                filterPd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.cancel();
                                        finish();
                                        startActivity(new Intent(FilterdeActivity.this,College.class));
                                    }
                                });
                                filterPd.show();
                            }

                            //  ..........................................................................................................................

                            if (FilterResNameClg.equals(uploadCol.getUploadName()) && FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())
                                    && !FilterResLocationClg.equals(uploadCol.getUploadLocation())&& !FilterResTypeClg.equals(uploadCol.getUploadType())){

                                ProgressDialog filterPd = new ProgressDialog(FilterdeActivity.this);
                                filterPd.setTitle("No Match Found...");
                                filterPd.setMessage("Entered input are not matching");
                                filterPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                filterPd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.cancel();
                                        finish();
                                        startActivity(new Intent(FilterdeActivity.this,College.class));
                                    }
                                });
                                filterPd.show();
                            }
                            if (FilterResNameClg.equals(uploadCol.getUploadName()) && !FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())
                                    && !FilterResLocationClg.equals(uploadCol.getUploadLocation())&& FilterResTypeClg.equals(uploadCol.getUploadType())){

                                ProgressDialog filterPd = new ProgressDialog(FilterdeActivity.this);
                                filterPd.setTitle("No Match Found...");
                                filterPd.setMessage("Entered input are not matching");
                                filterPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                filterPd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.cancel();
                                        finish();
                                        startActivity(new Intent(FilterdeActivity.this,College.class));
                                    }
                                });
                                filterPd.show();
                            }
                            if (!FilterResNameClg.equals(uploadCol.getUploadName()) && !FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())
                                    && FilterResLocationClg.equals(uploadCol.getUploadLocation())&& FilterResTypeClg.equals(uploadCol.getUploadType())){

                                ProgressDialog filterPd = new ProgressDialog(FilterdeActivity.this);
                                filterPd.setTitle("No Match Found...");
                                filterPd.setMessage("Entered input are not matching");
                                filterPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                filterPd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.cancel();
                                        finish();
                                        startActivity(new Intent(FilterdeActivity.this,College.class));
                                    }
                                });
                                filterPd.show();

                            }
                            if (FilterResNameClg.equals(uploadCol.getUploadName()) && !FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())
                                    && FilterResLocationClg.equals(uploadCol.getUploadLocation())&& !FilterResTypeClg.equals(uploadCol.getUploadType())){

                                ProgressDialog filterPd = new ProgressDialog(FilterdeActivity.this);
                                filterPd.setTitle("No Match Found...");
                                filterPd.setMessage("Entered input are not matching");
                                filterPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                filterPd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.cancel();
                                        finish();
                                        startActivity(new Intent(FilterdeActivity.this,College.class));
                                    }
                                });
                                filterPd.show();
                            }
                            if (!FilterResNameClg.equals(uploadCol.getUploadName()) && FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())
                                    && FilterResLocationClg.equals(uploadCol.getUploadLocation())&& !FilterResTypeClg.equals(uploadCol.getUploadType())){

                                ProgressDialog filterPd = new ProgressDialog(FilterdeActivity.this);
                                filterPd.setTitle("No Match Found...");
                                filterPd.setMessage("Entered input are not matching");
                                filterPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                filterPd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.cancel();
                                        finish();
                                        startActivity(new Intent(FilterdeActivity.this,College.class));
                                    }
                                });
                                filterPd.show();
                            }
                            if (!FilterResNameClg.equals(uploadCol.getUploadName()) && FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())
                                    && !FilterResLocationClg.equals(uploadCol.getUploadLocation())&& FilterResTypeClg.equals(uploadCol.getUploadType())){

                                ProgressDialog filterPd = new ProgressDialog(FilterdeActivity.this);
                                filterPd.setTitle("No Match Found...");
                                filterPd.setMessage("Entered input are not matching");
                                filterPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                filterPd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.cancel();
                                        finish();
                                        startActivity(new Intent(FilterdeActivity.this,College.class));
                                    }
                                });
                                filterPd.show();
                            }

                            //------......................................................................................................................................

                            if (FilterResNameClg.equals(uploadCol.getUploadName()) && !FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())
                                    && !FilterResLocationClg.equals(uploadCol.getUploadLocation())&& !FilterResTypeClg.equals(uploadCol.getUploadType())){

                                ProgressDialog filterPd = new ProgressDialog(FilterdeActivity.this);
                                filterPd.setTitle("No Match Found...");
                                filterPd.setMessage("Entered input are not matching");
                                filterPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                filterPd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.cancel();
                                        finish();
                                        startActivity(new Intent(FilterdeActivity.this,College.class));
                                    }
                                });
                                filterPd.show();
                            }
                            if (!FilterResNameClg.equals(uploadCol.getUploadName()) && FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())
                                    && !FilterResLocationClg.equals(uploadCol.getUploadLocation())&& !FilterResTypeClg.equals(uploadCol.getUploadType())){

                                ProgressDialog filterPd = new ProgressDialog(FilterdeActivity.this);
                                filterPd.setTitle("No Match Found...");
                                filterPd.setMessage("Entered input are not matching");
                                filterPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                filterPd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.cancel();
                                        finish();
                                        startActivity(new Intent(FilterdeActivity.this,College.class));
                                    }
                                });
                                filterPd.show();
                            }
                            if (!FilterResNameClg.equals(uploadCol.getUploadName()) && !FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())
                                    && FilterResLocationClg.equals(uploadCol.getUploadLocation())&& !FilterResTypeClg.equals(uploadCol.getUploadType())){

                                ProgressDialog filterPd = new ProgressDialog(FilterdeActivity.this);
                                filterPd.setTitle("No Match Found...");
                                filterPd.setMessage("Entered input are not matching");
                                filterPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                filterPd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.cancel();
                                        finish();
                                        startActivity(new Intent(FilterdeActivity.this,College.class));
                                    }
                                });
                                filterPd.show();
                            }
                            if (!FilterResNameClg.equals(uploadCol.getUploadName()) && !FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())
                                    && !FilterResLocationClg.equals(uploadCol.getUploadLocation())&& FilterResTypeClg.equals(uploadCol.getUploadType())){

                                ProgressDialog filterPd = new ProgressDialog(FilterdeActivity.this);
                                filterPd.setTitle("No Match Found...");
                                filterPd.setMessage("Entered input are not matching");
                                filterPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                filterPd.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dialog.cancel();
                                        finish();
                                        startActivity(new Intent(FilterdeActivity.this,College.class));
                                    }
                                });
                                filterPd.show();
                            }


                        }

                    }

                    if (CheckIfAllValuCome.equals("underIsComming")){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                            upload_college_scholarship uploadCol = snapshot.getValue(upload_college_scholarship.class);

                            if (FilterResUnderClg.equals(uploadCol.getUploadUnder())){

                                upload_college_FilterL.add(uploadCol);
                                collegeListadapter = new College_Adapter_only(Checkfragment,FilterdeActivity.this,upload_college_FilterL);
                                recyclerView.setAdapter(collegeListadapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(FilterdeActivity.this));

                            }

                        }

                    }

                    if (CheckIfAllValuCome.equals("justTypeandUnderIsComming")){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                            upload_college_scholarship uploadCol = snapshot.getValue(upload_college_scholarship.class);

                            if (FilterResTypeClg.equals(uploadCol.getUploadType()) && FilterResUnderClg.equals(uploadCol.getUploadUnder())){

                                upload_college_FilterL.add(uploadCol);
                                collegeListadapter = new College_Adapter_only(Checkfragment,FilterdeActivity.this,upload_college_FilterL);
                                recyclerView.setAdapter(collegeListadapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(FilterdeActivity.this));

                            }

                        }

                    }

                    if (CheckIfAllValuCome.equals("SearchByLocationAndUnder")){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                            upload_college_scholarship uploadCol = snapshot.getValue(upload_college_scholarship.class);

                            if (FilterResLocationClg.equals(uploadCol.getUploadLocation()) && FilterResUnderClg.equals(uploadCol.getUploadUnder())){

                                upload_college_FilterL.add(uploadCol);
                                collegeListadapter = new College_Adapter_only(Checkfragment,FilterdeActivity.this,upload_college_FilterL);
                                recyclerView.setAdapter(collegeListadapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(FilterdeActivity.this));

                            }

                        }

                    }

                    if (CheckIfAllValuCome.equals("SearchBynameAndAffiliation")){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                            upload_college_scholarship uploadCol = snapshot.getValue(upload_college_scholarship.class);

                            if (FilterResNameClg.equals(uploadCol.getUploadName()) && FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())){

                                upload_college_FilterL.add(uploadCol);
                                collegeListadapter = new College_Adapter_only(Checkfragment,FilterdeActivity.this,upload_college_FilterL);
                                recyclerView.setAdapter(collegeListadapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(FilterdeActivity.this));

                            }

                        }

                    }

                    if (CheckIfAllValuCome.equals("SearchByAffiliationAndLocation")){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                            upload_college_scholarship uploadCol = snapshot.getValue(upload_college_scholarship.class);

                            if (FilterResLocationClg.equals(uploadCol.getUploadLocation()) && FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())){

                                upload_college_FilterL.add(uploadCol);
                                collegeListadapter = new College_Adapter_only(Checkfragment,FilterdeActivity.this,upload_college_FilterL);
                                recyclerView.setAdapter(collegeListadapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(FilterdeActivity.this));

                            }

                        }

                    }

                    if (CheckIfAllValuCome.equals("SearchByLocationAndType")){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                            upload_college_scholarship uploadCol = snapshot.getValue(upload_college_scholarship.class);

                            if (FilterResLocationClg.equals(uploadCol.getUploadLocation()) && FilterResTypeClg.equals(uploadCol.getUploadType())){

                                upload_college_FilterL.add(uploadCol);
                                collegeListadapter = new College_Adapter_only(Checkfragment,FilterdeActivity.this,upload_college_FilterL);
                                recyclerView.setAdapter(collegeListadapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(FilterdeActivity.this));

                            }

                        }

                    }

                    if (CheckIfAllValuCome.equals("SearchByAffiliationAndType")){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                            upload_college_scholarship uploadCol = snapshot.getValue(upload_college_scholarship.class);

                            if (FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated()) && FilterResTypeClg.equals(uploadCol.getUploadType())){

                                upload_college_FilterL.add(uploadCol);
                                collegeListadapter = new College_Adapter_only(Checkfragment,FilterdeActivity.this,upload_college_FilterL);
                                recyclerView.setAdapter(collegeListadapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(FilterdeActivity.this));

                            }

                        }

                    }

                    if (CheckIfAllValuCome.equals("SearchByNameAndType")){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                            upload_college_scholarship uploadCol = snapshot.getValue(upload_college_scholarship.class);

                            if (FilterResNameClg.equals(uploadCol.getUploadName()) && FilterResTypeClg.equals(uploadCol.getUploadType())){

                                upload_college_FilterL.add(uploadCol);
                                collegeListadapter = new College_Adapter_only(Checkfragment,FilterdeActivity.this,upload_college_FilterL);
                                recyclerView.setAdapter(collegeListadapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(FilterdeActivity.this));

                            }

                        }

                    }

                    if (CheckIfAllValuCome.equals("SearchByAffiliationAndUnder")){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                            upload_college_scholarship uploadCol = snapshot.getValue(upload_college_scholarship.class);

                            if (FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated()) && FilterResUnderClg.equals(uploadCol.getUploadUnder())){

                                upload_college_FilterL.add(uploadCol);
                                collegeListadapter = new College_Adapter_only(Checkfragment,FilterdeActivity.this,upload_college_FilterL);
                                recyclerView.setAdapter(collegeListadapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(FilterdeActivity.this));

                            }

                        }

                    }

                    if (CheckIfAllValuCome.equals("SearchByNameAndUnder")){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                            upload_college_scholarship uploadCol = snapshot.getValue(upload_college_scholarship.class);

                            if (FilterResNameClg.equals(uploadCol.getUploadName()) && FilterResUnderClg.equals(uploadCol.getUploadUnder())){

                                upload_college_FilterL.add(uploadCol);
                                collegeListadapter = new College_Adapter_only(Checkfragment,FilterdeActivity.this,upload_college_FilterL);
                                recyclerView.setAdapter(collegeListadapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(FilterdeActivity.this));

                            }

                        }

                    }

                    if (CheckIfAllValuCome.equals("SearchByNameAndLocation")){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                            upload_college_scholarship uploadCol = snapshot.getValue(upload_college_scholarship.class);

                            if (FilterResNameClg.equals(uploadCol.getUploadName()) && FilterResLocationClg.equals(uploadCol.getUploadLocation())){

                                upload_college_FilterL.add(uploadCol);
                                collegeListadapter = new College_Adapter_only(Checkfragment,FilterdeActivity.this,upload_college_FilterL);
                                recyclerView.setAdapter(collegeListadapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(FilterdeActivity.this));

                            }

                        }

                    }

                    if (CheckIfAllValuCome.equals("SearchByLocationAndTypeAndUnder")){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                            upload_college_scholarship uploadCol = snapshot.getValue(upload_college_scholarship.class);

                            if (FilterResLocationClg.equals(uploadCol.getUploadLocation()) && FilterResTypeClg.equals(uploadCol.getUploadType()) && FilterResUnderClg.equals(uploadCol.getUploadUnder())){

                                upload_college_FilterL.add(uploadCol);
                                collegeListadapter = new College_Adapter_only(Checkfragment,FilterdeActivity.this,upload_college_FilterL);
                                recyclerView.setAdapter(collegeListadapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(FilterdeActivity.this));

                            }

                        }

                    }

                    if (CheckIfAllValuCome.equals("SearchByAffiliationAndLocationAndUnder")){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                            upload_college_scholarship uploadCol = snapshot.getValue(upload_college_scholarship.class);

                            if (FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated()) && FilterResLocationClg.equals(uploadCol.getUploadLocation()) && FilterResUnderClg.equals(uploadCol.getUploadUnder())){

                                upload_college_FilterL.add(uploadCol);
                                collegeListadapter = new College_Adapter_only(Checkfragment,FilterdeActivity.this,upload_college_FilterL);
                                recyclerView.setAdapter(collegeListadapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(FilterdeActivity.this));

                            }

                        }

                    }

                    if (CheckIfAllValuCome.equals("SearchByNameAndAffiliationAndLocation")){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                            upload_college_scholarship uploadCol = snapshot.getValue(upload_college_scholarship.class);

                            if (FilterResNameClg.equals(uploadCol.getUploadName()) && FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated()) && FilterResLocationClg.equals(uploadCol.getUploadLocation())){

                                upload_college_FilterL.add(uploadCol);
                                collegeListadapter = new College_Adapter_only(Checkfragment,FilterdeActivity.this,upload_college_FilterL);
                                recyclerView.setAdapter(collegeListadapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(FilterdeActivity.this));

                            }

                        }

                    }

                    if (CheckIfAllValuCome.equals("SearchByNameAndAffiliationAndUnder")){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                            upload_college_scholarship uploadCol = snapshot.getValue(upload_college_scholarship.class);

                            if (FilterResNameClg.equals(uploadCol.getUploadName()) && FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated()) && FilterResUnderClg.equals(uploadCol.getUploadUnder())){

                                upload_college_FilterL.add(uploadCol);
                                collegeListadapter = new College_Adapter_only(Checkfragment,FilterdeActivity.this,upload_college_FilterL);
                                recyclerView.setAdapter(collegeListadapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(FilterdeActivity.this));

                            }

                        }

                    }

                    if (CheckIfAllValuCome.equals("SearchByNameAndAffiliationAndType")){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                            upload_college_scholarship uploadCol = snapshot.getValue(upload_college_scholarship.class);

                            if (FilterResNameClg.equals(uploadCol.getUploadName()) && FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated()) && FilterResTypeClg.equals(uploadCol.getUploadType())){

                                upload_college_FilterL.add(uploadCol);
                                collegeListadapter = new College_Adapter_only(Checkfragment,FilterdeActivity.this,upload_college_FilterL);
                                recyclerView.setAdapter(collegeListadapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(FilterdeActivity.this));

                            }

                        }

                    }

                    if (CheckIfAllValuCome.equals("SearchByAffiliationAndLocationAndType")){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                            upload_college_scholarship uploadCol = snapshot.getValue(upload_college_scholarship.class);

                            if (FilterResLocationClg.equals(uploadCol.getUploadLocation()) && FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated()) && FilterResTypeClg.equals(uploadCol.getUploadType())){

                                upload_college_FilterL.add(uploadCol);
                                collegeListadapter = new College_Adapter_only(Checkfragment,FilterdeActivity.this,upload_college_FilterL);
                                recyclerView.setAdapter(collegeListadapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(FilterdeActivity.this));

                            }

                        }

                    }

                    if (CheckIfAllValuCome.equals("SearchByNameAndTypeAndUnder")){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                            upload_college_scholarship uploadCol = snapshot.getValue(upload_college_scholarship.class);

                            if (FilterResNameClg.equals(uploadCol.getUploadName()) && FilterResTypeClg.equals(uploadCol.getUploadType()) && FilterResUnderClg.equals(uploadCol.getUploadUnder())){

                                upload_college_FilterL.add(uploadCol);
                                collegeListadapter = new College_Adapter_only(Checkfragment,FilterdeActivity.this,upload_college_FilterL);
                                recyclerView.setAdapter(collegeListadapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(FilterdeActivity.this));

                            }

                        }

                    }

                    if (CheckIfAllValuCome.equals("SearchByAffiliationAndTypeAndUnder")){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                            upload_college_scholarship uploadCol = snapshot.getValue(upload_college_scholarship.class);

                            if (FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated()) && FilterResTypeClg.equals(uploadCol.getUploadType()) && FilterResUnderClg.equals(uploadCol.getUploadUnder())){

                                upload_college_FilterL.add(uploadCol);
                                collegeListadapter = new College_Adapter_only(Checkfragment,FilterdeActivity.this,upload_college_FilterL);
                                recyclerView.setAdapter(collegeListadapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(FilterdeActivity.this));

                            }

                        }

                    }

                    if (CheckIfAllValuCome.equals("SearchByNameAndLocationAndUnder")){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                            upload_college_scholarship uploadCol = snapshot.getValue(upload_college_scholarship.class);

                            if (FilterResLocationClg.equals(uploadCol.getUploadLocation()) && FilterResNameClg.equals(uploadCol.getUploadName()) && FilterResUnderClg.equals(uploadCol.getUploadUnder())){

                                upload_college_FilterL.add(uploadCol);
                                collegeListadapter = new College_Adapter_only(Checkfragment,FilterdeActivity.this,upload_college_FilterL);
                                recyclerView.setAdapter(collegeListadapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(FilterdeActivity.this));

                            }

                        }

                    }

                    if (CheckIfAllValuCome.equals("allButNotNameIsComming")){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                            upload_college_scholarship uploadCol = snapshot.getValue(upload_college_scholarship.class);

                            if (FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated()) && FilterResLocationClg.equals(uploadCol.getUploadLocation())&& FilterResTypeClg.equals(uploadCol.getUploadType())
                                    && FilterResUnderClg.equals(uploadCol.getUploadUnder())){

                                upload_college_FilterL.add(uploadCol);
                                collegeListadapter = new College_Adapter_only(Checkfragment,FilterdeActivity.this,upload_college_FilterL);
                                recyclerView.setAdapter(collegeListadapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(FilterdeActivity.this));

                            }

                        }

                    }

                    if (CheckIfAllValuCome.equals("SearchByNALT")){

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                            upload_college_scholarship uploadCol = snapshot.getValue(upload_college_scholarship.class);

                            if (FilterResNameClg.equals(uploadCol.getUploadName()) && FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated()) && FilterResLocationClg.equals(uploadCol.getUploadLocation())&& FilterResTypeClg.equals(uploadCol.getUploadType())){

                                upload_college_FilterL.add(uploadCol);
                                collegeListadapter = new College_Adapter_only(Checkfragment,FilterdeActivity.this,upload_college_FilterL);
                                recyclerView.setAdapter(collegeListadapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(FilterdeActivity.this));

                            }

                        }

                    }

                    if (CheckIfAllValuCome.equals("SearchByNAUT")){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                            upload_college_scholarship uploadCol = snapshot.getValue(upload_college_scholarship.class);

                            if (FilterResNameClg.equals(uploadCol.getUploadName()) && FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated()) && FilterResUnderClg.equals(uploadCol.getUploadUnder()) && FilterResTypeClg.equals(uploadCol.getUploadType())){

                                upload_college_FilterL.add(uploadCol);
                                collegeListadapter = new College_Adapter_only(Checkfragment,FilterdeActivity.this,upload_college_FilterL);
                                recyclerView.setAdapter(collegeListadapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(FilterdeActivity.this));

                            }

                        }

                    }

                    if (CheckIfAllValuCome.equals("SearchByNLUT")){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                            upload_college_scholarship uploadCol = snapshot.getValue(upload_college_scholarship.class);

                            if (FilterResNameClg.equals(uploadCol.getUploadName()) && FilterResLocationClg.equals(uploadCol.getUploadLocation()) && FilterResUnderClg.equals(uploadCol.getUploadUnder()) && FilterResTypeClg.equals(uploadCol.getUploadType())){

                                upload_college_FilterL.add(uploadCol);
                                collegeListadapter = new College_Adapter_only(Checkfragment,FilterdeActivity.this,upload_college_FilterL);
                                recyclerView.setAdapter(collegeListadapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(FilterdeActivity.this));

                            }

                        }

                    }

                    if (CheckIfAllValuCome.equals("SearchByNALU")){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                            upload_college_scholarship uploadCol = snapshot.getValue(upload_college_scholarship.class);

                            if (FilterResNameClg.equals(uploadCol.getUploadName()) && FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated()) && FilterResLocationClg.equals(uploadCol.getUploadLocation())&& FilterResUnderClg.equals(uploadCol.getUploadUnder())){

                                upload_college_FilterL.add(uploadCol);
                                collegeListadapter = new College_Adapter_only(Checkfragment,FilterdeActivity.this,upload_college_FilterL);
                                recyclerView.setAdapter(collegeListadapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(FilterdeActivity.this));

                            }

                        }

                    }

                    if (CheckIfAllValuCome.equals("allButNotNameIsCommingNounder")){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                            upload_college_scholarship uploadCol = snapshot.getValue(upload_college_scholarship.class);

                            if (FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated()) && FilterResLocationClg.equals(uploadCol.getUploadLocation())&& FilterResTypeClg.equals(uploadCol.getUploadType())){

                                upload_college_FilterL.add(uploadCol);
                                collegeListadapter = new College_Adapter_only(Checkfragment,FilterdeActivity.this,upload_college_FilterL);
                                recyclerView.setAdapter(collegeListadapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(FilterdeActivity.this));

                            }

                        }

                    }
                    if (CheckIfAllValuCome.equals("exceptNameAndAffilIsComming")){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                            upload_college_scholarship uploadCol = snapshot.getValue(upload_college_scholarship.class);

                            if (FilterResLocationClg.equals(uploadCol.getUploadLocation())&& FilterResTypeClg.equals(uploadCol.getUploadType())
                                    && FilterResUnderClg.equals(uploadCol.getUploadUnder())){

                                upload_college_FilterL.add(uploadCol);
                                collegeListadapter = new College_Adapter_only(Checkfragment,FilterdeActivity.this,upload_college_FilterL);
                                recyclerView.setAdapter(collegeListadapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(FilterdeActivity.this));

                            }

                        }

                    }


if (CheckIfAllValuCome.equals("exceptNameAndAffilIsCommingnounder")){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                            upload_college_scholarship uploadCol = snapshot.getValue(upload_college_scholarship.class);

                            if (FilterResLocationClg.equals(uploadCol.getUploadLocation()) && FilterResTypeClg.equals(uploadCol.getUploadType())){

                                upload_college_FilterL.add(uploadCol);
                                collegeListadapter = new College_Adapter_only(Checkfragment,FilterdeActivity.this,upload_college_FilterL);
                                recyclerView.setAdapter(collegeListadapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(FilterdeActivity.this));

                            }

                        }

                    }




                    if (CheckIfAllValuCome.equals("justTypeIsCommingnoUnder")){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                            upload_college_scholarship uploadCol = snapshot.getValue(upload_college_scholarship.class);

                            if ( FilterResTypeClg.equals(uploadCol.getUploadType())){

                                upload_college_FilterL.add(uploadCol);
                                collegeListadapter = new College_Adapter_only(Checkfragment,FilterdeActivity.this,upload_college_FilterL);
                                recyclerView.setAdapter(collegeListadapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(FilterdeActivity.this));

                            }

                        }

                    }

                    if (CheckIfAllValuCome.equals("SearchByLocation")){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                            upload_college_scholarship uploadCol = snapshot.getValue(upload_college_scholarship.class);

                            if (FilterResLocationClg.equals(uploadCol.getUploadLocation())){

                                upload_college_FilterL.add(uploadCol);
                                collegeListadapter = new College_Adapter_only(Checkfragment,FilterdeActivity.this,upload_college_FilterL);
                                recyclerView.setAdapter(collegeListadapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(FilterdeActivity.this));

                            }

                        }

                    }

                    if (CheckIfAllValuCome.equals("SearchByAffiliation")){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                            upload_college_scholarship uploadCol = snapshot.getValue(upload_college_scholarship.class);

                            if (FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())){

                                upload_college_FilterL.add(uploadCol);
                                collegeListadapter = new College_Adapter_only(Checkfragment,FilterdeActivity.this,upload_college_FilterL);
                                recyclerView.setAdapter(collegeListadapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(FilterdeActivity.this));

                            }

                        }

                    }

                    if (CheckIfAllValuCome.equals("SearchByName")){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                            upload_college_scholarship uploadCol = snapshot.getValue(upload_college_scholarship.class);

                            if (FilterResNameClg.equals(uploadCol.getUploadName())){

                                upload_college_FilterL.add(uploadCol);
                                collegeListadapter = new College_Adapter_only(Checkfragment,FilterdeActivity.this,upload_college_FilterL);
                                recyclerView.setAdapter(collegeListadapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(FilterdeActivity.this));

                            }

                        }

                    }

                    if (CheckIfAllValuCome.equals("SearchByLocationAndTypeNoUnder")){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                            upload_college_scholarship uploadCol = snapshot.getValue(upload_college_scholarship.class);

                            if (FilterResLocationClg.equals(uploadCol.getUploadLocation()) && FilterResTypeClg.equals(uploadCol.getUploadType())){

                                upload_college_FilterL.add(uploadCol);
                                collegeListadapter = new College_Adapter_only(Checkfragment,FilterdeActivity.this,upload_college_FilterL);
                                recyclerView.setAdapter(collegeListadapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(FilterdeActivity.this));

                            }

                        }

                    }

                    if (CheckIfAllValuCome.equals("SearchByAffilitionAndLocationNoUnder")){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                            upload_college_scholarship uploadCol = snapshot.getValue(upload_college_scholarship.class);

                            if (FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated()) && FilterResLocationClg.equals(uploadCol.getUploadLocation())){

                                upload_college_FilterL.add(uploadCol);
                                collegeListadapter = new College_Adapter_only(Checkfragment,FilterdeActivity.this,upload_college_FilterL);
                                recyclerView.setAdapter(collegeListadapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(FilterdeActivity.this));

                            }

                        }

                    }

                    if (CheckIfAllValuCome.equals("SearchByNameAndAffiliationNoUnder")){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                            upload_college_scholarship uploadCol = snapshot.getValue(upload_college_scholarship.class);

                            if (FilterResNameClg.equals(uploadCol.getUploadName()) && FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())){

                                upload_college_FilterL.add(uploadCol);
                                collegeListadapter = new College_Adapter_only(Checkfragment,FilterdeActivity.this,upload_college_FilterL);
                                recyclerView.setAdapter(collegeListadapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(FilterdeActivity.this));

                            }

                        }

                    }

                    if (CheckIfAllValuCome.equals("SearchByAffiliationaAndTypeNoUnder")){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                            upload_college_scholarship uploadCol = snapshot.getValue(upload_college_scholarship.class);

                            if (FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated()) && FilterResTypeClg.equals(uploadCol.getUploadType())){

                                upload_college_FilterL.add(uploadCol);
                                collegeListadapter = new College_Adapter_only(Checkfragment,FilterdeActivity.this,upload_college_FilterL);
                                recyclerView.setAdapter(collegeListadapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(FilterdeActivity.this));

                            }

                        }

                    }

                    if (CheckIfAllValuCome.equals("SearchByNameAndTypeNoUnder")){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                            upload_college_scholarship uploadCol = snapshot.getValue(upload_college_scholarship.class);

                            if (FilterResNameClg.equals(uploadCol.getUploadName()) && FilterResTypeClg.equals(uploadCol.getUploadType())){

                                upload_college_FilterL.add(uploadCol);
                                collegeListadapter = new College_Adapter_only(Checkfragment,FilterdeActivity.this,upload_college_FilterL);
                                recyclerView.setAdapter(collegeListadapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(FilterdeActivity.this));

                            }

                        }

                    }

                    if (CheckIfAllValuCome.equals("SearchByNameAndTLocationNoUnder")){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                            upload_college_scholarship uploadCol = snapshot.getValue(upload_college_scholarship.class);

                            if (FilterResNameClg.equals(uploadCol.getUploadName()) && FilterResLocationClg.equals(uploadCol.getUploadLocation())){

                                upload_college_FilterL.add(uploadCol);
                                collegeListadapter = new College_Adapter_only(Checkfragment,FilterdeActivity.this,upload_college_FilterL);
                                recyclerView.setAdapter(collegeListadapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(FilterdeActivity.this));

                            }

                        }

                    }

                    if (CheckIfAllValuCome.equals("allButNotNameIsCommingNounder")){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                            upload_college_scholarship uploadCol = snapshot.getValue(upload_college_scholarship.class);

                            if (FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated()) && FilterResLocationClg.equals(uploadCol.getUploadLocation()) && FilterResTypeClg.equals(uploadCol.getUploadType())){

                                upload_college_FilterL.add(uploadCol);
                                collegeListadapter = new College_Adapter_only(Checkfragment,FilterdeActivity.this,upload_college_FilterL);
                                recyclerView.setAdapter(collegeListadapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(FilterdeActivity.this));

                            }

                        }

                    }

                    if (CheckIfAllValuCome.equals("allButNotAffiliationIsCommingNounder")){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                            upload_college_scholarship uploadCol = snapshot.getValue(upload_college_scholarship.class);

                            if (FilterResNameClg.equals(uploadCol.getUploadName()) && FilterResLocationClg.equals(uploadCol.getUploadLocation()) && FilterResTypeClg.equals(uploadCol.getUploadType())){

                                upload_college_FilterL.add(uploadCol);
                                collegeListadapter = new College_Adapter_only(Checkfragment,FilterdeActivity.this,upload_college_FilterL);
                                recyclerView.setAdapter(collegeListadapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(FilterdeActivity.this));

                            }

                        }

                    }

                    if (CheckIfAllValuCome.equals("allButNotTypeIsCommingNounder")){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                            upload_college_scholarship uploadCol = snapshot.getValue(upload_college_scholarship.class);

                            if (FilterResNameClg.equals(uploadCol.getUploadName()) && FilterResLocationClg.equals(uploadCol.getUploadLocation()) && FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())){

                                upload_college_FilterL.add(uploadCol);
                                collegeListadapter = new College_Adapter_only(Checkfragment,FilterdeActivity.this,upload_college_FilterL);
                                recyclerView.setAdapter(collegeListadapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(FilterdeActivity.this));

                            }

                        }

                    }

                    if (CheckIfAllValuCome.equals("allButNotLocationIsCommingNounder")){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                            upload_college_scholarship uploadCol = snapshot.getValue(upload_college_scholarship.class);

                            if (FilterResNameClg.equals(uploadCol.getUploadName()) && FilterResTypeClg.equals(uploadCol.getUploadType()) && FilterResAffiliatedClg.equals(uploadCol.getUploadAffiliated())){

                                upload_college_FilterL.add(uploadCol);
                                collegeListadapter = new College_Adapter_only(Checkfragment,FilterdeActivity.this,upload_college_FilterL);
                                recyclerView.setAdapter(collegeListadapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(FilterdeActivity.this));

                            }

                        }

                    }








                    //.................................................................................

                    if (!FilterResNameClg.isEmpty() && FilterResAffiliatedClg.isEmpty() && !FilterResLocationClg.isEmpty() && !FilterResTypeClg.isEmpty()){

                        CheckIfAllValuCome = "allButNotAffiliationIsCommingNounder";

                    }

                    if (!FilterResNameClg.isEmpty() && !FilterResAffiliatedClg.isEmpty() && !FilterResLocationClg.isEmpty() && FilterResTypeClg.isEmpty()){

                        CheckIfAllValuCome = "allButNotTypeIsCommingNounder";

                    }
                    if (!FilterResNameClg.isEmpty() && !FilterResAffiliatedClg.isEmpty() && FilterResLocationClg.isEmpty() && !FilterResTypeClg.isEmpty()){

                        CheckIfAllValuCome = "allButNotLocationIsCommingNounder";

                    }





                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
