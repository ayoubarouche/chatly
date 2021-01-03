package com.inpt.messagingapp.project.student;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.inpt.messagingapp.GlobalApplication;
import com.inpt.messagingapp.R;
import com.inpt.messagingapp.loadingDialog;
import com.inpt.messagingapp.project.shared.CoursActivity;
import com.inpt.messagingapp.wrapper.controllers.NotificationController;
import com.inpt.messagingapp.wrapper.controllers.student.StudentCoursController;

public class AjouterCourStudent extends AppCompatActivity {
    EditText code_cour;
    Button ajouter_cour_button;
    GlobalApplication application ;
    loadingDialog loading_dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (GlobalApplication)getApplication();
        loading_dialog = new loadingDialog(this);

        setContentView(R.layout.activity_ajouter_cour_student);
        initialiseElements();
        ajouter_cour_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inscritCour(code_cour.getText().toString());
            }
        });
    }

public void initialiseElements(){
    code_cour = findViewById(R.id.cour_id_box);
    ajouter_cour_button = findViewById(R.id.ajouter_cour_button);
}
    public void inscritCour(final String courId){
        application.setStudentCoursController();
       application.setNotificationController();
        application.getStudentCoursController().inscritCour(courId, new StudentCoursController.OnAfterRegisterInCour() {
            @Override
            public void OnCallBack() {
                application.getNotificationController().registerToTopic(courId, new NotificationController.OnTopicSubscribed() {
                    @Override
                    public void OnCallBack() {
                        Toast.makeText(getApplicationContext(),"you will receive notifications",Toast.LENGTH_SHORT).show();
                    loading_dialog.dismissdialog();
                    Intent intent = new Intent(getApplicationContext(), CoursActivity.class);
                    startActivity(intent);
                    }

                    @Override
                    public void OnFailed() {
                        loading_dialog.dismissdialog();
                        confirmDialog();
                    }
                });

            }

            @Override
            public void OnErreur() {
                loading_dialog.dismissdialog();
                confirmDialog();
            }
        });

        loading_dialog.startLoadingDialog("attendez s'ils vous plait ...");
    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Erreur");
        builder.setMessage(" problème de la connextion !  :");
        builder.setPositiveButton("d'accord", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.create().show();
    }
}