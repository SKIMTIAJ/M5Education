package com.imtiaj.m5education.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
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
import com.imtiaj.m5education.modelClass.uploadSchool_pojo;

public class PostHomeItem_recycler extends AppCompatActivity {

    private ImageView Imagev;
    private Button Imgtakebtn,SubmitAll,SubmitBanner;
    private EditText ImageTitle,ImageDes;
    Uri Imageuri;

    private DatabaseReference firebaseDatabase;
    private StorageReference storageReference;

    private DatabaseReference firebaseDatabase_banner;
    private StorageReference storageReference_banner;
    private ProgressDialog progressDialog;

    private StorageTask mUploadTask;
    private static final int GELLERYPICTAKE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_home_item_recycler);

        Imagev = (ImageView)findViewById(R.id.Home_Itemcycle_post_imageView);

        ImageTitle = (EditText) findViewById(R.id.Home_ItemcycleName_to_upload);
        ImageDes = (EditText) findViewById(R.id.Home_ItemcycleDes_to_upload);

        Imgtakebtn = (Button) findViewById(R.id.selectImage_for_Home_Itemcycle);
        SubmitAll = (Button) findViewById(R.id.Upload_Home_Itemcycle_list);
        SubmitBanner = (Button)findViewById(R.id.Upload_Home_first_banner);
        progressDialog = new ProgressDialog(this);

       /* ............................................ FOR HOME FIRST RECYCLER VIEW ITEM ................................................*/

        firebaseDatabase = FirebaseDatabase.getInstance().getReference("CreerL/HomeItemRecycle");
        storageReference = FirebaseStorage.getInstance().getReference("HomeItemRecycleImage");

        /* ............................................ FOR HOME FIRST BANNER ITEM ................................................*/

        firebaseDatabase_banner = FirebaseDatabase.getInstance().getReference("CreerL/Home1st_Banner");
        storageReference_banner  = FirebaseStorage.getInstance().getReference("Home1st_BannerImg");

        Imgtakebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,GELLERYPICTAKE);
            }
        });

        SubmitAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUploadTask!=null && mUploadTask.isInProgress()){
                    Toast.makeText(PostHomeItem_recycler.this,"Uploading is in Progress",Toast.LENGTH_LONG).show();
                }else {
                    postingData();
                }
            }
        });

        SubmitBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mUploadTask!=null && mUploadTask.isInProgress()){
                    Toast.makeText(PostHomeItem_recycler.this,"Uploading is in Progress",Toast.LENGTH_LONG).show();
                }else {
                    postingBanner();
                }
            }
        });


    }

    private void postingBanner() {
        progressDialog.setMessage("Data is Posting.. ");
        progressDialog.show();
        if (Imageuri!=null){
            final StorageReference filterImag = storageReference_banner.child(System.currentTimeMillis()+" "+getFileExtension(Imageuri));

            mUploadTask = filterImag.putFile(Imageuri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    progressDialog.setProgress(0);
                                }
                            },500);
                            filterImag.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    uploadSchool_pojo uploadItemRecycl = new uploadSchool_pojo(uri.toString(),ImageTitle.getText().toString(),
                                            ImageDes.getText().toString());
                                    String UploadBanner = firebaseDatabase_banner.push().getKey();
                                    firebaseDatabase_banner.child(UploadBanner).setValue(uploadItemRecycl);
                                    progressDialog.dismiss();
                                    Toast.makeText(PostHomeItem_recycler.this,"Upload Successful",Toast.LENGTH_LONG).show();

                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(PostHomeItem_recycler.this,e.getMessage(),Toast.LENGTH_SHORT).show();

                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            progressDialog.setProgress((int) progress);
                        }
                    });
        }
    }


    private void postingData() {
        progressDialog.setMessage("Data is Posting.. ");
        progressDialog.show();

        if (Imageuri!=null){
            final StorageReference filter = storageReference.child(System.currentTimeMillis()+" "+getFileExtension(Imageuri));

            mUploadTask = filter.putFile(Imageuri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    progressDialog.setProgress(0);
                                }
                            },500);

                            filter.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    uploadSchool_pojo uploadItemRecycl = new uploadSchool_pojo(uri.toString(),ImageTitle.getText().toString(),
                                            ImageDes.getText().toString());
                                    String uploadItemHome = firebaseDatabase.push().getKey();
                                    firebaseDatabase.child(uploadItemHome).setValue(uploadItemRecycl);
                                    progressDialog.dismiss();
                                    Toast.makeText(PostHomeItem_recycler.this,"Upload Successful",Toast.LENGTH_LONG).show();

                                }
                            });

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(PostHomeItem_recycler.this,e.getMessage(),Toast.LENGTH_SHORT).show();

                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            progressDialog.setProgress((int) progress);
                        }
                    });

        }


    }


    private String getFileExtension(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getMimeTypeFromExtension(contentResolver.getType(uri));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==GELLERYPICTAKE && resultCode == RESULT_OK && data!=null && data.getData()!=null){
            Imageuri = data.getData();
            Glide.with(PostHomeItem_recycler.this)
                    .load(Imageuri)
                    .centerCrop()
                    .into(Imagev);
        }

    }
}
