package com.imtiaj.m5education.Activities;

import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.imtiaj.m5education.R;

public class AfterMetric extends AppCompatActivity {

    private Button Intermediate,Polytechnic,Peramedical,ShortTermCource,IIIT,ITI,Other;
    private TextView GuideTextFor_studyOrJob;

    private Button ComptExamp,GovtJob;

    private LinearLayout CompetitiveExmListview,JObAfterMatricLinearLay;
    String GuitTextaftermatric;
    private String CompetitiveExampStringArray[];
    private  CmpititiveExampAdapterClass cmpititiveExampAdapterClass;
    private AdView MatricBannerAds;
    private InterstitialAd MatricInterStitialAds;
    private String CheckAds=" ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_metric);

        MatricBannerAds = findViewById(R.id.AfterMatricadView);
        MatricBannerAds.loadAd(new AdRequest.Builder().build());

        MatricInterStitialAds = new InterstitialAd(this);
        MatricInterStitialAds.setAdUnitId("ca-app-pub-8967101046628767/7599262302");
        MatricInterStitialAds.loadAd(new AdRequest.Builder().build());

        GuideTextFor_studyOrJob = (TextView)findViewById(R.id.Prematric_MidleText);

        Intermediate = (Button) findViewById(R.id.Prematric_IntermediateText);
        Polytechnic = (Button) findViewById(R.id.Prematric_PolytechnicText);
        Peramedical = (Button) findViewById(R.id.Prematric_PeramedicalText);
        ShortTermCource = (Button) findViewById(R.id.Prematric_ShortTermCoursesText);
        IIIT = (Button) findViewById(R.id.Prematric_IIIText);
        ITI = (Button) findViewById(R.id.Prematric_ITIText);
        Other = (Button) findViewById(R.id.Prematric_OtherText);
        ComptExamp = (Button) findViewById(R.id.Prematric_Competetive_Examination_Head);
        GovtJob = (Button) findViewById(R.id.Prematric_Job);


        CompetitiveExmListview = (LinearLayout) findViewById(R.id.ExaminationAfterMatricLinearLay);
        JObAfterMatricLinearLay = (LinearLayout)findViewById(R.id.JObAfterMatricLinearLay);

        CompetitiveExampStringArray = getResources().getStringArray(R.array.CompetitiveExamStringArrayAfterMatric);
        GuitTextaftermatric = getResources().getString(R.string.afterMatric_meddleText);

        cmpititiveExampAdapterClass = new  CmpititiveExampAdapterClass();



        /* registerForContextMenu(Intermediate);
         */
        GuideTextFor_studyOrJob.setText(GuitTextaftermatric);


        ComptExamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CompetitiveExmListview.setVisibility(View.VISIBLE);
                JObAfterMatricLinearLay.setVisibility(View.GONE);
            }
        });

        GovtJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CompetitiveExmListview.setVisibility(View.GONE);
                JObAfterMatricLinearLay.setVisibility(View.VISIBLE);
            }
        });


        IIIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AfterMetric.this, IIITActivity.class));
            }
        });

        Peramedical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MatricInterStitialAds.isLoaded())
                {
                    MatricInterStitialAds.show();
                    CheckAds = "Peramedical";
                }
                else{
                    startActivity(new Intent(AfterMetric.this, PeramedicalActivity.class));
                }

            }
        });

        Polytechnic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (MatricInterStitialAds.isLoaded())
                {
                    MatricInterStitialAds.show();
                    CheckAds = "Polytechnic";
                }else {
                    startActivity(new Intent(AfterMetric.this, PolytechnicActivity.class));
                }

            }
        });



        ShortTermCource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MatricInterStitialAds.isLoaded())
                {
                    MatricInterStitialAds.show();
                    CheckAds = "ShortTermCource";
                }
                else{
                    startActivity(new Intent(AfterMetric.this, ShortCourseActivity.class));
                }
            }
        });


        Other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu Otherafterten = new PopupMenu(AfterMetric.this,Other);
                Otherafterten.getMenuInflater().inflate(R.menu.intermediate_sub_menu,Otherafterten.getMenu());

                Otherafterten.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()){

                            case R.id.Beauty_And_Cosmetologyid:


                                break;

                            case R.id.Jewelry_designid:


                                break;

                            case R.id.Fashion_designid:


                                break;

                            case R.id.Photographyid:



                                break;
                        }
                        return true;
                    }
                });
                Otherafterten.show();

            }
        });


        ITI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (MatricInterStitialAds.isLoaded())
                {
                    MatricInterStitialAds.show();
                    CheckAds = "ITI";
                }else {
                    startActivity(new Intent(AfterMetric.this, ITIActivity.class));
                }
            }
        });




        Intermediate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(AfterMetric.this, Intermediate_gellery.class));

                // IntermediatePopup();

            }
        });

        MatricInterStitialAds.setAdListener(new AdListener(){

            @Override
            public void onAdClosed() {
                MatricInterStitialAds.loadAd(new AdRequest.Builder().build());
                switch (CheckAds){

                    case "ITI":
                        CheckAds = " ";
                        startActivity(new Intent(AfterMetric.this,ITIActivity.class));
                        break;
                    case "ShortTermCource":
                        CheckAds = " ";
                        startActivity(new Intent(AfterMetric.this,ShortCourseActivity.class));
                        break;
                    case "Polytechnic":
                        CheckAds = " ";
                        startActivity(new Intent(AfterMetric.this,PolytechnicActivity.class));
                        break;
                    case "Peramedical":
                        CheckAds = " ";
                        startActivity(new Intent(AfterMetric.this,PeramedicalActivity.class));
                        break;
                }
            }
        });



    }



    private void IntermediatePopup(){

        PopupMenu IntermediatePopup = new PopupMenu(AfterMetric.this,Intermediate);
        IntermediatePopup.getMenuInflater().inflate(R.menu.intermediate_menu,IntermediatePopup.getMenu());

        IntermediatePopup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {


                switch (item.getItemId()){

                    case R.id.IntermediateArtsMEC:

                       /* Toast.makeText(AfterMetric.this,"yes MEC is clicked",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.addCategory(Intent.CATEGORY_BROWSABLE);
                        intent.setData(Uri.parse("http://www.youtube.com"));
                        startActivity(intent);*/

                        break;
                    case R.id.IntermediateArtsHEC:
                        /*Toast.makeText(AfterMetric.this,"yes HEC is clicked",Toast.LENGTH_SHORT).show();
                        Intent intentI = new Intent();
                        intentI.setAction(Intent.ACTION_VIEW);
                        intentI.addCategory(Intent.CATEGORY_BROWSABLE);
                        intentI.setData(Uri.parse("http://www.google.com"));
                        startActivity(intentI);*/

                        break;

                    case R.id.IntermediateCommerceCEC:


                        Toast.makeText(AfterMetric.this,"yes CEC is clicked",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.IntermediateScienceMPS:


                        Toast.makeText(AfterMetric.this,"yes MPS is clicked",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.IntermediateScienceBIPS:


                        Toast.makeText(AfterMetric.this,"yes BIPS is clicked",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.IntermediateScienceLibrarySc:


                        Toast.makeText(AfterMetric.this,"yes LibraryScience is clicked",Toast.LENGTH_SHORT).show();
                        break;


                }


                return true;
            }
        });

        IntermediatePopup.show();

    }




  /*  @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        getMenuInflater().inflate(R.menu.intermediate_menu,menu);

       // TextView IntermediateArts = ()
        //registerForContextMenu();
        super.onCreateContextMenu(menu, v, menuInfo);
    }*/

    //@Override
    /*public boolean onOptionsItemSelected(MenuItem item) {
How to click a menu item inside another menu item's menu
        switch (item.getItemId()){
            case R.id.IntermediateArtsMEC:

                Toast.makeText(AfterMetric.this,"yes MEC is clicked",Toast.LENGTH_SHORT).show();
                break;
            case R.id.IntermediateArtsHEC:

                Toast.makeText(AfterMetric.this,"yes HEC is clicked",Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }*/

   /* @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.IntermediateArtsMEC:

                Toast.makeText(AfterMetric.this,"yes MEC is clicked",Toast.LENGTH_SHORT).show();
                break;
            case R.id.IntermediateArtsHEC:


                Toast.makeText(AfterMetric.this,"yes HEC is clicked",Toast.LENGTH_SHORT).show();
                break;

            case R.id.IntermediateCommerceCEC:


                Toast.makeText(AfterMetric.this,"yes CEC is clicked",Toast.LENGTH_SHORT).show();
                break;

            case R.id.IntermediateScienceMPS:


                Toast.makeText(AfterMetric.this,"yes MPS is clicked",Toast.LENGTH_SHORT).show();
                break;
            case R.id.IntermediateScienceBIPS:


                Toast.makeText(AfterMetric.this,"yes BIPS is clicked",Toast.LENGTH_SHORT).show();
                break;
            case R.id.IntermediateScienceLibrarySc:


                Toast.makeText(AfterMetric.this,"yes LibraryScience is clicked",Toast.LENGTH_SHORT).show();
                break;


        }

        return super.onContextItemSelected(item);
    }*/

    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }


    class CmpititiveExampAdapterClass extends BaseAdapter {


        @Override
        public int getCount() {
            return CompetitiveExampStringArray.length;
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
            TextView CompetitiveExmp = (TextView)convertView.findViewById(R.id.mainMedicalText);

            CompetitiveExmp.setText(CompetitiveExampStringArray[position]);

            return convertView;
        }
    }



}
