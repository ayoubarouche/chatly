package com.inpt.messagingapp.project.shared;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.inpt.messagingapp.loadingDialog;
import com.inpt.messagingapp.project.teacher.AjouterCourActivity;
import com.inpt.messagingapp.GlobalApplication;
import com.inpt.messagingapp.R;
import com.inpt.messagingapp.adapters.CoursViewAdapter;
import com.inpt.messagingapp.project.student.AjouterCourStudent;
import com.inpt.messagingapp.wrapper.controllers.teacher.TeacherCoursController;
import com.inpt.messagingapp.wrapper.models.Cour;
import com.inpt.messagingapp.wrapper.models.UserType;

import java.util.List;

public class CoursActivity extends AppCompatActivity {
    private static final String TAG = "consulterCoursTeacher";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private GlobalApplication app ;
    private List<Cour> cours;
    private loadingDialog loading_dialog ;
    private Toolbar toolbar;
    private ImageView signout ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.consulter_cours_teacher);
        Button ajouter = findViewById(R.id.ajouter_button);
        signout = findViewById(R.id.signout);

        app = (GlobalApplication)getApplication();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"you clicked add cours");
                changeToAddCourPage();
            }
        });
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exitDialog();
            }
        });
        if(app.getUser().getType() == UserType.teacher)getCoursesTeacher();
        else getCoursesStudent();

    }
    public void changeToAddCourPage(){
        Intent intent ;
        if(app.getUser().getType() == UserType.student) {
            intent = new Intent(this, AjouterCourStudent.class);

        }
        else{
            intent = new Intent(this, AjouterCourActivity.class);

        }
        startActivity(intent);
    }

    public void getCoursesTeacher(){
        loading_dialog = new loadingDialog(this);
        if(app.getTeacherCoursController()==null)app.setTeacherCoursController();
        app.getTeacherCoursController().getCourses(new TeacherCoursController.OnGettingCourses() {
            @Override
            public void OnCallBack(List<Cour> courses) {
                cours = courses ;
                adapter = new CoursViewAdapter(cours, getApplicationContext(),app);
                recyclerView.setAdapter(adapter);
                loading_dialog.dismissdialog();

            }

            @Override
            public void OnErreur() {
                loading_dialog.dismissdialog();
                confirmDialog();
            }
        });
        loading_dialog.startLoadingDialog("chargement de votre cours ....");

    }


    public void getCoursesStudent(){
        loading_dialog = new loadingDialog(this);
        if(app.getStudentCoursController()==null)app.setStudentCoursController();
        app.getStudentCoursController().getCourses(new TeacherCoursController.OnGettingCourses() {
            @Override
            public void OnCallBack(List<Cour> courses) {
                cours = courses ;
                adapter = new CoursViewAdapter(cours, getApplicationContext(),app);
                recyclerView.setAdapter(adapter);
                loading_dialog.dismissdialog();

            }

            @Override
            public void OnErreur() {
                loading_dialog.dismissdialog();
                confirmDialog();
            }

        });
        loading_dialog.startLoadingDialog("rechargement de votre cours ....");
    }
    void confirmDialog(){
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
    void exitDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("QUESTION " );
        builder.setMessage("VOUS VOULEZ VRAIMENT SE DÉCONNECTER ?");
        builder.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                app.authentification.signOut();
                app.setUser(null);
                Intent intent = new Intent(CoursActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();


    }
}
