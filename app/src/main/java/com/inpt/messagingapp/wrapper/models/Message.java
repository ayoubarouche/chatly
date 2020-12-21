package com.inpt.messagingapp.wrapper.models;

import java.util.Date;

public class Message {
    private User from ; // the user who send the message
   private  String message ; // the message sent by the user
   private String date ; // when the user sent the message

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Message(User from, String message, String date) {
        this.from = from;
        this.message = message;
        this.date = date;
    }
}