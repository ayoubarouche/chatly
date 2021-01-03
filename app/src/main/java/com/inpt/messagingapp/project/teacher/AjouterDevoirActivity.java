package com.inpt.messagingapp.project.teacher;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.inpt.messagingapp.GlobalApplication;
import com.inpt.messagingapp.R;
import com.inpt.messagingapp.loadingDialog;
import com.inpt.messagingapp.wrapper.controllers.teacher.DevoirController;
import com.inpt.messagingapp.wrapper.models.Cour;
import com.inpt.messagingapp.wrapper.models.Devoir;

public class AjouterDevoirActivity extends AppCompatActivity {
    public EditText file ;
    Button ajouter;
    GlobalApplication application ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_dev);
        }
    private void initialiseElements(){
        file = findViewById(R.id.addfile);
        ajouter = findViewById(R.id.ajouter);

    }
    public void addDevoir(Devoir devoir){
            application = (GlobalApplication)getApplication();
            Cour cour  = new Cour("hello world","hello world titre","hello world 2","life is hard");
            final loadingDialog loading_devoir = new loadingDialog(this);

            application.setDevoirController(cour);
            application.getDevoirController().addDevoir(devoir, new DevoirController.OnFinishingDevoirOperation() {
                @Override
                public void OnCallBack() {
                loading_devoir.dismissdialog();
                }
            });
            loading_devoir.startLoadingDialog("ajout du devoir.....");
    }
}
