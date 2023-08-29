package com.imtiaj.m5education.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.imtiaj.m5education.R;

public class Login_activity extends AppCompatActivity {

    private ConnectivityManager mInternetManeger;
    private NetworkInfo mnetinfo;

    private EditText Username , Password;
    private Button LoginBtn;
    private TextView Register,ForgetPassword;
    private CheckBox RemeberMe;
    private String User, Pass;

   // private DatabaseSignUpHelper dataHelper;
    private DatabaseReference DatabaseLoginRef;
    private ProgressDialog LoginProgress;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);

        MobileAds.initialize(this, "ca-app-pub-8967101046628767~2410514806");



        Toolbar toolbar = findViewById(R.id.Signuptoolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("LogIn");

        mInternetManeger = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        mnetinfo = mInternetManeger.getActiveNetworkInfo();


        Username = (EditText)findViewById(R.id.Login_page_UserNameid);
        Password = (EditText)findViewById(R.id.Login_page_Password);
        LoginBtn = (Button)findViewById(R.id.Login_page_Buttonid);
        Register = (TextView)findViewById(R.id.Ligin_page_register_text_bellow);
        ForgetPassword = (TextView)findViewById(R.id.ForgetPasswordText);
        RemeberMe = (CheckBox)findViewById(R.id.Login_page_RememberMe_checkid);


        ForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ForgetPassIntent = new Intent(Login_activity.this, ForgetPasswordActivity.class);
                startActivity(ForgetPassIntent);
            }
        });

        LoginProgress = new ProgressDialog(Login_activity.this);


        //DatabaseLoginRef = FirebaseDatabase.getInstance().getReference().child("CreerL/UsreData");
        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();

        }
        //UserDataArrayList = new ArrayList<SignUpUserData>();



        //dataHelper = new DatabaseSignUpHelper(Login_activity.this);


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login_activity.this, Signup_Activity.class));
                finish();
            }
        });

        RemeberMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    sharedPreferance_for_login();
                }
            }
        });

        RemeberMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferance_for_login();
            }
        });

        LoginBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                User = Username.getText().toString().trim();
                Pass = Password.getText().toString().trim();

                if (!TextUtils.isEmpty(User) && !TextUtils.isEmpty(Pass)){

                    if(Patterns.EMAIL_ADDRESS.matcher(User).matches()){

                        // DatabaseLoginRef.orderByChild("email:").equalTo(User);
                        if(mnetinfo!=null && mnetinfo.isConnected()){
                            UserLogin(User,Pass);
                        }

                        else {
                            Toast.makeText(Login_activity.this,"Pleace Connet to the Internet",Toast.LENGTH_LONG).show();
                        }

                     /*    boolean res = dataHelper.CheckinUser(User,Pass);
                         if (res == true){

                            // Check_for_Remember = 1;

                             Handler handler = new Handler();
                             handler.postDelayed(new Runnable() {
                                 @Override
                                 public void run() {

                                     Toast.makeText(Login_activity.this,"Successfully Login",Toast.LENGTH_SHORT).show();
                                     startActivity(new Intent(Login_activity.this,MainActivity.class));

                                 }
                             },500);

                         }
                         else {
                             Toast.makeText(Login_activity.this,"Login Error",Toast.LENGTH_SHORT).show();
                         }*/
                    }
                    else {

                        LoginProgress.dismiss();
                        Toast.makeText(Login_activity.this,"Please Enter A Valid Email",Toast.LENGTH_LONG).show();
                    }

                }
                else {

                    Toast.makeText(Login_activity.this,"Field Can't be empty",Toast.LENGTH_LONG).show();
                }

            }
        });

    }


    private void UserLogin(final String User_in_method, String Pass_in_method){

        LoginProgress.setMessage(" Please Wait.. ");
        LoginProgress.show();

        mAuth.signInWithEmailAndPassword(User_in_method,Pass_in_method)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){


                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    finish();
                                    startActivity(new Intent(getApplicationContext(),MainActivity.class));

                                }
                            },500);

                            LoginProgress.dismiss();
                            Toast.makeText(Login_activity.this,"Successfully Login",Toast.LENGTH_SHORT).show();
                        }
                        else {

                            LoginProgress.dismiss();

                            // Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                            // updateUI(null);

                        }

                    }
                });

    }

    //@Override
    protected void onResume() {
        super.onResume();

        SharedPreferences shareLoginResume = getSharedPreferences("LoginUserRemember",MODE_APPEND);
        String shUser = shareLoginResume.getString("UserName","");
        String shPass = shareLoginResume.getString("Password","");

        Username.setText(shUser);
        Password.setText(shPass);

    }

    protected void sharedPreferance_for_login(){

        //if (Check_for_Remember == 1){

        SharedPreferences LoginRemember = getSharedPreferences("LoginUserRemember",MODE_PRIVATE);
        SharedPreferences.Editor LoginShare = LoginRemember.edit();
        LoginShare.putString("UserName",Username.getText().toString().trim());
        LoginShare.putString("Password",Password.getText().toString().trim());
        LoginShare.apply();

        //}

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(Login_activity.this,"Can't go Back", Toast.LENGTH_LONG).show();
    }
}
