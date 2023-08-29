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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.imtiaj.m5education.R;
import com.imtiaj.m5education.modelClass.upload_college_scholarship;

public class PostCollegeList extends AppCompatActivity {


    private ImageView uploadimagefor;
    private EditText uploadCollegeName,uploadCollegeLocation,uploadCollegeAffiliation,uploadCollegePhoneNo,
    uploadCollegeWebsite,uploadCollegeSubject,uploadCollegeRating,uploadCollegeType,uploadCollegeUnder;
    private Button selectImageForUpload,uploadAllCollegeList, updateCollegelistButton;
    private Uri collegeImageUri;

    private StorageReference CollegeImageStore;
    private DatabaseReference CollegeInformationDb,CollegeUpdateDb;
    private ProgressDialog uploadprogress;

    private static final int GELLERY_PIC_REQUEST = 2;
    private StorageTask mUploadTask;

    private String CollegeupdateKey = "", canClickupdatebtn = "";

    private String name, affiliated, Location, Subject,Rating, Type, phone, Website, Image, Under;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_college_list);


        uploadimagefor = (ImageView)findViewById(R.id.College_list_post_imageView);
        uploadCollegeName =(EditText)findViewById(R.id.College_name_to_upload);
        uploadCollegeLocation =(EditText)findViewById(R.id.College_location_to_upload);
        uploadCollegeAffiliation = (EditText)findViewById(R.id.College_Affiliation_to_upload);
        uploadCollegePhoneNo = (EditText)findViewById(R.id.College_PhoneNo_to_upload);
        uploadCollegeWebsite = (EditText)findViewById(R.id.College_Website_to_upload);
        uploadCollegeSubject = (EditText)findViewById(R.id.College_Subject_to_upload);
        uploadCollegeRating = (EditText)findViewById(R.id.College_Rating_to_upload);
        uploadCollegeType = (EditText)findViewById(R.id.College_Type_to_upload);
        uploadCollegeUnder = (EditText)findViewById(R.id.College_Under_to_upload);
        selectImageForUpload = (Button) findViewById(R.id.selectImage_for_college_list);
        updateCollegelistButton = (Button)findViewById(R.id.Update_all_college_list);
        uploadAllCollegeList = (Button)findViewById(R.id.Upload_all_college_list);
        uploadprogress = new ProgressDialog(this);

        CollegeImageStore = FirebaseStorage.getInstance().getReference("UploadCollegeImage");
        CollegeInformationDb = FirebaseDatabase.getInstance().getReference("CreerL").child("College");

        Bundle doseComeUpdateIntent = getIntent().getExtras();

        if (doseComeUpdateIntent!= null){

            CollegeupdateKey = doseComeUpdateIntent.getString("CollegeUpdateKey");
        }

        CollegeUpdateDb = FirebaseDatabase.getInstance().getReference("CreerL").child("College").child(CollegeupdateKey);



        CollegeUpdateDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){


                    name = dataSnapshot.child("uploadName").getValue(String.class);
                    affiliated = dataSnapshot.child("uploadAffiliated").getValue(String.class);
                    Location = dataSnapshot.child("uploadLocation").getValue(String.class);
                    Subject = dataSnapshot.child("uploadSub").getValue(String.class);
                    Rating = dataSnapshot.child("uploadRating").getValue(String.class);
                    Type  = dataSnapshot.child("uploadType").getValue(String.class);
                    phone = dataSnapshot.child("uploadPhone").getValue(String.class);
                    Website = dataSnapshot.child("uploadWebsite").getValue(String.class);
                    Image = dataSnapshot.child("uploadImage").getValue(String.class);
                    Under = dataSnapshot.child("uploadUnder").getValue(String.class);

                    Glide.with(PostCollegeList.this)
                            .load(Image)
                            .centerCrop()
                            .into(uploadimagefor);

                    uploadCollegeName.setText(name);
                    uploadCollegeLocation.setText(Location);
                    uploadCollegeAffiliation.setText(affiliated);
                    uploadCollegePhoneNo.setText(phone);
                    uploadCollegeWebsite.setText(Website);
                    uploadCollegeSubject.setText(Subject);
                    uploadCollegeRating.setText(Rating);
                    uploadCollegeType.setText(Type);
                    uploadCollegeUnder.setText(Under);


                    canClickupdatebtn = "yes";
                    updateCollegelistButton.setVisibility(View.VISIBLE);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        updateCollegelistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mUploadTask!=null && mUploadTask.isInProgress()){
                    Toast.makeText(PostCollegeList.this,"Upload in Progress",Toast.LENGTH_SHORT).show();
                }else {

                    UpdatingCollegeList();
                }

            }
        });




        selectImageForUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent GelleryPicForClg = new Intent(Intent.ACTION_GET_CONTENT);
                GelleryPicForClg.setType("image/*");
                startActivityForResult(GelleryPicForClg,GELLERY_PIC_REQUEST);
            }
        });


        uploadAllCollegeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mUploadTask!=null && mUploadTask.isInProgress()){
                    Toast.makeText(PostCollegeList.this,"Upload in Progress",Toast.LENGTH_SHORT).show();
                }else {
                    //Log.d("PostCollegeList","upload buttom is clicked");
                    PostingCollegeList();
                }

            }
        });


    }

    private void UpdatingCollegeList() {

        uploadprogress.setMessage("Posting on way list ......");
        uploadprogress.show();

    }

    private void PostingCollegeList(){

        uploadprogress.setMessage("Posting on way list ......");
        uploadprogress.show();

        Log.d("PostCollegeList","i am into the postingCollegeList");

        if(collegeImageUri != null){
            Log.d("PostCollegeList","now sure image is not null");
            final StorageReference fileRef = CollegeImageStore.child(System.currentTimeMillis()+"."+ GetFileExtension(collegeImageUri));

            Log.d("PostCollegeList","image has creat path");
            mUploadTask = fileRef.putFile(collegeImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Log.d("PostCollegeList","nimage has pushed");
                            Handler collegeHandeler = new Handler();
                            collegeHandeler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    uploadprogress.setProgress(0);
                                }
                            },500);

                            fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    upload_college_scholarship collegeList = new upload_college_scholarship(uri.toString(),uploadCollegeName.getText().toString().trim(),
                                            uploadCollegeLocation.getText().toString().trim(),uploadCollegeAffiliation.getText().toString().trim(),
                                            uploadCollegePhoneNo.getText().toString().trim(),uploadCollegeSubject.getText().toString().trim(),uploadCollegeRating.getText().toString().trim(),
                                            uploadCollegeWebsite.getText().toString().trim(),uploadCollegeType.getText().toString().trim(),
                                            uploadCollegeUnder.getText().toString().trim());
                                    String uploadCollegeAll = CollegeInformationDb.push().getKey();
                                    CollegeInformationDb.child(uploadCollegeAll).setValue(collegeList);

                                    uploadprogress.cancel();
                                    Toast.makeText(PostCollegeList.this,"upload successful",Toast.LENGTH_SHORT).show();

                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(PostCollegeList.this,e.getMessage(),Toast.LENGTH_SHORT).show();
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

            upload_college_scholarship collegeList = new upload_college_scholarship(uploadCollegeName.getText().toString().trim(),
                    uploadCollegeLocation.getText().toString().trim(),uploadCollegeAffiliation.getText().toString().trim(),
                    uploadCollegePhoneNo.getText().toString().trim(),uploadCollegeSubject.getText().toString().trim(),uploadCollegeRating.getText().toString().trim(),
                    uploadCollegeWebsite.getText().toString().trim(),uploadCollegeType.getText().toString().trim(),
                    uploadCollegeUnder.getText().toString().trim());
            String uploadCollegeAllExceptImg = CollegeInformationDb.push().getKey();
            CollegeInformationDb.child(uploadCollegeAllExceptImg).setValue(collegeList);

            uploadprogress.cancel();
            Toast.makeText(PostCollegeList.this,"upload successful",Toast.LENGTH_SHORT).show();
            uploadprogress.cancel();

        }

    }


    private String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();

        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        // Returning the file Extension.
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GELLERY_PIC_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            collegeImageUri = data.getData();
            Glide.with(PostCollegeList.this)
                    .load(collegeImageUri)
                    .centerCrop()
                    .into(uploadimagefor);
        }
    }

}
