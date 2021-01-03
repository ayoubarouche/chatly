package com.inpt.messagingapp.wrapper.controllers.teacher;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.inpt.messagingapp.wrapper.models.Cour;
import com.inpt.messagingapp.wrapper.models.Devoir;
import com.inpt.messagingapp.wrapper.models.Reponse;
import com.inpt.messagingapp.wrapper.models.User;

import java.util.ArrayList;
import java.util.List;

public class DevoirController {
    private Devoir tempdevoir;
    private Reponse tempReponse;
    private boolean tempTester ;
    private Cour cour;
    public User teacher ;
    private static FirebaseFirestore firebaseFirestore;
    private DocumentReference documentReference;
    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public Cour getCour() {
        return cour;
    }

    public void setCour(Cour cour) {
        this.cour = cour;
    }

    public DevoirController(FirebaseFirestore firebase_database ,Cour cour) {

        this.cour = cour;
        firebaseFirestore = firebase_database;
        documentReference = firebaseFirestore.collection("cours").document(cour.getIdCour());
    }
    public Devoir addDevoir(Devoir devoir, final OnFinishingDevoirOperation onFinishingDevoirOperation){
        tempdevoir = devoir;
        final DocumentReference documentReference1= documentReference.collection("devoirs").document();
        tempdevoir.setIdDevoir(documentReference1.getId());
                documentReference1.set(tempdevoir).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("hello world", "onSuccess: the is from docuement rference is :"+documentReference1.getId());
                        onFinishingDevoirOperation.OnCallBack();
                    }
                });
        return  tempdevoir;
    }
    public List<Devoir> getDevoirs(){
        return null;
    }
    public Devoir getDevoir(String idDevoir){
        tempdevoir = new Devoir();
        documentReference.collection("devoirs").document(idDevoir).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
              tempdevoir =   documentSnapshot.toObject(Devoir.class);
              tempdevoir.setIdDevoir(documentSnapshot.getId());
            }
        });
        return tempdevoir;
    }
    public List<Reponse> getReponses(Devoir devoir){
        documentReference.collection("devoirs")
                         .document(devoir.getIdDevoir())
                         .get()
                         .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                tempdevoir = documentSnapshot.toObject(Devoir.class);
            }
        });
        List<Reponse> reponses = new ArrayList<>();
        for(String rep : tempdevoir.getReponses()){
            reponses.add(getReponse(rep));
        }
        return reponses;
    }
    public boolean deleteDevoir(Devoir devoir){
        documentReference.collection("devoirs")
                         .document(devoir.getIdDevoir())
                         .delete()
                         .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                tempTester = true;
                Log.d("devoircontroller", "onSuccess: devoir a ete supprimer avec succes");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("devoircontroller", "onFailure: devoir n'a pas etre supprimer");
            }
        });
        return tempTester;
    }
    public Reponse getReponse(String idReponse){
        DocumentReference documentReference1 = firebaseFirestore.collection("reponses").document(idReponse);
                documentReference1.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        tempReponse = documentSnapshot.toObject(Reponse.class);
                    }
                });
        return tempReponse;
    }
    public interface OnFinishingDevoirOperation{
        public void OnCallBack();
    }
}
