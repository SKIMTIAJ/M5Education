package com.imtiaj.m5education.Activities;

import android.app.ProgressDialog;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.imtiaj.m5education.R;
import com.imtiaj.m5education.modelClass.Upload_Talent_Examp_After_inter;

public class PostSimpleCourseActivity extends AppCompatActivity {

    private EditText UnderGraduateCoureseName,IntegratedCourseName,PostGraduateCourseName,MoreCourseName;
    private LinearLayout UndergraduateLay,IntregratedLay,PostGraduateLay,MoreLay;
    private RadioButton UnderGraduateBtn,IntegratedBtn,PostGraduateBtn,MoreBtn;
    private Button CourseButtonSubmit;
    private String PostExampName;
    private int For_EditText_Appearance = 0 ,Chechk_EditText_isClear = 1;
    private DatabaseReference UndergraduateSimpleDatabaseRef,PostGraduateSimpleDatabaseRef,IntegratedSimpleDatabaseRef,MoreSimpleDatabaseRef;
    private ProgressDialog SimpleprogressDialog;

    private Upload_Talent_Examp_After_inter UploadList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_simple_course);


        UndergraduateLay = (LinearLayout)findViewById(R.id.Post_UnderGraduate_Edit_Course_Lay);
        IntregratedLay  = (LinearLayout)findViewById(R.id.Post_Integrated_Edit_Course_Lay);
        PostGraduateLay = (LinearLayout)findViewById(R.id.Post_PostGraduate_Edit_Course_Lay);
        MoreLay = (LinearLayout)findViewById(R.id.Post_More_Edit_Course_Lay);

        UnderGraduateCoureseName = (EditText) findViewById(R.id.Post_UnderGraduate_Edit_Course);
        IntegratedCourseName = (EditText)findViewById(R.id.Post_Integrated_Edit_Course);
        PostGraduateCourseName = (EditText)findViewById(R.id.Post_PostGraduate_Edit_Course);
        MoreCourseName = (EditText)findViewById(R.id.Post_More_Edit_Course);

        UnderGraduateBtn = (RadioButton) findViewById(R.id.UnderGraduateRadioButton);
        IntegratedBtn = (RadioButton)findViewById(R.id.IntegratedRadioButton);
        PostGraduateBtn = (RadioButton)findViewById(R.id.PostGraduationRadioButton);
        MoreBtn = (RadioButton)findViewById(R.id.MoreRadioButton);



        CourseButtonSubmit = (Button) findViewById(R.id.Post_Simple_Submit_Course);

        UndergraduateSimpleDatabaseRef = FirebaseDatabase.getInstance().getReference("CreerL/UnderGraduateSipmleCoursePage");
        PostGraduateSimpleDatabaseRef = FirebaseDatabase.getInstance().getReference("CreerL/PostGraduateSimpleCoursesPage");
        IntegratedSimpleDatabaseRef = FirebaseDatabase.getInstance().getReference("CreerL/IntegratedSimpleCouragePage");
        MoreSimpleDatabaseRef = FirebaseDatabase.getInstance().getReference("CreerL/MoreSimpleCouragePage");

        SimpleprogressDialog = new ProgressDialog(this);


      /*  UndergraduateLay.setVisibility(View.VISIBLE);
        IntregratedLay.setVisibility(View.GONE);
        PostGraduateLay.setVisibility(View.GONE);
        MoreLay.setVisibility(View.GONE);*/

        Edit_text_apparance(For_EditText_Appearance);

        UnderGraduateBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked==true){
                    For_EditText_Appearance = 1;
                    Edit_text_apparance(For_EditText_Appearance);
                }

            }
        });

        PostGraduateBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked==true){
                    For_EditText_Appearance = 3;
                    Edit_text_apparance(For_EditText_Appearance);
                }

            }
        });

        IntegratedBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked==true){
                    For_EditText_Appearance = 2;
                    Edit_text_apparance(For_EditText_Appearance);
                }

            }
        });
        MoreBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked==true){
                    For_EditText_Appearance = 4;
                    Edit_text_apparance(For_EditText_Appearance);
                }

            }
        });



    }



    private void Edit_text_apparance(int For_EditText_Appearance){

        switch (For_EditText_Appearance){


            /*case 0:

                UndergraduateLay.setVisibility(View.VISIBLE);
                IntregratedLay.setVisibility(View.GONE);
                PostGraduateLay.setVisibility(View.GONE);
                MoreLay.setVisibility(View.GONE);

                break;*/

            case 1:

                UndergraduateLay.setVisibility(View.VISIBLE);
                IntregratedLay.setVisibility(View.GONE);
                PostGraduateLay.setVisibility(View.GONE);
                MoreLay.setVisibility(View.GONE);

                break;

            case 2:

                UndergraduateLay.setVisibility(View.GONE);
                IntregratedLay.setVisibility(View.VISIBLE);
                PostGraduateLay.setVisibility(View.GONE);
                MoreLay.setVisibility(View.GONE);

                break;

            case 3:

                UndergraduateLay.setVisibility(View.GONE);
                IntregratedLay.setVisibility(View.GONE);
                PostGraduateLay.setVisibility(View.VISIBLE);
                MoreLay.setVisibility(View.GONE);

                break;

            case 4:

                UndergraduateLay.setVisibility(View.GONE);
                IntregratedLay.setVisibility(View.GONE);
                PostGraduateLay.setVisibility(View.GONE);
                MoreLay.setVisibility(View.VISIBLE);

                break;

            default:

                UndergraduateLay.setVisibility(View.VISIBLE);
                IntregratedLay.setVisibility(View.GONE);
                PostGraduateLay.setVisibility(View.GONE);
                MoreLay.setVisibility(View.GONE);

        }

        UnderGraduateCoureseName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Chechk_EditText_isClear = 1;

            }
        });

        UnderGraduateCoureseName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus){

                    Chechk_EditText_isClear = 1;

                /*IntegratedCourseName.getText().clear();
                PostGraduateCourseName.getText().clear();
                MoreCourseName.getText().clear();*/

                }
            }
        });

        IntegratedCourseName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus ){

                    Chechk_EditText_isClear = 2;

               /* UnderGraduateCoureseName.getText().clear();
                PostGraduateCourseName.getText().clear();
                MoreCourseName.getText().clear();*/

                }
            }
        });


        PostGraduateCourseName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus){

                    Chechk_EditText_isClear =3;

               /* UnderGraduateCoureseName.getText().clear();
                IntegratedCourseName.getText().clear();
                MoreCourseName.getText().clear();*/

                }
            }
        });


        MoreCourseName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus){

                    Chechk_EditText_isClear = 4;

                /*UnderGraduateCoureseName.getText().clear();
                PostGraduateCourseName.getText().clear();
                IntegratedCourseName.getText().clear();*/

                }
            }
        });




        CourseButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(PostSimpleCourseActivity.this,"Submit Button Has Clicked",Toast.LENGTH_SHORT).show();

               /* Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(PostSimpleCourseActivity.this,"Submit Button Has Clicked",Toast.LENGTH_SHORT).show();
                    }
                },5000);*/

                switch ( Chechk_EditText_isClear){

                    case 1:
                        Snackbar.make(CourseButtonSubmit,"Now Undergarduate Is Working",Snackbar.LENGTH_SHORT).show();

                        Posting_UnderGraduate_Data();

                        break;

                    case 2:

                        Posting_Integrated_Data();

                        break;

                    case 3:

                        Posting_PostGraduate_Data();

                        break;

                    case 4:

                        Posting_MoreCourse_Data();

                        break;

                    default:

                        Posting_UnderGraduate_Data();
                        Snackbar.make(CourseButtonSubmit,"Default is Working",Snackbar.LENGTH_SHORT).show();

                }

        /* if (Chechk_EditText_isClear.equals("UnderGraduate")){

             Posting_UnderGraduate_Data();

         }
         else if (Chechk_EditText_isClear.equals("Integrated")){

             Posting_Integrated_Data();
         }
         else if (Chechk_EditText_isClear.equals("PostGraduate")){

             Posting_PostGraduate_Data();
         }
         else if (Chechk_EditText_isClear.equals("MoreCourse")){
             Posting_MoreCourse_Data();

         }
         else {
             Posting_UnderGraduate_Data();
         }*/


            }
        });


    }




    private void Posting_UnderGraduate_Data() {


        SimpleprogressDialog.setMessage("Posting Content...........");
        SimpleprogressDialog.show();

        if (!TextUtils.isEmpty(UnderGraduateCoureseName.getText().toString())) {

            PostExampName = UnderGraduateCoureseName.getText().toString().trim();

            UploadList = new Upload_Talent_Examp_After_inter(PostExampName);

            String UnderGraduateKeyofUpload = UndergraduateSimpleDatabaseRef.push().getKey();

            UndergraduateSimpleDatabaseRef.child(UnderGraduateKeyofUpload).setValue(UploadList);

            SimpleprogressDialog.dismiss();
            Toast.makeText(PostSimpleCourseActivity.this, "upload successful", Toast.LENGTH_SHORT).show();


        }

    }

    private void Posting_Integrated_Data(){

        if (!TextUtils.isEmpty(IntegratedCourseName.getText().toString())){


            PostExampName = IntegratedCourseName.getText().toString().trim();

            UploadList = new Upload_Talent_Examp_After_inter(PostExampName);

            String IntegratedKeyofUpload = IntegratedSimpleDatabaseRef.push().getKey();

            IntegratedSimpleDatabaseRef.child(IntegratedKeyofUpload).setValue(UploadList);

            SimpleprogressDialog.dismiss();
            Toast.makeText(PostSimpleCourseActivity.this, "upload successful", Toast.LENGTH_SHORT).show();

        }


    }

    private void Posting_PostGraduate_Data(){

        if (!TextUtils.isEmpty(PostGraduateCourseName.getText().toString()) && TextUtils.isEmpty(MoreCourseName.getText().toString())){


            PostExampName = PostGraduateCourseName.getText().toString().trim();

            UploadList = new Upload_Talent_Examp_After_inter(PostExampName);

            String PostGraduatedKeyofUpload = PostGraduateSimpleDatabaseRef.push().getKey();

            PostGraduateSimpleDatabaseRef.child(PostGraduatedKeyofUpload).setValue(UploadList);

            SimpleprogressDialog.dismiss();
            Toast.makeText(PostSimpleCourseActivity.this, "upload successful", Toast.LENGTH_SHORT).show();

        }

    }

    private void Posting_MoreCourse_Data(){


        if (!TextUtils.isEmpty(MoreCourseName.getText().toString())){


            PostExampName = MoreCourseName.getText().toString().trim();

            UploadList = new Upload_Talent_Examp_After_inter(PostExampName);

            String MoreKeyofUpload = MoreSimpleDatabaseRef.push().getKey();

            MoreSimpleDatabaseRef.child(MoreKeyofUpload).setValue(UploadList);

            SimpleprogressDialog.dismiss();
            Toast.makeText(PostSimpleCourseActivity.this, "upload successful", Toast.LENGTH_SHORT).show();

        }



    }


}
