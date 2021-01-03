package com.inpt.messagingapp.project.student;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.inpt.messagingapp.GlobalApplication;
import com.inpt.messagingapp.R;
import com.inpt.messagingapp.helpers.sqliteHelpers.SqliteConnector;
import com.inpt.messagingapp.project.shared.ChatActivity;
import com.inpt.messagingapp.project.shared.CoursActivity;
import com.inpt.messagingapp.project.shared.DevoirsActivity;
import com.inpt.messagingapp.wrapper.controllers.teacher.TeacherCoursController;
import com.inpt.messagingapp.wrapper.models.Cour;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class StudentCourOperationsActivity extends AppCompatActivity {
    TextView title;
    CardView cardchat;
    CardView cardshare;
    CardView carddelete;
    String id_cour;
    Cour public_cour ;
    Intent i ;
    GlobalApplication app;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_cour_operations);
        cardchat = findViewById(R.id.cardchat);
        Intent intent = getIntent();
        id_cour = intent.getStringExtra("id_cour");
        app = (GlobalApplication)getApplication();
        initialisingTheCour(id_cour);
        cardshare = findViewById(R.id.cardshare);
        carddelete = findViewById(R.id.carddelete);
        title = findViewById(R.id.textview);
        cardshare.setVisibility(View.GONE);
        i = new Intent(getApplicationContext(), ChatActivity.class);

        cardchat.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                i.putExtra("name_cour",public_cour.getTitre());
                i.putExtra("id_cour",id_cour);
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
                Toast.makeText(getApplicationContext(),"cour is deleted",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), CoursActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();


    }
    public void initialisingTheCour(String cour_id){

        app.setTeacherCoursController();

        app.teacherCoursController.getCour(cour_id, new TeacherCoursController.OnGetCourFinished() {
            @Override
            public void OnCallBack(Cour cour) {
                public_cour = cour ;
                Toast.makeText(getApplicationContext(), "cours infor received successifly..." ,Toast.LENGTH_SHORT).show();
                title.setText(cour.getTitre());
            }
        });
        Toast.makeText(getApplicationContext(), "getting the cours informations..." ,Toast.LENGTH_SHORT).show();



    }
    }