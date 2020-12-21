package com.inpt.messagingapp.wrapper.controllers;

import com.inpt.messagingapp.wrapper.models.Cour;
import com.inpt.messagingapp.wrapper.models.Message;
import com.inpt.messagingapp.wrapper.models.User;

import java.util.List;
// ce class ou bien se controller il va se charger d'ajouter et manipuler les donnes des messages

public class MessageController {
   private Cour cour ;// le cour que on veux avoir ces messages
    private User sendfrom ;
    public Cour getCour() {
        return cour;
    }

    public void setCour(Cour cour) {
        this.cour = cour;
    }

    public MessageController(User sendfrom,Cour cour) {
        this.sendfrom = sendfrom ;this.cour = cour;
    }
    public List<Message> getCourMessages(){
        return null ;
    }
    public boolean addMesssage( Message message){
        return false;
    }
    public Message getMessage(){
        return null;
    }

}
