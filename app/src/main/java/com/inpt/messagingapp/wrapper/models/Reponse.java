package com.inpt.messagingapp.wrapper.models;

public class Reponse {
    private String idReponse ;
    private String student ;
    private String file;
    public String getIdReponse() {
        return idReponse;
    }

    public void setIdReponse(String idReponse) {
        this.idReponse = idReponse;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
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

    public Reponse(String idReponse, String student, String file) {
        this.idReponse = idReponse;
        this.student = student;
        this.file = file ;
    }
}
