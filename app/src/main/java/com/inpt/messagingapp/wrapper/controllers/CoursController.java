package com.inpt.messagingapp.wrapper.controllers;

import com.inpt.messagingapp.wrapper.models.Cour;

import java.util.List;

public interface CoursController {
    //uid c'est le user id
    public List<Cour> getCourses(String uid);

}
