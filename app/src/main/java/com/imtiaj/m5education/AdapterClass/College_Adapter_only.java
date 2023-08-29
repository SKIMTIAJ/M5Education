package com.imtiaj.m5education.AdapterClass;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.imtiaj.m5education.Activities.NewsActivity;
import com.imtiaj.m5education.Activities.PostCollegeList;
import com.imtiaj.m5education.R;
import com.imtiaj.m5education.modelClass.upload_college_scholarship;

import java.util.ArrayList;

public class College_Adapter_only extends RecyclerView.Adapter<College_Adapter_only.CollegeOnlyHolder> {

    private String Checkint;
    private Context CollegeContext;
    private ArrayList<String> KeyList;
    private ArrayList<upload_college_scholarship> upload_college_List;



   /* public College_Adapter_only(String checkint, Context collegeContext, ArrayList<upload_college_scholarship> upload_college_List) {
        Checkint = checkint;
        CollegeContext = collegeContext;
        this.upload_college_List = upload_college_List;
    }*/

    public College_Adapter_only(String checkint, Context collegeContext, ArrayList<String> keyList, ArrayList<upload_college_scholarship> upload_college_List) {
        Checkint = checkint;
        CollegeContext = collegeContext;
        KeyList = keyList;
        this.upload_college_List = upload_college_List;
    }

    public College_Adapter_only(String checkint, Context collegeContext, ArrayList<upload_college_scholarship> upload_college_List) {
        Checkint = checkint;
        CollegeContext = collegeContext;
        this.upload_college_List = upload_college_List;
    }

    @NonNull
    @Override
    public CollegeOnlyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater CollegeRowInf = LayoutInflater.from(CollegeContext);
        View CollegeViewRow = CollegeRowInf.inflate(R.layout.college_row_only,viewGroup,false);
        return new CollegeOnlyHolder(CollegeViewRow);
    }

    @Override
    public void onBindViewHolder(@NonNull CollegeOnlyHolder collegeOnlyHolder, int i) {

        final upload_college_scholarship college_scholarship_arryList = upload_college_List.get(i);
         final String collegeRoot_Keylist = KeyList.get(i);

        collegeOnlyHolder.CollegeName.setText(college_scholarship_arryList.getUploadName());
        collegeOnlyHolder.CollegeLocation.setText(college_scholarship_arryList.getUploadLocation());
        collegeOnlyHolder.CollegeAffiliated.setText(college_scholarship_arryList.getUploadAffiliated());
        collegeOnlyHolder.CollegePhone.setText(college_scholarship_arryList.getUploadPhone());
        collegeOnlyHolder.CollegeSub.setText(college_scholarship_arryList.getUploadSub());
        collegeOnlyHolder.CollegeRating.setText(college_scholarship_arryList.getUploadRating());
        collegeOnlyHolder.CollegeWebsite.setText(college_scholarship_arryList.getUploadWebsite());
        collegeOnlyHolder.CollegeType.setText(college_scholarship_arryList.getUploadType());

        Glide.with(CollegeContext)
                .load(college_scholarship_arryList.getUploadImage())
                /* .centerCrop()
                 .centerInside()*/
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .circleCrop()
                .into(collegeOnlyHolder.CollegeImag);

                collegeOnlyHolder.CollegeListLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Toast.makeText(CollegeContext,"Now ok",Toast.LENGTH_LONG).show();

                        if (Checkint.equals("doUpdate")){

                            PopupMenu editPopup = new PopupMenu(CollegeContext,v);
                            editPopup.getMenuInflater().inflate(R.menu.update_popupmenu, editPopup.getMenu());
                            editPopup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                @Override
                                public boolean onMenuItemClick(MenuItem item) {

                                    Intent datapost_to_CollegePost = new Intent(CollegeContext, PostCollegeList.class);
                                    datapost_to_CollegePost.putExtra("CollegeUpdateKey",collegeRoot_Keylist);
                                    CollegeContext.startActivity(datapost_to_CollegePost);
                                    /*Snackbar.make(v,"You have applyed for Edit",100).show();*/
                                   // Toast.makeText(CollegeContext,collegeRoot_Keylist.toString(),Toast.LENGTH_SHORT).show();

                                    return true;
                                }

                            });
                            editPopup.show();


                        }else {
                            callAlertMassage(college_scholarship_arryList.getUploadName(),college_scholarship_arryList.getUploadPhone()
                                    ,college_scholarship_arryList.getUploadWebsite());

                        }




                    }
                });
    }


    @Override
    public int getItemCount() {
        return upload_college_List.size();
    }

    public void callAlertMassage(String Name, final String Phone, final String Web){


          AlertDialog.Builder checkcollege = new AlertDialog.Builder(CollegeContext);
                        checkcollege.setTitle("Choose Any");
                        checkcollege.setMessage(Name);
                        checkcollege.setPositiveButton("Call", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try{

                                    if(Phone!= null){

                                        Intent callcollege = new Intent();
                                        callcollege.setAction(Intent.ACTION_VIEW);
                                        callcollege.setData(Uri.parse("tel:"+ Phone));
                                        CollegeContext.startActivity(callcollege);
                                    }
                                    else{
                                        Toast.makeText(CollegeContext,"Can't Call",Toast.LENGTH_LONG).show();
                                    }
                                }
                                catch (Exception e){
                                    Toast.makeText(CollegeContext,e.getMessage().toString(),Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                        checkcollege.setNegativeButton("Website", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                try{
                                    if (Web!= null){
                                        Intent webCollege = new Intent(CollegeContext, NewsActivity.class);
                                        webCollege.putExtra("NewsUrl",Web);
                                        CollegeContext.startActivity(webCollege);
                                    }
                                    else {
                                        Toast.makeText(CollegeContext,"Sorry For no Website fond",Toast.LENGTH_LONG).show();
                                    }
                                }
                                catch (Exception e){
                                    Toast.makeText(CollegeContext,e.getMessage().toString(),Toast.LENGTH_LONG).show();
                                }

                            }
                        });

                        checkcollege.show();
    }

    public class CollegeOnlyHolder extends RecyclerView.ViewHolder{

        ImageView CollegeImag;
        TextView CollegeName,CollegeLocation,CollegeAffiliated,CollegePhone,CollegeWebsite,CollegeSub,CollegeRating, CollegeType;
        LinearLayout CollegeListLayout;

        public CollegeOnlyHolder(@NonNull View itemView) {
            super(itemView);
            CollegeImag = (ImageView)itemView.findViewById(R.id.College_row_image);
            CollegeName = (TextView)itemView.findViewById(R.id.College_row_college_name);
            CollegeLocation = (TextView)itemView.findViewById(R.id.College_row_college_location);
            CollegeAffiliated = (TextView)itemView.findViewById(R.id.College_rpw_college_affiliated);
            CollegePhone = (TextView)itemView.findViewById(R.id.College_row_college_PhoneNo);
            CollegeSub = (TextView)itemView.findViewById(R.id.College_row_college_Subject);
            CollegeRating = (TextView)itemView.findViewById(R.id.College_row_college_Rating);
            CollegeWebsite = (TextView)itemView.findViewById(R.id.College_row_college_website);
            CollegeType = (TextView)itemView.findViewById(R.id.College_row_college_type);
            CollegeListLayout = (LinearLayout)itemView.findViewById(R.id.College_row_main_layout);

        }
    }
}
