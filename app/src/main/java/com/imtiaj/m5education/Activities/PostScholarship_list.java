package com.imtiaj.m5education.Activities;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.imtiaj.m5education.R;
import com.imtiaj.m5education.modelClass.uploadScholarship_pojoClass;

public class PostScholarship_list extends AppCompatActivity {

    private ImageView Scholarshipimage;
    private EditText ScholarshipName,ScholarshipOrganization,ScholarshipEligiblity,ScholarshipWebsite,ScholarshipAmount, ScholarshipMode;
    private Button ImageSelect, UploadAllScholarship_Post;
    private Uri ScholarshipImageUri;


    private StorageReference ScholarshipStoreref;
    private DatabaseReference ScholarshipInfodb;
    private ProgressDialog uploadprogress;

    private static final int GELLERY_PIC_REQUEST = 2;
    private StorageTask mUploadTaskk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_scholarship_list);


        Scholarshipimage =(ImageView)findViewById(R.id.Scholarship_list_post_imageView);
        ScholarshipName = (EditText)findViewById(R.id.Scholarship_name_to_upload);
        ScholarshipOrganization = (EditText)findViewById(R.id.Scholarship_Organization_to_upload);
        ScholarshipEligiblity = (EditText)findViewById(R.id.Scholarship_Eligiblity_to_upload);
        ScholarshipWebsite = (EditText)findViewById(R.id.Scholarship_Website_to_upload);
        ScholarshipAmount = (EditText)findViewById(R.id.Scholarship_Amount_to_upload);
        ScholarshipMode = (EditText)findViewById(R.id.Scholarship_Application_Mode_to_upload);

        ImageSelect = (Button)findViewById(R.id.selectImage_for_Scholarship_list);
        UploadAllScholarship_Post = (Button)findViewById(R.id.Upload_all_Scholarship_list);
        uploadprogress = new ProgressDialog(this);


        ScholarshipStoreref = FirebaseStorage.getInstance().getReference("UploadScholarshipImage");
        ScholarshipInfodb = FirebaseDatabase.getInstance().getReference("CreerL").child("Scholarship");

        Log.d("PostScholarship_list","ok befor ImageBtn");
        ImageSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent gelleryPicTake = new Intent(Intent.ACTION_GET_CONTENT);
                gelleryPicTake.setType("image/*");
                startActivityForResult(gelleryPicTake,GELLERY_PIC_REQUEST);
            }
        });

        UploadAllScholarship_Post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostingScholarship();

            }
        });

    }


    private void PostingScholarship(){

        uploadprogress.setMessage("Posting on way list ......");
        uploadprogress.show();

        if(ScholarshipImageUri !=null ){

            final StorageReference ScholarshipRef = ScholarshipStoreref.child(System.currentTimeMillis()+"."+GetFileExtension(ScholarshipImageUri));

            mUploadTaskk = ScholarshipRef.putFile(ScholarshipImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler scholarschiphandaler = new Handler();
                            scholarschiphandaler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    uploadprogress.setProgress(0);
                                }
                            },500);

                            ScholarshipRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    uploadScholarship_pojoClass scholarshipList = new uploadScholarship_pojoClass(uri.toString(),ScholarshipName.getText().toString().trim(),
                                            ScholarshipOrganization.getText().toString().trim(),ScholarshipEligiblity.getText().toString().trim(),
                                            ScholarshipWebsite.getText().toString().trim(),ScholarshipAmount.getText().toString().trim(),ScholarshipMode.getText().toString().trim());

                                    String UploadScholarshipAll =  ScholarshipInfodb.push().getKey();
                                    ScholarshipInfodb.child(UploadScholarshipAll).setValue(scholarshipList);
                                    uploadprogress.cancel();
                                    Toast.makeText(PostScholarship_list.this,"upload successful",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(PostScholarship_list.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            uploadprogress.setProgress((int) progress);
                        }
                    });

        }
        else {

            uploadScholarship_pojoClass scholarshipList = new uploadScholarship_pojoClass(ScholarshipName.getText().toString().trim(),
                    ScholarshipOrganization.getText().toString().trim(),ScholarshipEligiblity.getText().toString().trim(),
                    ScholarshipWebsite.getText().toString().trim(),ScholarshipAmount.getText().toString().trim(),ScholarshipMode.getText().toString().trim());

            String UploadScholarshipAllexceptImg =  ScholarshipInfodb.push().getKey();
            ScholarshipInfodb.child(UploadScholarshipAllexceptImg).setValue(scholarshipList);
            uploadprogress.cancel();
            Toast.makeText(PostScholarship_list.this,"upload successful",Toast.LENGTH_SHORT).show();


        }

    }



    private String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();

        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        // Returning the file Extension.
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GELLERY_PIC_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            ScholarshipImageUri = data.getData();
            Glide.with(PostScholarship_list.this)
                    .load(ScholarshipImageUri)
                    .centerCrop()
                    .into(Scholarshipimage);
        }
    }


}
