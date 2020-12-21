package com.inpt.messagingapp.wrapper.models;

public class Reponse {
    private String idReponse ;
    private User student ;

    public String getIdReponse() {
        return idReponse;
    }

    public void setIdReponse(String idReponse) {
        this.idReponse = idReponse;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public Reponse(String idReponse, User student) {
        this.idReponse = idReponse;
        this.student = student;
    }
}
