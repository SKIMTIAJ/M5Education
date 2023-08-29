package com.imtiaj.m5education.Activities;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
/*import androidx.annotation.NonNull;
import androidx.annotation.Nullable;*/
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

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
import com.imtiaj.m5education.modelClass.way;

public class postWayList extends AppCompatActivity {

    private ImageButton imgbtn;
    private EditText titleEdi, desEdi;
    private Uri imageuri;
    private Button submitButton;
    private static final int GELLERY_PIC_REQUEST = 1;
    private StorageReference mStorageRef;
    private ProgressDialog uploadprogress;
    private DatabaseReference uploadwaylistDatabaseRef;
    private String WayItemIdSpinner;
    // private int justfor[] = new int[100];;
    //private String thumb_download_url;
    //public ArrayList<String> Title_store;
    private StorageTask mUploadTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_way_list);


        //Title_store = new ArrayList<String>();

        imgbtn = (ImageButton) findViewById(R.id.imageInput);
        titleEdi =(EditText)findViewById(R.id.titleId);
        desEdi = (EditText)findViewById(R.id.DesId);
        submitButton = (Button)findViewById(R.id.submitbtn);
        uploadprogress = new ProgressDialog(this);


        Spinner spinner = (Spinner) findViewById(R.id.WayitemId);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(postWayList.this,
                R.array.WayidString_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // An item was selected. You can retrieve the selected item using
                WayItemIdSpinner = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                // Another interface callback

            }
        });



        mStorageRef = FirebaseStorage.getInstance().getReference("uploadedWayImg");
        uploadwaylistDatabaseRef = FirebaseDatabase.getInstance().getReference().child("CreerL").child("way");

        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent gelleryPicTake = new Intent(Intent.ACTION_GET_CONTENT);
                gelleryPicTake.setType("image/*");
                startActivityForResult(gelleryPicTake,GELLERY_PIC_REQUEST);
            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mUploadTask!=null && mUploadTask.isInProgress()){
                    Toast.makeText(postWayList.this,"Upload in Progress",Toast.LENGTH_SHORT).show();
                }else {

                    startPosting();
                }
            }
        });

    }


    private void startPosting(){

        uploadprogress.setMessage("Posting on way list ......");
        uploadprogress.show();

        if(imageuri != null){

            final StorageReference fileRef = mStorageRef.child(System.currentTimeMillis()+"."+ GetFileExtension(imageuri));
            //fileRef.putFile(imageuri).

            mUploadTask = fileRef.putFile(imageuri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    uploadprogress.setProgress(0);
                                }
                            },500);

                            fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    way waycon = new way(titleEdi.getText().toString().trim(), desEdi.getText().toString().trim(),
                                            uri.toString(),WayItemIdSpinner);
                                    // Title_store.add(titleEdi.getText().toString().trim());
                                    String uploadAll = uploadwaylistDatabaseRef.push().getKey();
                                    uploadwaylistDatabaseRef.child(uploadAll).setValue(waycon);
                                    uploadprogress.cancel();
                                    Toast.makeText(postWayList.this,"upload successful",Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }).

                    /* continueWith(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {

                 @Override
                 public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {

                     if(!task.isSuccessful()){
                         throw task.getException();
                     }
                     return fileRef.getDownloadUrl();
                 }
             })
                     .addOnSuccessListener(new OnSuccessListener<Uri>() {
                         @Override
                         public void onSuccess(Uri uri) {
                     Handler handler = new Handler();
                     handler.postDelayed(new Runnable() {
                         @Override
                         public void run() {
                             uploadprogress.setProgress(0);
                         }
                     },500);
                     Toast.makeText(postWayList.this,"upload successful",Toast.LENGTH_LONG).show();
                     way waycon = new way(titleEdi.getText().toString().trim(), desEdi.getText().toString().trim(),
                             uri.toString().);
                     String uploadAll = uploadwaylistDatabaseRef.push().getKey();
                     uploadwaylistDatabaseRef.child(uploadAll).setValue(waycon);
                     uploadprogress.cancel();
                 }
             })*/
                            addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure( Exception e) {
                            Toast.makeText(postWayList.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            uploadprogress.setProgress((int) progress);
                        }
                    });
        }else{
            Toast.makeText(postWayList.this,"No Image is selected",Toast.LENGTH_SHORT).show();
        }



        /*  ***************************************** THIS IS BELLOW CODE WORKING WELL BUT *************************** */


        /*uploadprogress.setMessage("Posting on way list ......");
        uploadprogress.show();
         final String title_val = titleEdi.getText().toString().trim();
         final String des_val = desEdi.getText().toString().trim();
        if(!TextUtils.isEmpty(title_val) && !TextUtils.isEmpty(des_val) && imageuri != null){
            for(int i = 0; ;i++){
               justfor[i] = i+1;


            }
            final StorageReference filePath = mStorageRef.child("WayImage/").child(imageuri+.+justfor[0]".jpg");
            filePath.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                   // for(String name_i :  mStorageRef.child("WayImage/"+getComponentName()).getDownloadUrl()){

                    //}
                    Task<Uri> downloadUrl = mStorageRef.child("WayImage/image:79352 " ).getDownloadUrl();
                    Log.d("PostWayList","before uiji");
                   // Log.d("PostWayList",downloadUrl.getResult().getPath());

                    DatabaseReference textPost = uploadwaylist.push();
                    textPost.child("Title").setValue(title_val);
                    textPost.child("Description").setValue(des_val);
                    textPost.child("Image").setValue(downloadUrl);
                    uploadprogress.cancel();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(postWayList.this,"server is not responding",Toast.LENGTH_LONG).show();
                }
            });
        }
        else {
            Toast.makeText(postWayList.this,"plaese fill all the field",Toast.LENGTH_LONG).show();
        }*/


        /* *****************  THIS CODE IS UPDATED FOR NOW *********************************  */



       /* if (imageuri != null) {

            // Setting progressDialog Title.
            uploadprogress.setTitle("Image is Uploading...");

            // Showing progressDialog.
           uploadprogress.show();

            // Creating second StorageReference.
            StorageReference storageReference2nd = mStorageRef.child("WayImage"+ System.currentTimeMillis() + "." + GetFileExtension(imageuri));

            // Adding addOnSuccessListener to second StorageReference.
            storageReference2nd.putFile(imageuri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            // Getting image name from EditText and store into string variable.
                            String TempImageName = ImageName.getText().toString().trim();

                            // Hiding the progressDialog after done uploading.
                            uploadprogress.dismiss();

                            // Showing toast message after done uploading.
                            Toast.makeText(getApplicationContext(), "Image Uploaded Successfully ", Toast.LENGTH_LONG).show();

                            @SuppressWarnings("VisibleForTests")
                            way imageUploadInfo = new way(TempImageName, storageReference2nd.getDownloadUrl().toString());

                            // Getting image upload ID.
                            String ImageUploadId = databaseReference.push().getKey();

                            // Adding image upload id s child element into databaseReference.
                            databaseReference.child(ImageUploadId).setValue(imageUploadInfo);
                        }
                    })
                    // If something goes wrong .
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {

                            // Hiding the progressDialog.
                            progressDialog.dismiss();

                            // Showing exception erro message.
                            Toast.makeText(MainActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })

                    // On progress change upload time.
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                            // Setting progressDialog Title.
                            progressDialog.setTitle("Image is Uploading...");

                        }
                    });
        }
        else {

            Toast.makeText(MainActivity.this, "Please Select Image or Add Image Name", Toast.LENGTH_LONG).show();

        }*/

    }

    private String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();

        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        // Returning the file Extension.
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GELLERY_PIC_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
           /* try {

                // Getting selected image into Bitmap.
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageuri);

                // Setting up bitmap selected image into ImageView.
                imgbtn.setImageBitmap(bitmap);

                // After selecting image change choose button above text.
                //  ChooseButton.setText("Image Selected");

            } catch (IOException e) {

                e.printStackTrace();
            }*/

            imageuri = data.getData();
            imgbtn.setImageURI(imageuri);

        }
    }

}
