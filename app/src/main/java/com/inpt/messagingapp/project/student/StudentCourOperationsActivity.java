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
import com.inpt.messagingapp.loadingDialog;
import com.inpt.messagingapp.project.shared.ChatActivity;
import com.inpt.messagingapp.project.shared.CoursActivity;
import com.inpt.messagingapp.wrapper.controllers.student.StudentCoursController;
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
    CardView carddow;
    loadingDialog loading_dialog;
    String id_cour;
    Cour public_cour ;
    Intent i ;
    GlobalApplication app;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_cour_operations);
        cardchat = findViewById(R.id.cardchat);
        carddow = findViewById(R.id.cardDev);
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
        carddow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"downloading the file",Toast.LENGTH_SHORT).show();
                app.setFilesController();
                app.getFilesController().downloadFile(getApplicationContext(),"ayoub",".pdf",public_cour.getFile());
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
        loading_dialog = new loadingDialog(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("QUESTION ");
        builder.setMessage("TU VEUX SUPPRIMER LE COUR ?");
        builder.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                app.getStudentCoursController().quiterCour(id_cour, new StudentCoursController.OnAfterRegisterInCour() {
                    @Override
                    public void OnCallBack() {
                        app.getLocaldatabase().deleteCourL(id_cour);
                        loading_dialog.dismissdialog();
                        Toast.makeText(getApplicationContext(),"cour ",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), CoursActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        startActivity(intent);

                    }

                    @Override
                    public void OnErreur() {
                       loading_dialog.dismissdialog();
                        erreurDialog();
                    }
                });
loading_dialog.startLoadingDialog("suppression du cour....");
            }
        });
        builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();


    }
    public void initialisingTheCour(String cour_id){
        loading_dialog = new loadingDialog(this);
        app.setTeacherCoursController();

        app.teacherCoursController.getCour(cour_id, new TeacherCoursController.OnGetCourFinished() {
            @Override
            public void OnCallBack(Cour cour) {
                public_cour = cour ;
                title.setText(cour.getTitre());
            loading_dialog.dismissdialog();
            }

            @Override
            public void OnErreur() {
                loading_dialog.dismissdialog();
                erreurDialog();
            }
        });
        loading_dialog.startLoadingDialog("recherche du cours ...");


    }
    void erreurDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Erreur");
        builder.setMessage(" problème de la connextion !  :");
        builder.setPositiveButton("d'accord", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.create().show();
    }

}