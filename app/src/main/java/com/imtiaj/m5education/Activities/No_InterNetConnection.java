package com.imtiaj.m5education.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;

import com.imtiaj.m5education.InternetConBrodcast;
import com.imtiaj.m5education.R;

public class No_InterNetConnection extends AppCompatActivity {

    //MyAsyncTaskClass Mytask;

    InternetConBrodcast internetConBrodcast = new InternetConBrodcast();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no__inter_net_connection);

       /* Mytask = new MyAsyncTaskClass();
        if (Mytask.doInBackground()){

        }*/
    }

    /*public void checkInternet(){

        Splash_actvity splash_actvity = new Splash_actvity();
        Intent intent = new Intent(No_InterNetConnection.this,Splash_actvity.class);
        startActivity(intent);
        finish();

    }*/

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(internetConBrodcast,filter);

    }


    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(internetConBrodcast);
    }

}

