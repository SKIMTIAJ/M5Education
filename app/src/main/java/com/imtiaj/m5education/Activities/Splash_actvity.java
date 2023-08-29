package com.imtiaj.m5education.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.imtiaj.m5education.R;

public class Splash_actvity extends AppCompatActivity {


   // private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_actvity);

        /*Toolbar toolbar = findViewById(R.id.Splashtoolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("M5education");*/

        //progressBar = (ProgressBar)findViewById(R.id.SplashProgress);


        if (CheckInternetConnection()){
            //progressBar.setVisibility(View.VISIBLE);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent SplashIntent = new Intent(Splash_actvity.this, Login_activity.class);
                    startActivity(SplashIntent);
                    //progressBar.setVisibility(View.GONE);
                    finish();
                }
            },2500);


        }else {

            Intent NoInterIntent = new Intent(Splash_actvity.this, No_InterNetConnection.class);
            startActivity(NoInterIntent);
            finish();
        }

    }

    public boolean CheckInternetConnection() {
        ConnectivityManager InternetCon = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = InternetCon.getActiveNetworkInfo();
        return(networkInfo!=null && networkInfo.isConnected());
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(Splash_actvity.this,"Can't go Back", Toast.LENGTH_LONG).show();
    }
}
