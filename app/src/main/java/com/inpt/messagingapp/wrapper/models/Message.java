package com.inpt.messagingapp.wrapper.models;

import java.util.Date;

public class Message {
    private String from ; // the user who send the message
   private  String message ; // the message sent by the user
    private String idMessage;

    public String getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(String idMessage) {
        this.idMessage = idMessage;
    }

    public Message() {
    }

    private String date ; // when the user sent the message

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
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

    public Message(String from, String message, String date) {
        this.from = from;
        this.message = message;
        this.date = date;
    }
}
