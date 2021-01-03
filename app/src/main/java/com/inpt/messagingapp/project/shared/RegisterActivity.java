package com.inpt.messagingapp.project.shared;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.inpt.messagingapp.GlobalApplication;
import com.inpt.messagingapp.MainActivity;
import com.inpt.messagingapp.R;
import com.inpt.messagingapp.loadingDialog;
import com.inpt.messagingapp.wrapper.controllers.UserController;
import com.inpt.messagingapp.wrapper.models.User;
import com.inpt.messagingapp.wrapper.models.UserType;

public class RegisterActivity extends AppCompatActivity {
    EditText nom_box;  // pour le username
    EditText email_box;  // pour l'email
    EditText password_box;  // pour le mot de pass
    EditText prenom_box;  // pour retaper le mot de passe
    RadioGroup user_type; // pour le type de l'utilisateur
    RadioButton type; // after chosing the type from the radio group
    Button complete_button; // after clicking the register button
    TextView choice_other; // if he needs to chose the login type
    int selected_id ; // fot type user selected i will need it in the chekch button
    static String erreur ; // when we have and erreur
    GlobalApplication app ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (GlobalApplication)this.getApplication();
        if(app.getUser()!=null)changeToHomePage();
        setContentView(R.layout.activity_register);
        initialiseWidgets(); // pour l'initailisation des widgets

        complete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickToRegister(); // adding the listerner if the user ckicked ad

            }
        });
        choice_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
    changeToLogin();

            }
        });
    }

    public void changeToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        this.finish();
    }

    //methode pour l'initialisation de tous les widgets
    @SuppressLint("WrongViewCast")
    public void initialiseWidgets() {
        nom_box = findViewById(R.id.nom_box);
        email_box = findViewById(R.id.email_box);
        password_box = findViewById(R.id.password_box);
        prenom_box = findViewById(R.id.prenom_box);
        user_type = findViewById(R.id.user_type);
        complete_button = findViewById(R.id.complete_button);
        choice_other = findViewById(R.id.choice_other);
    }

    public void clickToRegister() {
        selected_id = user_type.getCheckedRadioButtonId() ;

                if (check()){
                    register();
                }
                else {
                    show_the_erreur();
                }
    }

    public boolean check() {
         if(nom_box == null || nom_box.getText().length() < 3){
             erreur = "le nom est très court ";
             return false;
         }
         if(prenom_box == null | prenom_box.getText().length() < 3){
            erreur = "le prenom est tres court";
            return false;
        }
        if(email_box == null || email_box.getText().length() < 5){
            erreur = "Email est incorrecte";
            return false;
        }
        if(password_box == null || password_box.getText().length() < 5){
            erreur = "le mot de passe est très court";
            return false;
        }
        if(selected_id == -1){
            erreur = "s'il vous plait séléctioner un type de l'utilisatuer";
            return false;
        }
        else {
            type  = findViewById(selected_id);
        }
    return true;
    }


    public void register() {
        final loadingDialog loading_dialog = new loadingDialog(this);
        String nom = this.nom_box.getText().toString();
        String password = this.password_box.getText().toString();
        String email = this.email_box.getText().toString();
        String prenom = this.prenom_box.getText().toString();
        String username = nom+"_"+prenom;
        UserType us_type = UserType.valueOf(type.getText().toString());
        User user = new User();
        user.setType(us_type);
        user.setUsername(username);
        user.setEmail(email);
        user.setName(nom);
        user.setPrenom(prenom);
        user.setUsername(username);
       app.userController.signup(user, password, new UserController.AfterGettingUser() {
           @Override
           public void OnCallBack(User user) {
               app.setUser(user);
               loading_dialog.dismissdialog();
               changeToHomePage();
           }

       });
        loading_dialog.startLoadingDialog("Entrain de créer votre compte...");

    }

    public void show_the_erreur() {
        Toast.makeText(this ,erreur , Toast.LENGTH_LONG).show();
    }
    public void changeToHomePage(){
        Intent intent = new Intent(this, CoursActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        this.finish();
    }
    public void fetchFCMToken(){
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if(!task.isSuccessful()){
                    Log.d("fcm", "onComplete: ferching for the fcm failed");return ;
                }
                String token = task.getResult();

            }
        });
    }
}
