package com.inpt.messagingapp.wrapper.controllers.student;

import com.inpt.messagingapp.wrapper.models.Cour;
import com.inpt.messagingapp.wrapper.models.Devoir;
import com.inpt.messagingapp.wrapper.models.Reponse;
import com.inpt.messagingapp.wrapper.models.User;

import java.util.List;
// la gestion des reponses et les devoir des etudiants
public class DevoirReponseController {
    private User student;
    private Cour cour;
    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public Cour getCour() {
        return cour;
    }

    public void setCour(Cour cour) {
        this.cour = cour;
    }

    public DevoirReponseController(User student, Cour cour) {
        this.student = student;
        this.cour = cour;
    }
    public boolean addReponse(Reponse reponse){
        return false ;
    }
    public Reponse getReponse (String idReponse){
        return null;
    }
    public List<Devoir> getDevoirs(){
        return null;
    }
    public Devoir getDevoir(){
        return null ;
    }
    public boolean deleteReponse(Reponse reponse){
        return false;
    }
}
