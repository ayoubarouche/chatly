package com.inpt.messagingapp.wrapper.controllers.teacher;


import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.inpt.messagingapp.wrapper.controllers.CoursController;
import com.inpt.messagingapp.wrapper.models.Cour;
import com.inpt.messagingapp.wrapper.models.Devoir;
import com.inpt.messagingapp.wrapper.models.User;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeacherCoursController implements CoursController {
    User teacher;
    Cour tempcour ;
   static private FirebaseFirestore db;
    static String TAG  = "hello world";
    public TeacherCoursController(){
        db= FirebaseFirestore.getInstance();

    }
    @Override
    public List<Cour> getCourses() {
        return null;
    }
    public boolean supprimerCour(String coursId){
        return true;
    }
    public Cour getCour(String courseId) {
        tempcour = new Cour();
        db.collection("cours").document(courseId).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                tempcour = documentSnapshot.toObject(Cour.class);
            }
        });
        return tempcour;
    }
    //for test code hadi 4ir kantester la base de donnnes 5edama mezian hmd li lah

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public TeacherCoursController(User teacher) {
        this.teacher = teacher;
    }
    public String courId(){
        return null;
    }
    public List<User> getCourStudents(){
        return null;
    }
    public Cour addCour(Cour cour){

       DocumentReference documentReference =  db.collection("cours").document();

    documentReference.set(cour).addOnSuccessListener(new OnSuccessListener<Void>() {
           @Override
           public void onSuccess(Void aVoid) {
               Log.d(TAG , "ajouter avec succes");

           }
       }).addOnFailureListener(new OnFailureListener() {
           @Override
           public void onFailure(@NonNull Exception e) {
               Log.e(TAG,"failed to add the document");
           }
       });
    cour.setIdCour(documentReference.getId() );
    return  cour;
    }
    public void deleteCour(Cour cour){
        db.collection("cours").document(cour.getIdCour()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "onSuccess: delete with succes");

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "onFailure: failed to delete");
            }
        });
    }
}
