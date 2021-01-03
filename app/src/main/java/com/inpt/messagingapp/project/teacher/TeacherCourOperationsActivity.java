package com.inpt.messagingapp.project.teacher;

import android.content.ClipData;
import android.content.ClipboardManager;
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
import com.inpt.messagingapp.project.shared.ChatActivity;
import com.inpt.messagingapp.project.shared.CoursActivity;
import com.inpt.messagingapp.project.shared.DevoirsActivity;
import com.inpt.messagingapp.wrapper.controllers.teacher.TeacherCoursController;
import com.inpt.messagingapp.wrapper.models.Cour;
import com.inpt.messagingapp.wrapper.models.UserType;

public class TeacherCourOperationsActivity extends AppCompatActivity {
    TextView title;
    CardView cardchat;
    CardView cardshare;
    CardView carddelete;
    GlobalApplication app ;
    String id_cour ;
    Intent i;
    Cour public_cour ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        id_cour = intent.getStringExtra("id_cour");
        setContentView(R.layout.activity_teacher_cour_operations);
        app = (GlobalApplication)getApplication();
        initialisingTheCour(id_cour);
            cardchat = findViewById(R.id.cardchat);
        cardshare = findViewById(R.id.cardshare);
        carddelete = findViewById(R.id.carddelete);
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
                SqliteConnector myDB = new SqliteConnector(TeacherCourOperationsActivity.this);
             //   myDB.deleteCourL(null);
                Cour cour = new Cour();
                cour.setIdCour(id_cour);
               app.getTeacherCoursController().deleteCour(cour, new TeacherCoursController.OnCourDeleted() {
                   @Override
                   public void OnCallBack() {
                       Toast.makeText(getApplicationContext(),"cour is deleted",Toast.LENGTH_SHORT).show();
                       Intent intent = new Intent(getApplicationContext(), CoursActivity.class);
                       intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                       startActivity(intent);

                   }
               });
                Toast.makeText(getApplicationContext(),"deleting the cour",Toast.LENGTH_SHORT).show();

            }
        });
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
