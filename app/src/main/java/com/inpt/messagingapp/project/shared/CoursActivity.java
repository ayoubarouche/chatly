package com.inpt.messagingapp.project.shared;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.inpt.messagingapp.loadingDialog;
import com.inpt.messagingapp.project.teacher.AjouterCourActivity;
import com.inpt.messagingapp.GlobalApplication;
import com.inpt.messagingapp.MainActivity;
import com.inpt.messagingapp.R;
import com.inpt.messagingapp.adapters.CoursViewAdapter;
import com.inpt.messagingapp.project.student.AjouterCourStudent;
import com.inpt.messagingapp.wrapper.controllers.teacher.TeacherCoursController;
import com.inpt.messagingapp.wrapper.models.Cour;
import com.inpt.messagingapp.wrapper.models.UserType;

import java.util.List;

public class CoursActivity extends AppCompatActivity {
    private static final String TAG = "ConsulterCoursTeacher";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private GlobalApplication app ;
    private List<Cour> cours;
    private loadingDialog loading_dialog ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consulter_cours_teacher);
        Button ajouter = findViewById(R.id.ajouter_button);
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

        });
        loading_dialog.startLoadingDialog("rechargement de votre cours ....");
    }

}
