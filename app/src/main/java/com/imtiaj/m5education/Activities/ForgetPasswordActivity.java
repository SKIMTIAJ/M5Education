package com.imtiaj.m5education.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.imtiaj.m5education.R;

public class ForgetPasswordActivity extends AppCompatActivity {

    private EditText ForgetPassEditText;
    private Button ForgetPassSubButton;
    private FirebaseAuth firebaseAuth_for_PassReset;
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        ForgetPassEditText = (EditText)findViewById(R.id.ForgotPassEmail);
        ForgetPassSubButton = (Button) findViewById(R.id.ForgotPassResetBtn);

        firebaseAuth_for_PassReset = FirebaseAuth.getInstance();

        ForgetPassSubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(ForgetPassEditText.getText())){

                    firebaseAuth_for_PassReset.sendPasswordResetEmail(ForgetPassEditText.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()){
                                        Toast.makeText(ForgetPasswordActivity.this,"Password hasbeen Send To Your Email",
                                                Toast.LENGTH_LONG).show();
                                    }
                                    else {

                                        Toast.makeText(ForgetPasswordActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                }
            }
        });
    }
}
