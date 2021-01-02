package com.inpt.messagingapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class loadingDialog {
    Activity activity;
    AlertDialog dialog;

    public loadingDialog(Activity myActivity) {
        this.activity = myActivity;
    }
    void startLoadingDialog(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_loading_dialog,null);
        builder.setView(view);
        ((TextView)view.findViewById(R.id.textView3)).setText(message);
        builder.setCancelable(false);
        dialog = builder.create();
        dialog.show();
    }
     void dismissdialog(){
        dialog.dismiss();
     }
}
