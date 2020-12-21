package com.inpt.messagingapp.wrapper.controllers.student;

import com.inpt.messagingapp.wrapper.controllers.CoursController;
import com.inpt.messagingapp.wrapper.models.Cour;
import com.inpt.messagingapp.wrapper.models.Devoir;
import com.inpt.messagingapp.wrapper.models.User;

import java.util.List;
// la gestion des cours des etudiants
public class StudentCoursController implements CoursController {
    private User student;
    @Override
    public List<Cour> getCourses() {
        return null;
    }
    public Cour getCour(String coursId){
        return null;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public StudentCoursController(User student) {
        this.student = student;
    }

}
