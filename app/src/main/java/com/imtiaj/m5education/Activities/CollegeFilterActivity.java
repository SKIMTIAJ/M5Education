package com.imtiaj.m5education.Activities;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.Query;
import com.imtiaj.m5education.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CollegeFilterActivity extends AppCompatActivity {

    private int sentForChildeGone = 2, checkClgType = 0;

    private ArrayList<String> CommingClgname,CommingClgLocation,CommingClgAffiliation, CommingClgType, CommingClgUnder;
    private Spinner Typespinner,ratingSpinner;
    private String CheckedItem;
    private String ClgTypevar,ClgQalityvar;
    private String rating[];
    private Button ApplyBtn;
    private Switch ClgTypeswitch;
    private AutoCompleteTextView CollegeNameEdit,CollegeLocationEdit,CollegeAffiliationEdit;

    private RadioButton CheckGov,CheckPrivate,CheckAll;

    private Query FirebaseQ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_filter);

        Bundle BClg = getIntent().getExtras();
Bundle BClgLocation = getIntent().getExtras();
        Bundle BClgAffiliation = getIntent().getExtras();
        Bundle BClgType = getIntent().getExtras();
        Bundle BClgUnder = getIntent().getExtras();


        CheckGov = (RadioButton)findViewById(R.id.FilterGovCheck);
        CheckPrivate = (RadioButton)findViewById(R.id.FilterPrivateCheck);
        CheckAll = (RadioButton)findViewById(R.id.FilterAllCheck);

        rating = getResources().getStringArray(R.array.RatingClgArrayString);
        ApplyBtn = (Button)findViewById(R.id.ClgSreachApplyBtn);
        ClgTypeswitch = (Switch)findViewById(R.id.typeClgSwitch);

        if (BClg!= null){

            CommingClgname = BClg.getStringArrayList("TakenClgName");
            CommingClgLocation = BClg.getStringArrayList("TakenClgLocation");
            CommingClgAffiliation = BClg.getStringArrayList("TakenClgAffilited");
            CommingClgType = BClg.getStringArrayList("TakenClgType");
            CommingClgUnder = BClg.getStringArrayList("TakenClgUnder");
        }

        Set<String> removeDupLoaction = new HashSet<>(CommingClgLocation);
        CommingClgLocation.clear();
        CommingClgLocation.addAll(removeDupLoaction);

        Set<String> removeDupAffiliation = new HashSet<>(CommingClgAffiliation);
        CommingClgAffiliation.clear();
        CommingClgAffiliation.addAll(removeDupAffiliation);

        Set<String> removeDupType = new HashSet<>(CommingClgType);
        CommingClgType.clear();
        CommingClgType.addAll(removeDupType);

        Set<String> removeDupUnder = new HashSet<>(CommingClgUnder);
        CommingClgUnder.clear();
        CommingClgUnder.addAll(removeDupUnder);

        CollegeNameEdit = findViewById(R.id.search_collegeBy_Name);
        ArrayAdapter<String> ClgNameAdapter = new ArrayAdapter<>(CollegeFilterActivity.this,
                android.R.layout.simple_list_item_1,CommingClgname);
        CollegeNameEdit.setAdapter(ClgNameAdapter);

        CollegeLocationEdit = findViewById(R.id.search_collegeBy_Location);
        ArrayAdapter<String> ClgLocationAdapter = new ArrayAdapter<>(CollegeFilterActivity.this,
                android.R.layout.simple_list_item_1,CommingClgLocation);
        CollegeLocationEdit.setAdapter(ClgLocationAdapter);

        CollegeAffiliationEdit = findViewById(R.id.search_collegeBy_Affiliation);
        ArrayAdapter<String> ClgAffiliationAdapter = new ArrayAdapter<>(CollegeFilterActivity.this,
                android.R.layout.simple_list_item_1,CommingClgAffiliation);
        CollegeAffiliationEdit.setAdapter(ClgAffiliationAdapter);

         Typespinner = (Spinner)findViewById(R.id.ClgTypeSearch_spinner);
        ratingSpinner = (Spinner)findViewById(R.id.ClgQualitySearch_spinner);


            CheckedItem = "AllChecked";
CheckedItem = "";


        CheckGov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckedItem = "Under Government";
            }
        });
        CheckPrivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckedItem = "Under private";
            }
        });

        CheckAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckedItem = "AllChecked";
