package com.inpt.messagingapp.wrapper.shared;

public class Cour  {
    String head,desc;
    public Cour(String head, String desc){
        this.desc = desc;
        this.head = head;

    }
    public String getHead(){
        return head;
    }
    public String getDesc(){
        return desc;
    }
}
