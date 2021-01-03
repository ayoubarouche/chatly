package com.inpt.messagingapp.project.shared;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.inpt.messagingapp.GlobalApplication;
import com.inpt.messagingapp.R;
import com.inpt.messagingapp.adapters.ChatViewAdapter;
import com.inpt.messagingapp.loadingDialog;
import com.inpt.messagingapp.wrapper.controllers.MessageController;
import com.inpt.messagingapp.wrapper.controllers.NotificationController;
import com.inpt.messagingapp.wrapper.models.Cour;
import com.inpt.messagingapp.wrapper.models.Message;

import java.util.Date;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private GlobalApplication app ;
    List<Message> mymessages ;
    ImageView sendfile ;
    EditText message_text ;
    Message message1 ; // for helping sending the message
    String idCour ; // for getting the id of the cour
    String nameCour ; // the name of the cour
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Intent intent = getIntent();
        idCour = intent.getStringExtra("id_cour");
        nameCour = intent.getStringExtra("name_cour");
        recyclerView = (RecyclerView) findViewById(R.id.chatrecyclerview);
        sendfile = findViewById(R.id.sendmsg);
        message_text = findViewById(R.id.add_msg);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        app = (GlobalApplication)getApplication();
        Cour cour = new Cour();
        cour.setIdCour(idCour);
        app.setMessageController(cour);

        getMessages();
        Toast.makeText(this,"the cour id  is : "+app.getMessageController().getCour().getIdCour(),Toast.LENGTH_LONG).show();
        sendfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMessage(message_text.getText().toString());
                message_text.setText("");
                getMessages();
            }
        });
    }
    public void getMessages(){
        Cour cour = new Cour();
        final loadingDialog loading_dialog = new loadingDialog(this);
        app.getMessageController().getCourMessages(new MessageController.OnGettedMessages() {
            @Override
            public void OnCallBack(List<Message> messages) {
                mymessages = messages;
                Toast.makeText(getApplicationContext(),"the messages is returned",Toast.LENGTH_LONG).show();
                updateMessages(mymessages);
                loading_dialog.dismissdialog();
            }
        });
        loading_dialog.startLoadingDialog("loading messages ....");
    }

    public void updateMessages(List<Message> messages){

        adapter = new ChatViewAdapter(messages,getApplicationContext(),app);
        recyclerView.setAdapter(adapter);
    }
    public void addMessage(String message_text){
        app.setNotificationController();
        message1 = new Message();
        Date date = new Date();
        message1.setDate(new Date());
        message1.setMessage(message_text);
        message1.setFrom(app.getUser().getIdUser());
        app.getMessageController().addMesssage(message1, new MessageController.OnMessageSent() {
            @Override
            public void OnCallBack(Message message) {
                message1 = message ;
            app.getNotificationController().sendNotificationToOthers(getApplicationContext(), app.getUser().getName(), nameCour, idCour, message, new NotificationController.OnTopicSubscribed() {
                @Override
                public void OnCallBack() {
                    Toast.makeText(getApplicationContext(),"notification sent to others "+ nameCour,Toast.LENGTH_SHORT).show();

                }

                @Override
                public void OnFailed() {
                    Toast.makeText(getApplicationContext(),"notification not sent to others !!!",Toast.LENGTH_SHORT).show();

                }
            });
            }
        });
    }
}
