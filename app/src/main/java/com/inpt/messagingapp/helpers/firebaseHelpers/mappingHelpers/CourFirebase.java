package com.inpt.messagingapp.helpers.firebaseHelpers.mappingHelpers;

import com.inpt.messagingapp.wrapper.models.Cour;
import com.inpt.messagingapp.wrapper.models.Devoir;
import com.inpt.messagingapp.wrapper.models.Message;
import com.inpt.messagingapp.wrapper.models.User;

import java.util.ArrayList;
import java.util.List;

public class CourFirebase {
    private String idCour ; // hna id dyal cours
    private String teacher ; // the teacher of the cour
    private List<String> students ; // list des etudiant du cours// list des devoir du cours
    private String description = "aucun description sur le cours" ; // pour la description sur le cours
    private String titre ="aucun titre pour le cours" ; // pour le titre du cour
    private String file ; // la place du fichier dans google storage dans firebase

    public CourFirebase() {
    }

    public String getIdCour() {
        return idCour;
    }

    public void setIdCour(String idCour) {
        this.idCour = idCour;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public List<String> getStudents() {
        return students;
    }

    public void setStudents(List<String> students) {
        this.students = students;
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

    public CourFirebase(Cour cour){
        this.idCour  = cour.getIdCour() ;
        this.teacher = cour.getTeacher();
        this.description = cour.getDescription();
        this.titre = cour.getTitre();
        this.file = cour.getFile();
        students = new ArrayList<>();
       if(cour.getStudents() != null) for(User student : cour.getStudents()){
            this.students.add(student.getIdUser());
        }

    }

    public Cour OriginalCours(){
        Cour cour = new Cour();
        cour.setIdCour(this.getIdCour());
        cour.setDescription(this.getDescription());
        cour.setFile(this.getFile());
        cour.setTeacher(this.getTeacher());
       List<User> sts = new ArrayList<>();
if(this.students != null)       for(String student : this.students){
           User stud = new User();
           stud.setIdUser(student);
           sts.add(stud);
       }
       cour.setStudents(sts);
       cour.setTitre(this.getTitre());
       cour.setDevoirs(null);
        return cour ;
    }

    @Override
    public String toString() {
        return "CourFirebase{" +
                "idCour='" + idCour + '\'' +
                ", teacher='" + teacher + '\'' +
                ", students=" + students +
                ", description='" + description + '\'' +
                ", titre='" + titre + '\'' +
                ", file='" + file + '\'' +
                '}';
    }
}
