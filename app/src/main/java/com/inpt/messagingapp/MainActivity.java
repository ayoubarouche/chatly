package com.inpt.messagingapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

import com.inpt.messagingapp.wrapper.controllers.teacher.TeacherCoursController;
import com.inpt.messagingapp.wrapper.models.Cour;
import com.inpt.messagingapp.wrapper.models.User;
import com.inpt.messagingapp.wrapper.models.UserType;

public class MainActivity extends AppCompatActivity {
    Cour cour ;
    TeacherCoursController tcontroller;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.hello_world);
        Button button1 = findViewById(R.id.delete_world);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tcontroller = new TeacherCoursController();
                cour = new Cour(null,"hello world","description ","hello world id teacher");

               cour = tcontroller.addCour(cour);

                Log.d("hello world", "onClick: the cour is added");

            }
        });
    button1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Cour new_cour = tcontroller.getCour(cour.getIdCour());
            Log.d("hello world", "the cour is : added and : "+new_cour.getIdCour());
        }
    });
    }


}
