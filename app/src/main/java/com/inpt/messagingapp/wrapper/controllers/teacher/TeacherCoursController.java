package com.inpt.messagingapp.wrapper.controllers.teacher;

import com.inpt.messagingapp.wrapper.controllers.CoursController;
import com.inpt.messagingapp.wrapper.models.Cour;

import java.util.List;

public class TeacherCoursController implements CoursController {
    @Override
    public List<Cour> getCourses(String uid) {
        return null;
    }
    public boolean DeleteCour(String coursId){
        return true;
    }
}
