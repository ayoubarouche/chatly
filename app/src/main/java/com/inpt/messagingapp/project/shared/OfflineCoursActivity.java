package com.inpt.messagingapp.project.shared;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.inpt.messagingapp.GlobalApplication;
import com.inpt.messagingapp.R;
import com.inpt.messagingapp.adapters.CoursViewAdapter;
import com.inpt.messagingapp.loadingDialog;
import com.inpt.messagingapp.wrapper.controllers.teacher.TeacherCoursController;
import com.inpt.messagingapp.wrapper.models.Cour;

import java.util.List;

public class OfflineCoursActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private GlobalApplication app ;
    private loadingDialog loading_dialog;
    List<Cour> cours;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.consulter_cours_offline);
        super.onCreate(savedInstanceState);

        app = (GlobalApplication)getApplication();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getCoursesUser();

    }
    public void getCoursesUser(){
    cours =  app.getLocaldatabase().allCourL();
    if(!cours.isEmpty()){
        adapter = new CoursViewAdapter(cours, getApplicationContext(),app);
        recyclerView.setAdapter(adapter);

    }
            }

    }


