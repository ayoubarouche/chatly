package com.inpt.messagingapp.wrapper.models;

public class User {
  private  String id;

    public User(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    private String nom;
    private String prenom;
   private UserType type;
}
