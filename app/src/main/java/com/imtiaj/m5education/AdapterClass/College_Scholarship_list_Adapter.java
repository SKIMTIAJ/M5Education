package com.imtiaj.m5education.AdapterClass;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.imtiaj.m5education.R;
import com.imtiaj.m5education.modelClass.uploadSchool_pojo;

import java.util.ArrayList;

public class College_Scholarship_list_Adapter extends RecyclerView.Adapter<College_Scholarship_list_Adapter.CollegeAdapterHoldr> {


    private int Checkint;
    private Context CollegeContext;
    private ArrayList<uploadSchool_pojo> upload_college_List;

    /*public College_Scholarship_list_Adapter(Context collegeContext, ArrayList<upload_college_scholarship> upload_college_List) {
        CollegeContext = collegeContext;
        this.upload_college_List = upload_college_List;
    }*/

    public College_Scholarship_list_Adapter( Context collegeContext, ArrayList<uploadSchool_pojo> upload_college_List) {
        //Checkint = checkint;
        CollegeContext = collegeContext;
        this.upload_college_List = upload_college_List;
    }

    @NonNull
    @Override
    public CollegeAdapterHoldr onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater CollegeListInflater = LayoutInflater.from(CollegeContext);
        View CollegeView = CollegeListInflater.inflate(R.layout.college_or_scholarship_list,viewGroup,false);
        return new CollegeAdapterHoldr(CollegeView);

    }

    @Override
    public void onBindViewHolder(@NonNull CollegeAdapterHoldr collegeAdapterHoldr, int i) {

        uploadSchool_pojo college_scholarship_arryList = upload_college_List.get(i);

        collegeAdapterHoldr.CollegeName.setText(college_scholarship_arryList.getUploadName());
        collegeAdapterHoldr.CollegeLocation.setText(college_scholarship_arryList.getUploadLocation());
        collegeAdapterHoldr.CollegeAffiliated.setText(college_scholarship_arryList.getUploadAffiliated());
        collegeAdapterHoldr.CollegePhone.setText(college_scholarship_arryList.getUploadPhone());
        collegeAdapterHoldr.CollegeWebsite.setText(college_scholarship_arryList.getUploadWebsite());
        collegeAdapterHoldr.CollegeMedium.setText(college_scholarship_arryList.getUploadMedium());
        collegeAdapterHoldr.CollegeClasses.setText(college_scholarship_arryList.getUploadClasses());
        collegeAdapterHoldr.CollegeRating.setText(college_scholarship_arryList.getUploadRating());

        Glide.with(CollegeContext)
                .load(college_scholarship_arryList.getUploadImage())
                /* .centerCrop()
                 .centerInside()*/
                .fitCenter()
                .circleCrop()
                .into(collegeAdapterHoldr.CollegeImag);

       collegeAdapterHoldr.CollegeListLayout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });

    }

    @Override
    public int getItemCount() {
        return upload_college_List.size();
    }

    public class CollegeAdapterHoldr extends RecyclerView.ViewHolder {
        ImageView CollegeImag;
        TextView CollegeName,CollegeLocation,CollegeAffiliated,CollegePhone, CollegeWebsite, CollegeMedium,CollegeClasses,CollegeRating;
        LinearLayout CollegeListLayout;

        public CollegeAdapterHoldr(@NonNull View itemView) {
            super(itemView);
            CollegeImag = (ImageView)itemView.findViewById(R.id.College_or_Scholarship_image);
            CollegeName = (TextView)itemView.findViewById(R.id.College_or_Scholarship_college_name);
            CollegeLocation = (TextView)itemView.findViewById(R.id.College_or_Scholarship_college_location);
            CollegeAffiliated = (TextView)itemView.findViewById(R.id.College_or_Scholarship_college_affiliated);
            CollegePhone = (TextView)itemView.findViewById(R.id.College_or_Scholarship_college_PhoneNo);
            CollegeWebsite = (TextView)itemView.findViewById(R.id.College_or_Scholarship_college_Website);
            CollegeMedium = (TextView)itemView.findViewById(R.id.College_or_Scholarship_college_Medium);
            CollegeClasses = (TextView)itemView.findViewById(R.id.College_or_Scholarship_college_Classes);
            CollegeRating = (TextView)itemView.findViewById(R.id.College_or_Scholarship_college_Rating);
            CollegeListLayout = (LinearLayout)itemView.findViewById(R.id.College_or_Scholarship_main_layout);
        }
    }

}
