package com.inpt.messagingapp.wrapper.controllers.teacher;


import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.inpt.messagingapp.wrapper.controllers.CoursController;
import com.inpt.messagingapp.wrapper.models.Cour;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeacherCoursController implements CoursController {
   static private FirebaseFirestore db;
    static String TAG  = "hello world";
    public TeacherCoursController(){
        db= FirebaseFirestore.getInstance();

    }
    @Override
    public List<Cour> getCourses(String uid) {
        return null;
    }
    public boolean DeleteCour(String coursId){
        return true;
    }

    //for test code hadi 4ir kantester la base de donnnes 5edama mezian hmd li lah

    public void insertCours(Cour cour){
        Map<String, Object> user = new HashMap<>();
        user.put("first", "Alan");
        user.put("middle", "Mathison");
        user.put("last", "Turing");
        user.put("born", 1912);

        db.collection("cours").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d(TAG ,"insert succes");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG ,"insert failed");
            }
        });
    }
}