CheckedItem = "";

            }
        });


        ClgTypeswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Typespinner.setVisibility(View.VISIBLE);
                    checkClgType = 2;


 if (CommingClgType!= null){


  String[] typeArry = CommingClgType.toArray(new String[CommingClgType.size()]);



                    }
                    else {
                        Toast.makeText(CollegeFilterActivity.this,"No Content",Toast.LENGTH_LONG).show();
                    }



                }
                else {
                    Typespinner.setVisibility(View.GONE);
                    checkClgType = 0;
                    // ClgTypevar = "NullRef";
                }
            }
        });



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CollegeFilterActivity.this, android.R.layout.simple_spinner_item,CommingClgType);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Typespinner.setAdapter(adapter);
        Typespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ClgTypevar = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter<String> qualityClgRate = new ArrayAdapter<>(CollegeFilterActivity.this,android.R.layout.simple_spinner_item,rating);
        qualityClgRate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ratingSpinner.setAdapter(qualityClgRate);

        ratingSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ClgQalityvar = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



//        NameClg = CollegeNameEdit.getText().toString();
//        AffiliatedClg = CollegeAffiliationEdit.getText().toString();
//        LocationClg = CollegeLocationEdit.getText().toString();

        ApplyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Typespinner.isShown()){

                    Intent ClgeFilterRes = new Intent(CollegeFilterActivity.this, FilterdeActivity.class);
                    ClgeFilterRes.putExtra("CollegeNameRes",CollegeNameEdit.getText().toString());
                    ClgeFilterRes.putExtra( "CollegeAffiliatedRes",CollegeAffiliationEdit.getText().toString());
                    ClgeFilterRes.putExtra( "CollegeLocationRes",CollegeLocationEdit.getText().toString());
                    ClgeFilterRes.putExtra( "CollegeTypeRes",ClgTypevar);
                    ClgeFilterRes.putExtra( "CollegeUnderRes",CheckedItem);
                    ClgeFilterRes.putExtra( "CollegeQualityRes",ClgQalityvar);
                    ClgeFilterRes.putExtra("ForChildGone",sentForChildeGone);
                    ClgeFilterRes.putExtra("typeCheck",checkClgType);
                    startActivity(ClgeFilterRes);




Log.d("CollegeName",CollegeNameEdit.getText().toString());
                    Log.d("CollegeAffiliated",CollegeAffiliationEdit.getText().toString());
                    Log.d("CollegeLocation",CollegeLocationEdit.getText().toString());
                    Log.d("CollegeType",ClgTypevar);
                    Log.d("CollegeUnder",CheckedItem);
                    Log.d("CollegeQuality",ClgQalityvar);

                }
                else{

                    Intent ClgeFilterRes = new Intent(CollegeFilterActivity.this,FilterdeActivity.class);
                    ClgeFilterRes.putExtra("CollegeNameRes",CollegeNameEdit.getText().toString());
                    ClgeFilterRes.putExtra( "CollegeAffiliatedRes",CollegeAffiliationEdit.getText().toString());
                    ClgeFilterRes.putExtra( "CollegeLocationRes",CollegeLocationEdit.getText().toString());
                    ClgeFilterRes.putExtra( "CollegeUnderRes",CheckedItem);
                    ClgeFilterRes.putExtra( "CollegeQualityRes",ClgQalityvar);
                    ClgeFilterRes.putExtra("ForChildGone",sentForChildeGone);
                    ClgeFilterRes.putExtra("typeCheck",checkClgType);
                    startActivity(ClgeFilterRes);


//                    Log.d("CollegeName",CollegeNameEdit.getText().toString());
//                    Log.d("CollegeAffiliated",CollegeAffiliationEdit.getText().toString());
//                    Log.d("LocationClg",CollegeLocationEdit.getText().toString());
//                    Log.d("CollegeUnder",CheckedItem);
//                    Log.d("CollegeQuality",ClgQalityvar);
                }



            }
        });



    }

public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.FilterGovCheck:
                if (checked){
                    CheckedItem = "Under government";
                }else{
                    CheckedItem = "NullRef";
                }
                // Remove the meat
                break;
            case R.id.FilterPrivateCheck:
                if (checked){
                    CheckedItem = "Under private";
                }
                else{
                    CheckedItem = "NullRef";
                }
                // I'm lactose intolerant
                break;
            case R.id.FilterAllCheck:
                if (checked){
                    CheckedItem = "AllChecked";
                }
                else {
                    CheckedItem = "NullRef";
                }
            // TODO: Veggie sandwich
        }
    }


}
