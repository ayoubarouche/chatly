package com.inpt.messagingapp.wrapper.controllers;

import android.database.Cursor;

import com.inpt.messagingapp.wrapper.models.Cour;
import com.inpt.messagingapp.wrapper.models.Devoir;
import com.inpt.messagingapp.wrapper.models.Reponse;
import com.inpt.messagingapp.wrapper.models.User;

public interface ControllerLocale {
    public void addLocale(Cour cour);
    public void addLocale(User user);
    public void addLocale(Devoir devoir);
    public void addLocale(Reponse reponse);
    public Cursor allCourL();
    public Cursor allDevoirL();
    public Cursor allUserL();
    public Cursor allReponse();
    public void deleteCourL(String idCour);
    public void deleteDevoirL(String idDevoir);
    public void deleteUserL(String idUser);
    public void deleteReponseL(String idReponse);
    public void updateCourL(Cour cour);
    public void updateDevoirL(Devoir devoir);
    public void updateUserL(User user);
    public void updateReponseL(Reponse reponse);
}
