package com.inpt.messagingapp.wrapper.controllers;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.inpt.messagingapp.wrapper.models.Cour;
import com.inpt.messagingapp.wrapper.models.Message;
import com.inpt.messagingapp.wrapper.models.User;

import java.util.ArrayList;
import java.util.List;
// ce class ou bien se controller il va se charger d'ajouter et manipuler les donnes des messages

public class MessageController {
   private Cour cour ;// le cour que on veux avoir ces messages
    private FirebaseFirestore db;
    private DocumentReference documentReference;
    public Cour getCour() {
        return cour;
    }
    private  List<Message> tempmessages ;
    private Message tempmessage;
    public void setCour(Cour cour) {
        this.cour = cour;
    }

    public MessageController(FirebaseFirestore firebase_database, Cour cour) {
        db = firebase_database;
        documentReference = db.collection("cours").document(cour.getIdCour());
        this.cour = cour;
    }
    public List<Message> getCourMessages(final OnGettedMessages onGettedMessages){
        tempmessages = new ArrayList<>();

         documentReference.collection("messages").orderBy("date").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                List<Message> messageList = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Message message = document.toObject(Message.class);
                        messageList.add(message);
                        Log.d("messages controller", document.getId() + " => " + document.getData());
                    }
                onGettedMessages.OnCallBack(messageList);
                }
            }
        });
        return tempmessages;
    }
    public Message addMesssage(Message message , final OnMessageSent onMessageSent){
        tempmessage = message;
        DocumentReference documentReference1 = documentReference.collection("messages").document();
        tempmessage.setIdMessage(documentReference1.getId());
        documentReference1.set(tempmessage).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("message controller", "onSuccess: the message has been added succes");
                onMessageSent.OnCallBack(tempmessage);
            }
        });
        return tempmessage;
    }
    public interface OnGettedMessages{
        public void OnCallBack(List<Message> messages);
    }
    public interface OnMessageSent{
        public void OnCallBack(Message message);
    }
}
