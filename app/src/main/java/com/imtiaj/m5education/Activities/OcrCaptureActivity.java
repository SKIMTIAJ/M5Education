package com.imtiaj.m5education.Activities;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.imtiaj.m5education.R;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class OcrCaptureActivity extends AppCompatActivity {

    private float pitch,speed;
    private int OcrimgSpeek_CODE = 0;

    private EditText mResultEt;
    private ImageView mImageView;
    private String mText_for_save;
    private String nameFor_File;
    private TextToSpeech mTTS;
    private EditText inputname;


    private static final int CAMERA_REQUEST_CODE = 200;
    private static final int STORAGE_REQUEST_CODE = 400;
    private static final int IMAGE_PICK_GELLERY_CODE = 1000;
    private static final int IMAGE_PICK_CAMERA_CODE = 1001;
    private static final int WRITE_EXTERNAL_STORAGE_CODE = 10;

    String cameraPermission[];
    String storagePermission[];

    Uri image_uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocr_capture);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setSubtitle("click + buttom to insert");

        mResultEt = (EditText)findViewById(R.id.ResultImgEt);
        mImageView = (ImageView)findViewById(R.id.imagepreIv);

        Bundle comeFrom_Ocrsetting = getIntent().getExtras();
        if (comeFrom_Ocrsetting!= null){
            pitch = comeFrom_Ocrsetting.getFloat("MesurePtch");
            speed = comeFrom_Ocrsetting.getFloat("MesureSpeed");
        }
        cameraPermission = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS){
                    int result = mTTS.setLanguage(Locale.ENGLISH);
                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED){
                       Log.e("TTS","Initialization Failed");
                        OcrimgSpeek_CODE = 1;
                    }
                   /* else {

                    }*/
                }
                else {
                    Log.e("TTS","Initialization Failed");
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ocr_read_image_menu,menu);
       // MenuItem OcrSpeeker = menu.getItem(1);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.Ocr_addImage:
                showImageImportDialog();

                break;
            case R.id.Ocr_setting:
                startActivity(new Intent(OcrCaptureActivity.this,Ocrsetting.class));
                break;
            case R.id.OcrImageSpeek:
                if(OcrimgSpeek_CODE == 0){
                    speek();
                }else{
                    Toast.makeText(this,"Language is not supported", Toast.LENGTH_LONG).show();
                }

                break;

            case R.id.savetoFile_id:
                final AlertDialog.Builder saveText = new AlertDialog.Builder(OcrCaptureActivity.this);
                saveText.setTitle("Save file");
                saveText.setMessage("Enter Any File name");
                final EditText inputname = new EditText(this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                inputname.setLayoutParams(lp);
                saveText.setView(inputname);

                //Log.d("OcrFileName",nameFor_File);
                //Toast.makeText(this,inputname.getText().toString(),Toast.LENGTH_LONG).show();
                saveText.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        nameFor_File = inputname.getText().toString();
                        if (!nameFor_File.isEmpty()){
                            onSaveTextFile();
                        }
                    }
                });
                saveText.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                saveText.show();


                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void showImageImportDialog() {

        String[] items = {"Camera", "Gellery"};

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Select Image");
        dialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0){
                    /*camera option is clicked*/
                    if (!checkCameraPermission()){
                        /*camera is not allowed request for camera*/
                        requestCameraPermission();

                    }
                    else{
                        /*permission is allowed take picture*/
                        pickCamera();
                    }
                }
                if (which == 1){
                    /* Gellery Option is clicked */

                    if (!checkStoragePermission()){
                        /*Storage permission is not allowed request for camera*/
                        requestStoragePermission();

                    }
                    else{
                        /*permission is allowed */
                        pickGellery();
                    }
                }
            }
        });
        dialog.create().show();

    }

    private void pickGellery() {
        // Intent to pic Image from gellery
        Intent intent = new Intent(Intent.ACTION_PICK);
        //set Intent type to image
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_GELLERY_CODE);

    }

    private void pickCamera() {
        /*intent to take image from camera, it will also be saved to storage to get high quality image*/

        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "NewPic");
        values.put(MediaStore.Images.Media.DESCRIPTION, "Image To text");
        image_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
        startActivityForResult(cameraIntent, IMAGE_PICK_CAMERA_CODE);


    }

    private void requestStoragePermission() {

        ActivityCompat.requestPermissions(this, storagePermission, STORAGE_REQUEST_CODE);
    }

    private boolean checkStoragePermission() {

        boolean result = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result;

    }

    private void requestCameraPermission() {

        ActivityCompat.requestPermissions(this, cameraPermission, CAMERA_REQUEST_CODE);
    }

    private boolean checkCameraPermission() {
        boolean result = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA) == (PackageManager.PERMISSION_GRANTED);

        boolean result1 = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);

        return result && result1;
    }


    //HANDRL permission result
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){

            case CAMERA_REQUEST_CODE:

                if (grantResults.length>0){
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                    boolean writeStorageAcepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (cameraAccepted && writeStorageAcepted){
                        pickCamera();
                    }
                    else {
                        Toast.makeText(this,"Permission Denied", Toast.LENGTH_LONG).show();
                    }
                }
                break;

            case STORAGE_REQUEST_CODE:

                if (grantResults.length>0){
                    boolean writeStorageAcepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (writeStorageAcepted){
                        pickGellery();
                    }
                    else {
                        Toast.makeText(this,"Permission Denied", Toast.LENGTH_LONG).show();
                    }
                }
                break;

            case WRITE_EXTERNAL_STORAGE_CODE:
                //if the request is cancelled, the request array empty
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //permission was granted save data
                    saveTotextFile(mText_for_save);
                }
                else {
                    Toast.makeText(this, "strage permission is required to store data", Toast.LENGTH_SHORT).show();
                }


        }
    }


    // handel Image result


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK){

            if (requestCode == IMAGE_PICK_GELLERY_CODE){

                //got image from gallery now crop
                CropImage.activity(data.getData())
                        .setGuidelines(CropImageView.Guidelines.ON) //enalble image guidline
                        .start(this);
            }
            if (requestCode == IMAGE_PICK_CAMERA_CODE){

                //got image from camera now crop

                CropImage.activity(image_uri)
                        .setGuidelines(CropImageView.Guidelines.ON) //enalble image guidline
                        .start(this);


            }

        }

        //get cropped image

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK){

                Uri resultUri = result.getUri(); //get image uri
                // set image to image View
                mImageView.setImageURI(resultUri);

                //get drawable bitmap for text recognaization

                BitmapDrawable bitmapDrawable = (BitmapDrawable)mImageView.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                TextRecognizer recognizer = new TextRecognizer.Builder(getApplicationContext()).build();
                if (!recognizer.isOperational()){
                    Toast.makeText(this,"Error",Toast.LENGTH_LONG).show();
                }
                else {
                    Frame frame = new Frame.Builder().setBitmap(bitmap).build();
                    SparseArray<TextBlock> items = recognizer.detect(frame);
                    StringBuilder sb = new StringBuilder();
                    //get text from sb until there is no text
                    for(int i = 0; i< items.size();i++){
                        TextBlock myitem = items.valueAt(i);
                        sb.append(myitem.getValue());
                        sb.append("\n");
                    }
                    mResultEt.setText(sb.toString());
                }
            }
            else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                //if there is any error show it
                Exception error = result.getError();
                Toast.makeText(this, ""+error, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void speek() {
        String text_for_speek = mResultEt.getText().toString();
        if (text_for_speek.isEmpty()){
            Toast.makeText(this,"No text found ",Toast.LENGTH_LONG).show();
        }else {

            mTTS.setPitch(pitch);
            mTTS.setSpeechRate(speed);
            mTTS.speak(text_for_speek,TextToSpeech.QUEUE_FLUSH,null);
        }
    }


    private void onSaveTextFile() {

        mText_for_save = mResultEt.getText().toString().trim();
        if (mText_for_save.isEmpty()){

            Toast.makeText(this,"Please Enter Somthing",Toast.LENGTH_LONG).show();
        }else {// user has entered string data
            //if user OS is >= masrshmallow we need User permission
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

                if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
                {
                    String[] permission = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    // show popup for runtime permission
                    requestPermissions(permission,WRITE_EXTERNAL_STORAGE_CODE);
                }
                else {
                    //permission already grant
                    saveTotextFile(mText_for_save);

                }
            }
            else {
                //permission already grant
                saveTotextFile(mText_for_save);

            }

        }

    }



    private void saveTotextFile(String mText_for_save) {

        //get current file for file name
        String timeStamp = new SimpleDateFormat("yyyymmdd_HHmmss",
                Locale.getDefault()).format(System.currentTimeMillis());
        try{

            //Path to Storage
            File path = Environment.getExternalStorageDirectory();
            //creat folder name MyName
            File dir = new File(path+"/M5educationOcr/");
            dir.mkdirs();
            //file name
            //Log.d("OcrFileName",nameFor_File);
            String file_name = nameFor_File + ".doc";
            File file = new File(dir, file_name);

            // filwWriter is a class used to store character into the file
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(mText_for_save);
            bw.close();

            Toast.makeText(this, file_name+" is Saved", Toast.LENGTH_LONG).show();
        }
        catch (Exception e){

            // if any thing gose wrong
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {

        if (mTTS!= null){
            mTTS.stop();
            mTTS.shutdown();
        }
        super.onDestroy();
    }
}
