package com.inpt.messagingapp;

import android.app.Application;
import android.content.Intent;
import android.os.Build;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FirebaseStorage;
import com.inpt.messagingapp.helpers.sqliteHelpers.SqliteConnector;
import com.inpt.messagingapp.wrapper.controllers.FilesController;
import com.inpt.messagingapp.wrapper.controllers.MessageController;
import com.inpt.messagingapp.wrapper.controllers.NotificationController;
import com.inpt.messagingapp.wrapper.controllers.UserController;
import com.inpt.messagingapp.wrapper.controllers.student.StudentCoursController;
import com.inpt.messagingapp.wrapper.controllers.teacher.TeacherCoursController;
import com.inpt.messagingapp.wrapper.models.Cour;
import com.inpt.messagingapp.wrapper.models.User;

public class GlobalApplication extends Application {
    public UserController userController;
    public MessageController messageController;

    public FilesController filesController;
    public FirebaseStorage storage;
    public FirebaseMessaging firebaseMessaging;
    public Cour working_cour;
    public User user;

    public FirebaseFirestore firebase_database;
    public FirebaseAuth authentification;
    public TeacherCoursController teacherCoursController;
    public StudentCoursController studentCoursController;

    public NotificationController notificationController;
    public SqliteConnector localdatabase;

    public TeacherCoursController getTeacherCoursController() {
        return teacherCoursController;
    }

    public void setTeacherCoursController() {
        this.teacherCoursController = new TeacherCoursController(firebase_database, user);
    }

    public NotificationController getNotificationController() {
        return notificationController;
    }

    public void setNotificationController() {
        this.notificationController = new NotificationController(firebaseMessaging);
    }

    public StudentCoursController getStudentCoursController() {
        return studentCoursController;
    }

    public void setStudentCoursController() {
        this.studentCoursController = new StudentCoursController(firebase_database, user);
    }


    public SqliteConnector getLocaldatabase() {
        return localdatabase;
    }

    public void setLocaldatabase(SqliteConnector localdatabase) {
        this.localdatabase = localdatabase;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        firebase_database = FirebaseFirestore.getInstance();
        authentification = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        firebaseMessaging = FirebaseMessaging.getInstance();
        localdatabase = new SqliteConnector(this);
        userController = new UserController(firebase_database, authentification);
        Intent intent = new Intent(this, GlobalFireBaseMessagingService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            this.getApplicationContext().startForegroundService(intent);
        } else {
            this.getApplicationContext().startService(intent);
        }
    }


    public MessageController getMessageController() {
        return messageController;
    }

    public void setMessageController(Cour cour) {
        this.messageController = new MessageController(firebase_database, cour);
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public FilesController getFilesController() {
        return filesController;
    }

    public void setFilesController() {
        this.filesController = new FilesController(storage);
    }
}
