package com.imtiaj.m5education.Activities;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.imtiaj.m5education.R;

public class gellery_activity extends AppCompatActivity {


    private static final String TAG = "Gellery";
    private ImageView mainImage;
    private TextView mainTitle, mainDesc, chooseQualification;
    private TextView IntermediateText, MatricText, GraduateText;
    private TextView After10addbtn, After12addbtn,AfterGraduateaddbtn;
    private postWayList postWayList_Obj;
    private Button After10,After12,AfterGraduate;
    private AdView BannerAdView;
    private InterstitialAd gelleryInterads;
    private String Checkads = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gellery_activity);

        gelleryInterads = new InterstitialAd(this);
        gelleryInterads.setAdUnitId("ca-app-pub-8967101046628767/7599262302");
        gelleryInterads.loadAd(new AdRequest.Builder().build());

        //chooseQualification = (TextView) findViewById(R.id.chooseQualification);
        postWayList_Obj = new postWayList();

        IntermediateText = (TextView) findViewById(R.id.aboutIntermediateText);
        MatricText = (TextView) findViewById(R.id.aboutMatricText);
        GraduateText = (TextView) findViewById(R.id.aboutGraduateText);
        BannerAdView = findViewById(R.id.gelleryMainadView);
        AdRequest request = new AdRequest.Builder().build();
        BannerAdView.loadAd(request);

        After10 = (Button)findViewById(R.id.AfterMatricGelleryButtonid);
        After12 = (Button)findViewById(R.id.AfterIntermediateGelleryButtonid);
        AfterGraduate = (Button)findViewById(R.id.AfterGraduationGelleryButtonid);

        After10addbtn = (TextView)findViewById(R.id.AfterMatricGelleryaddbtnid);
        After12addbtn = (TextView)findViewById(R.id.AfterIntermediateGelleryaddbtnid);
        AfterGraduateaddbtn = (TextView)findViewById(R.id.AfterGraduationGelleryaddbtnid);



        //registerForContextMenu(chooseQualification);

        //radioGroup = (RadioGroup)findViewById(R.id.gelleryRedioGroup);
        getIncomingExtra();

        After10addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MatricText.setVisibility(View.VISIBLE);
                IntermediateText.setVisibility(View.GONE);
                GraduateText.setVisibility(View.GONE);
            }
        });

        After12addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MatricText.setVisibility(View.GONE);
                IntermediateText.setVisibility(View.VISIBLE);
                GraduateText.setVisibility(View.GONE);
            }
        });

        AfterGraduateaddbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MatricText.setVisibility(View.GONE);
                IntermediateText.setVisibility(View.GONE);
                GraduateText.setVisibility(View.VISIBLE);
            }
        });


        After10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gelleryInterads.isLoaded()){
                    gelleryInterads.show();
                    Checkads = "After10";
                }else{

                    Intent goAfterMetricpage = new Intent(gellery_activity.this, AfterMetric.class);
                    startActivity(goAfterMetricpage);
                }
            }
        });

        After12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gelleryInterads.isLoaded()){
                    gelleryInterads.show();
                    Checkads = "After12";
                }
                else {

                    Intent goPostMetricpage = new Intent(gellery_activity.this, AfterIntermediate.class);
                    startActivity(goPostMetricpage);
                }
            }
        });

        AfterGraduate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gelleryInterads.isLoaded()){
                    gelleryInterads.show();
                    Checkads = "AfterGraduate";
                }
                else {
                    Intent goAfterGraduationnpage = new Intent(gellery_activity.this, AfterGraduation.class);
                    startActivity(goAfterGraduationnpage);
                }
            }
        });

        /*chooseQualification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(gellery_activity.this,More_Course_Activity.class));
            }
        });*/

        gelleryInterads.setAdListener(new AdListener(){

            @Override
            public void onAdClosed() {
                gelleryInterads.loadAd(new AdRequest.Builder().build());
                switch (Checkads){

                    case "After10":
                        Checkads = " ";
                        Intent goAfterMetricpage = new Intent(gellery_activity.this, AfterMetric.class);
                        startActivity(goAfterMetricpage);
                        break;
                    case "After12":
                        Checkads = " ";
                        Intent goPostMetricpage = new Intent(gellery_activity.this,AfterIntermediate.class);
                        startActivity(goPostMetricpage);
                        break;
                    case "AfterGraduate":
                        Checkads = " ";
                        Intent goAfterGraduationnpage = new Intent(gellery_activity.this,AfterGraduation.class);
                        startActivity(goAfterGraduationnpage);
                        break;
                }
            }
        });


    }


    private void getIncomingExtra() {
        if (getIntent().hasExtra("Image_url") && getIntent().hasExtra("Title_url") && getIntent().hasExtra("Desc_url")) {

            Bundle b1 = getIntent().getExtras();
            String imgefromlist = b1.getString("Image_url");
            String titleFromList = b1.getString("Title_url");
            String descfromList = b1.getString("Desc_url");

            /*for(int i; i<=postWayList_Obj.Title_store.size();i++){
                if(titleFromList == postWayList_Obj.Title_store.get(i)){
                    break;
                }
            }*/
            setIncomingExtra(imgefromlist, titleFromList, descfromList);
        } else {
            Log.d(TAG, "exception is heppening on getincoming()");
        }
    }

    private void setIncomingExtra(String Imagem, String Titlem, String Descm) {

        mainImage = (ImageView) findViewById(R.id.gelleryImage);
        Glide.with(this)
                .load(Imagem)
                .centerCrop()
                .into(mainImage);
        mainTitle = (TextView) findViewById(R.id.gelleryTextTitle);
        mainTitle.setText(Titlem);
        mainDesc = (TextView) findViewById(R.id.gelleryTextDesc);
        mainDesc.setText(Descm);

        IntermediateText.setText(R.string.About_College);
        MatricText.setText(R.string.About_School);
        GraduateText.setText(R.string.About_Scholarship);
    }

}
