package com.inpt.messagingapp.project;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.inpt.messagingapp.R;
import com.inpt.messagingapp.project.shared.LoginActivity;
import com.inpt.messagingapp.project.shared.OfflineCoursActivity;

public class WelcomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        if(isConnectedToInternet()){
            Intent intent = new Intent(this , LoginActivity.class);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(this , OfflineCoursActivity.class);
            startActivity(intent);

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(isConnectedToInternet()){
            Intent intent = new Intent(this , LoginActivity.class);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(this , OfflineCoursActivity.class);
            startActivity(intent);

        }
    }

    private boolean isConnectedToInternet(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }
}
