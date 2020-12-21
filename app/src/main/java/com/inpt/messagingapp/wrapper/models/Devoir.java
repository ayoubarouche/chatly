package com.inpt.messagingapp.wrapper.models;

import java.util.List;

public class Devoir {
     private String idDevoir;
    private String date;  // la date du devoir c"est a dire le dernier delais
             private String file;             // le lien du fichier dans google storage du google firebase
private List<String> reponses; // les reponses des etudiant
    public String getIdDevoir() {
        return idDevoir;
    }

    public void setIdDevoir(String idDevoir) {
        this.idDevoir = idDevoir;
    }

    private String titre;            // le titre du devoir

    public Devoir() {
    }

    public List<String> getReponses() {
        return reponses;
    }

    public void setReponses(List<String> reponses) {
        this.reponses = reponses;
    }

    public Devoir(String idDevoir, String date, String file, String titre, List<String> reponses) {
        this.date = date;
        this.file = file;
        this.titre = titre;
        this.idDevoir = idDevoir;
        this.reponses = reponses ;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
}
