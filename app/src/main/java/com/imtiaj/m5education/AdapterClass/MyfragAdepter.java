package com.imtiaj.m5education.AdapterClass;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.imtiaj.m5education.Activities.gellery_activity;
import com.imtiaj.m5education.Activities.OfferActivity;
import com.imtiaj.m5education.R;
import com.imtiaj.m5education.modelClass.way;

import java.util.ArrayList;

public class MyfragAdepter extends RecyclerView.Adapter<MyfragAdepter.MyfragViewHolder> {


    private ProgressDialog MyFragProgress;
    private InterstitialAd WayInterstitialad;
    private ArrayList<way> wayListView;
    private Context ct;
    private way wayobj;
    private String CheckAds = " ";

    public MyfragAdepter(Context ctx, ArrayList<way> w) {
        ct = ctx;
        wayListView = w;
    }
    @NonNull
    @Override
    public MyfragAdepter.MyfragViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater Myinfalte = LayoutInflater.from(ct);
        View v = Myinfalte.inflate(R.layout.my_row,viewGroup,false);

        WayInterstitialad = new InterstitialAd(ct);
        WayInterstitialad.setAdUnitId("ca-app-pub-8967101046628767/7599262302");
        AdRequest adRequest = new AdRequest.Builder().build();
        WayInterstitialad.loadAd(adRequest);

        return new MyfragViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyfragViewHolder myfragViewHolder, int i) {

       /* StorageReference ref = FirebaseStorage.getInstance().getReference().child("WayImage/image1.jpg");

        Log.d("MyfragAdapter","into the MyfragAdapter 1st massege");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Log.d("image uri",uri.getPath());
                //Picasso.with(ct).load(uri).into(myfragViewHolder.im);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
        */
       saveWayData();

        final way wList = wayListView.get(i);
        myfragViewHolder.t1.setText(wList.getTitle());
        myfragViewHolder.t2.setText(wList.getDescription());
        Glide.with(ct)
                .load(wList.getImage())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .apply(new RequestOptions().override(1600, 1000))
                .into(myfragViewHolder.im);
        Log.d("MyfragAdapter","now this");


        myfragViewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(ct,"till this method", Toast.LENGTH_SHORT).show();


                switch (wList.getWayId()){

                    case "Study":
                        if (WayInterstitialad.isLoaded()){
                            WayInterstitialad.show();
                            CheckAds = "Study";
                        }else {
                            Intent intentStudy = new Intent(ct, gellery_activity.class);
                            intentStudy.putExtra("Image_url",wList.getImage());
                            intentStudy.putExtra("Title_url",wList.getTitle());
                            intentStudy.putExtra("Desc_url",wList.getDescription());
                            ct.startActivity(intentStudy);
                        }
                        break;

                    case "Sports":

                        if (WayInterstitialad.isLoaded()){
                            WayInterstitialad.show();
                            CheckAds = "Sports";
                        }else {
                            Intent intentSports = new Intent(ct, OfferActivity.class);
                            intentSports.putExtra("Image_url",wList.getImage());
                            intentSports.putExtra("Title_url",wList.getTitle());
                            intentSports.putExtra("Desc_url",wList.getDescription());
                            ct.startActivity(intentSports);;
                        }
                        break;

                    case "Acting":

                        Intent intentActing = new Intent(ct,OfferActivity.class);
                        intentActing.putExtra("Image_url",wList.getImage());
                        intentActing.putExtra("Title_url",wList.getTitle());
                        intentActing.putExtra("Desc_url",wList.getDescription());
                        ct.startActivity(intentActing);
                        break;

                    case "Airt":

                        Intent intent1 = new Intent(ct,OfferActivity.class);
                        intent1.putExtra("Image_url",wList.getImage());
                        intent1.putExtra("Title_url",wList.getTitle());
                        intent1.putExtra("Desc_url",wList.getDescription());
                        ct.startActivity(intent1);
                        break;
                }

                //Log.d("MyfragAdepter",.toString());

            }
        });

        WayInterstitialad.setAdListener(new AdListener(){

            @Override
            public void onAdClosed() {
                WayInterstitialad.loadAd(new AdRequest.Builder().build());
                switch (CheckAds){
                    case "Study":

                        CheckAds = " ";
                        Intent intentStudy = new Intent(ct,gellery_activity.class);
                        intentStudy.putExtra("Image_url",wList.getImage());
                        intentStudy.putExtra("Title_url",wList.getTitle());
                        intentStudy.putExtra("Desc_url",wList.getDescription());
                        ct.startActivity(intentStudy);

                        break;
                    case "Sports":

                        CheckAds = " ";
                        Intent intentSports = new Intent(ct,OfferActivity.class);
                        intentSports.putExtra("Image_url",wList.getImage());
                        intentSports.putExtra("Title_url",wList.getTitle());
                        intentSports.putExtra("Desc_url",wList.getDescription());
                        ct.startActivity(intentSports);

                        break;

                }
            }
        });
    }

    private void saveWayData() {


    }

    @Override
    public int getItemCount() {
        return wayListView.size();
    }

    public class MyfragViewHolder extends RecyclerView.ViewHolder {
        TextView t1,t2;
        ImageView im;
        LinearLayout parentLayout;
        public MyfragViewHolder(@NonNull View itemView) {
            super(itemView);
            t1 = (TextView)itemView.findViewById(R.id.Cardtitle);
            t2 = (TextView)itemView.findViewById(R.id.Carddesc);
            im = (ImageView)itemView.findViewById(R.id.Cardimg);
            parentLayout = (LinearLayout)itemView.findViewById(R.id.myRowParentLayout);
        }
    }

}
