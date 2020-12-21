package com.inpt.messagingapp.wrapper.models;

public class Reponse {
    private String idReponse ;
    private User student ;
    private String file;
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

    public Reponse() {
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Reponse(String idReponse, User student, String file) {
        this.idReponse = idReponse;
        this.student = student;
        this.file = file ;
    }
}
