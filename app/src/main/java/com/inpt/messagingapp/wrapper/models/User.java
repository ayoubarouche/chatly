package com.inpt.messagingapp.wrapper.models;

public class User {
        private String email ; // email of the user
       private UserType type ; // the type of the user
       private String idUser ;// the id of the user
    private String name , prenom , username;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User(String email, UserType type, String idUser, String name, String prenom, String username) {
        this.email = email;
        this.type = type;
        this.idUser = idUser;
        this.name = name;
        this.prenom = prenom;
        this.username = username;
    }
}
