package com.imtiaj.m5education.Activities;

import android.app.ProgressDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.imtiaj.m5education.R;
import com.imtiaj.m5education.modelClass.Upload_Talent_Examp_After_inter;

public class Post_Intermediate_talent_List extends AppCompatActivity {

    private EditText PostExampName,PostCondutedName,PostInventedFor,PostNotification,PostMode,PostWeb;
    private Button SubmitBtn;

    private DatabaseReference PostIntermediate_talent_list;
    private ProgressDialog CompExampProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post__intermediate_talent__list);

        PostExampName = (EditText)findViewById(R.id.PostIntermediateExampName);
        PostCondutedName =(EditText)findViewById(R.id.PostIntermediateConductedBy);
        PostInventedFor = (EditText)findViewById(R.id.PostIntermediateInventedFor);
        PostNotification = (EditText)findViewById(R.id.PostIntermediateNotificationMonth);
        PostMode = (EditText)findViewById(R.id.PostIntermediateModeOfSelection);
        PostWeb = (EditText)findViewById(R.id.PostIntermediateWebsite);

        SubmitBtn = (Button)findViewById(R.id.PostIntermediateSubmitButton);

        CompExampProgress = new ProgressDialog(this);

        PostIntermediate_talent_list = FirebaseDatabase.getInstance().getReference("CreerL").child("competetive_Examp_After Intermediate");

        SubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posting_talent_list_data();
            }
        });

    }


    private void posting_talent_list_data(){

        CompExampProgress.setMessage("Postion Your Data....");
        CompExampProgress.show();

      /*  String nameExmap = PostExampName.getText().toString().trim();
        String ConductName = PostCondutedName.getText().toString().trim();
        String InventedName = PostInventedFor.getText().toString().trim();
        String NotificationName = PostNotification.getText().toString().trim();
        String ModeName = PostMode.getText().toString().trim();
        String WebName = PostWeb.getText().toString().trim();*/


        if (!TextUtils.isEmpty(PostExampName.getText().toString() )){


            Upload_Talent_Examp_After_inter UploadList = new Upload_Talent_Examp_After_inter(PostExampName.getText().toString().trim(),
                    PostCondutedName.getText().toString().trim(),PostInventedFor.getText().toString().trim(),PostNotification.getText().toString().trim(),
                    PostMode.getText().toString().trim(),PostWeb.getText().toString().trim());

            String KeyofUpload =  PostIntermediate_talent_list.push().getKey();

            PostIntermediate_talent_list.child(KeyofUpload).setValue(UploadList);

            CompExampProgress.dismiss();
            Toast.makeText(Post_Intermediate_talent_List.this,"upload successful",Toast.LENGTH_SHORT).show();



            /*DatabaseReference NewDatabaseRef = PostIntermediate_talent_list.push();
            NewDatabaseRef.child("Examination_Name").setValue(nameExmap);
            NewDatabaseRef.child("Examination_Conduct").setValue(ConductName);
            NewDatabaseRef.child("Examination_Invent").setValue(InventedName);
            NewDatabaseRef.child("Examination_Notification").setValue(NotificationName);
            NewDatabaseRef.child("Examination_Mode").setValue(ModeName);
            NewDatabaseRef.child("Examination_WebName").setValue(WebName);*/



        }


    }


   /*class UploadListOfCompExamp {


        public String ExampName,ConductenByName,InventFoRName,NotificationName,Modename,WebName;

        public UploadListOfCompExamp(String exampName, String conductenByName, String inventFoRName, String notificationName, String modename, String webName) {

            ExampName = exampName;
            ConductenByName = conductenByName;
            InventFoRName = inventFoRName;
            NotificationName = notificationName;
            Modename = modename;
            WebName = webName;

        }

        public UploadListOfCompExamp() {

        }
    }*/

}
