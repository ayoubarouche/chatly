package com.inpt.messagingapp.project.student;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.inpt.messagingapp.R;
import com.inpt.messagingapp.helpers.sqliteHelpers.SqliteConnector;
import com.inpt.messagingapp.project.shared.ChatActivity;
import com.inpt.messagingapp.project.shared.DevoirsActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class StudentCourOperationsActivity extends AppCompatActivity {
    TextView title;
    CardView cardchat;
    CardView cardDev;
    CardView cardshare;
    CardView carddelete;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_cour_operations);
        cardchat = findViewById(R.id.cardchat);
        cardDev = findViewById(R.id.cardDev);
        cardshare = findViewById(R.id.cardshare);
        carddelete = findViewById(R.id.carddelete);
        title = findViewById(R.id.textview);
        cardshare.setVisibility(View.GONE);
        cardchat.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(StudentCourOperationsActivity.this, ChatActivity.class);
                startActivity(i);
            }
        });
        cardDev.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(StudentCourOperationsActivity.this, DevoirsActivity.class);
                startActivity(i);
            }
        });

        carddelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + title.getText().toString().trim() + " ?");
        builder.setMessage("Are you sure you want to delete " + title + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SqliteConnector myDB = new SqliteConnector(StudentCourOperationsActivity.this);
                myDB.deleteCourL(null);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
