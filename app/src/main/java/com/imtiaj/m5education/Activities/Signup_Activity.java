package com.imtiaj.m5education.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
//import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.imtiaj.m5education.R;
import com.imtiaj.m5education.modelClass.SignUpUserData;

public class Signup_Activity extends AppCompatActivity {

    private ConnectivityManager mConninternet;
    private NetworkInfo minfo;

    private EditText Name_of_user, UserName_of_user, Password_of_user, ConfirmPassword_of_user;
    private Button SubmitButton;
    private TextView Login;

    private String Name, UserName, Password,ConfirmPass;
    private DatabaseReference signUpFireBaseData;
    private FirebaseAuth mAuth;
    private ProgressDialog SignupProgress;


   // private DatabaseSignUpHelper datahelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_);



        Toolbar toolbar = findViewById(R.id.Signuptoolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("SignUp");


        mConninternet = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        minfo = mConninternet.getActiveNetworkInfo();

        Name_of_user = (EditText)findViewById(R.id.Signup_EditText_Name_id);
        UserName_of_user = (EditText)findViewById(R.id.Signup_EditText_UserName_id);
        Password_of_user = (EditText)findViewById(R.id.Signup_EditText_Password_id);
        ConfirmPassword_of_user = (EditText)findViewById(R.id.Signup_EditText_ConfirmPassword_id);
        Login = (TextView)findViewById(R.id.Signup_page_Login_text_bellow);


        SubmitButton = (Button)findViewById(R.id.Signup_Button_Submit_id);

        signUpFireBaseData = FirebaseDatabase.getInstance().getReference("CreerL/UsreData");
        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));

        }

        SignupProgress = new ProgressDialog(Signup_Activity.this);

        //datahelper = new DatabaseSignUpHelper(Signup_Activity.this);




        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Signup_Activity.this, Login_activity.class));
                finish();

            }
        });


        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Name = Name_of_user.getText().toString().trim();
                UserName = UserName_of_user.getText().toString().trim();
                Password = Password_of_user.getText().toString().trim();
                ConfirmPass = ConfirmPassword_of_user.getText().toString().trim();


                if (!TextUtils.isEmpty(Name) && !TextUtils.isEmpty(UserName) && !TextUtils.isEmpty(Password) && !TextUtils.isEmpty(ConfirmPass)){

                    if (!Patterns.EMAIL_ADDRESS.matcher(UserName).matches()){

                        Toast.makeText(Signup_Activity.this,"Please Put Correct Email",Toast.LENGTH_LONG).show();
                    }
                    else {
                        if(minfo!=null && minfo.isConnected()){


                            if (Password.equals(ConfirmPass)) {

                                SignupProgress.setMessage("Processing..");
                                SignupProgress.show();

                                SignUpUserData signUpUserData = new SignUpUserData(Name, UserName, Password);
                                //long value = datahelper.addUser(Name, UserName, Password);

                                String UserDetails = signUpFireBaseData.push().getKey();
                                signUpFireBaseData.child(UserDetails).setValue(signUpUserData);

                                mAuth.createUserWithEmailAndPassword(UserName,Password)
                                        .addOnCompleteListener(Signup_Activity.this, new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(Task<AuthResult> task) {
                                                if (task.isSuccessful()){

                                                    FirebaseUser mUser = mAuth.getCurrentUser();
                                                    //updateUI(mUser);

                                                    finish();
                                                    startActivity(new Intent(getApplicationContext(),Login_activity.class));
                                                }
                                                else {
                                                    SignupProgress.dismiss();
                                                    // Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                                    Toast.makeText(Signup_Activity.this, "Authentication failed.",
                                                            Toast.LENGTH_LONG).show();
                                                    //updateUI(null);

                                                }
                                            }
                                        });


                            } else {

                                Toast.makeText(Signup_Activity.this, "password Does not Match", Toast.LENGTH_SHORT).show();
                            }


                        }
                        else {

                            Toast.makeText(Signup_Activity.this,"Please Connet Internet",Toast.LENGTH_LONG).show();
                        }


                    }

                }
                else {

                    Toast.makeText(Signup_Activity.this,"Can't empty any field ",Toast.LENGTH_LONG).show();
                }

              /*  if (Password.equals(ConfirmPass)){
                    long value = datahelper.addUser(Name,UserName,Password);
                    if (value > 0){


                        Toast.makeText(Signup_Activity.this,"You have Registered",Toast.LENGTH_SHORT).show();

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Signup_Activity.this,"You have Registered",Toast.LENGTH_SHORT).show();
                            }
                        },5000);

                        startActivity(new Intent(Signup_Activity.this,Login_activity.class));
                    }
                    else {
                        Toast.makeText(Signup_Activity.this,"Registertion Error",Toast.LENGTH_SHORT).show();
                    }
                }
                else{

                    Toast.makeText(Signup_Activity.this,"password Does not Match",Toast.LENGTH_SHORT).show();
                }*/

            }
        });




    }
}
