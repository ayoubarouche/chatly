package com.inpt.messagingapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import com.inpt.messagingapp.helpers.firebaseHelpers.FireBaseConnector;
import com.inpt.messagingapp.helpers.firebaseHelpers.mappingHelpers.CourFirebase;
import com.inpt.messagingapp.project.shared.CoursActivity;
import com.inpt.messagingapp.wrapper.controllers.UserController;
import com.inpt.messagingapp.wrapper.controllers.student.StudentCoursController;
import com.inpt.messagingapp.wrapper.controllers.teacher.DevoirController;
import com.inpt.messagingapp.wrapper.controllers.teacher.TeacherCoursController;
import com.inpt.messagingapp.wrapper.models.Cour;
import com.inpt.messagingapp.wrapper.models.Devoir;
import com.inpt.messagingapp.wrapper.models.User;
import com.inpt.messagingapp.wrapper.models.UserType;
import com.inpt.messagingapp.wrapper.views.teacher.ConsulterCoursTeacher;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Cour general_cour ;

    Button button ;
    Button go_ahead ;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         button = findViewById(R.id.hello_world);
         button.setText("ajouter cour");
         /*
         button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 addCour();
             }
         });
         go_ahead = findViewById(R.id.cours_parcour);
         go_ahead.setText("consulter les cours");
         go_ahead.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 goToCours();
             }
         });*/
    }


}
