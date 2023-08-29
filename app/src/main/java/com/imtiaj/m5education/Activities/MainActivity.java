package com.imtiaj.m5education.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
/*import androidx.annotation.NonNull;*/

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.imtiaj.m5education.Fragments.Home;
import com.imtiaj.m5education.Fragments.Insperation;
import com.imtiaj.m5education.Fragments.News;
import com.imtiaj.m5education.Fragments.Way;
import com.imtiaj.m5education.R;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    private int For_Medical_And_Defence,For_Agriculture_And_Defence;
    private int draercheckint_FOR_MENU_BTN = 0;
    //private static int draercheckint_FOR_BACK_BTN = 0;

    private TabLayout tb;
    private ViewPager vp;
    private FirebaseAuth mMainActyvityAuth;
    private DrawerLayout MainDrawer;
    private NavigationView MainNevigation;
    private InterstitialAd mInterstitialAd;
    private String chechAdsClosed = " ";
    ActionBar actionbar;
    // private Drawable homeMenuDrawble;

    // private DatabaseReference MainActivityDatabase;
    //  private ArrayList<SignUpUserData> AdminInforData
    private String AdminLoginMail;
    private FirebaseUser user;
    private int backtoExit = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-8967101046628767/7599262302");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());


        actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        //homeMenuDrawble = getResources().getDrawable(R.drawable.ic_menu_black_24dp);

        /*tb = (TabLayout)findViewById(R.id.myTab);
        vp = (ViewPager)findViewById(R.id.myPage);*/


        //AdminInforData = new ArrayList<SignUpUserData>();
        mMainActyvityAuth = FirebaseAuth.getInstance();
        MainDrawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        MainNevigation = (NavigationView)findViewById(R.id.nav_view);
        View mheader = MainNevigation.getHeaderView(0);
        TextView NavHeaderTxt = (TextView)mheader.findViewById(R.id.NaavHeaderText);

        //MainNevigation.getBackground().setColorFilter(0x80008577, PorterDuff.Mode.MULTIPLY);
        //mheader.getBackground().setColorFilter(0x80000000, PorterDuff.Mode.MULTIPLY);

        /*MainNevigation.getBackground().setAlpha(110);*/


        if (mMainActyvityAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(MainActivity.this,Login_activity.class));
        }

        user = mMainActyvityAuth.getCurrentUser();
        NavHeaderTxt.setText(user.getEmail().toString());
        NavHeaderTxt.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.colorAccent));

        /*if (user.getEmail().equals("skimtiaj2274@gmail.com")){

            AdminLoginMail = user.getEmail();
        }*/

        //MainActivityDatabase = FirebaseDatabase.getInstance().getReference().child("CreerL/UsreData");


        /*vp.setAdapter(new MyOwnPagerAdapter(getSupportFragmentManager()));
        tb.setupWithViewPager(vp);
        tb.getTabAt(0).setIcon(R.drawable.ic_home_black_24dp);
        LinearLayout layout = ((LinearLayout) ((LinearLayout) tb.getChildAt(0)).getChildAt(0));
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) layout.getLayoutParams();
        layoutParams.weight = 0.5f; // e.g. 0.5f
        layout.setLayoutParams(layoutParams);

        tb.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });*/

        loadfragment(new Home());

        BottomNavigationView nevigationView = findViewById(R.id.BottomNevigation);
        nevigationView.setOnNavigationItemSelectedListener(this);



        MainNevigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected( MenuItem menuItem) {

                switch (menuItem.getItemId()){

                    case R.id.OfferDrawer:
                        if (mInterstitialAd.isLoaded()){
                            mInterstitialAd.show();

                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    chechAdsClosed = "OfferDrawer";
                                }
                            },500);
                        }
                        else {
                            startActivity(new Intent(MainActivity.this, OfferActivity.class));
                        }

                        break;

                    case R.id.JobDrawer:
                        if (mInterstitialAd.isLoaded()){
                            mInterstitialAd.show();

                            Handler jobhandaler = new Handler();
                            jobhandaler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    chechAdsClosed = "JobDrawer";
                                }
                            },500);
                        }
                        else{
                            startActivity(new Intent(MainActivity.this,OfferActivity.class));
                        }

                        break;

                    case R.id.SettingDrawer:

                        break;

                    case R.id.LogoutDrawer:

                        final AlertDialog.Builder LogOutAlert = new AlertDialog.Builder(MainActivity.this);
                        LogOutAlert.setTitle("Exit");
                        LogOutAlert.setMessage("Are You Sure? Want To Exit.");
                        LogOutAlert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                mMainActyvityAuth.signOut();
                                finish();
                                startActivity(new Intent(MainActivity.this,Login_activity.class));

                            }
                        });
                        LogOutAlert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                        LogOutAlert.setCancelable(false);
                        LogOutAlert.show();


                        break;


                    case R.id.menuCollege:
                        if (mInterstitialAd.isLoaded()){
                            mInterstitialAd.show();
                            chechAdsClosed = "MenuCollege";
                        }
                        else {
                            startActivity(new Intent(MainActivity.this, College.class));
                        }
                        //Toast.makeText(MainActivity.this,"College Menu have selected",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.menuSchool:

                        if (mInterstitialAd.isLoaded()){
                            mInterstitialAd.show();
                            chechAdsClosed = "menuSchool";
                        }else {
                            startActivity(new Intent(MainActivity.this, School.class));
                        }

                        // Toast.makeText(MainActivity.this,"School Menu have selected",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.menuScholarship:

                        if (mInterstitialAd.isLoaded()){
                            mInterstitialAd.show();
                            chechAdsClosed = "menuScholarship";
                        }else {
                            startActivity(new Intent(MainActivity.this, Scholarship.class));
                        }

                        // Toast.makeText(MainActivity.this,"Scholarship Menu have selected",Toast.LENGTH_SHORT).show();
                        break;


                    case R.id.SchoolLavelExampid:
                        startActivity(new Intent(MainActivity.this, TalentTestAfterIntermediate.class));

                        break;

                    case R.id.HSlavelExampid:

                        if (mInterstitialAd.isLoaded()){
                            mInterstitialAd.show();
                            chechAdsClosed = "HSlavelExampid";
                        }else {
                            startActivity(new Intent(MainActivity.this, AfterIntermediateCompetetive.class));
                        }
                        break;

                    case R.id.SimpleCourseDrawer:
                        startActivity(new Intent(MainActivity.this,More_Course_Activity.class));

                        break;

                }
                // set item as selected to persist highlight
                menuItem.setChecked(true);
                // close drawer when item is tapped
                MainDrawer.closeDrawers();

                // Add code here to update the UI based on the item selected
                // For example, swap UI fragments here


                return true;
            }
        });

        mInterstitialAd.setAdListener(new AdListener(){


            @Override
            public void onAdClosed() {
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
                switch(chechAdsClosed){

                    case "OfferDrawer":
                        chechAdsClosed = " ";
                        startActivity(new Intent(MainActivity.this,OfferActivity.class));
                        break;
                    case "MenuCollege":
                        chechAdsClosed = " ";
                        startActivity(new Intent(MainActivity.this, College.class));
                        break;
                    case "menuSchool":
                        chechAdsClosed = " ";
                        startActivity(new Intent(MainActivity.this, School.class));
                        break;
                    case "menuScholarship":
                        chechAdsClosed = " ";
                        startActivity(new Intent(MainActivity.this, Scholarship.class));
                        break;
                    case "HSlavelExampid":
                        chechAdsClosed = " ";
                        startActivity(new Intent(MainActivity.this,AfterIntermediateCompetetive.class));
                        break;
                    case "GoalMenuEngineering":
                        chechAdsClosed = " ";
                        startActivity(new Intent(MainActivity.this, EngineeringEntrance.class));
                        break;
                    case "GoalMenuMedical":
                        chechAdsClosed = " ";
                        For_Medical_And_Defence = 1;
                        Intent Formedical = new Intent(MainActivity.this, DefenceEntrance.class);
                        Formedical.putExtra("ForDefenceClass",For_Medical_And_Defence);
                        startActivity(Formedical);
                        break;
                    case "GoalMenuLaw":
                        chechAdsClosed = " ";
                        For_Agriculture_And_Defence = 1;
                        Intent ForLow = new Intent(MainActivity.this,LowEntrance.class);
                        ForLow.putExtra("ForLowEntrance",For_Agriculture_And_Defence);
                        startActivity(ForLow);
                        break;
                    case "JobDrawer":
                        chechAdsClosed = " ";
                        startActivity(new Intent(MainActivity.this,OfferActivity.class));
                        break;

                }
            }
        });


    }

    private boolean loadfragment(Fragment fragment) {

        if (fragment!=null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameLay,fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected( MenuItem menuItem) {

        Fragment fragment = null;
        switch (menuItem.getItemId()){
            case R.id.Homi_menuid:
                fragment = new Home();
                break;
            case R.id.Insp_menuid:
                fragment = new Insperation();
                break;
            case R.id.Way_menuid:
                fragment = new Way();
                break;
            case R.id.News_menuid:
                fragment = new News();
                break;
        }

        return loadfragment(fragment);
    }


    class MyOwnPagerAdapter extends FragmentPagerAdapter {

        String data[] = {"","Insperation","Way","News"};

        public MyOwnPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            if (i==0){
                return new Home();
            }
            if (i==1){
                return new Insperation();
            }
            if (i==2){
                return new Way();
            }
            if(i==3){
                return new News();
            }
            return null;

        }


        @Override
        public int getCount() {
            return data.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return data[position];
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);

        MenuItem AdminPageButton = menu.getItem(0);

        if (user.getEmail().equals("skimtiaj2274@gmail.com")){

            AdminPageButton.setVisible(true);
        }
        else{
            AdminPageButton.setVisible(false);
        }


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()){

            case  R.id.addItem:
                startActivity(new Intent(MainActivity.this, Admin_Panel.class));
                break;

            case R.id.GoalMenuEngineering:

                if (mInterstitialAd.isLoaded()){
                    mInterstitialAd.show();
                    chechAdsClosed = "GoalMenuEngineering";
                }else {
                    startActivity(new Intent(MainActivity.this,EngineeringEntrance.class));
                }

                break;

            case R.id.GoalMenuMedical:

                if (mInterstitialAd.isLoaded()){
                    mInterstitialAd.show();
                    chechAdsClosed = "GoalMenuMedical";
                }else {
                    For_Medical_And_Defence = 1;
                    Intent Formedical = new Intent(MainActivity.this,DefenceEntrance.class);
                    Formedical.putExtra("ForDefenceClass",For_Medical_And_Defence);
                    startActivity(Formedical);
                }

                break;

            case R.id.GoalMenuLaw:

                if (mInterstitialAd.isLoaded()){
                    mInterstitialAd.show();
                    chechAdsClosed = "GoalMenuLaw";
                }else {
                    For_Agriculture_And_Defence = 1;
                    Intent ForLow = new Intent(MainActivity.this,LowEntrance.class);
                    ForLow.putExtra("ForLowEntrance",For_Agriculture_And_Defence);
                    startActivity(ForLow);
                }

                break;

            case R.id.GoalMenuAgriculture:

                For_Agriculture_And_Defence = 2;
                Intent ForAgriculture = new Intent(MainActivity.this,LowEntrance.class);
                ForAgriculture.putExtra("ForLowEntrance",For_Agriculture_And_Defence);
                startActivity(ForAgriculture);

                break;

            case R.id.GoalMenuDefence:
                For_Medical_And_Defence = 2;
                Intent ForDefence = new Intent(MainActivity.this,DefenceEntrance.class);
                ForDefence.putExtra("ForDefenceClass",For_Medical_And_Defence);
                startActivity(ForDefence);

                break;

            case R.id.MenuFashionDesigning:

                startActivity(new Intent(MainActivity.this, FashionDesign_Entrance.class));
                break;

            case android.R.id.home:
                drawermethodecall();
                break;

            case R.id.privacysetid:
                Intent privacyintent = new Intent();
                privacyintent.setAction(Intent.ACTION_VIEW);
                privacyintent.addCategory(Intent.CATEGORY_BROWSABLE);
                privacyintent.setData(Uri.parse("https://sites.google.com/view/m5educationprivacyapp/home"));
                startActivity(privacyintent);

                break;

        }
        return super.onOptionsItemSelected(item);
    }


    //@Override
   /* protected void onResume(){
        super.onResume();


    }*/


    private void drawermethodecall(){

        if (MainDrawer.isDrawerOpen(GravityCompat.START)){
            MainDrawer.closeDrawer(GravityCompat.START);
            actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        }
        else {
            MainDrawer.openDrawer(GravityCompat.START);
            actionbar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        }

       /* if (draercheckint_FOR_MENU_BTN == 0){
            MainDrawer.openDrawer(GravityCompat.START);
            draercheckint_FOR_MENU_BTN = 1;
            //homeMenuDrawble.setColorFilter("#fff",);
        }else{
            MainDrawer.closeDrawer(GravityCompat.START);
            draercheckint_FOR_MENU_BTN = 0;
        }*/

    }

    @Override
    public void onBackPressed() {

        if(MainDrawer.isDrawerOpen(GravityCompat.START)){
            MainDrawer.closeDrawer(GravityCompat.START);
            draercheckint_FOR_MENU_BTN = 0;

        }
        else{
            /*AlertDialog.Builder myAlert = new AlertDialog.Builder(MainActivity.this);
            myAlert.setTitle("Exit");
            myAlert.setMessage("Are you really want to exit ?");
            myAlert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                    //MainActivity.super.onBackPressed();
                }
            });
            myAlert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            myAlert.setCancelable(false);
            myAlert.show();*/

            if (backtoExit == 1){
                moveTaskToBack(true);
            }

            Toast.makeText(this,"Tap Again To Exit",Toast.LENGTH_SHORT).show();
            backtoExit = 1;
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    backtoExit = 0;
                }
            },2000);
        }







        //int Count = getFragmentManager().getBackStackEntryCount();

       /* if (Count == 1){

            finish();

        }
        super.onBackPressed();*/

    }


}
