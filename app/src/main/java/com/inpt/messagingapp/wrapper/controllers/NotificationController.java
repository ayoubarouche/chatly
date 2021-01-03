package com.inpt.messagingapp.wrapper.controllers;

import android.app.Notification;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.inpt.messagingapp.MainActivity;
import com.inpt.messagingapp.helpers.MySingleton;
import com.inpt.messagingapp.wrapper.models.Message;
import com.google.firebase.messaging.RemoteMessage;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class NotificationController {
    FirebaseMessaging firebaseMessaging;

    public FirebaseMessaging getFirebaseMessaging() {
        return firebaseMessaging;
    }
    public  NotificationController(FirebaseMessaging firebaseMessaging){
        this.firebaseMessaging = firebaseMessaging;
    }
    public void setFirebaseMessaging(FirebaseMessaging firebaseMessaging) {
        this.firebaseMessaging = firebaseMessaging;
    }
    public void registerToTopic(String cours_id, final OnTopicSubscribed onTopicSubscribed){
        firebaseMessaging.subscribeToTopic(cours_id)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful()) {
                        onTopicSubscribed.OnFailed();
                        }
                        onTopicSubscribed.OnCallBack();
                    }
                });
    }
    public interface OnTopicSubscribed{
        public  void OnCallBack();
        public void OnFailed();
    }
    public void sendNotificationToOthers(String from , String cour_name , String to , Message message){
        RemoteMessage remoteMessage = new RemoteMessage.Builder("").addData("title",cour_name)
                .addData("from",from).addData("text",message.getMessage()).build();

    }
    public void sendNotificationToOthers(Context context , String from_name ,String cour_name,String to ,  Message message,OnTopicSubscribed onTopicSubscribed){
        final  String FCM_API = "https://fcm.googleapis.com/fcm/send";
        final  String serverKey = "key=" + "AAAAwxvaADM:APA91bGoOp9f_yngCeRgecIEhqPTcA6qRmeJKF7IBSF5zl1w7VLJ9H_dnk1sG6orZiC5_WKsJjqHKNPtXPg3ChAWkAQM9f9XtABfuK0XQIRV-9tBAAui4CcNN7CV_NgVHhubGs7oUdNZ";
        final  String contentType = "application/json";
        final String TAG = "NOTIFICATION TAG";

        String NOTIFICATION_TITLE;
        String NOTIFICATION_MESSAGE;
        String TOPIC;
        TOPIC = "/topics/"+to; //topic must match with what the receiver subscribed to
        NOTIFICATION_TITLE = cour_name+" - "+from_name ;
        NOTIFICATION_MESSAGE = message.getMessage();

        JSONObject notification = new JSONObject();
        JSONObject notifcationBody = new JSONObject();
        try {
            notifcationBody.put("title", NOTIFICATION_TITLE);
            notifcationBody.put("message", NOTIFICATION_MESSAGE);

            notification.put("to", TOPIC);
            notification.put("data", notifcationBody);
        } catch (JSONException e) {
            Log.e(TAG, "onCreate: " + e.getMessage() );
        }
        sendNotification(context , serverKey , FCM_API , contentType , notification,onTopicSubscribed);
    }

    private void sendNotification(Context context, final String serverKey , final String FCM_API, final String contentType , JSONObject notification, final OnTopicSubscribed onTopicSubscribed) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(FCM_API, notification,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("message sent", "onResponse: " + response.toString());
                        onTopicSubscribed.OnCallBack();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    onTopicSubscribed.OnFailed();
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", serverKey);
                params.put("Content-Type", contentType);
                return params;
            }
        };
        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }


}
