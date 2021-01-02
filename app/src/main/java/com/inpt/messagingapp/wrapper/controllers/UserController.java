package com.inpt.messagingapp.wrapper.controllers;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.inpt.messagingapp.wrapper.models.User;

public class UserController {
    FirebaseFirestore db ;
    FirebaseAuth auth;
    private User user ;
    private User tempuser; // temporaire utilisateur pour la consultation des utilisateurs
    public UserController(FirebaseFirestore firebasedatabase , FirebaseAuth authentification){
        db = firebasedatabase;
        auth = authentification;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getTempuser() {
        return tempuser;
    }

    public void setTempuser(User tempuser) {
        this.tempuser = tempuser;
    }

    public void addUser(final User user, final AfterGettingUser afterGettingUser){
        db.collection("users").document(user.getIdUser()).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                afterGettingUser.OnCallBack(user);
                Log.d("user controller", "onSuccess: add is succes");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("user controller", "onFailure: adding is failed");
            }
        });
    }
    public void login(String email , String password, final AfterGettingUser afterGettingUser){
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser firebaseUser = auth.getCurrentUser();
                    if(firebaseUser!=null){


                        fromFireBaseUserToNormalUser(firebaseUser,afterGettingUser);
                       //   Log.d("login", "onComplete: the user is"+user1.getName()+" "+user1.getIdUser());
                          setUser(user);
                      }
                }
                else Log.d("login", "onComplete: failed to get the user");
            }
        });

    }
    public boolean signup(final User user_to_sign_up, String password, final AfterGettingUser afterGettingUser){
        auth.createUserWithEmailAndPassword(user_to_sign_up.getEmail(),password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d("user controller", "onComplete: the user is created");
                    FirebaseUser firebaseUser = auth.getCurrentUser();
                        if(firebaseUser!=null){
                           User us =  fromFireBaseUserToNormalUser(firebaseUser,user_to_sign_up);
                        addUser(us,afterGettingUser);
                            setUser(us);
                        }
                        else Log.d("the user is null", "onComplete: ");

                }
                else{
                    Log.d("use controller", "onComplete: the user is not created");
                }
            }
        });
return true;
    }
    private User getUser(String userid){

        db.collection("users").document(userid).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                setTempuser(documentSnapshot.toObject(User.class));
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("usercontroller", "onFailure: it not finded");
            }
        });
        return tempuser ;
    }
    private User getUser(String userid, final AfterGettingUser afterGettingUser){

        db.collection("users").document(userid).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                setTempuser(documentSnapshot.toObject(User.class));
                afterGettingUser.OnCallBack(documentSnapshot.toObject(User.class));
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("usercontroller", "onFailure: it not finded");
            }
        });
        return tempuser ;
    }

    public User getCurrentUser(){
        return null ;
    }
    public User fromFireBaseUserToNormalUser( FirebaseUser firebaseUser,User user1){
        String uid = firebaseUser.getUid();
        String email = firebaseUser.getEmail();
        User user = new User();
        user.setIdUser(uid);
        user.setEmail(email);
        user.setName(user1.getName());
        user.setPrenom(user1.getPrenom());
        user.setType(user1.getType());
        user.setUsername(user1.getUsername());
        return user ;
    }
    public User fromFireBaseUserToNormalUser( FirebaseUser firebaseUser, AfterGettingUser afterGettingUser){
        String uid = firebaseUser.getUid();
        User user1 = getUser(uid,afterGettingUser);
        if(user1!=null){
           user = new User();
            user.setIdUser(uid);
            user.setEmail(user1.getEmail());
            user.setName(user1.getName());
            user.setPrenom(user1.getPrenom());
            user.setType(user1.getType());
            return user ;
        }
        return null ;

    }
    public interface AfterGettingFireBaseUser{
        void OnCallBack(FirebaseUser user);
    }
    public interface AfterGettingUser{
        void OnCallBack(User user);
    }
}
