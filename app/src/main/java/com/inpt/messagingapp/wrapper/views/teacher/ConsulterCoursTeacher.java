package com.inpt.messagingapp.wrapper.views.teacher;

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
import java.util.ArrayList;
import java.util.List;

public class ConsulterCoursTeacher extends AppCompatActivity {
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

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ajouter.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {

         Log.d(TAG,"you clicked add cours");
     }
 });
        getCourses();
    }

    public void getCourses(){
        app = (GlobalApplication)getApplication();
       if(app.getTeacherCoursController()==null)app.setTeacherCoursController();
       app.getTeacherCoursController().getCourses(new TeacherCoursController.OnGettingCourses() {
           @Override
           public void OnCallBack(List<Cour> courses) {
               cours = courses ;
               Toast.makeText(getApplicationContext(),"the courses are returned size is : "+cours.size(),Toast.LENGTH_LONG).show();
               adapter = new CoursViewAdapter(cours, getApplicationContext(),app);
               recyclerView.setAdapter(adapter);
           }

       });
        Toast.makeText(getApplicationContext(),"please wait we are getting the courses",Toast.LENGTH_LONG).show();

    }
}
