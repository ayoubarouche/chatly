package com.inpt.messagingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.inpt.messagingapp.adapters.CoursViewAdapter;
import com.inpt.messagingapp.wrapper.shared.Cour;

import java.util.List;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<Cour> cours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView. setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cours = new ArrayList<>();
        for(int i = 0; i <=10; i++){
            Cour listItem = new Cour(
                    "heading" +(i+1),
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
            );
            cours.add(listItem);
        }

        adapter = new CoursViewAdapter(cours, this);
        recyclerView.setAdapter(adapter);
    }

}
