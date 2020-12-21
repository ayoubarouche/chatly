package com.inpt.messagingapp.wrapper.controllers.teacher;

import com.inpt.messagingapp.wrapper.models.Cour;
import com.inpt.messagingapp.wrapper.models.Devoir;
import com.inpt.messagingapp.wrapper.models.Reponse;
import com.inpt.messagingapp.wrapper.models.User;

import java.util.List;

public class DevoirController {
    private Cour cour;
    public User teacher ;

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

    public DevoirController(User teacher,Cour cour) {
        this.teacher = teacher ;
        this.cour = cour;
    }
    public Devoir addDevoir(Devoir devoir){
        return null;
    }
    public List<Devoir> getDevoirs(){
        return null;
    }
    public Devoir getDevoir(String idDevoir){
        return null;
    }
    public List<Reponse> getReponses(){
        return null;
    }
    public boolean deleteDevoir(Devoir devoir){
        return false;
    }
}
