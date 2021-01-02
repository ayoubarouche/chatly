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
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.inpt.messagingapp.helpers.firebaseHelpers.FireBaseConnector;
import com.inpt.messagingapp.helpers.firebaseHelpers.mappingHelpers.CourFirebase;
import com.inpt.messagingapp.wrapper.controllers.CoursController;
import com.inpt.messagingapp.wrapper.models.Cour;
import com.inpt.messagingapp.wrapper.models.Devoir;
import com.inpt.messagingapp.wrapper.models.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeacherCoursController implements CoursController {
    User teacher;
    List<Cour> courses ; // helper for courses
    CourFirebase tempcour ;

   static private FirebaseFirestore db;
    static String TAG  = "hello world";
    public TeacherCoursController(FirebaseFirestore firebase_database,User teacher){
        db= firebase_database;
        this.teacher = teacher ;
    }
    @Override
    public List<Cour> getCourses(final OnGettingCourses onGettingCourses) {
          courses = new ArrayList<>();
        db.collection("cours").whereEqualTo("teacher",teacher.getIdUser())
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

                            }
                            onGettingCourses.OnCallBack(courses1);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
        return courses;
    }
    public boolean supprimerCour(String coursId){
        return true;
    }
    public Cour getCour(String courseId) {

        tempcour = new CourFirebase();
        db.collection("cours").document(courseId).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                tempcour = (CourFirebase) documentSnapshot.toObject(CourFirebase.class);

            }
        });
        return tempcour.OriginalCours();
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

    public Cour addCour(final Cour cour, final OnCourAdded onCourAdded){
        final CourFirebase courFirebase = new CourFirebase(cour);
       final DocumentReference documentReference =  db.collection("cours").document();
                    courFirebase.setIdCour(documentReference.getId());
    documentReference.set(courFirebase).addOnSuccessListener(new OnSuccessListener<Void>() {
           @Override
           public void onSuccess(Void aVoid) {
                onCourAdded.onCallBack(courFirebase.OriginalCours());
               Log.d(TAG , "ajouter avec succes ");

           }
       }).addOnFailureListener(new OnFailureListener() {
           @Override
           public void onFailure(@NonNull Exception e) {
               Log.e(TAG,"failed to add the document");
           }
       });
        Log.d(TAG, "addCour: the id is : "+documentReference.getId());
  //      courFirebase.setIdCour(documentReference.getId() );
    return  courFirebase.OriginalCours();
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
    public interface OnGettingCourses{
       public void OnCallBack(List<Cour> courses);
    }
    public interface OnCourAdded{
        public void onCallBack(Cour cour);
    }
}
