package com.inpt.messagingapp.wrapper.controllers.student;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.inpt.messagingapp.helpers.firebaseHelpers.mappingHelpers.CourFirebase;
import com.inpt.messagingapp.wrapper.controllers.CoursController;
import com.inpt.messagingapp.wrapper.controllers.teacher.TeacherCoursController;
import com.inpt.messagingapp.wrapper.models.Cour;
import com.inpt.messagingapp.wrapper.models.Devoir;
import com.inpt.messagingapp.wrapper.models.User;

import java.util.ArrayList;
import java.util.List;
// la gestion des cours des etudiants
public class StudentCoursController implements CoursController {
    private User student;
    private List<Cour> courses ;
    FirebaseFirestore db;
    @Override
    public List<Cour> getCourses(final TeacherCoursController.OnGettingCourses onGettingCourses) {
        courses = new ArrayList<>();
        db.collection("cours")
                .whereArrayContains("students",student.getIdUser())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                    List<Cour> courses1 = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                CourFirebase firebasecour = document.toObject(CourFirebase.class);
                                Cour cour = firebasecour.OriginalCours();
                                cour.setIdCour(document.getId());
                                courses1.add(cour);

                                Log.d("student cour controller", "cour id is : "+ cour.getDescription());
                            }
                            onGettingCourses.OnCallBack(courses1);
                        } else {
                            Log.d("student cour controller", "Error getting documents: ", task.getException());
                        }
                    }
                });
return courses;

    }


    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public StudentCoursController(FirebaseFirestore firebase_database , User student) {
        this.student = student;
        db = FirebaseFirestore.getInstance();
    }
    public void inscritCour(String IdCour, final OnAfterRegisterInCour onAfterRegisterInCour){
       db.collection("cours")
               .document(IdCour)
               .update("students", FieldValue.arrayUnion(student.getIdUser()))
               .addOnSuccessListener(new OnSuccessListener<Void>() {
           @Override
           public void onSuccess(Void aVoid) {
               onAfterRegisterInCour.OnCallBack();
               Log.d("studentcourcontroller", "onSuccess: student added with succes");
           }
       }).addOnFailureListener(new OnFailureListener() {
           @Override
           public void onFailure(@NonNull Exception e) {
               onAfterRegisterInCour.OnErreur();
           }
       });
    }
    public void quiterCour(String IdCour, final OnAfterRegisterInCour onAfterRegisterInCour){
        db.collection("cours")
                .document(IdCour)
                .update("students", FieldValue.arrayRemove(student.getIdUser()))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        onAfterRegisterInCour.OnCallBack();
                        Log.d("studentcourcontroller", "onSuccess: student added with succes");
                    }
                });
    }

    public interface OnAfterRegisterInCour{
        public void OnCallBack();
        public void OnErreur();
    }
}
