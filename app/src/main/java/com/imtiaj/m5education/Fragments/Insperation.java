package com.imtiaj.m5education.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.LinearLayout;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.imtiaj.m5education.R;
import com.imtiaj.m5education.modelClass.YoutubeUploadPOjo;
import com.imtiaj.m5education.AdapterClass.YoutubeVideoadapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Insperation extends Fragment {


    private RecyclerView FragInsperationRecycle;
    private ArrayList<YoutubeUploadPOjo> YoutubeVideoArrayList;
    private ArrayList<String> YoutubeKey;
    private DatabaseReference YoutubeViderRef;
    private YoutubeVideoadapter youtubeAdapterObj;
    private android.app.ProgressDialog ProgressDialog;
    private FloatingActionButton fab_plus,fab_link,fab_scann;
    private Animation fabOpen,fabClose,fabClockwisw,fabAntiClockwise;
    boolean isOpen = false;

    private LinearLayout OcrLayout,AutofillLayout,AutoSpeekLayout;
    private BottomSheetDialog bottomSheetDialog;


    public Insperation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_insperation, container, false);


        ProgressDialog = new ProgressDialog(getActivity());

        FragInsperationRecycle = (RecyclerView)view.findViewById(R.id.InperstionRecyclerView);
        FragInsperationRecycle.setHasFixedSize(true);
        ProgressDialog.setProgress(100);
        ProgressDialog.show();

        YoutubeVideoArrayList = new ArrayList<YoutubeUploadPOjo>();
        YoutubeKey = new ArrayList<>();


        /*fab_plus = (FloatingActionButton)view.findViewById(R.id.fab_plusid);
        fab_link = (FloatingActionButton)view.findViewById(R.id.fab_linkid);
        fab_scann = (FloatingActionButton)view.findViewById(R.id.fab_scannid);

        fabOpen = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_close);
        fabClockwisw = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_clockwise);
        fabAntiClockwise = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_anticlockwise);*/

        /*creatBottomSheetDialog();

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
        });*/



        YoutubeViderRef = FirebaseDatabase.getInstance().getReference().child("CreerL/YoutubeData");

        YoutubeViderRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                //Log.d("Second Fragment","before Creating Obj of Pojoclass");

                YoutubeUploadPOjo youtubeUploadPOjo = dataSnapshot.getValue(YoutubeUploadPOjo.class);
                //Log.d("Second Fragment","before adding pojo obj to arrayList");
                YoutubeVideoArrayList.add(youtubeUploadPOjo);
                String mkey = dataSnapshot.getKey();
                YoutubeKey.add(mkey);
                //Log.d("Second Fragment","before senting arrayList to The AdapterClass");
                youtubeAdapterObj = new YoutubeVideoadapter(YoutubeVideoArrayList,getActivity());

                //Log.d("Second Fragment","before set Adapter to RecyclerView");
                FragInsperationRecycle.setAdapter(youtubeAdapterObj);
                //Log.d("Second Fragment","before setLayOut Manager to recyclerview");
                FragInsperationRecycle.setLayoutManager(new LinearLayoutManager(getActivity()));
                ProgressDialog.dismiss();


                //FragInsperationRecycle.addOnItemTouchListener(new );



               /* FragInsperationRecycle.addOnItemTouchListener(
                        new RecyclerItemClickListener(
                                getContext(),
                                FragInsperationRecycle,
                                new RecyclerItemClickListener.OnItemClickListener() {
                                    @Override public void onItemClick(View view, int position) {
                                        // view is the clicked view (the one you wanted
                                        // position is its position in the adapter
                                    }
                                    @Override public void onLongItemClick(View view, int position) {
                                    }
                                }
                        )
                );
*/



            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                YoutubeUploadPOjo youtubeUploadPOjo = dataSnapshot.getValue(YoutubeUploadPOjo.class);

                String chengeKey = dataSnapshot.getKey();

                int index = YoutubeKey.indexOf(chengeKey);

                YoutubeVideoArrayList.set(index,youtubeUploadPOjo);
                youtubeAdapterObj = new YoutubeVideoadapter(YoutubeVideoArrayList,getActivity());

                FragInsperationRecycle.setAdapter(youtubeAdapterObj);
                FragInsperationRecycle.setLayoutManager(new LinearLayoutManager(getActivity()));
                ProgressDialog.dismiss();


            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                String RemoveKey = dataSnapshot.getKey();
                int index = YoutubeKey.indexOf(RemoveKey);

                YoutubeVideoArrayList.remove(index);
                YoutubeKey.remove(index);
                youtubeAdapterObj = new YoutubeVideoadapter(YoutubeVideoArrayList,getActivity());

                FragInsperationRecycle.setAdapter(youtubeAdapterObj);
                FragInsperationRecycle.setLayoutManager(new LinearLayoutManager(getActivity()));
                ProgressDialog.dismiss();

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });






        return view;
    }

    /*private void creatBottomSheetDialog(){

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
                Toast.makeText(getActivity(),"You Clicked Ocr",Toast.LENGTH_SHORT).show();
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
        }
    }*/


}
