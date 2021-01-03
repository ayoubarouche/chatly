package com.inpt.messagingapp.project.student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.inpt.messagingapp.GlobalApplication;
import com.inpt.messagingapp.MainActivity;
import com.inpt.messagingapp.R;
import com.inpt.messagingapp.loadingDialog;
import com.inpt.messagingapp.wrapper.controllers.NotificationController;
import com.inpt.messagingapp.wrapper.controllers.student.StudentCoursController;

public class AjouterCourStudent extends AppCompatActivity {
    EditText code_cour;
    Button ajouter_cour_button;
    GlobalApplication application ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (GlobalApplication)getApplication();

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
        final loadingDialog loading_dialog = new loadingDialog(this);
        application.setStudentCoursController();
       application.setNotificationController();
        application.getStudentCoursController().inscritCour(courId, new StudentCoursController.OnAfterRegisterInCour() {
            @Override
            public void OnCallBack() {
                application.getNotificationController().registerToTopic(courId, new NotificationController.OnTopicSubscribed() {
                    @Override
                    public void OnCallBack() {
                        Toast.makeText(getApplicationContext(),"you will receive notifications",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void OnFailed() {
                        Toast.makeText(getApplicationContext(),"you can'receive notifications",Toast.LENGTH_SHORT).show();

                    }
                });
                Toast.makeText(getApplicationContext(),"looking if you can receive notifications...",Toast.LENGTH_SHORT).show();

                loading_dialog.dismissdialog();
            }
        });
        loading_dialog.startLoadingDialog("attendez s'ils vous plait ...");
    }

}