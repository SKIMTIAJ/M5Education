package com.imtiaj.m5education.AdapterClass;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.imtiaj.m5education.Activities.NewsActivity;
import com.imtiaj.m5education.R;
import com.imtiaj.m5education.modelClass.uploadScholarship_pojoClass;

import java.util.ArrayList;

public class Scholarship_adapter extends RecyclerView.Adapter<Scholarship_adapter.ScholarshipAdapterHoldr> {


    Context ScholarshipContext;
    ArrayList<uploadScholarship_pojoClass> upload_scholarship_List;

    public Scholarship_adapter(Context scholarshipContext, ArrayList<uploadScholarship_pojoClass> upload_scholarship_List) {
        ScholarshipContext = scholarshipContext;
        this.upload_scholarship_List = upload_scholarship_List;
    }

    public Scholarship_adapter() {

    }

    @NonNull
    @Override
    public ScholarshipAdapterHoldr onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater ScholarshipListInflater = LayoutInflater.from(ScholarshipContext);
        View ScholarshipView = ScholarshipListInflater.inflate(R.layout.scholarship_row_layout,viewGroup,false);
        return new ScholarshipAdapterHoldr(ScholarshipView);

    }

    @Override
    public void onBindViewHolder(@NonNull ScholarshipAdapterHoldr scholarshipAdapterHoldr, int i) {


        final uploadScholarship_pojoClass scholarship_arryList = upload_scholarship_List.get(i);

        scholarshipAdapterHoldr.ScholarshipName.setText(scholarship_arryList.getUploadName());
        scholarshipAdapterHoldr.ScholarshipOrganization.setText(scholarship_arryList.getOrganization());
        scholarshipAdapterHoldr.ScholarshipEligible.setText(scholarship_arryList.getEligiblity());
        scholarshipAdapterHoldr.ScholarhipWebsite.setText(scholarship_arryList.getWebsite());
        scholarshipAdapterHoldr.ScholarshipAmount.setText(scholarship_arryList.getScholarshipAmount());
        scholarshipAdapterHoldr.ScholarshipMode.setText(scholarship_arryList.getScholarshipMode());

        Glide.with(ScholarshipContext)
                .load(scholarship_arryList.getUploadImage())
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .circleCrop()
                .into(scholarshipAdapterHoldr.ScholarshipImag);


        scholarshipAdapterHoldr.ScholarshipListLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scholarshipIntent = new Intent(ScholarshipContext, NewsActivity.class);
                scholarshipIntent.putExtra("NewsUrl",scholarship_arryList.getWebsite());
                ScholarshipContext.startActivity(scholarshipIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return upload_scholarship_List.size();
    }

    public class ScholarshipAdapterHoldr extends RecyclerView.ViewHolder {


        ImageView ScholarshipImag;
        TextView ScholarshipName,ScholarshipOrganization,ScholarshipEligible,ScholarhipWebsite,ScholarshipAmount, ScholarshipMode;
        LinearLayout ScholarshipListLayout, ScholarshipImageLay;

        public ScholarshipAdapterHoldr(@NonNull View itemView) {
            super(itemView);

            ScholarshipImag = (ImageView)itemView.findViewById(R.id.Scholarship_image);
            ScholarshipName = (TextView)itemView.findViewById(R.id.Scholarship_name);
            ScholarshipOrganization = (TextView)itemView.findViewById(R.id.Scholarship_given_from);
            ScholarshipEligible = (TextView)itemView.findViewById(R.id.Scholarship_Eligible_for);
            ScholarhipWebsite = (TextView)itemView.findViewById(R.id.Scholarship_Website);
            ScholarshipAmount = (TextView)itemView.findViewById(R.id.Scholarship_Amount);
            ScholarshipMode = (TextView)itemView.findViewById(R.id.Scholarship_Mode);
            ScholarshipListLayout = (LinearLayout)itemView.findViewById(R.id.Scholarship_main_layout);
            ScholarshipImageLay = (LinearLayout)itemView.findViewById(R.id.Scholarship_image_layout);

        }
    }

}
