package com.inpt.messagingapp.project.shared;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
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
    private Toolbar toolbar;
    private ImageView imageView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app.authentification = FirebaseAuth.getInstance();
        setContentView(R.layout.consulter_cours_teacher);
        Button ajouter = findViewById(R.id.ajouter_button);
        imageView = findViewById(R.id.imageView4);
        registerForContextMenu(imageView);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.logout){
                Intent intent = new Intent(CoursActivity.this, LoginActivity.class);
                app.authentification.signOut();
                startActivity(intent);
                return true;
        }
        return super.onContextItemSelected(item);
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
