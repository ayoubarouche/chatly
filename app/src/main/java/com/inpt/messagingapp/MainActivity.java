package com.inpt.messagingapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import com.inpt.messagingapp.helpers.firebaseHelpers.FireBaseConnector;
import com.inpt.messagingapp.helpers.firebaseHelpers.mappingHelpers.CourFirebase;
import com.inpt.messagingapp.wrapper.controllers.student.StudentCoursController;
import com.inpt.messagingapp.wrapper.controllers.teacher.DevoirController;
import com.inpt.messagingapp.wrapper.controllers.teacher.TeacherCoursController;
import com.inpt.messagingapp.wrapper.models.Cour;
import com.inpt.messagingapp.wrapper.models.Devoir;
import com.inpt.messagingapp.wrapper.models.User;
import com.inpt.messagingapp.wrapper.models.UserType;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Cour cour ;
    TeacherCoursController tcontroller;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.hello_world);
        Button button1 = findViewById(R.id.delete_world);
        final AppCompatTextView textView = findViewById(R.id.hel);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //og.d("hello world", "onClick: the cour is added");
                User student = new User();
                student.setIdUser("helloworld");
                StudentCoursController sccontroller = new StudentCoursController(student);
               List<Cour> courses = sccontroller.getCourses();

               for(Cour cou : courses){
                   Log.d("mainactivity button ", "onClick: the course id is  "+cou.getIdCour());
               }
            }
        });
    button1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            User student = new User();
            student.setIdUser("helloworld");
            StudentCoursController sccontroller = new StudentCoursController(student);
            sccontroller.inscritCour("XoUKDkEnZT6E1XF5SMBA");
        }
    });
    }


}
