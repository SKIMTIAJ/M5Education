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

public class PostSchoolList extends AppCompatActivity {

    private ImageView SchoolImage;
    private Button SchoolImagePic, SubmitBtn;
    private EditText Schoolname, SchoolLocation, SchoolAffiliated, SchoolPhoneNo, SchoolWebsite, SchoolMedium, SchoolClasses, SchoolRating;
    private Uri schoolImageUri;

    private StorageReference SchoolImageStore;
    private DatabaseReference SchoolDatabaseref;
    private ProgressDialog SchoolProgress;
    //private Spinner SchoolSpiner;
   //private String schoolidSpineer;

    private StorageTask mUploadTask;
    private static final int GELLERYPICTAKE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_school_list);


        SchoolImage = (ImageView)findViewById(R.id.School_list_post_imageView);

        SchoolImagePic = (Button)findViewById(R.id.selectImage_for_School_list);
        SubmitBtn = (Button)findViewById(R.id.Upload_all_college_list);

        Schoolname = (EditText)findViewById(R.id.School_name_to_upload);
        SchoolLocation = (EditText)findViewById(R.id.School_location_to_upload);
        SchoolAffiliated = (EditText)findViewById(R.id.School_Affiliation_to_upload);
        SchoolPhoneNo = (EditText)findViewById(R.id.School_PhoneNo_to_upload);
        SchoolWebsite = (EditText)findViewById(R.id.School_Website_to_upload);
        SchoolMedium = (EditText)findViewById(R.id.School_Medium_to_upload);
        SchoolClasses = (EditText)findViewById(R.id.School_Classes_to_upload);
        SchoolRating = (EditText)findViewById(R.id.School_Rating_to_upload);
        //SchoolSpiner = (Spinner)findViewById(R.id.Schoolspinner);

        SchoolProgress = new ProgressDialog(this);


        SchoolImageStore = FirebaseStorage.getInstance().getReference("UploadSchoolImage");
        SchoolDatabaseref = FirebaseDatabase.getInstance().getReference("CreerL/School");


        /*ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(PostSchoolList.this,
                R.array.WayidString_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        SchoolSpiner.setAdapter(adapter);
        SchoolSpiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                schoolidSpineer = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/

        SchoolImagePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent SchoolUpload = new Intent(Intent.ACTION_GET_CONTENT);
                SchoolUpload.setType("image/*");
                startActivityForResult(SchoolUpload,GELLERYPICTAKE);

            }
        });

        SubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mUploadTask!=null && mUploadTask.isInProgress()){

                    Toast.makeText(PostSchoolList.this,"Uploading is inProgress.. ",Toast.LENGTH_LONG).show();

                }
                else {
                    PostingSchoolData();
                }

            }
        });


    }


    private void PostingSchoolData(){
        SchoolProgress.setMessage("Data is Posting.. ");
        SchoolProgress.show();
        if (schoolImageUri!=null){

            final StorageReference fileRef = SchoolImageStore.child(System.currentTimeMillis()+" "+GetFileExtension(schoolImageUri));

            mUploadTask = fileRef.putFile(schoolImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    SchoolProgress.setProgress(0);
                                }
                            },500);

                            fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    uploadSchool_pojo SchoolList = new uploadSchool_pojo(uri.toString(),Schoolname.getText().toString().trim(),
                                            SchoolLocation.getText().toString().trim(),SchoolAffiliated.getText().toString().trim(),
                                            SchoolPhoneNo.getText().toString().trim(),SchoolWebsite.getText().toString().trim(),SchoolMedium.getText().toString().trim(),
                                            SchoolClasses.getText().toString().trim(), SchoolRating.getText().toString().trim());
                                    String uploadCollegeAll = SchoolDatabaseref.push().getKey();
                                    SchoolDatabaseref.child(uploadCollegeAll).setValue(SchoolList);

                                    SchoolProgress.cancel();
                                    Toast.makeText(PostSchoolList.this,"upload successful",Toast.LENGTH_SHORT).show();


                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(PostSchoolList.this,e.getMessage(),Toast.LENGTH_SHORT).show();

                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            SchoolProgress.setProgress((int) progress);
                        }
                    });
        }
        else {

            uploadSchool_pojo SchoolList = new uploadSchool_pojo(Schoolname.getText().toString().trim(),
                    SchoolLocation.getText().toString().trim(),SchoolAffiliated.getText().toString().trim(),
                    SchoolPhoneNo.getText().toString().trim(),SchoolWebsite.getText().toString().trim(),SchoolMedium.getText().toString().trim(),SchoolClasses.getText().toString().trim(),
                    SchoolRating.getText().toString().trim());

            String uploadCollegeAllexceptimg = SchoolDatabaseref.push().getKey();
            SchoolDatabaseref.child(uploadCollegeAllexceptimg).setValue(SchoolList);

            SchoolProgress.cancel();
            Toast.makeText(PostSchoolList.this,"upload successful",Toast.LENGTH_SHORT).show();

            SchoolProgress.cancel();
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

        if (requestCode == GELLERYPICTAKE && resultCode == RESULT_OK && data != null && data.getData() != null){

            schoolImageUri = data.getData();
            Glide.with(PostSchoolList.this)
                    .load(schoolImageUri)
                    .centerCrop()
                    .into(SchoolImage);

        }


    }


}
