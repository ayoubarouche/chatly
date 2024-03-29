package com.inpt.messagingapp.wrapper.models;

import java.util.List;

public class Cour  {
    private String idCour ; // hna id dyal cours
   private String teacher ; // the teacher of the cour
    private List<User> students ; // list des etudiant du cours
    public List<Devoir> devoirs ; // list des devoir du cours
    public List<Message> messages ; // list des messages du chat sur le cours
    private String description = "aucun description sur le cours" ; // pour la description sur le cours
   private String titre ="aucun titre pour le cours" ; // pour le titre du cour
    private String file ; // la place du fichier dans google storage dans firebase
    public Cour(String idCour,String titre, String desc,String teacher){
        this.titre = titre;
        this.description = desc;
        this.teacher = teacher;
        this.idCour = idCour;
    }

    public Cour() {
    }

    public Cour(String idCour, String teacher, List<User> students, List<Devoir> devoirs, List<Message> messages, String description, String titre, String file) {
        this.teacher = teacher;
        this.students = students;
        this.devoirs = devoirs;
        this.messages = messages;
        this.description = description;
        this.titre = titre;
        this.file = file ;
        this.idCour = idCour;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public List<User> getStudents() {
        return students;
    }

    public void setStudents(List<User> students) {
        this.students = students;
    }



    public String getIdCour() {
        return idCour;
    }

    public void setIdCour(String idCour) {
        this.idCour = idCour;
    }


    public List<Devoir> getDevoirs() {
        return devoirs;
    }

    public void setDevoirs(List<Devoir> devoirs) {
        this.devoirs = devoirs;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
