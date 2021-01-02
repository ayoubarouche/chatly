package com.inpt.messagingapp.project.shared;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.inpt.messagingapp.GlobalApplication;
import com.inpt.messagingapp.R;
import com.inpt.messagingapp.adapters.CoursViewAdapter;
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
            }
        });
        if(app.getUser().getType() == UserType.teacher)getCoursesTeacher();
        else getCoursesStudent();
    }

    public void getCoursesTeacher(){
        if(app.getTeacherCoursController()==null)app.setTeacherCoursController();
        app.getTeacherCoursController().getCourses(new TeacherCoursController.OnGettingCourses() {
            @Override
            public void OnCallBack(List<Cour> courses) {
                cours = courses ;
                Toast.makeText(getApplicationContext(),"the teacher courses are returned size is : "+cours.size(),Toast.LENGTH_LONG).show();
                adapter = new CoursViewAdapter(cours, getApplicationContext(),app);
                recyclerView.setAdapter(adapter);
            }
        });
        Toast.makeText(getApplicationContext(),"please wait we are getting the teacher courses",Toast.LENGTH_LONG).show();

    }

    public void getCoursesStudent(){
        if(app.getStudentCoursController()==null)app.setStudentCoursController();
        app.getStudentCoursController().getCourses(new TeacherCoursController.OnGettingCourses() {
            @Override
            public void OnCallBack(List<Cour> courses) {
                cours = courses ;
                Toast.makeText(getApplicationContext(),"the student courses are returned size is : "+cours.size(),Toast.LENGTH_LONG).show();
                adapter = new CoursViewAdapter(cours, getApplicationContext(),app);
                recyclerView.setAdapter(adapter);
            }

        });
        Toast.makeText(getApplicationContext(),"please wait we are getting the student courses",Toast.LENGTH_LONG).show();

    }

}
