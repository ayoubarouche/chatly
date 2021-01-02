package com.inpt.messagingapp.project.shared;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.inpt.messagingapp.GlobalApplication;
import com.inpt.messagingapp.MainActivity;
import com.inpt.messagingapp.R;
import com.inpt.messagingapp.wrapper.controllers.UserController;
import com.inpt.messagingapp.wrapper.models.User;

public class LoginActivity extends AppCompatActivity {
    EditText email_box;  // pour l'email
    EditText password_box;  // pour le mot de pass
    Button complete_login ;
    TextView choice_other; // if he needs to chose the login type

    static String erreur ; // pour les erreurs
    GlobalApplication app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (GlobalApplication)getApplication();
        if(app.getUser()!=null)changeToHomePage();
        setContentView(R.layout.activity_login);
        initialiseWidgets();

        complete_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickToLogin();
            }
        });
        choice_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeToRegister();
            }
        });
    }
    public void changeToRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        this.finish();
    }
    public void changeToHomePage(){
        Intent intent = new Intent(this, CoursActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        this.finish();
    }
    @SuppressLint("WrongViewCast")
    public void initialiseWidgets() {
        email_box = findViewById(R.id.email_box);
        password_box = findViewById(R.id.password_box);
        complete_login = findViewById(R.id.complete_button);
        choice_other = findViewById(R.id.choice_other);
    }
    public void clickToLogin() {

        if (check())
            login();
        else {
            show_the_erreur();
        }
    }
    public boolean check() {
        if(email_box == null || email_box.getText().length() < 5){
            erreur = "Email est incorrecte";
            return false;
        }
        if(password_box == null || password_box.getText().length() < 5){
            erreur = "le mot de passe est très court";
            return false;
        }

        return true;
    }

    public void login(){
        String password = this.password_box.getText().toString();
        String email = this.email_box.getText().toString();

        app.userController.login(email, password, new UserController.AfterGettingUser() {
            @Override
            public void OnCallBack(User user) {
                app.setUser(user);
                User user2 = app.getUser();
                Toast.makeText(getApplicationContext() , "the user is : "+user2.getPrenom(),Toast.LENGTH_LONG).show();
                changeToHomePage();
            }
        });
        Toast.makeText(getApplicationContext() ,"please wait we are loging in",Toast.LENGTH_LONG).show();


    }

    public void show_the_erreur() {
        Toast.makeText(this ,erreur , Toast.LENGTH_LONG).show();
    }

}
