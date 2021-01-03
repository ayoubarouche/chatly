package com.inpt.messagingapp.project.teacher;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.inpt.messagingapp.GlobalApplication;
import com.inpt.messagingapp.R;
import com.inpt.messagingapp.helpers.sqliteHelpers.SqliteConnector;
import com.inpt.messagingapp.loadingDialog;
import com.inpt.messagingapp.project.shared.ChatActivity;
import com.inpt.messagingapp.project.shared.CoursActivity;
import com.inpt.messagingapp.wrapper.controllers.teacher.TeacherCoursController;
import com.inpt.messagingapp.wrapper.models.Cour;

public class TeacherCourOperationsActivity extends AppCompatActivity {
    TextView title;
    CardView cardchat;
    CardView cardshare;
    CardView carddelete;
    CardView carddow;
    GlobalApplication app ;
    String id_cour ;
    Intent i;
    Cour public_cour ;
    loadingDialog loading_dialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        id_cour = intent.getStringExtra("id_cour");
        setContentView(R.layout.activity_teacher_cour_operations);
        loading_dialog = new loadingDialog(this);

        app = (GlobalApplication)getApplication();
        initialisingTheCour(id_cour);
            cardchat = findViewById(R.id.cardchat);
        cardshare = findViewById(R.id.cardshare);
        carddelete = findViewById(R.id.carddelete);
        carddow =findViewById(R.id.cardDev);
        title = findViewById(R.id.textview);
        cardshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setClipboard(getApplicationContext(), id_cour);
                }
        });
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
                app.setFilesController();
                app.getFilesController().downloadFile(getApplicationContext(),id_cour,".pdf",public_cour.getFile());
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
        builder.setTitle("question ?");
        builder.setMessage(" tu veux vraiment supprimer le cour ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                supprimerCour();
        }});
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
    // for copying the cour code
    private void setClipboard(Context context, String text) {
        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(text);
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", text);
            clipboard.setPrimaryClip(clip);
        }

        Toast.makeText(getApplicationContext() , "text copied" ,Toast.LENGTH_SHORT).show();
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "hi these is the id of the course to use : "+text);
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);


    }
    public void initialisingTheCour(String cour_id){

        app.setTeacherCoursController();

         app.teacherCoursController.getCour(cour_id, new TeacherCoursController.OnGetCourFinished() {
           @Override
           public void OnCallBack(Cour cour) {
               loading_dialog.dismissdialog();
               public_cour = cour ;
                title.setText(cour.getTitre());
           }

             @Override
             public void OnErreur() {
               loading_dialog.dismissdialog();
               erreurDialog();
             }
         });
loading_dialog.startLoadingDialog("chargement du cour....");

    }
    public void supprimerCour(){
       final SqliteConnector myDB = new SqliteConnector(TeacherCourOperationsActivity.this);
        //   myDB.deleteCourL(null);
        Cour cour = new Cour();
        cour.setIdCour(id_cour);
        app.getTeacherCoursController().deleteCour(cour, new TeacherCoursController.OnCourDeleted() {
            @Override
            public void OnCallBack() {

                myDB.deleteCourL(id_cour);

                loading_dialog.dismissdialog();

                Toast.makeText(getApplicationContext(),"cour is deleted",Toast.LENGTH_SHORT).show();
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
        loading_dialog.startLoadingDialog("suppresion du cour ...");
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
