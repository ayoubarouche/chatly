package com.inpt.messagingapp.wrapper.models;

import java.util.Date;

public class Message {
   private Date date;
   private String message;
    public Message(Date date, String message) {
        this.date = date;
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
