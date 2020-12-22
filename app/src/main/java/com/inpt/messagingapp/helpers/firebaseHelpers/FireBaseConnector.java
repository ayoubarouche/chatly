package com.inpt.messagingapp.helpers.firebaseHelpers;

import com.inpt.messagingapp.wrapper.models.Devoir;

import java.util.List;

public class FireBaseConnector {
        List<Devoir> devoirs ;
         String id = "helloworld";

    public List<Devoir> getDevoirs() {
        return devoirs;
    }

    public void setDevoirs(List<Devoir> devoirs) {
        this.devoirs = devoirs;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public FireBaseConnector() {
    }
}
