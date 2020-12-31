package com.inpt.messagingapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import com.inpt.messagingapp.helpers.firebaseHelpers.FireBaseConnector;
import com.inpt.messagingapp.helpers.firebaseHelpers.mappingHelpers.CourFirebase;
import com.inpt.messagingapp.wrapper.controllers.UserController;
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


                User user = new User("hello@gmail.com",UserType.teacher,"testindid","ayoub","arouche","ayoubarouche");
     signup(user);



             //   User user2 = ucontroller.getUser("5a2Aj9swf2R00rTfDbeTnVAxoMw1");

            }
        });
    button1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    });
    }

public void makeToast(String message){
        Toast.makeText(this,message,Toast.LENGTH_LONG);
}
public void signup(User user){
    UserController ucontroller = new UserController();
    ucontroller.login(user.getEmail() , "helloworld");
    User user2 = ucontroller.getUser();
    if (user2 != null) {
        System.out.println("email : " + user2.getEmail()+ " id : "+user2.getEmail());
        Log.d("mainactivity", "onClick: the user is : " + user2.getIdUser() +" nom is : "+user2.getName());
        makeToast("onClick: the user is : " + user2.getIdUser() +" nom is : "+user2.getName());
    }else {
        System.out.println("user is null");
    }
    }
}
