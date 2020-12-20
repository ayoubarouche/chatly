package com.inpt.messagingapp.wrapper.models;

public class Cour  {

    private String head,desc;
    public Cour(String head, String desc){
        this.desc = desc;
        this.head = head;

    }
    public void setHead(String head) {
        this.head = head;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getHead(){
        return head;
    }
    public String getDesc(){
        return desc;
    }
}
