package com.inpt.messagingapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

import com.inpt.messagingapp.wrapper.controllers.teacher.TeacherCoursController;
import com.inpt.messagingapp.wrapper.models.Cour;
import com.inpt.messagingapp.wrapper.models.User;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.hello_world);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TeacherCoursController tcontroller = new TeacherCoursController();
                tcontroller.addCour(new Cour("helloworldid ","developpement android","hello wrold",null));
            }
        });
    }
}
