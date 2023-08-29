package com.imtiaj.m5education.Activities;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.imtiaj.m5education.R;

import java.util.ArrayList;

public class AfterIntermediate extends AppCompatActivity {


    private ArrayList<String> Engineerig ;
    TableLayout engTable;
    TextView EngText;

    private Button MedicalLay, EngineeringLay, DegreeLay, TeachingLay, OthersLay, NADLay;
    private Button EngineeringEntranceBtn,EngineeringTypeListBtn,Medicalentrancebtn;
    private int GELLERY_REQUEST_CODE_FOR_INTERMEDIATE;
    private int NextLayFormedical;
    private int NextLayForDegree,NextLayForDualDegree,NextLayForProfessional;

    private LinearLayout AfterIntermediate_Eng,AfterIntermediate_Med,AfterIntermediate_Teach,AfterIntermediate_Nda
            ,AfterIntermediate_Degr,AfterIntermediate_Other;

    private Button MedicineButton,HealthButton,PeramedicalButton,NDAbuttonst,TeachingButtonst;

    private TextView Degree3yearBtntext,DualDegree4yearBtntext,ProfessionalCourses,Middle_Text,EngineeringExampAnd;

    private InterstitialAd AftertwelvesInterads;
    private String checkads=" ";

    private AdView IntermediateAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_intermediate);

        IntermediateAdView = (AdView)findViewById(R.id.IntermediateadView);
        IntermediateAdView.loadAd(new AdRequest.Builder().build());

        AftertwelvesInterads = new InterstitialAd(this);
        AftertwelvesInterads.setAdUnitId("ca-app-pub-8967101046628767/7599262302");
        AftertwelvesInterads.loadAd(new AdRequest.Builder().build());


        Degree3yearBtntext = (TextView)findViewById(R.id.Degree3yearBtntext);
        DualDegree4yearBtntext = (TextView)findViewById(R.id.DualDegree4yearBtntext);
        ProfessionalCourses = (TextView)findViewById(R.id.ProfessionalCourses);

        MedicineButton = (Button)findViewById(R.id.Medicine_service_button);
        HealthButton = (Button)findViewById(R.id.Health_Service_button);
        PeramedicalButton = (Button)findViewById(R.id.Peramedical_button);
        NDAbuttonst =(Button)findViewById(R.id.AfterIntermediate_NDA_Button);
        TeachingButtonst = (Button)findViewById(R.id.AfterIntermediate_Teaching_Button);

        MedicalLay = (Button) findViewById(R.id.Prematric_Intermediate_MedicalBtn);
        EngineeringLay = (Button)findViewById(R.id.Prematric_Intermediate_EngineeringBtn);
        DegreeLay = (Button)findViewById(R.id.Prematric_Intermediate_Degree_LayoutBtn);
        TeachingLay = (Button)findViewById(R.id.Prematric_Intermediate_TeachingBtn);
        OthersLay = (Button)findViewById(R.id.Prematric_Intermediate_OthersBtn);
        NADLay = (Button)findViewById(R.id.Prematric_Intermediate_NDABtn);

        Middle_Text = (TextView)findViewById(R.id.Prematric_Intermediate_MidleText);
        EngineeringExampAnd = (TextView)findViewById(R.id.ExampList_and_Type_For_EngText_id);

        AfterIntermediate_Eng = (LinearLayout)findViewById(R.id.AfterIntermediate_Engineering_Overview_XML);
        AfterIntermediate_Med = (LinearLayout)findViewById(R.id.AfterIntermediate_Medical_XML);
        AfterIntermediate_Teach = (LinearLayout)findViewById(R.id.AfterIntermediate_Teaching_XML);
        AfterIntermediate_Nda = (LinearLayout)findViewById(R.id.AfterIntermediate_NDA_XML);
        AfterIntermediate_Degr = (LinearLayout)findViewById(R.id.AfterIntermediate_Degree_XML);
        AfterIntermediate_Other = (LinearLayout)findViewById(R.id.AfterIntermediate_Others_XML);

        EngineeringEntranceBtn = (Button)findViewById(R.id.At_IntermediatePage_Engineering_Entrance_List);
        EngineeringTypeListBtn = (Button)findViewById(R.id.At_IntermediatePage_Engineering_Type_List);
        Medicalentrancebtn = (Button)findViewById(R.id.MedicalExampList_AtIntermediate_page_Btnid);

        String ThisPagemiddlePage = getResources().getString(R.string.Inermediate_after_metric_middle_text);
        String EngineeringExampAndTypeText = getResources().getString(R.string.ExampList_and_Type_For_Eng_4thyear_text);
        Middle_Text.setText(ThisPagemiddlePage);
        EngineeringExampAnd.setText(EngineeringExampAndTypeText);




        Medicalentrancebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int For_Medical_And_Defence = 1;
                Intent Formedical = new Intent(AfterIntermediate.this, DefenceEntrance.class);
                Formedical.putExtra("ForDefenceClass",For_Medical_And_Defence);
                startActivity(Formedical);
            }
        });

        EngineeringEntranceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AfterIntermediate.this, EngineeringEntrance.class));
            }
        });

        EngineeringTypeListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MedicalIntent = new Intent(AfterIntermediate.this, EngineerActivity.class);
                MedicalIntent.putExtra("MedicalVal", GELLERY_REQUEST_CODE_FOR_INTERMEDIATE);
                startActivity(MedicalIntent);
            }
        });

        MedicalLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AfterIntermediate_Med.setVisibility(View.VISIBLE);
                AfterIntermediate_Eng.setVisibility(View.GONE);
                AfterIntermediate_Teach.setVisibility(View.GONE);
                AfterIntermediate_Nda.setVisibility(View.GONE);
                AfterIntermediate_Degr.setVisibility(View.GONE);
                AfterIntermediate_Other.setVisibility(View.GONE);

            }
        });

        EngineeringLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AfterIntermediate_Med.setVisibility(View.GONE);
                AfterIntermediate_Eng.setVisibility(View.VISIBLE);
                AfterIntermediate_Teach.setVisibility(View.GONE);
                AfterIntermediate_Nda.setVisibility(View.GONE);
                AfterIntermediate_Degr.setVisibility(View.GONE);
                AfterIntermediate_Other.setVisibility(View.GONE);


            }
        });

        DegreeLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AfterIntermediate_Med.setVisibility(View.GONE);
                AfterIntermediate_Eng.setVisibility(View.GONE);
                AfterIntermediate_Teach.setVisibility(View.GONE);
                AfterIntermediate_Nda.setVisibility(View.GONE);
                AfterIntermediate_Degr.setVisibility(View.VISIBLE);
                AfterIntermediate_Other.setVisibility(View.GONE);


            }
        });

        TeachingLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AfterIntermediate_Med.setVisibility(View.GONE);
                AfterIntermediate_Eng.setVisibility(View.GONE);
                AfterIntermediate_Teach.setVisibility(View.VISIBLE);
                AfterIntermediate_Nda.setVisibility(View.GONE);
                AfterIntermediate_Degr.setVisibility(View.GONE);
                AfterIntermediate_Other.setVisibility(View.GONE);


            }
        });

        OthersLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AfterIntermediate_Med.setVisibility(View.GONE);
                AfterIntermediate_Eng.setVisibility(View.GONE);
                AfterIntermediate_Teach.setVisibility(View.GONE);
                AfterIntermediate_Nda.setVisibility(View.GONE);
                AfterIntermediate_Degr.setVisibility(View.GONE);
                AfterIntermediate_Other.setVisibility(View.VISIBLE);


            }
        });

        NADLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AfterIntermediate_Med.setVisibility(View.GONE);
                AfterIntermediate_Eng.setVisibility(View.GONE);
                AfterIntermediate_Teach.setVisibility(View.GONE);
                AfterIntermediate_Nda.setVisibility(View.VISIBLE);
                AfterIntermediate_Degr.setVisibility(View.GONE);
                AfterIntermediate_Other.setVisibility(View.GONE);


            }
        });

        MedicineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AftertwelvesInterads.isLoaded()){
                    AftertwelvesInterads.show();
                    checkads = "MedicineButton";
                }else{
                    NextLayFormedical = 2;
                    Intent AfterIntGellery = new Intent(AfterIntermediate.this, MadicalActivity.class);
                    AfterIntGellery.putExtra("forMedicine",NextLayFormedical);
                    startActivity(AfterIntGellery);
                }

            }
        });

        HealthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    NextLayFormedical = 3;
                    Intent AfterIntGellery = new Intent(AfterIntermediate.this,MadicalActivity.class);
                    AfterIntGellery.putExtra("forMedicine",NextLayFormedical);
                    startActivity(AfterIntGellery);

            }
        });

        PeramedicalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (AftertwelvesInterads.isLoaded()){
                    AftertwelvesInterads.show();
                    checkads = "PeramedicalButton";
                }
                else {
                    NextLayFormedical = 4;
                    Intent AfterIntGellery = new Intent(AfterIntermediate.this,MadicalActivity.class);
                    AfterIntGellery.putExtra("forMedicine",NextLayFormedical);
                    startActivity(AfterIntGellery);
                }

            }
        });

        TeachingButtonst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu TeachingPopup = new PopupMenu(AfterIntermediate.this,TeachingButtonst);
                TeachingPopup.getMenuInflater().inflate(R.menu.teachin_menu,TeachingPopup.getMenu());

                TeachingPopup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()){

                            case R.id.DEdid:


                                break;

                            case R.id.UDPEDid:


                                break;

                            case R.id.ECCEDid:



                                break;
                        }

                        return true;
                    }
                });

                TeachingPopup.show();
            }
        });

        NDAbuttonst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu NdaPopup = new PopupMenu(AfterIntermediate.this,NDAbuttonst);
                NdaPopup.getMenuInflater().inflate(R.menu.fornad_menu,NdaPopup.getMenu());

                NdaPopup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()){

                            case R.id.Armyid:


                                break;

                            case R.id.Navyid:


                                break;

                            case R.id.Airforceid:


                                break;
                        }
                        return true;
                    }
                });

                NdaPopup.show();
            }
        });


        AfterIntermediate_Med.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (AftertwelvesInterads.isLoaded()){
                    AftertwelvesInterads.show();
                    checkads = "AfterIntermediate_Med";
                }
                else {

                    GELLERY_REQUEST_CODE_FOR_INTERMEDIATE = 3;
                    Intent MedicalIntent = new Intent(AfterIntermediate.this,MadicalActivity.class);
                    MedicalIntent.putExtra("MedicalVal", GELLERY_REQUEST_CODE_FOR_INTERMEDIATE);
                    startActivity(MedicalIntent);
                }
            }
        });

        AfterIntermediate_Eng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (AftertwelvesInterads.isLoaded()){
                    AftertwelvesInterads.show();
                    checkads = "AfterIntermediate_Eng";
                }else {

                    GELLERY_REQUEST_CODE_FOR_INTERMEDIATE = 4;
                    Intent MedicalIntent = new Intent(AfterIntermediate.this,EngineerActivity.class);
                    MedicalIntent.putExtra("MedicalVal", GELLERY_REQUEST_CODE_FOR_INTERMEDIATE);
                    startActivity(MedicalIntent);
                }

            }

        });

        AfterIntermediate_Degr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (AftertwelvesInterads.isLoaded()){
                    AftertwelvesInterads.show();
                    checkads = "AfterIntermediate_Degr";
                }
                else {
                    GELLERY_REQUEST_CODE_FOR_INTERMEDIATE = 5;
                    Intent MedicalIntent = new Intent(AfterIntermediate.this, Degree.class);
                    MedicalIntent.putExtra("MedicalVal", GELLERY_REQUEST_CODE_FOR_INTERMEDIATE);
                    startActivity(MedicalIntent);
                }
            }

        });

        /*AfterIntermediate_Teach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GELLERY_REQUEST_CODE_FOR_INTERMEDIATE = 6;
                Intent MedicalIntent = new Intent(AfterIntermediate.this,Intermediate_gellery.class);
                MedicalIntent.putExtra("MedicalVal", GELLERY_REQUEST_CODE_FOR_INTERMEDIATE);
                startActivity(MedicalIntent);
            }

        });*/

        AfterIntermediate_Other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (AftertwelvesInterads.isLoaded()){
                    AftertwelvesInterads.show();
                    checkads = "AfterIntermediate_Other";
                }
                else {
                    GELLERY_REQUEST_CODE_FOR_INTERMEDIATE = 7;
                    Intent MedicalIntent = new Intent(AfterIntermediate.this, OtherAfterIntermediate.class);
                    MedicalIntent.putExtra("MedicalVal", GELLERY_REQUEST_CODE_FOR_INTERMEDIATE);
                    startActivity(MedicalIntent);
                }

            }

        });

        AfterIntermediate_Nda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GELLERY_REQUEST_CODE_FOR_INTERMEDIATE = 8;
                /*Intent MedicalIntent = new Intent(AfterIntermediate.this,Intermediate_gellery.class);
                MedicalIntent.putExtra("MedicalVal", GELLERY_REQUEST_CODE_FOR_INTERMEDIATE);
                startActivity(MedicalIntent);*/
            }

        });



        /* ******************************************* FOR DEGREE TEXT ITEM BELOW POPUP MENU CODE HASBEEN WRITEEN  ***************************************************** */



        Degree3yearBtntext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(AfterIntermediate.this,"yes this text is clicked properly",Toast.LENGTH_SHORT).show();

                PopupMenu DegreePopupmenu = new PopupMenu(AfterIntermediate.this,Degree3yearBtntext);
                DegreePopupmenu.getMenuInflater().inflate(R.menu.degree_for_thirdyear,DegreePopupmenu.getMenu());

                DegreePopupmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()){

                            case R.id.BAid:
                                if (AftertwelvesInterads.isLoaded()){
                                    AftertwelvesInterads.show();
                                    checkads = "BAid";
                                }else{
                                    NextLayForDegree = 1;
                                    Intent singleDegreeItem = new Intent(AfterIntermediate.this,Degree.class);
                                    singleDegreeItem.putExtra("Degreerespons",NextLayForDegree);
                                    startActivity(singleDegreeItem);
                                }

                                break;

                            case R.id.BScid:
                                NextLayForDegree = 2;
                                Intent singleDegreeItemtwo = new Intent(AfterIntermediate.this,Degree.class);
                                singleDegreeItemtwo.putExtra("Degreerespons",NextLayForDegree);
                                startActivity(singleDegreeItemtwo);


                                break;

                            case R.id.BComid:

                                NextLayForDegree = 3;
                                Intent singleDegreeItemthree= new Intent(AfterIntermediate.this,Degree.class);
                                singleDegreeItemthree.putExtra("Degreerespons",NextLayForDegree);
                                startActivity(singleDegreeItemthree);

                                break;

                            case R.id.OtherDegreeid:

                                if (AftertwelvesInterads.isLoaded()){
                                    AftertwelvesInterads.show();
                                    checkads = "OtherDegreeid";
                                }else {

                                    NextLayForDegree = 4;
                                    Intent singleDegreeItemfour= new Intent(AfterIntermediate.this,Degree.class);
                                    singleDegreeItemfour.putExtra("Degreerespons",NextLayForDegree);
                                    startActivity(singleDegreeItemfour);
                                }

                                break;

                        }
                        return true;
                    }
                });

                DegreePopupmenu.show();
            }
        });


        DualDegree4yearBtntext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NextLayForDualDegree = 5;
                Intent DualDegreeItemfour= new Intent(AfterIntermediate.this,Degree.class);
                DualDegreeItemfour.putExtra("Degreerespons",NextLayForDualDegree);
                startActivity(DualDegreeItemfour);
            }
        });


        ProfessionalCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu ProfessionalPopupmenu = new PopupMenu(AfterIntermediate.this,Degree3yearBtntext);
                ProfessionalPopupmenu.getMenuInflater().inflate(R.menu.dualdegreemenu,ProfessionalPopupmenu.getMenu());

                ProfessionalPopupmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()){

                            case R.id.CAid:

                                if (AftertwelvesInterads.isLoaded()){
                                    AftertwelvesInterads.show();
                                    checkads = "CAid";
                                }else
                                {
                                    NextLayForProfessional = 1;
                                    Intent ProfessionalDegreeItemfourCA= new Intent(AfterIntermediate.this,Degree.class);
                                    ProfessionalDegreeItemfourCA.putExtra("ProfessionalDegree",NextLayForProfessional);
                                    startActivity(ProfessionalDegreeItemfourCA);
                                }

                                break;

                            case R.id.CSid:

                                NextLayForProfessional = 2;
                                Intent ProfessionalDegreeItemfourCS= new Intent(AfterIntermediate.this,Degree.class);
                                ProfessionalDegreeItemfourCS.putExtra("ProfessionalDegree",NextLayForProfessional);
                                startActivity(ProfessionalDegreeItemfourCS);

                                break;

                            case R.id.ICWAid:

                               /* NextLayForProfessional = 3;
                                Intent ProfessionalDegreeItemfourICWA= new Intent(AfterIntermediate.this,Degree.class);
                                ProfessionalDegreeItemfourICWA.putExtra("ProfessionalDegree",NextLayForProfessional);
                                startActivity(ProfessionalDegreeItemfourICWA);*/

                                break;

                        }

                        return true;
                    }
                });

                ProfessionalPopupmenu.show();
            }
        });



        AftertwelvesInterads.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {
                AftertwelvesInterads.loadAd(new AdRequest.Builder().build());

                switch (checkads){

                    case "MedicineButton":
                        checkads = " ";

                        NextLayFormedical = 2;
                        Intent AfterIntGellery = new Intent(AfterIntermediate.this,MadicalActivity.class);
                        AfterIntGellery.putExtra("forMedicine",NextLayFormedical);
                        startActivity(AfterIntGellery);
                        break;
                    case "PeramedicalButton":
                        checkads = " ";

                        NextLayFormedical = 4;
                        Intent AfterIntPeraGellery = new Intent(AfterIntermediate.this,MadicalActivity.class);
                        AfterIntPeraGellery.putExtra("forMedicine",NextLayFormedical);
                        startActivity(AfterIntPeraGellery);
                        break;
                    case "AfterIntermediate_Med":
                        checkads = " ";

                        GELLERY_REQUEST_CODE_FOR_INTERMEDIATE = 3;
                        Intent MedicalIntent = new Intent(AfterIntermediate.this,MadicalActivity.class);
                        MedicalIntent.putExtra("MedicalVal", GELLERY_REQUEST_CODE_FOR_INTERMEDIATE);
                        startActivity(MedicalIntent);
                        break;
                    case "AfterIntermediate_Eng":
                        checkads = " ";

                        GELLERY_REQUEST_CODE_FOR_INTERMEDIATE = 4;
                        Intent MedicalEngIntent = new Intent(AfterIntermediate.this,EngineerActivity.class);
                        MedicalEngIntent.putExtra("MedicalVal", GELLERY_REQUEST_CODE_FOR_INTERMEDIATE);
                        startActivity(MedicalEngIntent);
                        break;
                    case "AfterIntermediate_Degr":
                        checkads = " ";

                        GELLERY_REQUEST_CODE_FOR_INTERMEDIATE = 5;
                        Intent MedicalIntentDeg = new Intent(AfterIntermediate.this,Degree.class);
                        MedicalIntentDeg.putExtra("MedicalVal", GELLERY_REQUEST_CODE_FOR_INTERMEDIATE);
                        startActivity(MedicalIntentDeg);
                        break;
                    case "AfterIntermediate_Other":
                        checkads = " ";

                        GELLERY_REQUEST_CODE_FOR_INTERMEDIATE = 7;
                        Intent MedicalIntentOther = new Intent(AfterIntermediate.this,OtherAfterIntermediate.class);
                        MedicalIntentOther.putExtra("MedicalVal", GELLERY_REQUEST_CODE_FOR_INTERMEDIATE);
                        startActivity(MedicalIntentOther);
                        break;
                    case "BAid":
                        checkads = " ";

                        NextLayForDegree = 1;
                        Intent singleDegreeItem = new Intent(AfterIntermediate.this,Degree.class);
                        singleDegreeItem.putExtra("Degreerespons",NextLayForDegree);
                        startActivity(singleDegreeItem);
                        break;
                    case "OtherDegreeid":
                        checkads = " ";

                        NextLayForDegree = 4;
                        Intent singleDegreeItemfour= new Intent(AfterIntermediate.this,Degree.class);
                        singleDegreeItemfour.putExtra("Degreerespons",NextLayForDegree);
                        startActivity(singleDegreeItemfour);
                        break;
                    case "CAid":
                        checkads = " ";
                        NextLayForProfessional = 1;
                        Intent ProfessionalDegreeItemfourCA= new Intent(AfterIntermediate.this,Degree.class);
                        ProfessionalDegreeItemfourCA.putExtra("ProfessionalDegree",NextLayForProfessional);
                        startActivity(ProfessionalDegreeItemfourCA);
                        break;
                }
            }
        });

    }

   /* public void MiddleText(){

        while (true){
            Middle_Text.setVisibility(View.VISIBLE);
            Middle_Text.setVisibility(View.GONE);
        }
    }*/

}
