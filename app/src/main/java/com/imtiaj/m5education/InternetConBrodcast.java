package com.imtiaj.m5education;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.widget.Toast;

import com.imtiaj.m5education.Activities.Splash_actvity;

public class InternetConBrodcast extends BroadcastReceiver {

    public InternetConBrodcast() {

    }

    @SuppressLint("UnsafeProtectedBroadcastReceiver")

    @Override
    public void onReceive(final Context context, Intent intent) {
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){

            //Intent intent1 = new Intent(context,)
            boolean noInternet = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY,false);
            if (noInternet){
                //context.startActivity(new Intent(context,No_InterNetConnection.class));
            }
            else{
                Toast.makeText(context,"Connected",Toast.LENGTH_LONG).show();
                android.os.Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        context.startActivity(new Intent(context, Splash_actvity.class));
                    }
                },3000);
            }
        };
        /*No_InterNetConnection no_interNetConnection = new No_InterNetConnection();
        no_interNetConnection.checkInternet();*/
    }
}
