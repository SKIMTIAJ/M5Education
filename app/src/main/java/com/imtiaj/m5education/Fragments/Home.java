package com.imtiaj.m5education.Fragments;


import android.content.Intent;
import android.os.Bundle;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.imtiaj.m5education.AdapterClass.HomeGridadapterClass;
import com.imtiaj.m5education.AdapterClass.HomeSwiperAdapter;
import com.imtiaj.m5education.Activities.NewsActivity;
import com.imtiaj.m5education.Activities.OcrCaptureActivity;
import com.imtiaj.m5education.R;
import com.imtiaj.m5education.modelClass.uploadSchool_pojo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_DRAGGING;
import static androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment implements View.OnClickListener, BaseSliderView.OnSliderClickListener ,ViewPagerEx.OnPageChangeListener {

    private GridView gridView;
    private RecyclerView homefirstRecyclerView, homesecondRecyclerView;
    private HomeGridAdapterClass homeGridAdapterClass;
    private HomeGridadapterClass homeGridadapterClass;
    private TextView MarqueText;
    private SliderLayout firstslider;
    private DatabaseReference firebasedata;
    private DatabaseReference firebasedata_forSlider;

    private FloatingActionButton fab_plus,fab_link,fab_scann;
    private Animation fabOpen,fabClose,fabClockwisw,fabAntiClockwise;
    boolean isOpen = false;

    private LinearLayout OcrLayout,AutofillLayout,AutoSpeekLayout;
    private BottomSheetDialog bottomSheetDialog;
    private ProgressBar progressBar;
    private ImageView leftArrow;
    private ImageView rightArrow;
    int currentVisibleItem = 0;
    private LinearLayoutManager linearLayoutManager;
    //To check whether user scrolled the recycler view or used arrows to navigate.
    private boolean programaticallyScrolled;


    private ArrayList<uploadSchool_pojo> uploadItemArry;
    private ArrayList<String> uploadItemArryKey;

    /*........... BANNER ARRAYLIST ...............*/

    private ArrayList<uploadSchool_pojo> uploadArry_forBanner;
    private ArrayList<String> KeyArry_forBanner;
    private Timer timer;
    private int CurrentPosition = 0;

    ViewPager viewPager;
   // int images[] = {R.drawable.image_1, R.drawable.image_2, R.drawable.image_3, R.drawable.image_4};
    HomeSwiperAdapter myCustomPagerAdapter;

    private int imagearray[] = {R.drawable.passport3, R.drawable.idpass, R.drawable.techonology2, R.drawable.bsiness2, R.drawable.social2,
                                R.drawable.relegion2};

   /* private int imagearray[] = {R.drawable.anandabazar, R.drawable.ajkerpatrika, R.drawable.prothomalo, R.drawable.businessstandard, R.drawable.timesofindia,
                                R.drawable.indianexpress};*/
    private String homeTextArray[] = {"Pasport","Id","Technology","Business","Social","Religion"};

    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        uploadItemArry = new ArrayList<>();
        uploadItemArryKey = new ArrayList<>();

        uploadArry_forBanner = new ArrayList<>();
        KeyArry_forBanner = new ArrayList<>();

        gridView = (GridView) view.findViewById(R.id.homegirdLayout);
        leftArrow = (ImageView) view.findViewById(R.id.tv_left_arrow);
        rightArrow = (ImageView) view.findViewById(R.id.tv_right_arrow);
        MarqueText = (TextView)view.findViewById(R.id.textMarquee1);
        homefirstRecyclerView = (RecyclerView)view.findViewById(R.id.homeRecyclerView);
        firstslider = (SliderLayout)view.findViewById(R.id.firstslider);
        //progressBar = (ProgressBar) view.findViewById(R.id.progressbar);

        firebasedata = FirebaseDatabase.getInstance().getReference().child("CreerL/HomeItemRecycle");
        firebasedata_forSlider = FirebaseDatabase.getInstance().getReference().child("CreerL/Home1st_Banner");
        //firebaseBanner = FirebaseDatabase.getInstance().getReference().child("CreerL/Home1st_Banner");

        leftArrow.setOnClickListener(this);
        rightArrow.setOnClickListener(this);

       viewPager = (ViewPager)view.findViewById(R.id.HomeViewPager);

        MarqueText.setSelected(true);
        homeGridAdapterClass = new HomeGridAdapterClass();
        gridView.setAdapter(homeGridAdapterClass);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch(position){
                    case 0:
                        view.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v) {
                                Intent pasportInten = new Intent(getContext(), NewsActivity.class);
                                pasportInten.putExtra("NewsUrl","https://portal2.passportindia.gov.in/AppOnlineProject/welcomeLink#");
                                getActivity().startActivity(pasportInten);
                            }
                        });
                    break;
                    case 1:

                    break;
                    case 2:


                    break;
                    case 3:


                    break;
                    case 4:


                    break;
                    case 5:


                    break;
                    case 6:


                    break;
                }
            }
        });

        try {

            firebasedata.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded( DataSnapshot dataSnapshot, String s) {
                    uploadSchool_pojo homepojo = dataSnapshot.getValue(uploadSchool_pojo.class);
                    uploadItemArry.add(homepojo);
                    String uploadKey = dataSnapshot.getKey();
                    uploadItemArryKey.add(uploadKey);
                    homeGridadapterClass = new HomeGridadapterClass(uploadItemArry,getActivity());
                    homefirstRecyclerView.hasFixedSize();
                    linearLayoutManager = new LinearLayoutManager(getContext());
                    linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
                    homefirstRecyclerView.setLayoutManager(linearLayoutManager);
                    homefirstRecyclerView.setAdapter(homeGridadapterClass);
                }

                @Override
                public void onChildChanged( DataSnapshot dataSnapshot, String s) {

                    uploadSchool_pojo homPojo = dataSnapshot.getValue(uploadSchool_pojo.class);
                    String mkey = dataSnapshot.getKey();
                    int index = uploadItemArryKey.indexOf(mkey);
                    uploadItemArry.set(index,homPojo);
                    homeGridadapterClass = new HomeGridadapterClass(uploadItemArry,getActivity());
                    homefirstRecyclerView.hasFixedSize();
                    linearLayoutManager = new LinearLayoutManager(getContext());
                    linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
                    homefirstRecyclerView.setLayoutManager(linearLayoutManager);
                    homefirstRecyclerView.setAdapter(homeGridadapterClass);


                }

                @Override
                public void onChildRemoved( DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved( DataSnapshot dataSnapshot,  String s) {

                }

                @Override
                public void onCancelled( DatabaseError databaseError) {

                }
            });

        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getContext(),e.getMessage().toString(),Toast.LENGTH_LONG).show();
        }

        /*........................................................ FOR BANNER .....................................................................*/


        try{
            firebasedata_forSlider.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    uploadSchool_pojo bannerPojo = dataSnapshot.getValue(uploadSchool_pojo.class);
                    uploadArry_forBanner.add(bannerPojo);
                    String bannerKey = dataSnapshot.getKey();
                    KeyArry_forBanner.add(bannerKey);
                    myCustomPagerAdapter = new HomeSwiperAdapter(uploadArry_forBanner,getActivity());
                    viewPager.setAdapter(myCustomPagerAdapter);
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    uploadSchool_pojo banerPojo = dataSnapshot.getValue(uploadSchool_pojo.class);
                    String mKey = dataSnapshot.getKey();
                    int index = KeyArry_forBanner.indexOf(mKey);
                    uploadArry_forBanner.set(index,banerPojo);
                    myCustomPagerAdapter = new HomeSwiperAdapter(uploadArry_forBanner,getActivity());
                    viewPager.setAdapter(myCustomPagerAdapter);

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
            /*for (int i=0; i<uploadArry_forBanner.size();i++){
                TextSliderView textSliderView = new TextSliderView(getActivity());
                textSliderView.description(uploadArry_forBanner.get(i).getUploadName())
                        .image(uploadArry_forBanner.get(i).getUploadImage())
                        .setScaleType(BaseSliderView.ScaleType.Fit);
                textSliderView.bundle(new Bundle());
                textSliderView.getBundle()
                        .putString("extra",uploadArry_forBanner.get(i).getUploadName());
                firstslider.addSlider(textSliderView);
                textSliderView.setOnSliderClickListener(this);
            }

            firstslider.setPresetTransformer(SliderLayout.Transformer.Accordion);
            firstslider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
            firstslider.setCustomAnimation(new DescriptionAnimation());
            firstslider.setDuration(3000);
            firstslider.addOnPageChangeListener(this);*/

            creatSlideShow();

        }catch (Exception e){

        }




        homefirstRecyclerView.addOnScrollListener(  new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState){
                    case SCROLL_STATE_DRAGGING:

                        programaticallyScrolled = false;
                        break;

                    case SCROLL_STATE_IDLE:
                        if (!programaticallyScrolled){
                            currentVisibleItem = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                            handleWritingViewNavigationArrows(false);
                        }
                        break;
                }
            }
        });

        homesecondRecyclerView = (RecyclerView)view.findViewById(R.id.homeRecyclerView2);


        fab_plus = (FloatingActionButton)view.findViewById(R.id.fab_plusid);
        fab_link = (FloatingActionButton)view.findViewById(R.id.fab_linkid);
        fab_scann = (FloatingActionButton)view.findViewById(R.id.fab_scannid);

        fabOpen = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_close);
        fabClockwisw = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_clockwise);
        fabAntiClockwise = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_anticlockwise);


        creatBottomSheetDialog();

        fab_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isOpen){

                    fab_link.startAnimation(fabClose);
                    fab_scann.startAnimation(fabClose);
                    fab_plus.startAnimation(fabAntiClockwise);
                    fab_link.setClickable(false);
                    fab_scann.setClickable(false);
                    isOpen = false;

                }
                else {

                    fab_link.startAnimation(fabOpen);
                    fab_scann.startAnimation(fabOpen);
                    fab_plus.startAnimation(fabClockwisw);
                    fab_link.setClickable(true);
                    fab_scann.setClickable(true);
                    isOpen = true;
                }
            }
        });

        fab_scann.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.show();
            }
        });


        return view;

    }

    private void creatSlideShow() {

        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if(CurrentPosition == Integer.MAX_VALUE){
                    CurrentPosition = 0;
                }
                viewPager.setCurrentItem(CurrentPosition++,true);
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        },250,2500);

    }


    private void handleWritingViewNavigationArrows(boolean b) {
        if (currentVisibleItem == (homefirstRecyclerView.getAdapter().getItemCount() - 1)){
            rightArrow.setVisibility(View.GONE);
            leftArrow.setVisibility(View.VISIBLE);
        }else if (currentVisibleItem !=0){
            rightArrow.setVisibility(View.VISIBLE);
            leftArrow.setVisibility(View.VISIBLE);
        }else if (currentVisibleItem == 0){
            rightArrow.setVisibility(View.VISIBLE);
            leftArrow.setVisibility(View.GONE);
        }
        if (b) {
            try {
                homefirstRecyclerView.smoothScrollToPosition(currentVisibleItem);
            }catch (Exception e){
                Toast.makeText(getContext(),e.getMessage(), Toast.LENGTH_LONG).show();
            }

        }

    }

    private void creatBottomSheetDialog() {

        if (bottomSheetDialog == null){

            View vsheet = LayoutInflater.from(getActivity()).inflate(R.layout.bottom_sheet_lay,null);
            OcrLayout = vsheet.findViewById(R.id.btomsheetOcr);
            AutofillLayout = vsheet.findViewById(R.id.btomsheetautoformfill);
            AutoSpeekLayout = vsheet.findViewById(R.id.btomsheetImgreader);

            OcrLayout.setOnClickListener(this);
            AutofillLayout.setOnClickListener(this);
            AutoSpeekLayout.setOnClickListener(this);

            bottomSheetDialog = new BottomSheetDialog(getActivity());
            bottomSheetDialog.setContentView(vsheet);

        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btomsheetOcr:
                startActivity(new Intent(getActivity(), OcrCaptureActivity.class));
                //Toast.makeText(getActivity(),"You Clicked Ocr",Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
                break;
            case R.id.btomsheetautoformfill:
                Toast.makeText(getActivity(),"You Clicked FormAutoFill",Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
                break;
            case R.id.btomsheetImgreader:
                Toast.makeText(getActivity(),"You Clicked Image Reader",Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
                break;
            case R.id.tv_left_arrow:
                programaticallyScrolled = true;
                //Decrement current visible item position to navigate back to previous item
                currentVisibleItem--;
                handleWritingViewNavigationArrows(true);
                break;
            case R.id.tv_right_arrow:
                programaticallyScrolled = true;
                //Increment current visible item position to navigate next item
                currentVisibleItem++;
                handleWritingViewNavigationArrows(true);
                break;
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onStop() {
        firstslider.stopAutoCycle();
        super.onStop();
    }

    class HomeGridAdapterClass extends BaseAdapter{


        @Override
        public int getCount() {
            return imagearray.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater)
                    getActivity().getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
            View gridVieww;

            if (convertView == null){


                gridVieww = new View(getActivity());

                // get layout from mobile.xml
                gridVieww = inflater.inflate(R.layout.gridview_forhome, null);

                //LinearLayout grid_main_lay = (LinearLayout)gridVieww.findViewById(R.id.Home_gridView_item);

                // set value into textview
                TextView textView = (TextView)
                        gridVieww.findViewById(R.id.gridTextid);
                textView.setText(homeTextArray[position]);
                // set image based on selected text
                /*CircleImageView imageView = (CircleImageView)
                        gridVieww.findViewById(R.id.profile_imag);*/
                ImageView imageView = (ImageView)
                        gridVieww.findViewById(R.id.profile_imageview);
                imageView.setImageResource(imagearray[position]);

            }
            else {

                gridVieww = (View) convertView;

            }

            /*CircleImageView homeImg = (CircleImageView)convertView.findViewById(R.id.profile_image);
            TextView homeImgText = (TextView)convertView.findViewById(R.id.gridTextid);

            homeImg.setImageResource(imagearray[position]);
            homeImgText.setText(homeTextArray[position]);
*/
            return gridVieww;
        }
    }

}


